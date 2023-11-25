package demo.demo_btl_iot.Service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher {
    private static final String MQTT_BROKER = "tcp://localhost:1883"; // Địa chỉ máy chủ MQTT
    private static final String CLIENT_ID = "mqtt_publisher"; // ID của client

    public void publishMessage(String message, String topic) {
        try {
            MqttClient mqttClient = new MqttClient(MQTT_BROKER, CLIENT_ID);
            mqttClient.connect();

            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(message.getBytes());
            mqttClient.publish(topic, mqttMessage);

            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}