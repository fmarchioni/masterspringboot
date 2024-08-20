import { createPrompt, useState, useKeypress, usePrefix, isEnterKey, Separator, makeTheme, } from '@inquirer/core';
import colors from 'yoctocolors-cjs';
const numberRegex = /\d+/;
function isSelectableChoice(choice) {
    return choice != null && !Separator.isSeparator(choice);
}
export default createPrompt((config, done) => {
    const { choices } = config;
    const [status, setStatus] = useState('pending');
    const [value, setValue] = useState('');
    const [errorMsg, setError] = useState();
    const theme = makeTheme(config.theme);
    const prefix = usePrefix({ theme });
    useKeypress((key, rl) => {
        if (isEnterKey(key)) {
            let selectedChoice;
            if (numberRegex.test(value)) {
                const answer = Number.parseInt(value, 10) - 1;
                selectedChoice = choices.filter(isSelectableChoice)[answer];
            }
            else {
                selectedChoice = choices.find((choice) => isSelectableChoice(choice) && choice.key === value);
            }
            if (isSelectableChoice(selectedChoice)) {
                setValue(selectedChoice.short ?? selectedChoice.name ?? String(selectedChoice.value));
                setStatus('done');
                done(selectedChoice.value);
            }
            else if (value === '') {
                setError('Please input a value');
            }
            else {
                setError(`"${colors.red(value)}" isn't an available option`);
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
        if (Separator.isSeparator(choice)) {
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
export { Separator } from '@inquirer/core';
