import makeAllPermutations from "../makeAllPermutations";
import countPathValue from "../countPathValue";
import countAllPathWithSwimmingTailAndReturnMax from "../countAllPathWithSwimmingTailAndReturnMax";

class FunctionToStringUtils {
    private constructor() {
    }

    public static makeAllPermutation(): string {
        return 'const makeAllPermutations = ' + makeAllPermutations.toString() + ';\n ';
    }

    public static countPathValue(): string {
        return 'const countPathValue = ' + countPathValue.toString() + ';\n ';
    }

    public static countAllPathWithSwimmingTailAndReturnMax(): string {
        return 'const countAllPathWithSwimmingTailAndReturnMax = ' + countAllPathWithSwimmingTailAndReturnMax.toString() + ';\n ';
    }
}

export default FunctionToStringUtils;