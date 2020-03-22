import express from 'express';
import multer  from 'multer';
import cors from 'cors';
import fs from 'fs';
import path from 'path';
import countPathValue from "./app/distribution/countPathValue";
import CountPathResult from "./app/types/CountPathResult";
import countAllPathWithSwimmingTailAndReturnMax from "./app/distribution/countAllPathWithSwimmingTailAndReturnMax";
import Task from "./app/entity/Task";

// const app = express();
// const corsOptions = {
//   origin: '*',
// };
// const upload = multer({ dest: 'upload/' });
//
// app.use(express.urlencoded({ extended: true }))
// app.use(cors(corsOptions));
//
// app.post('/file', upload.single('matrix'), (req, res) => {
//   const filename = req.file.filename;
//   const filePath = path.join(__dirname, filename);
//   fs.readFile(filePath, (err, data) => {
//     let result: string[][] = [];
//     const array = data.toString().split('\r\n');
//     for (let i in array) {
//       result.push(array[i].split(' '));
//     }
//     console.log(result);
//   });
//   res.sendStatus(200);
// });
//
//
// app.listen(3000, () => console.log('Example app listening on port 3000!'));


/*const example = '0,1,3,4';
const matrix = [
    [0,40,Infinity,Infinity,18],
    [40,0,22,6,15],
    [Infinity,22,0,14,Infinity],
    [Infinity,6,14,0,20],
    [18,15,Infinity,20,0],
];*/

const matrix = [
    [0,5,11,9],
    [10,0,8,7],
    [7,14,0,8],
    [12,6,15,0],
];

const initialData = {
    matrix: matrix,
    fixedHead: '0',
    vertexTail: [1,2,3]
};

const task = new Task(initialData);

// console.log(task.getToExecute());
console.log(eval(task.getToExecute()));