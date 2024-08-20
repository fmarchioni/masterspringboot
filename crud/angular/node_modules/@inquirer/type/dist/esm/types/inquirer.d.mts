import * as readline from 'node:readline';
import MuteStream from 'mute-stream';
export declare class CancelablePromise<T> extends Promise<T> {
    cancel: () => void;
}
export type InquirerReadline = readline.ReadLine & {
    output: MuteStream;
    input: NodeJS.ReadableStream;
    clearLine: (dir: 0 | 1 | -1) => void;
};
export type Context = {
    input?: NodeJS.ReadableStream;
    output?: NodeJS.WritableStream;
    clearPromptOnDone?: boolean;
};
export type Prompt<Value, Config> = (config: Config, context?: Context) => CancelablePromise<Value>;
