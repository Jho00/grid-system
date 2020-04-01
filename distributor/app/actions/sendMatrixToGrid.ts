import TaskGenerator from "../TaskGenerator";
import net from "net";

const sendMatrixToGrid = async (matrix: number[][]) => {
    const client = new net.Socket();

    const port = 5050;
    const host = '192.168.1.70';
    // const gen = TaskGenerator(matrix);

    console.log('try to connect');
    client.connect(port, host, () => {
        const gen = TaskGenerator(matrix);
            setTimeout(() => {
                const value = gen.next().value;
                if (!value) {
                    console.log('fuck');
                }
                client.write(value.toJson() + "\n")
            }, 3000);
        });


    client.on('data', function(data) {
        console.log('Received: ' + data);
    });

    client.on('close', function() {
        console.log('Connection closed');
    });

};


export default sendMatrixToGrid;