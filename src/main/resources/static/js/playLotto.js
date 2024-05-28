let selectedNumbers = [];

function selectNumber(element) {
    const number = parseInt(element.innerText);
    if (element.classList.contains('chosen')) {
        element.classList.remove('chosen');
        selectedNumbers = selectedNumbers.filter(n => n !== number);
    } else if (selectedNumbers.length < 6) {
        element.classList.add('chosen');
        selectedNumbers.push(number);
    }
    updateNumberBlock();
}

function updateNumberBlock() {
    const numberBlock = document.getElementById('numberBlock');
    Array.from(numberBlock.children).forEach(child => {
        if (!child.classList.contains('chosen') && selectedNumbers.length >= 6) {
            child.classList.add('disabled');
        } else {
            child.classList.remove('disabled');
        }
    });
}

function clearSelection() {
    selectedNumbers = [];
    const numberBlock = document.getElementById('numberBlock');
    Array.from(numberBlock.children).forEach(child => {
        child.classList.remove('chosen', 'disabled');
    });
}

function submitNumbers() {
    if (selectedNumbers.length < 6) {
        showErrorModal("Please select 6 numbers.");
        return;
    }

    const email = document.getElementById('email').value;

    fetch('/api/ticket/submit', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, numberSet: selectedNumbers })
    })
    .then(response => response.text())
    .then(data => {
        showSuccessModal(data);
        clearSelection();
    })
    .catch(error => {
        console.error('Error:', error);
        showErrorModal('An error occurred. Please try again.');
    });
}

function showSuccessModal(message) {
    document.getElementById('successMessage').innerText = message;
    $('#successModal').modal('show');
}

function showErrorModal(message) {
    document.getElementById('errorMessage').innerText = message;
    $('#errorModal').modal('show');
}

function selectRandomNumbers() {
    clearSelection();
    const numberBlock = document.getElementById('numberBlock');
    let randomNumbers = [];
    while (randomNumbers.length < 6) {
        const randomNumber = Math.floor(Math.random() * 49) + 1;
        if (!randomNumbers.includes(randomNumber)) {
            randomNumbers.push(randomNumber);
            const numberElement = Array.from(numberBlock.children).find(child => parseInt(child.innerText) === randomNumber);
            numberElement.classList.add('chosen');
        }
    }
    selectedNumbers = randomNumbers;
    updateNumberBlock();
}
