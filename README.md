# Salon Yönetim Sistemi Backend

## Proje Açıklaması

Bu proje, modern bir güzellik salonu için kapsamlı bir yönetim sistemi sağlayan bir backend uygulamasıdır. Spring Boot framework'ü kullanılarak geliştirilmiş olup, güvenli ve ölçeklenebilir RESTful API'ler sunar. Sistem, randevu yönetimi, müşteri ilişkileri yönetimi (CRM), hizmet kataloğu yönetimi ve içerik yönetimi (blog gönderileri) gibi temel işlevleri kapsar.

## İçindekiler

1. [Özellikler](#özellikler)
2. [Teknoloji Yığını](#teknoloji-yığını)
3. [Sistem Gereksinimleri](#sistem-gereksinimleri)
4. [Kurulum](#kurulum)
5. [Konfigürasyon](#konfigürasyon)
6. [API Dokümantasyonu](#api-dokümantasyonu)
7. [Güvenlik](#güvenlik)
8. [Veritabanı Şeması](#veritabanı-şeması)
9. [Test](#test)
10. [Dağıtım](#dağıtım)
11. [Katkıda Bulunma](#katkıda-bulunma)
12. [Sürüm Geçmişi](#sürüm-geçmişi)
13. [Lisans](#lisans)
14. [İletişim](#iletişim)

## Özellikler

1. **Kullanıcı Yönetimi**
    - Kayıt ve giriş işlemleri
    - Rol tabanlı yetkilendirme (Admin, Çalışan, Müşteri)
    - JWT tabanlı kimlik doğrulama

2. **Müşteri Yönetimi**
    - Müşteri profili oluşturma ve güncelleme
    - Müşteri geçmişi ve tercihlerinin takibi

3. **Randevu Yönetimi**
    - Randevu oluşturma, güncelleme ve iptal etme
    - Çakışma kontrolü ve otomatik zaman önerisi
    - Hatırlatma bildirimleri (e-posta entegrasyonu)

4. **Hizmet Yönetimi**
    - Hizmet kataloğu yönetimi
    - Fiyatlandırma ve süre bilgisi

5. **Blog Yönetimi**
    - Blog gönderilerinin oluşturulması ve yönetimi
    - Kategori ve etiket sistemi

6. **Raporlama**
    - Gelir raporları
    - Müşteri istatistikleri
    - Hizmet popülerlik analizleri

## Teknoloji Yığını

- **Backend:** Java 11, Spring Boot 2.5.x
- **Güvenlik:** Spring Security, JSON Web Tokens (JWT)
- **Veritabanı:** MySQL 8.0
- **ORM:** Hibernate
- **API Dokümantasyonu:** Swagger / OpenAPI 3.0
- **Build Aracı:** Maven
- **Test:** JUnit 5, Mockito
- **Loglama:** SLF4J & Logback
- **Diğer:** Lombok, MapStruct

## Sistem Gereksinimleri

- JDK 11 veya üzeri
- Maven 3.6 veya üzeri
- MySQL 8.0 veya üzeri

## Kurulum

1. Projeyi klonlayın:
