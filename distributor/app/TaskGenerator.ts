import Task from "./entity/Task";
import InitialData from "./types/InitialData";
import makeAllPermutations from "./distribution/makeAllPermutations";

function* TaskGenerator(matrix: number[][], headSize: number = 2): Generator {
    let initialData: InitialData = {
        matrix: matrix,
        fixedHead: '',
        vertexTail: []
    };

    const len = matrix.length;
    const vertexes: number[] = [];
    const head: number[] = [];

    for (let i = 0; i < len; i++) {
        vertexes.push(i);
        if ( i < headSize) {
            head.push(i);
        }
    }


    const permutations = makeAllPermutations(head);

    for (let perm of permutations) {
        initialData.fixedHead = perm;
        const usedVertexes = perm.split(',');
        initialData.vertexTail = vertexes.filter((item: number) => !usedVertexes.includes(String(item)));

        yield new Task(initialData);
    }
}

export default TaskGenerator;