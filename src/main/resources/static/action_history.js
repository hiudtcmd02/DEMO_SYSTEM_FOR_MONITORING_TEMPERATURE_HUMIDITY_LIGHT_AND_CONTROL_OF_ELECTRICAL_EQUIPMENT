document.addEventListener('DOMContentLoaded', function() {
    fetch('/getAction')
        .then(function(response) {
            return response.json();
        })
        .then(function(data) {
            // Xử lý dữ liệu và tạo bảng
            var tableBody = document.getElementById('action-body');

            data.forEach(function(action) {
                var row = '<tr>' +
                    '<td>' + action.id + '</td>' +
                    '<td>' + action.deviceName + '</td>' +
                    '<td>' + action.clientName + '</td>' +
                    '<td>' + action.state + '</td>' +
                    '<td>' + convertDateTime(action.dateTime) + '</td>' +
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