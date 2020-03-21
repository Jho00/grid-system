import makeAllPermutations from './makeAllPermutations';
const generateMakeAllPermutationString = () => 'const makeAllPermutations = ' + makeAllPermutations.toString() + ';\n';
export default generateMakeAllPermutationString;