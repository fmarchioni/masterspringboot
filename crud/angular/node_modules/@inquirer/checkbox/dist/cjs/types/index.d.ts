import { Separator, type Theme } from '@inquirer/core';
import type { PartialDeep } from '@inquirer/type';
type CheckboxTheme = {
    icon: {
        checked: string;
        unchecked: string;
        cursor: string;
    };
    style: {
        disabledChoice: (text: string) => string;
        renderSelectedChoices: <T>(selectedChoices: ReadonlyArray<Choice<T>>, allChoices: ReadonlyArray<Choice<T> | Separator>) => string;
    };
    helpMode: 'always' | 'never' | 'auto';
};
type Choice<Value> = {
    name?: string;
    value: Value;
    short?: string;
    disabled?: boolean | string;
    checked?: boolean;
    type?: never;
};
declare const _default: <Value>(config: {
    message: string;
    prefix?: string | undefined;
    pageSize?: number | undefined;
    instructions?: (string | boolean) | undefined;
    choices: readonly (Separator | Choice<Value>)[];
    loop?: boolean | undefined;
    required?: boolean | undefined;
    validate?: ((choices: readonly Choice<Value>[]) => boolean | string | Promise<string | boolean>) | undefined;
    theme?: PartialDeep<Theme<CheckboxTheme>> | undefined;
}, context?: import("@inquirer/type").Context) => import("@inquirer/type").CancelablePromise<Value[]>;
export default _default;
export { Separator } from '@inquirer/core';
