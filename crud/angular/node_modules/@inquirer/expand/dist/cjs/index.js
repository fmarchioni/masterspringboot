"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const core_1 = require("@inquirer/core");
const yoctocolors_cjs_1 = __importDefault(require("yoctocolors-cjs"));
const helpChoice = {
    key: 'h',
    name: 'Help, list all options',
    value: undefined,
};
function getChoiceKey(choice, key) {
    if (key === 'name') {
        if ('name' in choice)
            return choice.name;
        return choice.value;
    }
    if ('value' in choice)
        return choice.value;
    return choice.name;
}
exports.default = (0, core_1.createPrompt)((config, done) => {
    const { choices, default: defaultKey = 'h', expanded: defaultExpandState = false, } = config;
    const [status, setStatus] = (0, core_1.useState)('pending');
    const [value, setValue] = (0, core_1.useState)('');
    const [expanded, setExpanded] = (0, core_1.useState)(defaultExpandState);
    const [errorMsg, setError] = (0, core_1.useState)();
    const theme = (0, core_1.makeTheme)(config.theme);
    const prefix = (0, core_1.usePrefix)({ theme });
    (0, core_1.useKeypress)((event, rl) => {
        if ((0, core_1.isEnterKey)(event)) {
            const answer = (value || defaultKey).toLowerCase();
            if (answer === 'h' && !expanded) {
                setExpanded(true);
            }
            else {
                const selectedChoice = choices.find(({ key }) => key === answer);
                if (selectedChoice) {
                    const finalValue = getChoiceKey(selectedChoice, 'value');
                    setValue(finalValue);
                    setStatus('done');
                    done(finalValue);
                }
                else if (value === '') {
                    setError('Please input a value');
                }
                else {
                    setError(`"${yoctocolors_cjs_1.default.red(value)}" isn't an available option`);
                }
            }
        }
        else {
            setValue(rl.line);
            setError(undefined);
        }
    });
    const message = theme.style.message(config.message);
    if (status === 'done') {
        // TODO: `value` should be the display name instead of the raw value.
        return `${prefix} ${message} ${theme.style.answer(value)}`;
    }
    const allChoices = expanded ? choices : [...choices, helpChoice];
    // Collapsed display style
    let longChoices = '';
    let shortChoices = allChoices
        .map((choice) => {
        if (choice.key === defaultKey) {
            return choice.key.toUpperCase();
        }
        return choice.key;
    })
        .join('');
    shortChoices = ` ${theme.style.defaultAnswer(shortChoices)}`;
    // Expanded display style
    if (expanded) {
        shortChoices = '';
        longChoices = allChoices
            .map((choice) => {
            const line = `  ${choice.key}) ${getChoiceKey(choice, 'name')}`;
            if (choice.key === value.toLowerCase()) {
                return theme.style.highlight(line);
            }
            return line;
        })
            .join('\n');
    }
    let helpTip = '';
    const currentOption = allChoices.find(({ key }) => key === value.toLowerCase());
    if (currentOption) {
        helpTip = `${yoctocolors_cjs_1.default.cyan('>>')} ${getChoiceKey(currentOption, 'name')}`;
    }
    let error = '';
    if (errorMsg) {
        error = theme.style.error(errorMsg);
    }
    return [
        `${prefix} ${message}${shortChoices} ${value}`,
        [longChoices, helpTip, error].filter(Boolean).join('\n'),
    ];
});
