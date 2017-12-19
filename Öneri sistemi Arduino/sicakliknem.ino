

#include <dht11.h> // dht11 kütüphanesini ekliyoruz.
#define DHT11PIN 30 // DHT11PIN olarak Dijital 2'yi belirliyoruz.

dht11 DHT11;


float h = 0;
float t = 0;
void setup()
{
  pinMode(7, OUTPUT);
  pinMode(8, OUTPUT);
  pinMode(9, OUTPUT);
  pinMode(10, OUTPUT);

  Serial.begin(9600); // Seri iletişimi başlatıyoruz.
  Serial.println("Giysi Öneri Sistemi");
}

void loop()
{
  // Bir satır boşluk bırakıyoruz serial monitörde.
  Serial.println("\n");
  // Sensörün okunup okunmadığını konrol ediyoruz. 
  // chk 0 ise sorunsuz okunuyor demektir. Sorun yaşarsanız
  // chk değerini serial monitörde yazdırıp kontrol edebilirsiniz.
  int chk = DHT11.read(DHT11PIN);
 // Serial.print(chk);
 // Serial.println("\n");
  
  h =(float)DHT11.humidity, 2 ;
  t =(float)DHT11.temperature, 2 ;
  // Sensörden gelen verileri serial monitörde yazdırıyoruz.
  Serial.print("Nem (%): ");
  Serial.println(h);

  Serial.print("Sicaklik (Celcius): ");
  Serial.println(t);

  if(t<0){
    digitalWrite(7, HIGH);
  }
  else if(t>=0 && t<=10){
    digitalWrite(8, HIGH);
  }
  else if(t>10 && t<=20){
    digitalWrite(9, HIGH);
  }
  else{
    digitalWrite(10,HIGH);
  }



  
  // 2 saniye bekliyoruz. 2 saniyede bir veriler ekrana yazdırılacak.
  delay(3000);
  
}

