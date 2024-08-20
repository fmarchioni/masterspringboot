"use strict";
var __createBinding = (this && this.__createBinding) || (Object.create ? (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    var desc = Object.getOwnPropertyDescriptor(m, k);
    if (!desc || ("get" in desc ? !m.__esModule : desc.writable || desc.configurable)) {
      desc = { enumerable: true, get: function() { return m[k]; } };
    }
    Object.defineProperty(o, k2, desc);
}) : (function(o, m, k, k2) {
    if (k2 === undefined) k2 = k;
    o[k2] = m[k];
}));
var __setModuleDefault = (this && this.__setModuleDefault) || (Object.create ? (function(o, v) {
    Object.defineProperty(o, "default", { enumerable: true, value: v });
}) : function(o, v) {
    o["default"] = v;
});
var __importStar = (this && this.__importStar) || function (mod) {
    if (mod && mod.__esModule) return mod;
    var result = {};
    if (mod != null) for (var k in mod) if (k !== "default" && Object.prototype.hasOwnProperty.call(mod, k)) __createBinding(result, mod, k);
    __setModuleDefault(result, mod);
    return result;
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.createPrompt = createPrompt;
const readline = __importStar(require("node:readline"));
const node_async_hooks_1 = require("node:async_hooks");
const type_1 = require("@inquirer/type");
const mute_stream_1 = __importDefault(require("mute-stream"));
const signal_exit_1 = require("signal-exit");
const screen_manager_mjs_1 = __importDefault(require('./screen-manager.js'));
const hook_engine_mjs_1 = require('./hook-engine.js');
const errors_mjs_1 = require('./errors.js');
function createPrompt(view) {
    const prompt = (config, context) => {
        var _a, _b;
        // Default `input` to stdin
        const input = (_a = context === null || context === void 0 ? void 0 : context.input) !== null && _a !== void 0 ? _a : process.stdin;
        // Add mute capabilities to the output
        const output = new mute_stream_1.default();
        output.pipe((_b = context === null || context === void 0 ? void 0 : context.output) !== null && _b !== void 0 ? _b : process.stdout);
        const rl = readline.createInterface({
            terminal: true,
            input,
            output,
        });
        const screen = new screen_manager_mjs_1.default(rl);
        let cancel = () => { };
        const answer = new type_1.CancelablePromise((resolve, reject) => {
            (0, hook_engine_mjs_1.withHooks)(rl, (cycle) => {
                function checkCursorPos() {
                    screen.checkCursorPos();
                }
                const removeExitListener = (0, signal_exit_1.onExit)((code, signal) => {
                    onExit();
                    reject(new errors_mjs_1.ExitPromptError(`User force closed the prompt with ${code} ${signal}`));
                });
                const hooksCleanup = node_async_hooks_1.AsyncResource.bind(() => {
                    try {
                        hook_engine_mjs_1.effectScheduler.clearAll();
                    }
                    catch (error) {
                        reject(error);
                    }
                });
                function onExit() {
                    hooksCleanup();
                    screen.done({ clearContent: Boolean(context === null || context === void 0 ? void 0 : context.clearPromptOnDone) });
                    removeExitListener();
                    rl.input.removeListener('keypress', checkCursorPos);
                    rl.removeListener('close', hooksCleanup);
                    output.end();
                }
                cancel = () => {
                    onExit();
                    reject(new errors_mjs_1.CancelPromptError());
                };
                function done(value) {
                    // Delay execution to let time to the hookCleanup functions to registers.
                    setImmediate(() => {
                        onExit();
                        // Finally we resolve our promise
                        resolve(value);
                    });
                }
                cycle(() => {
                    try {
                        const nextView = view(config, done);
                        const [content, bottomContent] = typeof nextView === 'string' ? [nextView] : nextView;
                        screen.render(content, bottomContent);
                        hook_engine_mjs_1.effectScheduler.run();
                    }
                    catch (error) {
                        onExit();
                        reject(error);
                    }
                });
                // Re-renders only happen when the state change; but the readline cursor could change position
                // and that also requires a re-render (and a manual one because we mute the streams).
                // We set the listener after the initial workLoop to avoid a double render if render triggered
                // by a state change sets the cursor to the right position.
                rl.input.on('keypress', checkCursorPos);
                // The close event triggers immediately when the user press ctrl+c. SignalExit on the other hand
                // triggers after the process is done (which happens after timeouts are done triggering.)
                // We triggers the hooks cleanup phase on rl `close` so active timeouts can be cleared.
                rl.on('close', hooksCleanup);
            });
        });
        answer.cancel = cancel;
        return answer;
    };
    return prompt;
}
