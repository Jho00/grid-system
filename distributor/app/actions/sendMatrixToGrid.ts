import TaskGenerator from "../TaskGenerator";

const sendMatrixToGrid = async (matrix: number[][]) => {
    const gen = TaskGenerator(matrix);

    while (true) {
        const value = gen.next().value;
        if (!value) {
            break;
        }
        console.log(value.getInitialData());
        // TODO: make send to grid
    }
};


export default sendMatrixToGrid;