"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.usePrefix = usePrefix;
const node_async_hooks_1 = require("node:async_hooks");
const use_state_mjs_1 = require('./use-state.js');
const use_effect_mjs_1 = require('./use-effect.js');
const make_theme_mjs_1 = require('./make-theme.js');
function usePrefix({ isLoading = false, theme, }) {
    const [showLoader, setShowLoader] = (0, use_state_mjs_1.useState)(false);
    const [tick, setTick] = (0, use_state_mjs_1.useState)(0);
    const { prefix, spinner } = (0, make_theme_mjs_1.makeTheme)(theme);
    (0, use_effect_mjs_1.useEffect)(() => {
        if (isLoading) {
            let tickInterval;
            let inc = -1;
            // Delay displaying spinner by 300ms, to avoid flickering
            const delayTimeout = setTimeout(node_async_hooks_1.AsyncResource.bind(() => {
                setShowLoader(true);
                tickInterval = setInterval(node_async_hooks_1.AsyncResource.bind(() => {
                    inc = inc + 1;
                    setTick(inc % spinner.frames.length);
                }), spinner.interval);
            }), 300);
            return () => {
                clearTimeout(delayTimeout);
                clearInterval(tickInterval);
            };
        }
        else {
            setShowLoader(false);
        }
    }, [isLoading]);
    if (showLoader) {
        return spinner.frames[tick];
    }
    return prefix;
}
