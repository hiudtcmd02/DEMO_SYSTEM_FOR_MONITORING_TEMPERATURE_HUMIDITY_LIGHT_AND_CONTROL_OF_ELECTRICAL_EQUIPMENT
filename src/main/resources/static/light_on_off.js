function saveLightState(state) {
    localStorage.setItem('lightState', state);
}

function applyLightState() {
    var lightState = localStorage.getItem('lightState');
    if (lightState === 'ON') {
        // Bật đèn
        document.getElementById('lightBulb').src='/light-bulb-on.png';
        lightOn.classList.add('highlighted');
        lightOff.classList.remove('highlighted');
    } else {
        // Tắt đèn
        document.getElementById('lightBulb').src='/light-bulb-off.png';
        lightOff.classList.add('highlighted');
        lightOn.classList.remove('highlighted');
    }
}
$(document).ready(function() {
    $('#lightOn').click(function() {
        $.post('/turnOnLight', function(response) {
            console.log(response); // Log thông điệp trả về từ server (có thể bỏ đi nếu không cần)
        });
        document.getElementById('lightBulb').src='/light-bulb-on.png';
        lightOn.classList.add('highlighted');
        lightOff.classList.remove('highlighted');
        saveLightState("ON");
    });

    $('#lightOff').click(function() {
        $.post('/turnOffLight', function(response) {
            console.log(response);
        });
        document.getElementById('lightBulb').src='/light-bulb-off.png';
        lightOff.classList.add('highlighted');
        lightOn.classList.remove('highlighted');
        saveLightState("OFF");
    });
});