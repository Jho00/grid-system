class Task {
    private toExecute: string;
    private initialData: Object;

    constructor(data: Object) {
        this.initialData = data;
    }
}

const executeTemplate = "(() => {})()";


export default Task;