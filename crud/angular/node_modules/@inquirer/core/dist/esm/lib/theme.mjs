import colors from 'yoctocolors-cjs';
import spinners from 'cli-spinners';
export const defaultTheme = {
    prefix: colors.green('?'),
    spinner: {
        interval: spinners.dots.interval,
        frames: spinners.dots.frames.map((frame) => colors.yellow(frame)),
    },
    style: {
        answer: colors.cyan,
        message: colors.bold,
        error: (text) => colors.red(`> ${text}`),
        defaultAnswer: (text) => colors.dim(`(${text})`),
        help: colors.dim,
        highlight: colors.cyan,
        key: (text) => colors.cyan(colors.bold(`<${text}>`)),
    },
};
