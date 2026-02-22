# İbadet Uygulaması - Kurulum Rehberi

## Gereksinimler
- Android Studio Hedgehog (2023.1.1) veya üzeri
- Android SDK 34
- Kotlin 1.9.22
- JDK 17

## Kurulum Adımları

### 1. Projeyi Android Studio'da Açın
```
File > Open > /IbadetApp klasörünü seçin
```

### 2. Arapça Font Ekleyin (Zorunlu)
Arapça metinlerin doğru görüntülenmesi için:

1. [Scheherazade New](https://fonts.google.com/specimen/Scheherazade+New) fontunu indirin
2. İndirilen `ScheherazadeNew-Regular.ttf` dosyasını kopyalayın
3. `app/src/main/res/font/` klasörüne `arabic_font_regular.ttf` olarak kaydedin

### 3. Tüm Sure Verilerini Ekleyin (Opsiyonel)
`app/src/main/assets/quran/` klasöründe şu an sadece örnek sureler mevcuttur:
- Fatiha (1), Kevser (108), İhlas (112), Felak (113), Nas (114), Ya-sin (36)

Tüm Kuran'ı eklemek için açık kaynaklı API'leri kullanabilirsiniz:
- [Quran API](https://alquran.cloud/api) - Ücretsiz
- Her sure için `surah_X.json` formatında dosya oluşturun

### 4. Gradle Sync
Android Studio'da `File > Sync Project with Gradle Files` seçin.

### 5. Çalıştırın
`Run > Run 'app'` ile uygulamayı çalıştırın.

## Uygulama Özellikleri

### Kuran Okuma
- 114 sure listesi (tam liste için JSON dosyaları eklenmelidir)
- Arapça metin + Türkçe meal
- Ayet yer imleme
- Ayet kopyalama
- Yazı boyutu ayarı
- Sure arama

### Zikir Sayacı
- 8 önceden tanımlı zikir
- Özel zikir ekleme
- Sayaç (titreşim destekli)
- Hedef belirleme
- Tamamlanma bildirimi

### Yer İmleri
- Kaydedilen ayetler
- Tarih bilgisi

## Google Play'e Yükleme

### 1. Keystore Oluşturun
```
Build > Generate Signed Bundle/APK > Create new key store
```

### 2. Release APK/Bundle Oluşturun
```
Build > Generate Signed Bundle/APK > Android App Bundle (AAB önerilir)
```

### 3. Google Play Console
- [play.google.com/console](https://play.google.com/console) adresine gidin
- Yeni uygulama oluşturun
- AAB dosyasını yükleyin
- Gerekli ekran görüntülerini ve açıklamaları ekleyin

## Proje Yapısı

```
app/
├── src/main/
│   ├── java/com/ibadetapp/
│   │   ├── data/
│   │   │   ├── model/          # Veri modelleri
│   │   │   └── repository/     # Room DB ve DAO'lar
│   │   ├── ui/
│   │   │   ├── main/           # Ana ekran, yer imleri
│   │   │   ├── quran/          # Kuran modülü
│   │   │   └── zikir/          # Zikir modülü
│   │   └── util/               # Yardımcı sınıflar
│   ├── res/
│   │   ├── layout/             # XML layout dosyaları
│   │   ├── navigation/         # Nav graph
│   │   ├── values/             # Renkler, string, temalar
│   │   └── drawable/           # İkonlar
│   └── assets/quran/           # Kuran JSON verileri
```
