function permNxt(ar, lf) {
    let rt = ar.length - 1, i = rt - 1;
    while (i >= lf && ar[i] >= ar[i + 1]) i--;
    if (i < lf) {
        return false;
    }

    let j = rt;
    while (ar[i] >= ar[j]) {
        j--;
    }
    swap(ar, i, j);
    lf = i + 1;
    while (lf < rt) {
        swap(ar, lf++, rt--);
    }


    return true;
}

function swap(ar, i, j) {
    let a = ar[i];
    ar[i] = ar[j];
    ar[j] = a;
}

const makeAllPermutations = (initialSet: Array<String>, withCommas: boolean = true): Array<string> => {
    let result: Array<string> = [];
    do {
        result.push(initialSet.toString());
    }
    while (permNxt(initialSet, 0));

    if (withCommas) {
        return result;
    }
    return result.map((item: string) =>
         item.replace(/,/g, '')
    );
};

export default makeAllPermutations;