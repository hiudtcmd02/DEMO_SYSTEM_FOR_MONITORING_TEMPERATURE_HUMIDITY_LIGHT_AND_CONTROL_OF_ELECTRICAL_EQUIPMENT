package demo.demo_btl_iot;

import demo.demo_btl_iot.Service.MqttDataListener;
import demo.demo_btl_iot.Service.MqttPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class DemoBtlIotApplication {
    private static MqttDataListener mqttDataListener;

    @Autowired
    public DemoBtlIotApplication(MqttDataListener mqttDataListener) {
        DemoBtlIotApplication.mqttDataListener = mqttDataListener;
    }
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoBtlIotApplication.class, args);
        mqttDataListener.subscribeToMqttTopic();
    }

    @Scheduled(fixedDelay = 2000) // Chạy sau mỗi 2 giây
    public void processDataFromMqtt() {
        // Không cần thực hiện thêm công việc ở đây, vì dữ liệu được xử lý trong MqttDataListener
    }
}
