let [milliseconds, seconds, minutes, hours] = [0, 0, 0, 0];
let category = null;
let description = null;
let timerRef = document.querySelector('.timerDisplay');

var w = null;

document.getElementById('startAndPauseTimer').addEventListener('click', () => {
    if (document.getElementById('startAndPauseTimer').innerHTML == "Start") {
        if (w == null) {
            w = new Worker("/script/worker.js");
        } else {
            w.postMessage("start");
        }

        w.onmessage = function (event) {
            [milliseconds, seconds, minutes, hours] = [event.data[0], event.data[1], event.data[2], event.data[3]];

            let h = hours < 10 ? "0" + hours : hours;
            let m = minutes < 10 ? "0" + minutes : minutes;
            let s = seconds < 10 ? "0" + seconds : seconds;
            let ms = "";
            if (milliseconds < 10) {
                ms = "00"
            }
            else if (milliseconds < 100) {
                ms = "0" + Math.floor(milliseconds / 10);
            }
            else {
                ms = Math.floor(milliseconds / 10);
            }
        
            timerRef.innerHTML = ` ${h} : ${m} : ${s} : ${ms}`;
        };
        document.getElementById('startAndPauseTimer').innerHTML = "Pause";
    } 
    else {
        if (w != null) {
            w.postMessage("pause");
        }
        document.getElementById('startAndPauseTimer').innerHTML = "Start"
    }
});


document.getElementById('resetTimer').addEventListener('click', () => {
    if (w != null) {
        w.postMessage("reset");
    }
    document.getElementById('startAndPauseTimer').innerHTML = "Start"
});

document.addEventListener('keydown', (event) => {
    let keyName = event.key;
    if (keyName == "m") {
        if (w != null) {
            w.postMessage("m");
        }
    }
    if (keyName == "n") {
        if (w != null) {
            w.postMessage("n");
        }
    }
    if (keyName == "b") {
        if (w != null) {
            w.postMessage("b");
        }
    }

    if (keyName == " ") {
        if (w != null) {
            if (document.getElementById('startAndPauseTimer').innerHTML == "Start") {
                w.postMessage("start");
                document.getElementById('startAndPauseTimer').innerHTML == "Start"
            } else {
                w.postMessage("pause");
                document.getElementById('startAndPauseTimer').innerHTML == "Pause"
            }
        }
    }
});

document.getElementById('formSumbit').addEventListener('click', () => {
    document.getElementById('formHou').value = hours;
    document.getElementById('formMin').value = minutes;
    document.getElementById('formSec').value = seconds;
    document.getElementById('formMil').value = milliseconds;
    document.getElementById('formCat').value = document.getElementById('cat').value;
    document.getElementById('formDes').value = document.getElementById('desc').value;
    console.log(document.getElementById('formCat').value);

    
});


