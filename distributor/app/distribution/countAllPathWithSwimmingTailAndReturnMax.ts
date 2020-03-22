import CountPathResult from "../types/CountPathResult";

/**
 * Function will be make string and send to grid-layer. We should not use imports
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