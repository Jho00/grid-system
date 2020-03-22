import FunctionToStringUtils from "../distribution/function-to-string/FunctionToStringUtils";
import InitialData from "../types/InitialData";

class Task {
    private toExecute: string;
    private initialData: InitialData;

    constructor(data: InitialData) {
        this.initialData = data;
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
        return this.initialData;
    }


    public toJson(): string {
        return JSON.stringify({
            initialData: this.initialData,
            toExecute: this.toExecute
        })
    }
}

const headTemplate = "(() => {\n";
const tailTemplate = "\n})()";

const taskExecuteTemplate = 'return countAllPathWithSwimmingTailAndReturnMax(initialData.matrix, initialData.fixedHead, initialData.vertexTail)';


export default Task;