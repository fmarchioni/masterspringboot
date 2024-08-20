"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Separator = void 0;
const core_1 = require("@inquirer/core");
const yoctocolors_cjs_1 = __importDefault(require("yoctocolors-cjs"));
const figures_1 = __importDefault(require("@inquirer/figures"));
const ansi_escapes_1 = __importDefault(require("ansi-escapes"));
const checkboxTheme = {
    icon: {
        checked: yoctocolors_cjs_1.default.green(figures_1.default.circleFilled),
        unchecked: figures_1.default.circle,
        cursor: figures_1.default.pointer,
    },
    style: {
        disabledChoice: (text) => yoctocolors_cjs_1.default.dim(`- ${text}`),
        renderSelectedChoices: (selectedChoices) => selectedChoices
            .map((choice) => { var _a, _b; return (_b = (_a = choice.short) !== null && _a !== void 0 ? _a : choice.name) !== null && _b !== void 0 ? _b : choice.value; })
            .join(', '),
    },
    helpMode: 'auto',
};
function isSelectable(item) {
    return !core_1.Separator.isSeparator(item) && !item.disabled;
}
function isChecked(item) {
    return isSelectable(item) && Boolean(item.checked);
}
function toggle(item) {
    return isSelectable(item) ? Object.assign(Object.assign({}, item), { checked: !item.checked }) : item;
}
function check(checked) {
    return function (item) {
        return isSelectable(item) ? Object.assign(Object.assign({}, item), { checked }) : item;
    };
}
exports.default = (0, core_1.createPrompt)((config, done) => {
    const { instructions, pageSize = 7, loop = true, choices, required, validate = () => true, } = config;
    const theme = (0, core_1.makeTheme)(checkboxTheme, config.theme);
    const prefix = (0, core_1.usePrefix)({ theme });
    const firstRender = (0, core_1.useRef)(true);
    const [status, setStatus] = (0, core_1.useState)('pending');
    const [items, setItems] = (0, core_1.useState)(choices.map((choice) => (Object.assign({}, choice))));
    const bounds = (0, core_1.useMemo)(() => {
        const first = items.findIndex(isSelectable);
        const last = items.findLastIndex(isSelectable);
        if (first < 0) {
            throw new core_1.ValidationError('[checkbox prompt] No selectable choices. All choices are disabled.');
        }
        return { first, last };
    }, [items]);
    const [active, setActive] = (0, core_1.useState)(bounds.first);
    const [showHelpTip, setShowHelpTip] = (0, core_1.useState)(true);
    const [errorMsg, setError] = (0, core_1.useState)();
    (0, core_1.useKeypress)((key) => __awaiter(void 0, void 0, void 0, function* () {
        if ((0, core_1.isEnterKey)(key)) {
            const selection = items.filter(isChecked);
            const isValid = yield validate([...selection]);
            if (required && !items.some(isChecked)) {
                setError('At least one choice must be selected');
            }
            else if (isValid === true) {
                setStatus('done');
                done(selection.map((choice) => choice.value));
            }
            else {
                setError(isValid || 'You must select a valid value');
            }
        }
        else if ((0, core_1.isUpKey)(key) || (0, core_1.isDownKey)(key)) {
            if (loop ||
                ((0, core_1.isUpKey)(key) && active !== bounds.first) ||
                ((0, core_1.isDownKey)(key) && active !== bounds.last)) {
                const offset = (0, core_1.isUpKey)(key) ? -1 : 1;
                let next = active;
                do {
                    next = (next + offset + items.length) % items.length;
                } while (!isSelectable(items[next]));
                setActive(next);
            }
        }
        else if ((0, core_1.isSpaceKey)(key)) {
            setError(undefined);
            setShowHelpTip(false);
            setItems(items.map((choice, i) => (i === active ? toggle(choice) : choice)));
        }
        else if (key.name === 'a') {
            const selectAll = items.some((choice) => isSelectable(choice) && !choice.checked);
            setItems(items.map(check(selectAll)));
        }
        else if (key.name === 'i') {
            setItems(items.map(toggle));
        }
        else if ((0, core_1.isNumberKey)(key)) {
            // Adjust index to start at 1
            const position = Number(key.name) - 1;
            const item = items[position];
            if (item != null && isSelectable(item)) {
                setActive(position);
                setItems(items.map((choice, i) => (i === position ? toggle(choice) : choice)));
            }
        }
    }));
    const message = theme.style.message(config.message);
    const page = (0, core_1.usePagination)({
        items,
        active,
        renderItem({ item, isActive }) {
            if (core_1.Separator.isSeparator(item)) {
                return ` ${item.separator}`;
            }
            const line = String(item.name || item.value);
            if (item.disabled) {
                const disabledLabel = typeof item.disabled === 'string' ? item.disabled : '(disabled)';
                return theme.style.disabledChoice(`${line} ${disabledLabel}`);
            }
            const checkbox = item.checked ? theme.icon.checked : theme.icon.unchecked;
            const color = isActive ? theme.style.highlight : (x) => x;
            const cursor = isActive ? theme.icon.cursor : ' ';
            return color(`${cursor}${checkbox} ${line}`);
        },
        pageSize,
        loop,
    });
    if (status === 'done') {
        const selection = items.filter(isChecked);
        const answer = theme.style.answer(theme.style.renderSelectedChoices(selection, items));
        return `${prefix} ${message} ${answer}`;
    }
    let helpTipTop = '';
    let helpTipBottom = '';
    if (theme.helpMode === 'always' ||
        (theme.helpMode === 'auto' &&
            showHelpTip &&
            (instructions === undefined || instructions))) {
        if (typeof instructions === 'string') {
            helpTipTop = instructions;
        }
        else {
            const keys = [
                `${theme.style.key('space')} to select`,
                `${theme.style.key('a')} to toggle all`,
                `${theme.style.key('i')} to invert selection`,
                `and ${theme.style.key('enter')} to proceed`,
            ];
            helpTipTop = ` (Press ${keys.join(', ')})`;
        }
        if (items.length > pageSize &&
            (theme.helpMode === 'always' ||
                (theme.helpMode === 'auto' && firstRender.current))) {
            helpTipBottom = `\n${theme.style.help('(Use arrow keys to reveal more choices)')}`;
            firstRender.current = false;
        }
    }
    let error = '';
    if (errorMsg) {
        error = `\n${theme.style.error(errorMsg)}`;
    }
    return `${prefix} ${message}${helpTipTop}\n${page}${helpTipBottom}${error}${ansi_escapes_1.default.cursorHide}`;
});
var core_2 = require("@inquirer/core");
Object.defineProperty(exports, "Separator", { enumerable: true, get: function () { return core_2.Separator; } });
