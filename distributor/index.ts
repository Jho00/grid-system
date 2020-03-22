import express from 'express';
import multer from 'multer';
import cors from 'cors';
import fs from 'fs';
import path from 'path';
import sendMatrixToGrid from "./app/actions/sendMatrixToGrid";

const app = express();
const corsOptions = {
    origin: '*',
};
const upload = multer({dest: 'dist/'});

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
        sendMatrixToGrid(result);
        res.sendStatus(200);
    });
});


app.listen(3000, () => console.log('Example app listening on port 3000!'));
