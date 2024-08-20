import { createPrompt, useState, useKeypress, usePrefix, isEnterKey, makeTheme, } from '@inquirer/core';
import colors from 'yoctocolors-cjs';
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
export default createPrompt((config, done) => {
    const { choices, default: defaultKey = 'h', expanded: defaultExpandState = false, } = config;
    const [status, setStatus] = useState('pending');
    const [value, setValue] = useState('');
    const [expanded, setExpanded] = useState(defaultExpandState);
    const [errorMsg, setError] = useState();
    const theme = makeTheme(config.theme);
    const prefix = usePrefix({ theme });
    useKeypress((event, rl) => {
        if (isEnterKey(event)) {
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
                    setError(`"${colors.red(value)}" isn't an available option`);
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
        helpTip = `${colors.cyan('>>')} ${getChoiceKey(currentOption, 'name')}`;
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
