function updateTimer(nextDrawDateStr) {
    const nextDrawDate = new Date(nextDrawDateStr);
    const now = new Date();
    const timeDifference = nextDrawDate - now;

    if (timeDifference > 0) {
        const days = Math.floor(timeDifference / (1000 * 60 * 60 * 24));
        const hours = Math.floor((timeDifference % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        const minutes = Math.floor((timeDifference % (1000 * 60 * 60)) / (1000 * 60));
        const seconds = Math.floor((timeDifference % (1000 * 60)) / 1000);

        document.getElementById("timer").innerHTML = `${days}d ${hours}h ${minutes}m ${seconds}s`;
    } else {
        document.getElementById("timer").innerHTML = "The draw has occurred!";
    }
}

function fetchNextDrawDate() {
    fetch('/api/draw/nextDrawDate')
        .then(response => response.text())
        .then(data => {
            const nextDrawDate = new Date(data);
            const formattedDate = nextDrawDate.toLocaleString('de', { day: '2-digit', month: '2-digit', year: 'numeric', hour: '2-digit', minute: '2-digit', hour12: false });
            document.getElementById('nextDrawDate').innerText = formattedDate;
            updateTimer(nextDrawDate.toISOString());
            setInterval(() => updateTimer(nextDrawDate.toISOString()), 1000);
        })
        .catch(error => console.error('Error fetching next draw date:', error));
}

function fetchLastDrawNumbers() {
    fetch('/api/draw/lastDraw')
        .then(response => response.json())
        .then(data => {
            const lastDrawNumbers = data.winningNumbers;
            const lastDrawDate = new Date(data.drawDate).toLocaleString('de', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric',
                hour: '2-digit',
                minute: '2-digit',
                hour12: false
            });

            document.getElementById('lastDrawNumbers').innerHTML = '';
            lastDrawNumbers.forEach(num => {
                const numDiv = document.createElement('div');
                numDiv.textContent = num;
                document.getElementById('lastDrawNumbers').appendChild(numDiv);
            });

            document.querySelector('#lastDrawNumbers + p span').textContent = lastDrawDate;
        })
        .catch(error => console.error('Error fetching last draw numbers:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    fetchNextDrawDate();
    fetchLastDrawNumbers();
});
