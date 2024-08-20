"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.Separator = void 0;
const core_1 = require("@inquirer/core");
const yoctocolors_cjs_1 = __importDefault(require("yoctocolors-cjs"));
const numberRegex = /\d+/;
function isSelectableChoice(choice) {
    return choice != null && !core_1.Separator.isSeparator(choice);
}
exports.default = (0, core_1.createPrompt)((config, done) => {
    const { choices } = config;
    const [status, setStatus] = (0, core_1.useState)('pending');
    const [value, setValue] = (0, core_1.useState)('');
    const [errorMsg, setError] = (0, core_1.useState)();
    const theme = (0, core_1.makeTheme)(config.theme);
    const prefix = (0, core_1.usePrefix)({ theme });
    (0, core_1.useKeypress)((key, rl) => {
        var _a, _b;
        if ((0, core_1.isEnterKey)(key)) {
            let selectedChoice;
            if (numberRegex.test(value)) {
                const answer = Number.parseInt(value, 10) - 1;
                selectedChoice = choices.filter(isSelectableChoice)[answer];
            }
            else {
                selectedChoice = choices.find((choice) => isSelectableChoice(choice) && choice.key === value);
            }
            if (isSelectableChoice(selectedChoice)) {
                setValue((_b = (_a = selectedChoice.short) !== null && _a !== void 0 ? _a : selectedChoice.name) !== null && _b !== void 0 ? _b : String(selectedChoice.value));
                setStatus('done');
                done(selectedChoice.value);
            }
            else if (value === '') {
                setError('Please input a value');
            }
            else {
                setError(`"${yoctocolors_cjs_1.default.red(value)}" isn't an available option`);
            }
        }
        else {
            setValue(rl.line);
            setError(undefined);
        }
    });
    const message = theme.style.message(config.message);
    if (status === 'done') {
        return `${prefix} ${message} ${theme.style.answer(value)}`;
    }
    let index = 0;
    const choicesStr = choices
        .map((choice) => {
        if (core_1.Separator.isSeparator(choice)) {
            return ` ${choice.separator}`;
        }
        index += 1;
        const line = `  ${choice.key || index}) ${String(choice.name || choice.value)}`;
        if (choice.key === value.toLowerCase() || String(index) === value) {
            return theme.style.highlight(line);
        }
        return line;
    })
        .join('\n');
    let error = '';
    if (errorMsg) {
        error = theme.style.error(errorMsg);
    }
    return [
        `${prefix} ${message} ${value}`,
        [choicesStr, error].filter(Boolean).join('\n'),
    ];
});
var core_2 = require("@inquirer/core");
Object.defineProperty(exports, "Separator", { enumerable: true, get: function () { return core_2.Separator; } });
