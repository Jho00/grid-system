import CountPathResult from "./types/CountPathResult";

class ResultCalculateService {
    private _results: CountPathResult[] = []
    private _isHaveResult: boolean = false;
    private _best: CountPathResult;

    private static _instance: ResultCalculateService|null = null;

    constructor() {
    }

    public static getInstance(): ResultCalculateService {
        if (this._instance == null) {
            this._instance = new ResultCalculateService();
        }

        return this._instance;
    }

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

        this._best = best;
        this._isHaveResult = true;
        return best;
    }

    public isHaveResult(): boolean {
        return this._isHaveResult;
    }

    public getResult(): CountPathResult {
        const result =  this._best;
        this._isHaveResult = false;
        return result;
    }
}

export default ResultCalculateService;