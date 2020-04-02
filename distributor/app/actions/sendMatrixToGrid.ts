import TaskGenerator from "../TaskGenerator";
import net from "net";
import config from "./config";
import ResultCalculateService from "../ResultCalculateService";

const sendMatrixToGrid = async (matrix: number[][]) => {
    const client = new net.Socket();

    const port = config.peer_port;
    const host = config.peer_host;

    let tasksSent = 0;
    let tasksReceived = 0;
    let sentAllTasks = false;

    const result = ResultCalculateService.getInstance();

    const calculateResult = setInterval(() => {
        if (sentAllTasks && tasksSent === tasksReceived) {
            const bestResult = result.countBestResult();
            client.destroy();
            clearInterval(calculateResult);

            console.log('All tasks well done. The most short way: ');
            console.log(bestResult);
        }
    }, 2000)

    client.connect(port, host, () => {
        const gen = TaskGenerator(matrix);
        const interval = setInterval(() => {
            const value = gen.next().value;
            if (!value) {
                console.log('All tasks sent');
                sentAllTasks = true;
                clearInterval(interval)
                return;
            }
            client.write(value.toJson() + "\n")
            ++tasksSent;
            console.log(`Task sent: ${tasksSent}`)
        }, 3000);
    });



    client.on('data', function(data: Buffer) {
        ++tasksReceived;
        console.log(`Task received: ${tasksReceived}`);
        result.addResult(data.toString());

    });

    client.on('close', function() {
        console.log('Connection closed');
    });

};


export default sendMatrixToGrid;