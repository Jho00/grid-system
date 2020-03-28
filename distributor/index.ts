import express from 'express';
import multer from 'multer';
import cors from 'cors';
import fs from 'fs';
import path from 'path';
import sendMatrixToGrid from "./app/actions/sendMatrixToGrid";

// const app = express();
// const corsOptions = {
//     origin: '*',
// };
// const upload = multer({dest: 'dist/'});
//
// app.use(express.urlencoded({extended: true}))
// app.use(cors(corsOptions));
//
// app.post('/file', upload.single('matrix'), (req, res) => {
//     const filename = req.file.filename;
//     const filePath = path.join(__dirname, filename);
//     fs.readFile(filePath, (err, data) => {
//         let result: string[][] = [];
//         const array = data.toString().split('\n');
//         for (let i in array) {
//             result.push(array[i].split(' '));
//         }
//
//         console.log(result);
//
//         for (let i = 0; i < result.length; i++) {
//             for (let j = 0; j < result.length; j++) {
//                 // @ts-ignore
//                 result[i][j] = Number(result[i][j]);
//             }
//         }
//
//         // @ts-ignore
//         sendMatrixToGrid(result);
//         res.sendStatus(200);
//     });
// });
//
//
// app.listen(3000, () => console.log('Example app listening on port 3000!'));



// const object = {
//     action: 'task',
//     payload: {
//         toExecute: "(() => ({hui: 42}))()",
//         data: '123'
//     }
// };

// import net from 'net';
//
// const client = new net.Socket();
//
// const port = 5050;
// const host = '192.168.1.70';
// function runClient() {
//     console.log('try to connect');
//     client.connect(port, host, () => {
//         console.log('connected');
//         setInterval(() => {
//             // client.write(JSON.stringify(object))
//         }, 1500);
//     });
//     client.on('data', function(data) {
//         console.log('Received: ' + data);
//     });
//
//     client.on('close', function() {
//         console.log('Connection closed');
//     });
// }
//
//
// runClient();
var matrix = [ // для теста
    [0,5,11,9],
    [10,0,8,7],
    [7,14,0,8],
    [12,6,15,0],
];


sendMatrixToGrid(matrix);
