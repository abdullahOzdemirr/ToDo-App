# 📝 Spring Boot Todo Application

Bu proje, Java Spring Boot kullanılarak geliştirilmiş, JWT (JSON Web Token) tabanlı kimlik doğrulama ve yetkilendirme özelliklerine sahip güvenli bir **Todo (Yapılacaklar) Yönetim Sistemi** API'sidir.

---

## 🚀 Kullanılan Teknolojiler ve Mimari

* **Java 24 / Spring Boot (3.5.5)**
* **Spring Security & JWT** (Güvenlik ve Kimlik Doğrulama)
* **Spring Data JPA & Hibernate** (Veritabanı Yönetimi)
* **PostgreSQL** (İlişkisel Veritabanı)
* **Maven** (Bağımlılık Yönetimi)
* **SpringDoc OpenAPI / Swagger UI** (API Dokümantasyonu ve Test Arayüzü)

---

## 📂 Proje Katmanlı Mimari Yapısı

Proje, sürdürülebilir ve temiz bir kod tabanı için Katmanlı Mimari (Layered Architecture) yaklaşımıyla geliştirilmiştir:
- `controller`: HTTP isteklerini karşılayan REST uç noktaları.
- `business`: İş mantığının (Business Logic) yürütüldüğü servis katmanı.
- `repository`: Spring Data JPA veritabanı erişim arayüzleri.
- `entity`: Veritabanı tablo nesneleri.
- `dto`: Veri transfer nesneleri.
- `config` & `util`: Güvenlik, JWT ve yardımcı araç konfigürasyonları.

---

## ⚙️ Kurulum ve Çalıştırma

Projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları takip edebilirsiniz:

1. **Projeyi Klonlayın:**

   git clone https://github.com/abdullahOzdemirr/ToDo-App.git

2. **Veritabanı ve Ortam Ayarları:**
    Projenizin yapılandırmasını iki farklı şekilde yapabilirsiniz:
    Seçenek A: application.properties dosyasından bilgileri girin.
    Seçenek B (.env ile): Projenin ana dizinine (kök dizine) .env adında bir dosya oluşturarak gerekli değişkenleri tanımlayın:

-    DB_URL=jdbc:postgresql://localhost:5432/postgres
-    DB_USERNAME=postgres
-    DB_PASSWORD=veritabani_sifreniz
-    MAIL_HOST=smtp.gmail.com
-    MAIL_PORT=587
-    MAIL_USERNAME=eposta_adresiniz
-    MAIL_PASSWORD=eposta_sifreniz
-    JWT_SECRET=cok_gizli_ve_uzun_jwt_anahtariniz

3. **Projeyi Başlatın:**

    IntelliJ IDEA üzerinden: Projedeki ToDoApplication.java dosyasını açın ve yeşil Run butonuna basın.
    Ya da Maven Wrapper üzerinden terminale şunu yazabilirsiniz:

-    Windows: mvnw spring-boot:run
-    Mac / Linux: ./mvnw spring-boot:run

---

## 📖 API Dokümantasyonu (Swagger UI)

Uygulama ayakta iken API uç noktalarını görsel olarak incelemek ve test etmek için tarayıcınızdan şu adrese gidebilirsiniz:

    http://localhost:8080/swagger-ui/index.html