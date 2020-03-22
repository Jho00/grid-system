import CountPathResult from "../types/CountPathResult";

/**
 * Эта функция будет превращена в текст и передана грид-слою, тут нам не нужны импорт
 * Функция считает минимальный путь с заданным началом и свободными вершинами
 * Например если у нас 4 вершины, мы передали начало пути как 1,2, то будут посчитаны пути 1,2,3,4 и 1,2,4,3
 * @param matrix
 * @param fixedHead
 * @param tailVertex
 */
const countAllPathWithSwimmingTailAndReturnMax = (matrix: number[][], fixedHead: string, tailVertex: number[]): CountPathResult => {
    let min = Infinity;
    let permutation = '';
    // @ts-ignore
    const permutations = makeAllPermutations(tailVertex);

    for (let perm of permutations) {
        const currentPermutation = `${fixedHead},${perm}`;
        // @ts-ignore
        const result = countPathValue(matrix, currentPermutation);

        if (result < min) {
            min = result;
            permutation = currentPermutation
        }
    }

    return  {
        result: min,
        permutation: permutation
    }
};

export default countAllPathWithSwimmingTailAndReturnMax;