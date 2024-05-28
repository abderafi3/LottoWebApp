function checkTicket() {
    const ticketNumber = document.getElementById('ticketNumber').value.trim();
    if (!ticketNumber) {
        showErrorModal("Please enter a ticket number.");
        return;
    }

    fetch(`/api/ticket/check/${ticketNumber}`, {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        if (data.error) {
            showErrorModal(data.message);
        } else {
            displayResult(data);
            updateMatchCounts();
        }
    })
    .catch(error => {
        console.error('Error:', error);
        showErrorModal('An error occurred. Please try again.');
    });
}

function showErrorModal(message) {
    document.getElementById('errorMessage').innerText = message;
    $('#errorModal').modal('show');
}

function displayResult(data) {
    const resultDiv = document.getElementById('result');
    resultDiv.innerHTML = '';

    const ticketNumbers = data.ticketNumbers;
    const winningNumbers = data.winningNumbers;

    const numberBlock = document.createElement('div');
    numberBlock.classList.add('number-block');

    let matchCount = 0;
    ticketNumbers.forEach(number => {
        const numberDiv = document.createElement('div');
        numberDiv.textContent = number;
        numberDiv.classList.add('number-button');
        if (winningNumbers.includes(number)) {
            numberDiv.classList.add('match');
            matchCount++;
        } else {
            numberDiv.classList.add('nomatch');
        }
        numberBlock.appendChild(numberDiv);
    });

    resultDiv.appendChild(numberBlock);

    const messageDiv = document.createElement('div');
    messageDiv.classList.add('mt-3', 'p-3', 'text-center');
    if (matchCount >= 3) {
        messageDiv.classList.add('alert', 'alert-success');
        messageDiv.textContent = `Congratulations! You have ${matchCount} matching numbers!`;
    } else {
        messageDiv.classList.add('alert', 'alert-warning');
        messageDiv.textContent = 'Better luck next time!';
    }
    resultDiv.appendChild(messageDiv);
}

function updateMatchCounts() {
    fetch('/api/draw/ticketMatchCounts', {
        method: 'GET'
    })
    .then(response => response.json())
    .then(data => {
        const matchCounts = data.matchCounts;
        const prizes = data.prizes;

        document.getElementById('count3').innerText = matchCounts[3] || 0;
        document.getElementById('count4').innerText = matchCounts[4] || 0;
        document.getElementById('count5').innerText = matchCounts[5] || 0;
        document.getElementById('count6').innerText = matchCounts[6] || 0;

        document.getElementById('prize3').innerText = prizes[3] ? `${prizes[3].toFixed(2)}€` : '100€';
        document.getElementById('prize4').innerText = prizes[4] ? `${prizes[4].toFixed(2)}€` : '1 000€';
        document.getElementById('prize5').innerText = prizes[5] ? `${prizes[5].toFixed(2)}€` : '10 000€';
        document.getElementById('prize6').innerText = prizes[6] ? `${prizes[6].toFixed(2)}€` : '1 000 000€';
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('count3').innerText = 0;
        document.getElementById('count4').innerText = 0;
        document.getElementById('count5').innerText = 0;
        document.getElementById('count6').innerText = 0;

        document.getElementById('prize3').innerText = '0€';
        document.getElementById('prize4').innerText = '0€';
        document.getElementById('prize5').innerText = '0€';
        document.getElementById('prize6').innerText = '0€';
    });
}


function fetchLastDrawNumbers() {
    fetch('/api/draw/lastDraw')
        .then(response => response.json())
        .then(data => {
            const lastDrawNumbers = data.winningNumbers;

            document.getElementById('lastDrawNumbers').innerHTML = '';
            lastDrawNumbers.forEach(num => {
                const numDiv = document.createElement('div');
                numDiv.textContent = num;
                document.getElementById('lastDrawNumbers').appendChild(numDiv);
            });

        })
        .catch(error => console.error('Error fetching last draw numbers:', error));
}

document.addEventListener('DOMContentLoaded', () => {
    updateMatchCounts();
    fetchLastDrawNumbers();
});