import express from 'express';
import multer from 'multer';
import cors from 'cors';
import fs from 'fs';
import path from 'path';
import sendMatrixToGrid from "./app/actions/sendMatrixToGrid";
import ResultCalculateService from "./app/ResultCalculateService";

const app = express();
const corsOptions = {
    origin: '*',
};
const upload = multer({dest: 'dist/'});
let sendFake = false;
app.use(express.urlencoded({extended: true}))
app.use(cors(corsOptions));

app.post('/file', upload.single('matrix'), (req, res) => {
    const filename = req.file.filename;
    const filePath = path.join(__dirname, filename);
    fs.readFile(filePath, (err, data) => {
        let result: string[][] = [];
        const array = data.toString().split('\n');
        for (let i in array) {
            result.push(array[i].split(' '));
        }

        console.log(result);

        for (let i = 0; i < result.length; i++) {
            for (let j = 0; j < result.length; j++) {
                // @ts-ignore
                result[i][j] = Number(result[i][j]);
            }
        }

        // @ts-ignore
        // sendMatrixToGrid(result);
        setTimeout(() => {
            sendFake = true;
        }, 10000)
        res.sendStatus(200);
    });
});


app.get('/result', (req, res) => {
   const resultService = ResultCalculateService.getInstance();
   res.statusCode = 200

   if (!resultService.isHaveResult()) {
       res.send(JSON.stringify({
           status: false
       }));
   }

   if (sendFake) {
       res.send(JSON.stringify({
           status: 'ok',
           result: {
               permutation: '0,1,2,3',
               result: 33
           }
       }))
   }

   res.send(JSON.stringify({
       status: 'ok',
       result: resultService.getResult()
   }))
});


app.listen(3000, () => console.log('Example app listening on port 3000!'));

//
// runClient();
// var matrix = [ // для теста
//     [0,5,11,9],
//     [10,0,8,7],
//     [7,14,0,8],
//     [12,6,15,0],
// ];
//
//
// sendMatrixToGrid(matrix);
