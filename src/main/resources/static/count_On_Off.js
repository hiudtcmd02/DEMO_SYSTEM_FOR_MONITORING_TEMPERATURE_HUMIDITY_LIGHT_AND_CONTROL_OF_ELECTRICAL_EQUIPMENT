$(document).ready(function() {
    $.ajax({
        url: "/lights",
        method: "GET",
        success: function(response) {
            var onCount = response.onCount;
            var offCount = response.offCount;

            // Truyền dữ liệu vào thẻ div có id là "count-light"
            $("#count-light").html("<span>Số lần bật: " + onCount + "</span><span> Số lần tắt: " + offCount + "</span>");
        }
    });

    // Tương tự cho API endpoint của quạt
    $.ajax({
        url: "/fans",
        method: "GET",
        success: function(response) {
            var onCount = response.onCount;
            var offCount = response.offCount;

            // Truyền dữ liệu vào thẻ div có id là "count-fan"
            $("#count-fan").html("<span>Số lần bật: " + onCount + "</span><span> Số lần tắt: " + offCount + "</span>");
        }
    });
});