/**
 * Просто считаем вес нашего пути
 *
 * ВАЖНО: это полный цикл, то есть путь считает возвращение в начальную точку в том числе. Если путь 1,2,3,4, то будет посчитан вес пути 1,2,3,4,1
 * @param matrix
 * @param path
 */
const countPathValue = (matrix:number[][], path: String): number => {
    let result = 0;
    const pathIndexes = path.split(',');

    for (let i = 0; i < pathIndexes.length - 1; i++) {
        const curr = matrix[pathIndexes[i]][pathIndexes[i + 1]];
        if (curr == Infinity) {
            return Infinity;
        }

        result += curr;
    }

    const pathWeightToReturn = matrix[pathIndexes[pathIndexes.length - 1]][pathIndexes[0]];
    if (pathWeightToReturn == Infinity) {
        return Infinity;
    }

    result += pathWeightToReturn;

    return result;
};

export default countPathValue;