const express = require('express');
const uuid = require('uuid');
uuid.v4();
const app = express();

app.get("/randomWaitEndpoint", (req, res) => {
    console.log("id: ", req.query.id);
    console.log('uuid: ', uuid.v4())
    const randomTime = Math.floor(Math.random() * 10000) + 1;
    setTimeout(() => {
        return res.json({jobId: uuid.v4(req.query.id)});
    }, randomTime > 1000 ? randomTime : 1000);
});


app.listen(3000, console.log('port listening on port 3000'));