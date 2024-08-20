"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.defaultTheme = void 0;
const yoctocolors_cjs_1 = __importDefault(require("yoctocolors-cjs"));
const cli_spinners_1 = __importDefault(require("cli-spinners"));
exports.defaultTheme = {
    prefix: yoctocolors_cjs_1.default.green('?'),
    spinner: {
        interval: cli_spinners_1.default.dots.interval,
        frames: cli_spinners_1.default.dots.frames.map((frame) => yoctocolors_cjs_1.default.yellow(frame)),
    },
    style: {
        answer: yoctocolors_cjs_1.default.cyan,
        message: yoctocolors_cjs_1.default.bold,
        error: (text) => yoctocolors_cjs_1.default.red(`> ${text}`),
        defaultAnswer: (text) => yoctocolors_cjs_1.default.dim(`(${text})`),
        help: yoctocolors_cjs_1.default.dim,
        highlight: yoctocolors_cjs_1.default.cyan,
        key: (text) => yoctocolors_cjs_1.default.cyan(yoctocolors_cjs_1.default.bold(`<${text}>`)),
    },
};
