#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <DHT.h>

const char* ssid = "P302";
const char* password = "1234567890";

const char* mqtt_server = "192.168.0.3";
const int mqtt_port = 1883;

#define DHTPIN D5
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

#define LDRPIN A0

const int ledPin1 = D3;
bool ledState1 = LOW;
const int ledPin2 = D1;
bool ledState2 = LOW;

WiFiClient espClient;
PubSubClient client(espClient);

void callback(char* topic, byte* payload, unsigned int length) {
  if (strcmp(topic, "led1") == 0) {
    if (payload[0] == '1') {
      digitalWrite(ledPin1, HIGH);
      ledState1 = HIGH;
    } else if (payload[0] == '0') {
      digitalWrite(ledPin1, LOW);
      ledState1 = LOW;
    }
  } else if (strcmp(topic, "led2") == 0) {
    if (payload[0] == '1') {
      digitalWrite(ledPin2, HIGH);
      ledState2 = HIGH;
    } else if (payload[0] == '0') {
      digitalWrite(ledPin2, LOW);
      ledState2 = LOW;
    }
  }
}

void connectToWiFi() {
  Serial.print("Đang kết nối WiFi...");
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi đã kết nối thành công");
  Serial.print("Địa chỉ IP: ");
  Serial.println(WiFi.localIP());
}

void connectToMQTT() {
  Serial.print("Đang kết nối MQTT...");
  client.setServer(mqtt_server, mqtt_port);

  while (!client.connected()) {
    if (client.connect("ESP8266Client")) {
      Serial.println("");
      Serial.println("Đã kết nối MQTT thành công");
      client.subscribe("led1");
      client.subscribe("led2");
    } else {
      Serial.print("Kết nối thất bại, mã lỗi = ");
      Serial.print(client.state());
      Serial.println(" Thử kết nối lại sau 2 giây...");

      delay(2000);
    }
  }
}

void setup() {
  Serial.begin(9600);

  connectToWiFi();

  dht.begin();

  connectToMQTT();

  pinMode(ledPin1, OUTPUT);
  digitalWrite(ledPin1, ledState1);

  pinMode(ledPin2, OUTPUT);
  digitalWrite(ledPin2, ledState2);

  client.setCallback(callback);
}

void loop() {
  if (!client.connected()) {
    connectToMQTT();
  }
  client.loop();

  float temperature = dht.readTemperature();
  float humidity = dht.readHumidity();

  if (isnan(temperature) || isnan(humidity)) {
    Serial.println("Lỗi khi đọc dữ liệu từ cảm biến DHT11");
    return;
  }

  int light = analogRead(LDRPIN) * 10;

  String data = "Temperature: " + String(temperature) + " ;Humidity: " + String(humidity) + " ;Light: " + String(light);
  client.publish("Data", data.c_str());

  delay(2000);
}