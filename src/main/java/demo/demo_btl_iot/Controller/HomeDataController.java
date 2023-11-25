package demo.demo_btl_iot.Controller;

import demo.demo_btl_iot.Entity.Action;
import demo.demo_btl_iot.Entity.Data_ESP;
import demo.demo_btl_iot.Service.ActionService;
import demo.demo_btl_iot.Service.DataESPService;
import demo.demo_btl_iot.Service.MqttPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HomeDataController {

    @Autowired
    private DataESPService dataESPService;
    @Autowired
    private MqttPublisher mqttPublisher;
    @Autowired
    private ActionService actionService;

    private Data_ESP latestData; // Biến để lưu trữ dữ liệu mới nhất

    @GetMapping("/homeData")
    public Data_ESP getData() {
        return latestData; // Trả về dữ liệu đã được cập nhật
    }

    // Phương thức để cập nhật dữ liệu mới mỗi 2 giây
    @Scheduled(fixedRate = 2000) // Thiết lập thời gian giữa các lần gọi là 2000ms = 2 giây
    public void updateDataPeriodically() {
        // Lấy dữ liệu mới từ service và cập nhật biến latestData
        latestData = dataESPService.getNewestData();
    }

    @PostMapping("/turnOnLight")
    public ResponseEntity<String> turnOnLight() {
        // Gửi thông điệp tới Controller để bật đèn
        mqttPublisher.publishMessage("1","led1");

        LocalDateTime currentTime = LocalDateTime.now();
        Action a = new Action();
        a.setClientName("Tên người dùng");
        a.setDeviceName("Đèn");
        a.setState("ON");
        a.setDateTime(currentTime);
        actionService.saveAction(a);

        return ResponseEntity.ok("Light turned on!");
    }

    @PostMapping("/turnOffLight")
    public ResponseEntity<String> turnOffLight() {
        // Gửi thông điệp tới Controller để tắt đèn
        mqttPublisher.publishMessage("0","led1");

        LocalDateTime currentTime = LocalDateTime.now();
        Action a = new Action();
        a.setClientName("Tên người dùng");
        a.setDeviceName("Đèn");
        a.setState("OFF");
        a.setDateTime(currentTime);
        actionService.saveAction(a);

        return ResponseEntity.ok("Light turned off!");
    }

    @PostMapping("/turnOnFan")
    public ResponseEntity<String> turnOnFan() {
        // Gửi thông điệp tới Controller để tắt đèn
        mqttPublisher.publishMessage("1","led2");

        LocalDateTime currentTime = LocalDateTime.now();
        Action a = new Action();
        a.setClientName("Tên người dùng");
        a.setDeviceName("Quạt");
        a.setState("ON");
        a.setDateTime(currentTime);
        actionService.saveAction(a);

        return ResponseEntity.ok("Fan turned on!");
    }

    @PostMapping("/turnOffFan")
    public ResponseEntity<String> turnOffFan() {
        // Gửi thông điệp tới Controller để tắt đèn
        mqttPublisher.publishMessage("0","led2");

        LocalDateTime currentTime = LocalDateTime.now();
        Action a = new Action();
        a.setClientName("Tên người dùng");
        a.setDeviceName("Quạt");
        a.setState("OFF");
        a.setDateTime(currentTime);
        actionService.saveAction(a);

        return ResponseEntity.ok("Fan turned off!");
    }
}

