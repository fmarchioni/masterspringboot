"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.ValidationError = exports.HookError = exports.ExitPromptError = exports.CancelPromptError = void 0;
class CancelPromptError extends Error {
    constructor() {
        super(...arguments);
        Object.defineProperty(this, "name", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: 'CancelPromptError'
        });
        Object.defineProperty(this, "message", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: 'Prompt was canceled'
        });
    }
}
exports.CancelPromptError = CancelPromptError;
class ExitPromptError extends Error {
    constructor() {
        super(...arguments);
        Object.defineProperty(this, "name", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: 'ExitPromptError'
        });
    }
}
exports.ExitPromptError = ExitPromptError;
class HookError extends Error {
    constructor() {
        super(...arguments);
        Object.defineProperty(this, "name", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: 'HookError'
        });
    }
}
exports.HookError = HookError;
class ValidationError extends Error {
    constructor() {
        super(...arguments);
        Object.defineProperty(this, "name", {
            enumerable: true,
            configurable: true,
            writable: true,
            value: 'ValidationError'
        });
    }
}
exports.ValidationError = ValidationError;
