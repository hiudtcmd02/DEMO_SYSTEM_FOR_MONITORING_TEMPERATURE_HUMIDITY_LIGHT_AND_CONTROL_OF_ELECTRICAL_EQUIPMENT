function saveFanState(stated) {
    localStorage.setItem('fanState', stated);
}

function applyFanState() {
    var fanState = localStorage.getItem('fanState');
    if (fanState === 'ON') {
        $('#fan').addClass('rotate-image');
        onButton.classList.add('highlighted');
        offButton.classList.remove('highlighted');
    } else {
        $('#fan').removeClass('rotate-image');
        offButton.classList.add('highlighted');
        onButton.classList.remove('highlighted');
    }
}
$(document).ready(function() {
    $('#onButton').click(function() {
        $.post('/turnOnFan', function(response) {
            console.log(response);
        });
        $('#fan').addClass('rotate-image');
        onButton.classList.add('highlighted');
        offButton.classList.remove('highlighted');
        saveFanState("ON");
    });

    $('#offButton').click(function() {
        $.post('/turnOffFan', function(response) {
            console.log(response);
        });
        $('#fan').removeClass('rotate-image');
        offButton.classList.add('highlighted');
        onButton.classList.remove('highlighted');
        saveFanState("OFF");
    });
});