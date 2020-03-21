const countPathValue = (matrix:number[][], path: String): number => {
    let result = 0;
    const pathIndexes = path.split(',');

    for (let i = 0; i < pathIndexes.length - 1; i++) {
        const curr = matrix[pathIndexes[i]][pathIndexes[i + 1]];
        if (curr < 1) {
            return  -1;
        }

        result += curr;
    }

    const pathWeightToReturn = matrix[pathIndexes[pathIndexes.length - 1]][pathIndexes[0]];
    if (pathWeightToReturn < 1) {
        return -1;
    }

    result += pathWeightToReturn;

    return result;
};

export default countPathValue;