let [milliseconds, seconds, minutes, hours] = [0, 0, 0, 0];


var n = setInterval(timedCount, 10);

onmessage = function (e) {
    if (e.data == "start") {
        if (n != null) {
            this.clearInterval(n);
        }
        n = this.setInterval(timedCount, 10);
    }

    if (e.data == "pause") {
        this.clearInterval(n)
    }

    if (e.data == "reset") {
        this.clearInterval(n);
        [milliseconds, seconds, minutes, hours] = [0, 0, 0, 0];
        this.postMessage([0, 0, 0, 0]);
    }

    if (e.data == "m") {
        hours += 1;
    }

    if (e.data == "n") {
        minutes += 1;
    }

    if (e.data == "b") {
        seconds += 1;
    }
}

function timedCount() {
    milliseconds += 10;
    if (milliseconds == 1000) {
        milliseconds = 0;
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                hours++;
            }
        }
    }

    postMessage([milliseconds, seconds, minutes, hours]);
}

