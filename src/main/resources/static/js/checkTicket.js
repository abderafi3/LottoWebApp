function checkTicket() {
    const ticketNumber = document.getElementById('ticketNumber').value;
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
        document.getElementById('count3').innerText = data[3] || 0;
        document.getElementById('count4').innerText = data[4] || 0;
        document.getElementById('count5').innerText = data[5] || 0;
        document.getElementById('count6').innerText = data[6] || 0;
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('count3').innerText = 0;
        document.getElementById('count4').innerText = 0;
        document.getElementById('count5').innerText = 0;
        document.getElementById('count6').innerText = 0;
    });
}

document.addEventListener('DOMContentLoaded', () => {
    updateMatchCounts();
});
