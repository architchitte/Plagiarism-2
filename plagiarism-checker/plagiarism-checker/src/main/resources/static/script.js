document.getElementById('plagiarism-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const inputText = document.getElementById('input-text').value;
    const fileInput = document.getElementById('file-input').files[0];
    const formData = new FormData();

    if (inputText) {
        formData.append('text', inputText);
    }

    if (fileInput) {
        formData.append('file', fileInput);
    }

    fetch('/check-plagiarism', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        const resultDiv = document.getElementById('result');
        resultDiv.innerHTML = `<h3>Plagiarism Percentage: ${data.percentage}%</h3>`;
        resultDiv.style.display = 'block';
    })
    .catch(error => {
        console.error('Error:', error);
    });
});
