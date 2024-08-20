import { createPrompt, useState, useKeypress, usePrefix, usePagination, useRef, useMemo, useEffect, isBackspaceKey, isEnterKey, isUpKey, isDownKey, isNumberKey, Separator, ValidationError, makeTheme, } from '@inquirer/core';
import colors from 'yoctocolors-cjs';
import figures from '@inquirer/figures';
import ansiEscapes from 'ansi-escapes';
const selectTheme = {
    icon: { cursor: figures.pointer },
    style: {
        disabled: (text) => colors.dim(`- ${text}`),
        description: (text) => colors.cyan(text),
    },
    helpMode: 'auto',
};
function isSelectable(item) {
    return !Separator.isSeparator(item) && !item.disabled;
}
export default createPrompt((config, done) => {
    const { choices: items, loop = true, pageSize = 7 } = config;
    const firstRender = useRef(true);
    const theme = makeTheme(selectTheme, config.theme);
    const prefix = usePrefix({ theme });
    const [status, setStatus] = useState('pending');
    const searchTimeoutRef = useRef();
    const bounds = useMemo(() => {
        const first = items.findIndex(isSelectable);
        const last = items.findLastIndex(isSelectable);
        if (first < 0) {
            throw new ValidationError('[select prompt] No selectable choices. All choices are disabled.');
        }
        return { first, last };
    }, [items]);
    const defaultItemIndex = useMemo(() => {
        if (!('default' in config))
            return -1;
        return items.findIndex((item) => isSelectable(item) && item.value === config.default);
    }, [config.default, items]);
    const [active, setActive] = useState(defaultItemIndex === -1 ? bounds.first : defaultItemIndex);
    // Safe to assume the cursor position always point to a Choice.
    const selectedChoice = items[active];
    useKeypress((key, rl) => {
        clearTimeout(searchTimeoutRef.current);
        if (isEnterKey(key)) {
            setStatus('done');
            done(selectedChoice.value);
        }
        else if (isUpKey(key) || isDownKey(key)) {
            rl.clearLine(0);
            if (loop ||
                (isUpKey(key) && active !== bounds.first) ||
                (isDownKey(key) && active !== bounds.last)) {
                const offset = isUpKey(key) ? -1 : 1;
                let next = active;
                do {
                    next = (next + offset + items.length) % items.length;
                } while (!isSelectable(items[next]));
                setActive(next);
            }
        }
        else if (isNumberKey(key)) {
            rl.clearLine(0);
            const position = Number(key.name) - 1;
            const item = items[position];
            if (item != null && isSelectable(item)) {
                setActive(position);
            }
        }
        else if (isBackspaceKey(key)) {
            rl.clearLine(0);
        }
        else {
            // Default to search
            const searchTerm = rl.line.toLowerCase();
            const matchIndex = items.findIndex((item) => {
                if (Separator.isSeparator(item) || !isSelectable(item))
                    return false;
                return String(item.name || item.value)
                    .toLowerCase()
                    .startsWith(searchTerm);
            });
            if (matchIndex >= 0) {
                setActive(matchIndex);
            }
            searchTimeoutRef.current = setTimeout(() => {
                rl.clearLine(0);
            }, 700);
        }
    });
    useEffect(() => () => {
        clearTimeout(searchTimeoutRef.current);
    }, []);
    const message = theme.style.message(config.message);
    let helpTipTop = '';
    let helpTipBottom = '';
    if (theme.helpMode === 'always' ||
        (theme.helpMode === 'auto' && firstRender.current)) {
        firstRender.current = false;
        if (items.length > pageSize) {
            helpTipBottom = `\n${theme.style.help('(Use arrow keys to reveal more choices)')}`;
        }
        else {
            helpTipTop = theme.style.help('(Use arrow keys)');
        }
    }
    const page = usePagination({
        items,
        active,
        renderItem({ item, isActive }) {
            if (Separator.isSeparator(item)) {
                return ` ${item.separator}`;
            }
            const line = String(item.name || item.value);
            if (item.disabled) {
                const disabledLabel = typeof item.disabled === 'string' ? item.disabled : '(disabled)';
                return theme.style.disabled(`${line} ${disabledLabel}`);
            }
            const color = isActive ? theme.style.highlight : (x) => x;
            const cursor = isActive ? theme.icon.cursor : ` `;
            return color(`${cursor} ${line}`);
        },
        pageSize,
        loop,
    });
    if (status === 'done') {
        const answer = selectedChoice.short ??
            selectedChoice.name ??
            // TODO: Could we enforce that at the type level? Name should be defined for non-string values.
            String(selectedChoice.value);
        return `${prefix} ${message} ${theme.style.answer(answer)}`;
    }
    const choiceDescription = selectedChoice.description
        ? `\n${theme.style.description(selectedChoice.description)}`
        : ``;
    return `${[prefix, message, helpTipTop].filter(Boolean).join(' ')}\n${page}${helpTipBottom}${choiceDescription}${ansiEscapes.cursorHide}`;
});
export { Separator } from '@inquirer/core';
