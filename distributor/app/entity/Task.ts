import FunctionToStringUtils from "../distribution/function-to-string/FunctionToStringUtils";
import InitialData from "../types/InitialData";

class Task {
    private toExecute: string;
    private data: InitialData;

    constructor(data: InitialData) {
        this.data = data;
        this.toExecute = this.getTaskTemplate();
    }

    private getTaskTemplate(): string {
        let js = headTemplate;
        js += FunctionToStringUtils.makeAllPermutation();
        js += FunctionToStringUtils.countPathValue();
        js += FunctionToStringUtils.countAllPathWithSwimmingTailAndReturnMax();
        js += taskExecuteTemplate;
        js +=tailTemplate;

        return js;
    }

    public getToExecute(): string {
        return this.toExecute;
    }
    public getInitialData(): InitialData {
        return this.data;
    }


    public toJson(): string {
        return JSON.stringify({
            data: JSON.stringify(this.data),
            toExecute: `${this.toExecute}`
        })
    }
}

const headTemplate = '(() => { ';
const tailTemplate = ' })()';

const taskExecuteTemplate = 'return countAllPathWithSwimmingTailAndReturnMax(initialData.matrix, initialData.fixedHead, initialData.vertexTail)';


export default Task;