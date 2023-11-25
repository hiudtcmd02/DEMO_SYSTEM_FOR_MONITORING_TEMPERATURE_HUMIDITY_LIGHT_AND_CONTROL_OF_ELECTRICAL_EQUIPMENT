document.addEventListener('DOMContentLoaded', function() {
    fetch('/getData')
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            var tableBody = document.getElementById('data-body');

            data.forEach(function(sensorData) {
                var row = '<tr>' +
                    '<td>' + sensorData.id + '</td>' +
                    '<td>' + sensorData.temperature + ' °C</td>' +
                    '<td>' + sensorData.humidity + ' %</td>' +
                    '<td>' + sensorData.light + ' lux</td>' +
                    '<td>' + convertDateTime(sensorData.dateTime) + '</td>' +
                    '</tr>';

                tableBody.innerHTML += row;
            });
        })
        .catch(function(error) {
            console.error('Fetch Error: ', error);
        });
});

function convertDateTime(dateTimeString) {
    var date = new Date(dateTimeString);

    var day = padZero(date.getDate());
    var month = padZero(date.getMonth() + 1);
    var year = date.getFullYear();

    var hours = padZero(date.getHours());
    var minutes = padZero(date.getMinutes());
    var seconds = padZero(date.getSeconds());

    return day + '-' + month + '-' + year + ' ' + hours + ':' + minutes + ':' + seconds;
}

function padZero(num) {
    return (num < 10 ? '0' : '') + num;
}