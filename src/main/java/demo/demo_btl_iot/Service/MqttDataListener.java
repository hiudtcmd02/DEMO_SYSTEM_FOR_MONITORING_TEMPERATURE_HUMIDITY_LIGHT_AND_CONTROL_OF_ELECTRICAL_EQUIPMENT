package demo.demo_btl_iot.Service;

import demo.demo_btl_iot.Entity.Data_ESP;
import demo.demo_btl_iot.Repository.DataESPRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MqttDataListener implements MqttCallback {
    private final DataESPRepository dataESPRepository;

    @Autowired
    public MqttDataListener(DataESPRepository dataESPRepository) {
        this.dataESPRepository = dataESPRepository;
    }

    public void subscribeToMqttTopic() {
        String broker = "tcp://localhost:1883";
        String clientId = "mqtt-subscriber";
        String topic = "Data";

        try {
            MqttClient client = new MqttClient(broker, clientId);
            client.setCallback(this);
            client.connect();

            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        // Xử lý khi mất kết nối với MQTT broker
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Xử lý khi nhận được message từ MQTT topic
        String data = new String(message.getPayload());

        // Phân tích dữ liệu và lưu vào cơ sở dữ liệu
        // Ví dụ:
        String[] dataParts = data.split(";");

        double temperature = Double.parseDouble(dataParts[0].split(":")[1].trim());
        double humidity = Double.parseDouble(dataParts[1].split(":")[1].trim());
        int light = Integer.parseInt(dataParts[2].split(":")[1].trim());
        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        Data_ESP sensorData = new Data_ESP();
        sensorData.setTemperature(temperature);
        sensorData.setHumidity(humidity);
        sensorData.setLight(light);
        // Lưu dữ liệu vào đối tượng Data_ESP
        sensorData.setDateTime(currentTime);
        dataESPRepository.save(sensorData);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Xử lý khi gửi message thành công
    }
}