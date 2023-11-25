var tempData = document.getElementById("temperature");
var humiData = document.getElementById("humidity");
var liData = document.getElementById("light");

var temperatureData = [];
var humidityData = [];
var lightData = [];

// Hàm để cập nhật dữ liệu mới từ API
function fetchDataFromAPI() {
    fetch('/homeData') // Gửi yêu cầu GET đến '/homeData'
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Chuyển dữ liệu nhận được từ API sang dạng JSON
        })
        .then(data => {
            // Gọi hàm processDataFromAPI để xử lý dữ liệu từ API
            processDataFromAPI(data);
        })
        .catch(error => {
            console.error('There was a problem fetching data:', error);
        });
}

var temperatureChart = Highcharts.chart('do_thi', {
    title: {
        text: 'Biểu đồ nhiệt độ, độ ẩm và ánh sáng'
    },
    yAxis: [
        {
            title: {
                text: 'Nhiệt độ và độ ẩm'
            }
        },
        {
            title: {
                text: 'Ánh sáng'
            },
            opposite: true
        }
    ],
    xAxis: {
        categories: Array.from({ length: temperatureData.length }, (_, i) => i),
    },
    series: [
        {
            name: 'Nhiệt độ',
            data: temperatureData,
            color: 'red'
        },
        {
            name: 'Độ ẩm',
            data: humidityData,
            color: 'blue'
        },
        {
            name: 'Ánh sáng',
            data: lightData,
            color: 'yellow',
            yAxis: 1
        }
    ]
});

// Hàm để xử lý dữ liệu từ API và cập nhật giao diện người dùng
function processDataFromAPI(apiData) {
    // Lấy giá trị từ dữ liệu JSON và gán vào các biến tương ứng
    var newTemp = apiData.temperature;
    var newHumi = apiData.humidity;
    var newLight = apiData.light;

    //Hiển thị nhiệt độ
    var tempDisplay = document.getElementById("t-box");
    tempDisplay.classList.remove("hight-temp", "normal-temp", "low-temp");
    if(newTemp >= 35){
        tempDisplay.classList.add("hight-temp");
    } else if(newTemp >= 25){
        tempDisplay.classList.add("normal-temp");
    } else {
        tempDisplay.classList.add("low-temp");
    }
    tempData.textContent = newTemp + "°C";

    //Hiển thị độ ẩm
    var humiDisplay = document.getElementById("h-box");
    humiDisplay.classList.remove("hight-humi", "normal-humi", "low-humi");
    if(newHumi >= 75){
        humiDisplay.classList.add("hight-humi");
    } else if(newHumi >= 50){
        humiDisplay.classList.add("normal-humi");
    } else {
        humiDisplay.classList.add("low-humi");
    }
    humiData.textContent = newHumi + "%";

    //Hiển thị ánh sáng
    var liDisplay = document.getElementById("l-box");
    liDisplay.classList.remove("hight-li", "normal-li", "low-li");
    if(newLight >= 10500){
        liDisplay.classList.add("hight-li");
    } else if(newLight >= 10000){
        liDisplay.classList.add("normal-li");
    } else {
        liDisplay.classList.add("low-li");
    }
    liData.textContent = newLight + " lux";

    temperatureData.push(newTemp);
    humidityData.push(newHumi);
    lightData.push(newLight);

    if(temperatureData.length > 10){
        temperatureData.shift();
    }
    if(humidityData.length > 10){
        humidityData.shift();
    }
    if(lightData.length > 10){
        lightData.shift();
    }

    // Chỉ cập nhật một điểm dữ liệu mới nhất vào biểu đồ
    temperatureChart.series[0].addPoint(newTemp, true, temperatureData.length > 10);
    temperatureChart.series[1].addPoint(newHumi, true, humidityData.length > 10);
    temperatureChart.series[2].addPoint(newLight, true, lightData.length > 10);
}
fetchDataFromAPI();
setInterval(fetchDataFromAPI,2000);