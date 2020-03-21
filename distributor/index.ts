import express from 'express';
import multer  from 'multer';
import cors from 'cors';
import fs from 'fs';
import path from 'path';
import countPathValue from "./app/distribution/countPathValue";

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


const example = '0,1,3,4';
const matrix = [
    [0,40,-1,-1,18],
    [40,0,22,6,15],
    [-1,22,0,14,-1],
    [-1,6,14,0,20],
    [18,15,-1,20,0],
];

console.log(countPathValue(matrix, example));