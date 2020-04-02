import CountPathResult from "./types/CountPathResult";

class ResultCalculateService {
    private _results: CountPathResult[] = []

    public addResult(resultJson: string): void {
        const result = JSON.parse(resultJson);
        if (!result.hasOwnProperty('status') || !result.hasOwnProperty('result')) {
            console.log(`Invalid result data: ${result}`);
        }
        if (result.status != 'ok') {
            console.log('FUCK. Something went wrong on grid layer');
        }
        result.result = JSON.parse(result.result);

        this._results.push(result.result);
    }

    public countBestResult(): CountPathResult {
        let best: CountPathResult = {
            permutation: '',
            result: Infinity
        };
        for (let i = 0; i < this._results.length; i++) {
            const currentResult = this._results[i];
            if (currentResult.result < best.result) {
                best.permutation = currentResult.permutation;
                best.result = currentResult.result;
            }
        }

        return best;
    }
}

export default ResultCalculateService;