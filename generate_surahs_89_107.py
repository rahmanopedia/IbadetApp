#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generate Quran surah JSON files for surahs 89-107 (Juz Amma portion).
Arabic text and Turkish translations (Diyanet İşleri Başkanlığı style).
"""

import json
import os

OUTPUT_DIR = "/home/user/IbadetApp/app/src/main/assets/quran"

surahs = []

# ===========================================================================
# Surah 89 - Al-Fajr (The Dawn) - 30 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 89,
    "name": "الفجر",
    "englishName": "Al-Fajr",
    "turkishName": "Fecr",
    "numberOfAyahs": 30,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالْفَجْرِ", "turkishText": "Fecre (sabah aydınlığına) andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "وَلَيَالٍ عَشْرٍ", "turkishText": "On geceye andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "وَالشَّفْعِ وَالْوَتْرِ", "turkishText": "Çift ve teke andolsun,"},
        {"number": 4, "numberInSurah": 4, "text": "وَاللَّيْلِ إِذَا يَسْرِ", "turkishText": "Geçip giderken geceye andolsun."},
        {"number": 5, "numberInSurah": 5, "text": "هَلْ فِي ذَٰلِكَ قَسَمٌ لِّذِي حِجْرٍ", "turkishText": "Bunlarda akıl sahibi için (yeterli) bir yemin yok mu?"},
        {"number": 6, "numberInSurah": 6, "text": "أَلَمْ تَرَ كَيْفَ فَعَلَ رَبُّكَ بِعَادٍ", "turkishText": "Rabbinin Âd kavmine ne yaptığını görmedin mi?"},
        {"number": 7, "numberInSurah": 7, "text": "إِرَمَ ذَاتِ الْعِمَادِ", "turkishText": "Sütunlarla dolu İrem'e (ne yaptığını)?"},
        {"number": 8, "numberInSurah": 8, "text": "الَّتِي لَمْ يُخْلَقْ مِثْلُهَا فِي الْبِلَادِ", "turkishText": "Ki ülkelerde onun benzeri yaratılmamıştı."},
        {"number": 9, "numberInSurah": 9, "text": "وَثَمُودَ الَّذِينَ جَابُوا الصَّخْرَ بِالْوَادِ", "turkishText": "Vadide kayaları oyan Semûd'a (ne yaptığını)?"},
        {"number": 10, "numberInSurah": 10, "text": "وَفِرْعَوْنَ ذِي الْأَوْتَادِ", "turkishText": "Kazıklar sahibi Firavun'a (ne yaptığını)?"},
        {"number": 11, "numberInSurah": 11, "text": "الَّذِينَ طَغَوْا فِي الْبِلَادِ", "turkishText": "Onlar ki ülkelerde azgınlık etmişlerdi."},
        {"number": 12, "numberInSurah": 12, "text": "فَأَكْثَرُوا فِيهَا الْفَسَادَ", "turkishText": "Ve orada çok bozgunculuk yapmışlardı."},
        {"number": 13, "numberInSurah": 13, "text": "فَصَبَّ عَلَيْهِمْ رَبُّكَ سَوْطَ عَذَابٍ", "turkishText": "Bunun üzerine Rabbin onların üzerine azap kamçısı yağdırdı."},
        {"number": 14, "numberInSurah": 14, "text": "إِنَّ رَبَّكَ لَبِالْمِرْصَادِ", "turkishText": "Şüphesiz Rabbin gözetleme yerindedir (her şeyi görüp gözetmektedir)."},
        {"number": 15, "numberInSurah": 15, "text": "فَأَمَّا الْإِنسَانُ إِذَا مَا ابْتَلَاهُ رَبُّهُ فَأَكْرَمَهُ وَنَعَّمَهُ فَيَقُولُ رَبِّي أَكْرَمَنِ", "turkishText": "İnsana gelince, Rabbi onu sınayıp da ikram ettiğinde ve nimetler verdiğinde 'Rabbim bana ikram etti' der."},
        {"number": 16, "numberInSurah": 16, "text": "وَأَمَّا إِذَا مَا ابْتَلَاهُ فَقَدَرَ عَلَيْهِ رِزْقَهُ فَيَقُولُ رَبِّي أَهَانَنِ", "turkishText": "Ama onu sınayıp rızkını daraltınca 'Rabbim beni aşağıladı' der."},
        {"number": 17, "numberInSurah": 17, "text": "كَلَّا ۖ بَل لَّا تُكْرِمُونَ الْيَتِيمَ", "turkishText": "Hayır! Doğrusu siz yetime ikram etmiyorsunuz."},
        {"number": 18, "numberInSurah": 18, "text": "وَلَا تَحَاضُّونَ عَلَىٰ طَعَامِ الْمِسْكِينِ", "turkishText": "Birbirinizi yoksulu doyurmaya teşvik etmiyorsunuz."},
        {"number": 19, "numberInSurah": 19, "text": "وَتَأْكُلُونَ التُّرَاثَ أَكْلًا لَّمًّا", "turkishText": "Mirası alabildiğine yiyorsunuz."},
        {"number": 20, "numberInSurah": 20, "text": "وَتُحِبُّونَ الْمَالَ حُبًّا جَمًّا", "turkishText": "Malı aşırı bir sevgiyle seviyorsunuz."},
        {"number": 21, "numberInSurah": 21, "text": "كَلَّا إِذَا دُكَّتِ الْأَرْضُ دَكًّا دَكًّا", "turkishText": "Hayır! Yer parça parça düzlenip ezildiğinde,"},
        {"number": 22, "numberInSurah": 22, "text": "وَجَاءَ رَبُّكَ وَالْمَلَكُ صَفًّا صَفًّا", "turkishText": "Rabbin geldiğinde ve melekler saf saf dizildiğinde,"},
        {"number": 23, "numberInSurah": 23, "text": "وَجِيءَ يَوْمَئِذٍ بِجَهَنَّمَ ۚ يَوْمَئِذٍ يَتَذَكَّرُ الْإِنسَانُ وَأَنَّىٰ لَهُ الذِّكْرَىٰ", "turkishText": "Ve o gün cehennem getirildiğinde, insan o gün düşünüp öğüt alacak, fakat bu düşünüp öğüt almanın ne faydası olacak?"},
        {"number": 24, "numberInSurah": 24, "text": "يَقُولُ يَا لَيْتَنِي قَدَّمْتُ لِحَيَاتِي", "turkishText": "'Keşke bu hayatım için önceden (bir şeyler) gönderseydim' diyecek."},
        {"number": 25, "numberInSurah": 25, "text": "فَيَوْمَئِذٍ لَّا يُعَذِّبُ عَذَابَهُ أَحَدٌ", "turkishText": "O gün Allah'ın azabı gibi azap edebilecek hiç kimse yoktur."},
        {"number": 26, "numberInSurah": 26, "text": "وَلَا يُوثِقُ وَثَاقَهُ أَحَدٌ", "turkishText": "Ve O'nun bağladığı gibi bağlayabilecek hiç kimse yoktur."},
        {"number": 27, "numberInSurah": 27, "text": "يَا أَيَّتُهَا النَّفْسُ الْمُطْمَئِنَّةُ", "turkishText": "Ey huzura kavuşmuş nefs!"},
        {"number": 28, "numberInSurah": 28, "text": "ارْجِعِي إِلَىٰ رَبِّكِ رَاضِيَةً مَّرْضِيَّةً", "turkishText": "Sen O'ndan razı, O da senden razı olarak Rabbine dön."},
        {"number": 29, "numberInSurah": 29, "text": "فَادْخُلِي فِي عِبَادِي", "turkishText": "Kullarımın arasına gir."},
        {"number": 30, "numberInSurah": 30, "text": "وَادْخُلِي جَنَّتِي", "turkishText": "Ve cennetime gir."},
    ]
})

# ===========================================================================
# Surah 90 - Al-Balad (The City) - 20 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 90,
    "name": "البلد",
    "englishName": "Al-Balad",
    "turkishName": "Beled",
    "numberOfAyahs": 20,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "لَا أُقْسِمُ بِهَٰذَا الْبَلَدِ", "turkishText": "Bu şehre (Mekke'ye) andolsun;"},
        {"number": 2, "numberInSurah": 2, "text": "وَأَنتَ حِلٌّ بِهَٰذَا الْبَلَدِ", "turkishText": "Sen bu şehirde ikamet ediyorsun."},
        {"number": 3, "numberInSurah": 3, "text": "وَوَالِدٍ وَمَا وَلَدَ", "turkishText": "Babaya ve doğurduğuna andolsun ki,"},
        {"number": 4, "numberInSurah": 4, "text": "لَقَدْ خَلَقْنَا الْإِنسَانَ فِي كَبَدٍ", "turkishText": "Gerçekten biz insanı sıkıntı içinde yarattık."},
        {"number": 5, "numberInSurah": 5, "text": "أَيَحْسَبُ أَن لَّن يَقْدِرَ عَلَيْهِ أَحَدٌ", "turkishText": "Hiç kimsenin ona güç yetiremeyeceğini mi zannediyor?"},
        {"number": 6, "numberInSurah": 6, "text": "يَقُولُ أَهْلَكْتُ مَالًا لُّبَدًا", "turkishText": "'Yığınla mal harcadım' diyor."},
        {"number": 7, "numberInSurah": 7, "text": "أَيَحْسَبُ أَن لَّمْ يَرَهُ أَحَدٌ", "turkishText": "Hiç kimsenin onu görmediğini mi zannediyor?"},
        {"number": 8, "numberInSurah": 8, "text": "أَلَمْ نَجْعَل لَّهُ عَيْنَيْنِ", "turkishText": "Ona iki göz vermedik mi?"},
        {"number": 9, "numberInSurah": 9, "text": "وَلِسَانًا وَشَفَتَيْنِ", "turkishText": "Bir dil ve iki dudak (vermedik mi)?"},
        {"number": 10, "numberInSurah": 10, "text": "وَهَدَيْنَاهُ النَّجْدَيْنِ", "turkishText": "Ve ona iki yolu (hayır ve şer yollarını) göstermedik mi?"},
        {"number": 11, "numberInSurah": 11, "text": "فَلَا اقْتَحَمَ الْعَقَبَةَ", "turkishText": "Ama o sarp yokuşu aşmaya girişmedi."},
        {"number": 12, "numberInSurah": 12, "text": "وَمَا أَدْرَاكَ مَا الْعَقَبَةُ", "turkishText": "O sarp yokuşun ne olduğunu sana ne bildirdi?"},
        {"number": 13, "numberInSurah": 13, "text": "فَكُّ رَقَبَةٍ", "turkishText": "Köle azat etmek,"},
        {"number": 14, "numberInSurah": 14, "text": "أَوْ إِطْعَامٌ فِي يَوْمٍ ذِي مَسْغَبَةٍ", "turkishText": "Ya da kıtlık gününde doyurmaktır."},
        {"number": 15, "numberInSurah": 15, "text": "يَتِيمًا ذَا مَقْرَبَةٍ", "turkishText": "Yakınlığı olan bir yetimi,"},
        {"number": 16, "numberInSurah": 16, "text": "أَوْ مِسْكِينًا ذَا مَتْرَبَةٍ", "turkishText": "Ya da yoksulluğa düşmüş bir yoksulu."},
        {"number": 17, "numberInSurah": 17, "text": "ثُمَّ كَانَ مِنَ الَّذِينَ آمَنُوا وَتَوَاصَوْا بِالصَّبْرِ وَتَوَاصَوْا بِالْمَرْحَمَةِ", "turkishText": "Sonra iman edip birbirlerine sabrı ve merhameti tavsiye edenlerden olmaktır."},
        {"number": 18, "numberInSurah": 18, "text": "أُولَٰئِكَ أَصْحَابُ الْمَيْمَنَةِ", "turkishText": "İşte bunlar sağ yanın (cennetin) sahipleridir."},
        {"number": 19, "numberInSurah": 19, "text": "وَالَّذِينَ كَفَرُوا بِآيَاتِنَا هُمْ أَصْحَابُ الْمَشْأَمَةِ", "turkishText": "Ayetlerimizi inkâr edenler ise sol yanın (cehennemin) sahipleridir."},
        {"number": 20, "numberInSurah": 20, "text": "عَلَيْهِمْ نَارٌ مُّؤْصَدَةٌ", "turkishText": "Onların üzerlerine kapanmış bir ateş vardır."},
    ]
})

# ===========================================================================
# Surah 91 - Ash-Shams (The Sun) - 15 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 91,
    "name": "الشمس",
    "englishName": "Ash-Shams",
    "turkishName": "Şems",
    "numberOfAyahs": 15,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالشَّمْسِ وَضُحَاهَا", "turkishText": "Güneşe ve kuşluk aydınlığına andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "وَالْقَمَرِ إِذَا تَلَاهَا", "turkishText": "Onu (güneşi) izlediğinde aya andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "وَالنَّهَارِ إِذَا جَلَّاهَا", "turkishText": "Onu (güneşi) açığa çıkardığında gündüze andolsun,"},
        {"number": 4, "numberInSurah": 4, "text": "وَاللَّيْلِ إِذَا يَغْشَاهَا", "turkishText": "Onu (güneşi) örttüğünde geceye andolsun,"},
        {"number": 5, "numberInSurah": 5, "text": "وَالسَّمَاءِ وَمَا بَنَاهَا", "turkishText": "Göğe ve onu bina edene andolsun,"},
        {"number": 6, "numberInSurah": 6, "text": "وَالْأَرْضِ وَمَا طَحَاهَا", "turkishText": "Yere ve onu yayıp döşeyene andolsun,"},
        {"number": 7, "numberInSurah": 7, "text": "وَنَفْسٍ وَمَا سَوَّاهَا", "turkishText": "Nefse ve onu düzenleyip şekillendirene andolsun,"},
        {"number": 8, "numberInSurah": 8, "text": "فَأَلْهَمَهَا فُجُورَهَا وَتَقْوَاهَا", "turkishText": "Sonra ona kötülüğünü ve takvasını (iyiliğini) ilham edene andolsun ki,"},
        {"number": 9, "numberInSurah": 9, "text": "قَدْ أَفْلَحَ مَن زَكَّاهَا", "turkishText": "Onu (nefsini) arındıran gerçekten kurtuluşa ermiştir."},
        {"number": 10, "numberInSurah": 10, "text": "وَقَدْ خَابَ مَن دَسَّاهَا", "turkishText": "Onu (nefsini) kirleten ise ziyana uğramıştır."},
        {"number": 11, "numberInSurah": 11, "text": "كَذَّبَتْ ثَمُودُ بِطَغْوَاهَا", "turkishText": "Semûd kavmi azgınlığı yüzünden yalanladı."},
        {"number": 12, "numberInSurah": 12, "text": "إِذِ انبَعَثَ أَشْقَاهَا", "turkishText": "Hani onların en azgın olanı ayağa kalktığında,"},
        {"number": 13, "numberInSurah": 13, "text": "فَقَالَ لَهُمْ رَسُولُ اللَّهِ نَاقَةَ اللَّهِ وَسُقْيَاهَا", "turkishText": "Allah'ın elçisi onlara 'Allah'ın devesine ve onun su içme sırasına dokunmayın' demişti."},
        {"number": 14, "numberInSurah": 14, "text": "فَكَذَّبُوهُ فَعَقَرُوهَا فَدَمْدَمَ عَلَيْهِمْ رَبُّهُم بِذَنبِهِمْ فَسَوَّاهَا", "turkishText": "Fakat onu yalanladılar ve deveyi kestiler. Bunun üzerine Rableri günahları sebebiyle azabı onlara hak etti ve (şehri) yerle bir etti."},
        {"number": 15, "numberInSurah": 15, "text": "وَلَا يَخَافُ عُقْبَاهَا", "turkishText": "O bu işin sonucundan korkmaz."},
    ]
})

# ===========================================================================
# Surah 92 - Al-Layl (The Night) - 21 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 92,
    "name": "الليل",
    "englishName": "Al-Layl",
    "turkishName": "Leyl",
    "numberOfAyahs": 21,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَاللَّيْلِ إِذَا يَغْشَىٰ", "turkishText": "Bürüyüp kapladığında geceye andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "وَالنَّهَارِ إِذَا تَجَلَّىٰ", "turkishText": "Aydınlandığında gündüze andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "وَمَا خَلَقَ الذَّكَرَ وَالْأُنثَىٰ", "turkishText": "Erkeği ve dişiyi yaratana andolsun ki,"},
        {"number": 4, "numberInSurah": 4, "text": "إِنَّ سَعْيَكُمْ لَشَتَّىٰ", "turkishText": "Gerçekten çabalarınız farklıdır."},
        {"number": 5, "numberInSurah": 5, "text": "فَأَمَّا مَنْ أَعْطَىٰ وَاتَّقَىٰ", "turkishText": "Kim verir ve Allah'a karşı gelmekten sakınırsa,"},
        {"number": 6, "numberInSurah": 6, "text": "وَصَدَّقَ بِالْحُسْنَىٰ", "turkishText": "Ve en güzel olanı (cenneti) doğrularsa,"},
        {"number": 7, "numberInSurah": 7, "text": "فَسَنُيَسِّرُهُ لِلْيُسْرَىٰ", "turkishText": "Biz de onu en kolaya (hayra) hazırlarız."},
        {"number": 8, "numberInSurah": 8, "text": "وَأَمَّا مَن بَخِلَ وَاسْتَغْنَىٰ", "turkishText": "Ama kim cimrilik eder ve kendini müstağni görürse,"},
        {"number": 9, "numberInSurah": 9, "text": "وَكَذَّبَ بِالْحُسْنَىٰ", "turkishText": "Ve en güzeli (cenneti) yalanlarsa,"},
        {"number": 10, "numberInSurah": 10, "text": "فَسَنُيَسِّرُهُ لِلْعُسْرَىٰ", "turkishText": "Biz de onu en zora (şerre) hazırlarız."},
        {"number": 11, "numberInSurah": 11, "text": "وَمَا يُغْنِي عَنْهُ مَالُهُ إِذَا تَرَدَّىٰ", "turkishText": "Yok olup gittiğinde (düştüğünde) malı ona ne fayda sağlayacak?"},
        {"number": 12, "numberInSurah": 12, "text": "إِنَّ عَلَيْنَا لَلْهُدَىٰ", "turkishText": "Şüphesiz yol göstermek bize aittir."},
        {"number": 13, "numberInSurah": 13, "text": "وَإِنَّ لَنَا لَلْآخِرَةَ وَالْأُولَىٰ", "turkishText": "Ve şüphesiz ahiret de dünya da bize aittir."},
        {"number": 14, "numberInSurah": 14, "text": "فَأَنذَرْتُكُمْ نَارًا تَلَظَّىٰ", "turkishText": "İşte sizi alevlenen bir ateşe karşı uyardım."},
        {"number": 15, "numberInSurah": 15, "text": "لَا يَصْلَاهَا إِلَّا الْأَشْقَى", "turkishText": "O ateşe ancak en bedbaht olan girer."},
        {"number": 16, "numberInSurah": 16, "text": "الَّذِي كَذَّبَ وَتَوَلَّىٰ", "turkishText": "O ki yalanladı ve yüz çevirdi."},
        {"number": 17, "numberInSurah": 17, "text": "وَسَيُجَنَّبُهَا الْأَتْقَى", "turkishText": "En muttaki olan ise ondan uzak tutulacaktır."},
        {"number": 18, "numberInSurah": 18, "text": "الَّذِي يُؤْتِي مَالَهُ يَتَزَكَّىٰ", "turkishText": "O ki malını vererek temizlenir."},
        {"number": 19, "numberInSurah": 19, "text": "وَمَا لِأَحَدٍ عِندَهُ مِن نِّعْمَةٍ تُجْزَىٰ", "turkishText": "Yanında karşılık beklediği kimsenin nimeti yoktur."},
        {"number": 20, "numberInSurah": 20, "text": "إِلَّا ابْتِغَاءَ وَجْهِ رَبِّهِ الْأَعْلَىٰ", "turkishText": "Sadece yüce Rabbinin rızasını kazanmak için."},
        {"number": 21, "numberInSurah": 21, "text": "وَلَسَوْفَ يَرْضَىٰ", "turkishText": "Ve o elbette razı olacaktır."},
    ]
})

# ===========================================================================
# Surah 93 - Ad-Duha (The Morning Hours) - 11 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 93,
    "name": "الضحى",
    "englishName": "Ad-Duha",
    "turkishName": "Duha",
    "numberOfAyahs": 11,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالضُّحَىٰ", "turkishText": "Kuşluk vaktine andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "وَاللَّيْلِ إِذَا سَجَىٰ", "turkishText": "Karardığında geceye andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "مَا وَدَّعَكَ رَبُّكَ وَمَا قَلَىٰ", "turkishText": "Rabbin seni bırakmadı ve sana darılmadı."},
        {"number": 4, "numberInSurah": 4, "text": "وَلَلْآخِرَةُ خَيْرٌ لَّكَ مِنَ الْأُولَىٰ", "turkishText": "Ve elbette ahiret senin için dünyadan daha hayırlıdır."},
        {"number": 5, "numberInSurah": 5, "text": "وَلَسَوْفَ يُعْطِيكَ رَبُّكَ فَتَرْضَىٰ", "turkishText": "Rabbin sana verecek ve sen de razı olacaksın."},
        {"number": 6, "numberInSurah": 6, "text": "أَلَمْ يَجِدْكَ يَتِيمًا فَآوَىٰ", "turkishText": "Seni yetim bulup barındırmadı mı?"},
        {"number": 7, "numberInSurah": 7, "text": "وَوَجَدَكَ ضَالًّا فَهَدَىٰ", "turkishText": "Seni yolunu kaybetmiş bulup yola iletmedi mi?"},
        {"number": 8, "numberInSurah": 8, "text": "وَوَجَدَكَ عَائِلًا فَأَغْنَىٰ", "turkishText": "Seni yoksul bulup zengin etmedi mi?"},
        {"number": 9, "numberInSurah": 9, "text": "فَأَمَّا الْيَتِيمَ فَلَا تَقْهَرْ", "turkishText": "O hâlde yetimi sakın ezme."},
        {"number": 10, "numberInSurah": 10, "text": "وَأَمَّا السَّائِلَ فَلَا تَنْهَرْ", "turkishText": "İsteyeni (dilenciyi) sakın azarlama."},
        {"number": 11, "numberInSurah": 11, "text": "وَأَمَّا بِنِعْمَةِ رَبِّكَ فَحَدِّثْ", "turkishText": "Rabbinin nimetini ise anlat (şükret)."},
    ]
})

# ===========================================================================
# Surah 94 - Ash-Sharh / Al-Inshirah (The Relief) - 8 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 94,
    "name": "الشرح",
    "englishName": "Ash-Sharh",
    "turkishName": "İnşirah",
    "numberOfAyahs": 8,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "أَلَمْ نَشْرَحْ لَكَ صَدْرَكَ", "turkishText": "Senin göğsünü açıp genişletmedik mi?"},
        {"number": 2, "numberInSurah": 2, "text": "وَوَضَعْنَا عَنكَ وِزْرَكَ", "turkishText": "Ve yükünü senden almadık mı?"},
        {"number": 3, "numberInSurah": 3, "text": "الَّذِي أَنقَضَ ظَهْرَكَ", "turkishText": "Ki o sırtını çatırdatıyordu."},
        {"number": 4, "numberInSurah": 4, "text": "وَرَفَعْنَا لَكَ ذِكْرَكَ", "turkishText": "Ve senin şanını yükseltmedik mi?"},
        {"number": 5, "numberInSurah": 5, "text": "فَإِنَّ مَعَ الْعُسْرِ يُسْرًا", "turkishText": "Demek ki güçlükle beraber bir kolaylık vardır."},
        {"number": 6, "numberInSurah": 6, "text": "إِنَّ مَعَ الْعُسْرِ يُسْرًا", "turkishText": "Gerçekten güçlükle beraber bir kolaylık vardır."},
        {"number": 7, "numberInSurah": 7, "text": "فَإِذَا فَرَغْتَ فَانصَبْ", "turkishText": "Öyleyse bir işi bitirince diğerine giriş."},
        {"number": 8, "numberInSurah": 8, "text": "وَإِلَىٰ رَبِّكَ فَارْغَبْ", "turkishText": "Ve yalnızca Rabbine yönel."},
    ]
})

# ===========================================================================
# Surah 95 - At-Tin (The Fig) - 8 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 95,
    "name": "التين",
    "englishName": "At-Tin",
    "turkishName": "Tîn",
    "numberOfAyahs": 8,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالتِّينِ وَالزَّيْتُونِ", "turkishText": "İncire ve zeytine andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "وَطُورِ سِينِينَ", "turkishText": "Sina Dağı'na andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "وَهَٰذَا الْبَلَدِ الْأَمِينِ", "turkishText": "Ve şu güvenli beldeye (Mekke'ye) andolsun ki,"},
        {"number": 4, "numberInSurah": 4, "text": "لَقَدْ خَلَقْنَا الْإِنسَانَ فِي أَحْسَنِ تَقْوِيمٍ", "turkishText": "Gerçekten biz insanı en güzel biçimde yarattık."},
        {"number": 5, "numberInSurah": 5, "text": "ثُمَّ رَدَدْنَاهُ أَسْفَلَ سَافِلِينَ", "turkishText": "Sonra onu aşağıların en aşağısına indirdik."},
        {"number": 6, "numberInSurah": 6, "text": "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ فَلَهُمْ أَجْرٌ غَيْرُ مَمْنُونٍ", "turkishText": "Ancak iman edip salih amel işleyenler bunun dışındadır; onlara kesintisiz bir mükâfat vardır."},
        {"number": 7, "numberInSurah": 7, "text": "فَمَا يُكَذِّبُكَ بَعْدُ بِالدِّينِ", "turkishText": "Artık seni dini (hesap gününü) yalanlamaya ne sevk ediyor?"},
        {"number": 8, "numberInSurah": 8, "text": "أَلَيْسَ اللَّهُ بِأَحْكَمِ الْحَاكِمِينَ", "turkishText": "Allah hükmedenlerin en güzel hükmedeni değil midir?"},
    ]
})

# ===========================================================================
# Surah 96 - Al-Alaq (The Clot) - 19 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 96,
    "name": "العلق",
    "englishName": "Al-Alaq",
    "turkishName": "Alak",
    "numberOfAyahs": 19,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "اقْرَأْ بِاسْمِ رَبِّكَ الَّذِي خَلَقَ", "turkishText": "Yaratan Rabbinin adıyla oku."},
        {"number": 2, "numberInSurah": 2, "text": "خَلَقَ الْإِنسَانَ مِنْ عَلَقٍ", "turkishText": "O insanı bir kan pıhtısından yarattı."},
        {"number": 3, "numberInSurah": 3, "text": "اقْرَأْ وَرَبُّكَ الْأَكْرَمُ", "turkishText": "Oku! Rabbin en büyük kerem sahibidir."},
        {"number": 4, "numberInSurah": 4, "text": "الَّذِي عَلَّمَ بِالْقَلَمِ", "turkishText": "O ki kalemle öğretti."},
        {"number": 5, "numberInSurah": 5, "text": "عَلَّمَ الْإِنسَانَ مَا لَمْ يَعْلَمْ", "turkishText": "İnsana bilmediğini öğretti."},
        {"number": 6, "numberInSurah": 6, "text": "كَلَّا إِنَّ الْإِنسَانَ لَيَطْغَىٰ", "turkishText": "Hayır! Gerçekten insan azar."},
        {"number": 7, "numberInSurah": 7, "text": "أَن رَّآهُ اسْتَغْنَىٰ", "turkishText": "Kendini müstağni gördüğü için."},
        {"number": 8, "numberInSurah": 8, "text": "إِنَّ إِلَىٰ رَبِّكَ الرُّجْعَىٰ", "turkishText": "Şüphesiz dönüş Rabbinedir."},
        {"number": 9, "numberInSurah": 9, "text": "أَرَأَيْتَ الَّذِي يَنْهَىٰ", "turkishText": "Namazdan alıkoyanı gördün mü?"},
        {"number": 10, "numberInSurah": 10, "text": "عَبْدًا إِذَا صَلَّىٰ", "turkishText": "O kul namaz kıldığında (onu yasaklayanı)."},
        {"number": 11, "numberInSurah": 11, "text": "أَرَأَيْتَ إِن كَانَ عَلَى الْهُدَىٰ", "turkishText": "Ya kul doğru yol üzereyse (ne dersin)?"},
        {"number": 12, "numberInSurah": 12, "text": "أَوْ أَمَرَ بِالتَّقْوَىٰ", "turkishText": "Ya da takvayı emrediyorsa?"},
        {"number": 13, "numberInSurah": 13, "text": "أَرَأَيْتَ إِن كَذَّبَ وَتَوَلَّىٰ", "turkishText": "Ya (yasaklayan) yalanladı ve yüz çevirdiyse (ne dersin)?"},
        {"number": 14, "numberInSurah": 14, "text": "أَلَمْ يَعْلَم بِأَنَّ اللَّهَ يَرَىٰ", "turkishText": "Allah'ın her şeyi gördüğünü bilmiyor mu?"},
        {"number": 15, "numberInSurah": 15, "text": "كَلَّا لَئِن لَّمْ يَنتَهِ لَنَسْفَعًا بِالنَّاصِيَةِ", "turkishText": "Hayır! Eğer vazgeçmezse, andolsun o perçemden tutup sürükleyeceğiz."},
        {"number": 16, "numberInSurah": 16, "text": "نَاصِيَةٍ كَاذِبَةٍ خَاطِئَةٍ", "turkishText": "Yalancı ve günahkâr o perçemden."},
        {"number": 17, "numberInSurah": 17, "text": "فَلْيَدْعُ نَادِيَهُ", "turkishText": "Haydi çağırsın dostlarını."},
        {"number": 18, "numberInSurah": 18, "text": "سَنَدْعُ الزَّبَانِيَةَ", "turkishText": "Biz de zebanileri çağıracağız."},
        {"number": 19, "numberInSurah": 19, "text": "كَلَّا لَا تُطِعْهُ وَاسْجُدْ وَاقْتَرِب ۩", "turkishText": "Hayır, ona itaat etme; secde et ve (Rabbine) yaklaş."},
    ]
})

# ===========================================================================
# Surah 97 - Al-Qadr (The Power) - 5 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 97,
    "name": "القدر",
    "englishName": "Al-Qadr",
    "turkishName": "Kadir",
    "numberOfAyahs": 5,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "إِنَّا أَنزَلْنَاهُ فِي لَيْلَةِ الْقَدْرِ", "turkishText": "Şüphesiz biz onu (Kur'an'ı) Kadir gecesinde indirdik."},
        {"number": 2, "numberInSurah": 2, "text": "وَمَا أَدْرَاكَ مَا لَيْلَةُ الْقَدْرِ", "turkishText": "Kadir gecesinin ne olduğunu sana ne bildirdi?"},
        {"number": 3, "numberInSurah": 3, "text": "لَيْلَةُ الْقَدْرِ خَيْرٌ مِّنْ أَلْفِ شَهْرٍ", "turkishText": "Kadir gecesi bin aydan hayırlıdır."},
        {"number": 4, "numberInSurah": 4, "text": "تَنَزَّلُ الْمَلَائِكَةُ وَالرُّوحُ فِيهَا بِإِذْنِ رَبِّهِم مِّن كُلِّ أَمْرٍ", "turkishText": "O gecede melekler ve Ruh (Cebrail) Rablerinin izniyle her türlü iş için inerler."},
        {"number": 5, "numberInSurah": 5, "text": "سَلَامٌ هِيَ حَتَّىٰ مَطْلَعِ الْفَجْرِ", "turkishText": "O gece, fecrin doğuşuna kadar bir esenliktir."},
    ]
})

# ===========================================================================
# Surah 98 - Al-Bayyinah (The Clear Proof) - 8 ayahs - Medinan
# ===========================================================================
surahs.append({
    "number": 98,
    "name": "البينة",
    "englishName": "Al-Bayyinah",
    "turkishName": "Beyyine",
    "numberOfAyahs": 8,
    "revelationType": "Medinan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "لَمْ يَكُنِ الَّذِينَ كَفَرُوا مِنْ أَهْلِ الْكِتَابِ وَالْمُشْرِكِينَ مُنفَكِّينَ حَتَّىٰ تَأْتِيَهُمُ الْبَيِّنَةُ", "turkishText": "Kitap ehlinden ve müşriklerden inkâr edenler, kendilerine apaçık delil gelinceye kadar (küfürden) ayrılacak değillerdi."},
        {"number": 2, "numberInSurah": 2, "text": "رَسُولٌ مِّنَ اللَّهِ يَتْلُو صُحُفًا مُّطَهَّرَةً", "turkishText": "Allah'tan gelen bir elçi ki tertemiz sayfalar okur."},
        {"number": 3, "numberInSurah": 3, "text": "فِيهَا كُتُبٌ قَيِّمَةٌ", "turkishText": "İçinde doğru ve değerli hükümler bulunur."},
        {"number": 4, "numberInSurah": 4, "text": "وَمَا تَفَرَّقَ الَّذِينَ أُوتُوا الْكِتَابَ إِلَّا مِن بَعْدِ مَا جَاءَتْهُمُ الْبَيِّنَةُ", "turkishText": "Kitap verilenler, kendilerine apaçık delil geldikten sonra ayrılığa düştüler."},
        {"number": 5, "numberInSurah": 5, "text": "وَمَا أُمِرُوا إِلَّا لِيَعْبُدُوا اللَّهَ مُخْلِصِينَ لَهُ الدِّينَ حُنَفَاءَ وَيُقِيمُوا الصَّلَاةَ وَيُؤْتُوا الزَّكَاةَ ۚ وَذَٰلِكَ دِينُ الْقَيِّمَةِ", "turkishText": "Hâlbuki onlara sadece, dini yalnız Allah'a has kılarak, hakka yönelmiş kimseler olarak O'na kulluk etmeleri, namazı kılmaları ve zekâtı vermeleri emredilmişti. İşte bu dosdoğru dindir."},
        {"number": 6, "numberInSurah": 6, "text": "إِنَّ الَّذِينَ كَفَرُوا مِنْ أَهْلِ الْكِتَابِ وَالْمُشْرِكِينَ فِي نَارِ جَهَنَّمَ خَالِدِينَ فِيهَا ۚ أُولَٰئِكَ هُمْ شَرُّ الْبَرِيَّةِ", "turkishText": "Şüphesiz kitap ehlinden ve müşriklerden inkâr edenler cehennem ateşindedirler, orada ebedî kalacaklardır. İşte onlar yaratıkların en kötüsüdür."},
        {"number": 7, "numberInSurah": 7, "text": "إِنَّ الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ أُولَٰئِكَ هُمْ خَيْرُ الْبَرِيَّةِ", "turkishText": "Şüphesiz iman edip salih ameller işleyenler, işte onlar yaratıkların en hayırlısıdır."},
        {"number": 8, "numberInSurah": 8, "text": "جَزَاؤُهُمْ عِندَ رَبِّهِمْ جَنَّاتُ عَدْنٍ تَجْرِي مِن تَحْتِهَا الْأَنْهَارُ خَالِدِينَ فِيهَا أَبَدًا ۖ رَّضِيَ اللَّهُ عَنْهُمْ وَرَضُوا عَنْهُ ۚ ذَٰلِكَ لِمَنْ خَشِيَ رَبَّهُ", "turkishText": "Rableri katındaki mükâfatları, içinden ırmaklar akan Adn cennetleridir; orada ebedî kalacaklardır. Allah onlardan razı olmuş, onlar da O'ndan razı olmuşlardır. İşte bu, Rabbine derin saygı duyanlara mahsustur."},
    ]
})

# ===========================================================================
# Surah 99 - Az-Zalzalah (The Earthquake) - 8 ayahs - Medinan
# ===========================================================================
surahs.append({
    "number": 99,
    "name": "الزلزلة",
    "englishName": "Az-Zalzalah",
    "turkishName": "Zilzâl",
    "numberOfAyahs": 8,
    "revelationType": "Medinan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "إِذَا زُلْزِلَتِ الْأَرْضُ زِلْزَالَهَا", "turkishText": "Yer o şiddetli sarsıntısıyla sarsıldığında,"},
        {"number": 2, "numberInSurah": 2, "text": "وَأَخْرَجَتِ الْأَرْضُ أَثْقَالَهَا", "turkishText": "Yer içindeki ağırlıklarını (ölüleri) dışarı çıkardığında,"},
        {"number": 3, "numberInSurah": 3, "text": "وَقَالَ الْإِنسَانُ مَا لَهَا", "turkishText": "Ve insan 'Ona ne oluyor?' dediğinde,"},
        {"number": 4, "numberInSurah": 4, "text": "يَوْمَئِذٍ تُحَدِّثُ أَخْبَارَهَا", "turkishText": "O gün yer, haberlerini anlatır."},
        {"number": 5, "numberInSurah": 5, "text": "بِأَنَّ رَبَّكَ أَوْحَىٰ لَهَا", "turkishText": "Çünkü Rabbin ona (öyle) vahyetti."},
        {"number": 6, "numberInSurah": 6, "text": "يَوْمَئِذٍ يَصْدُرُ النَّاسُ أَشْتَاتًا لِّيُرَوْا أَعْمَالَهُمْ", "turkishText": "O gün insanlar, amellerinin kendilerine gösterilmesi için gruplar hâlinde çıkarlar."},
        {"number": 7, "numberInSurah": 7, "text": "فَمَن يَعْمَلْ مِثْقَالَ ذَرَّةٍ خَيْرًا يَرَهُ", "turkishText": "Kim zerre ağırlığınca iyilik yapmışsa onu görür."},
        {"number": 8, "numberInSurah": 8, "text": "وَمَن يَعْمَلْ مِثْقَالَ ذَرَّةٍ شَرًّا يَرَهُ", "turkishText": "Kim de zerre ağırlığınca kötülük yapmışsa onu görür."},
    ]
})

# ===========================================================================
# Surah 100 - Al-Adiyat (The Chargers) - 11 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 100,
    "name": "العاديات",
    "englishName": "Al-Adiyat",
    "turkishName": "Âdiyât",
    "numberOfAyahs": 11,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالْعَادِيَاتِ ضَبْحًا", "turkishText": "Soluk soluğa koşan atlara andolsun,"},
        {"number": 2, "numberInSurah": 2, "text": "فَالْمُورِيَاتِ قَدْحًا", "turkishText": "Tırnaklarıyla kıvılcımlar çıkaranlara andolsun,"},
        {"number": 3, "numberInSurah": 3, "text": "فَالْمُغِيرَاتِ صُبْحًا", "turkishText": "Sabah baskını yapanlara andolsun,"},
        {"number": 4, "numberInSurah": 4, "text": "فَأَثَرْنَ بِهِ نَقْعًا", "turkishText": "Ve (koşarken) toz dumana (bulutu) kaldıranlara andolsun,"},
        {"number": 5, "numberInSurah": 5, "text": "فَوَسَطْنَ بِهِ جَمْعًا", "turkishText": "Ve düşman topluluğunun ortasına girenlere andolsun ki,"},
        {"number": 6, "numberInSurah": 6, "text": "إِنَّ الْإِنسَانَ لِرَبِّهِ لَكَنُودٌ", "turkishText": "Gerçekten insan Rabbine karşı pek nankördür."},
        {"number": 7, "numberInSurah": 7, "text": "وَإِنَّهُ عَلَىٰ ذَٰلِكَ لَشَهِيدٌ", "turkishText": "Ve o buna bizzat şahittir."},
        {"number": 8, "numberInSurah": 8, "text": "وَإِنَّهُ لِحُبِّ الْخَيْرِ لَشَدِيدٌ", "turkishText": "Ve o gerçekten mala olan sevgisiyle çok şiddetlidir."},
        {"number": 9, "numberInSurah": 9, "text": "أَفَلَا يَعْلَمُ إِذَا بُعْثِرَ مَا فِي الْقُبُورِ", "turkishText": "Kabirlerdekiler diriltilip çıkarıldığında bilmez mi?"},
        {"number": 10, "numberInSurah": 10, "text": "وَحُصِّلَ مَا فِي الصُّدُورِ", "turkishText": "Ve göğüslerdekiler (kalplerdekilerin) ortaya döküldüğünde?"},
        {"number": 11, "numberInSurah": 11, "text": "إِنَّ رَبَّهُم بِهِمْ يَوْمَئِذٍ لَّخَبِيرٌ", "turkishText": "Şüphesiz Rableri o gün onlardan haberdardır."},
    ]
})

# ===========================================================================
# Surah 101 - Al-Qariah (The Calamity) - 11 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 101,
    "name": "القارعة",
    "englishName": "Al-Qariah",
    "turkishName": "Kâria",
    "numberOfAyahs": 11,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "الْقَارِعَةُ", "turkishText": "Büyük kıyamet felaketi (Karia)!"},
        {"number": 2, "numberInSurah": 2, "text": "مَا الْقَارِعَةُ", "turkishText": "Nedir o büyük kıyamet felaketi?"},
        {"number": 3, "numberInSurah": 3, "text": "وَمَا أَدْرَاكَ مَا الْقَارِعَةُ", "turkishText": "O büyük kıyamet felaketinin ne olduğunu sana ne bildirdi?"},
        {"number": 4, "numberInSurah": 4, "text": "يَوْمَ يَكُونُ النَّاسُ كَالْفَرَاشِ الْمَبْثُوثِ", "turkishText": "O gün insanlar dağılmış güveler gibi olurlar."},
        {"number": 5, "numberInSurah": 5, "text": "وَتَكُونُ الْجِبَالُ كَالْعِهْنِ الْمَنفُوشِ", "turkishText": "Dağlar atılmış renkli yün gibi olur."},
        {"number": 6, "numberInSurah": 6, "text": "فَأَمَّا مَن ثَقُلَتْ مَوَازِينُهُ", "turkishText": "Artık kimin tartıları ağır basarsa,"},
        {"number": 7, "numberInSurah": 7, "text": "فَهُوَ فِي عِيشَةٍ رَّاضِيَةٍ", "turkishText": "O hoşnut bir yaşayış içindedir."},
        {"number": 8, "numberInSurah": 8, "text": "وَأَمَّا مَنْ خَفَّتْ مَوَازِينُهُ", "turkishText": "Ama kimin tartıları hafif gelirse,"},
        {"number": 9, "numberInSurah": 9, "text": "فَأُمُّهُ هَاوِيَةٌ", "turkishText": "Onun yeri Hâviye'dir."},
        {"number": 10, "numberInSurah": 10, "text": "وَمَا أَدْرَاكَ مَا هِيَهْ", "turkishText": "Onun ne olduğunu sana ne bildirdi?"},
        {"number": 11, "numberInSurah": 11, "text": "نَارٌ حَامِيَةٌ", "turkishText": "O, alevleri yüksek bir ateştir."},
    ]
})

# ===========================================================================
# Surah 102 - At-Takathur (The Rivalry in World Increase) - 8 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 102,
    "name": "التكاثر",
    "englishName": "At-Takathur",
    "turkishName": "Tekâsür",
    "numberOfAyahs": 8,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "أَلْهَاكُمُ التَّكَاثُرُ", "turkishText": "Çoğaltma yarışı sizi oyaladı."},
        {"number": 2, "numberInSurah": 2, "text": "حَتَّىٰ زُرْتُمُ الْمَقَابِرَ", "turkishText": "Ta ki kabirleri ziyaret edinceye (ölünceye) kadar."},
        {"number": 3, "numberInSurah": 3, "text": "كَلَّا سَوْفَ تَعْلَمُونَ", "turkishText": "Hayır! İleride bileceksiniz."},
        {"number": 4, "numberInSurah": 4, "text": "ثُمَّ كَلَّا سَوْفَ تَعْلَمُونَ", "turkishText": "Yine hayır! İleride bileceksiniz."},
        {"number": 5, "numberInSurah": 5, "text": "كَلَّا لَوْ تَعْلَمُونَ عِلْمَ الْيَقِينِ", "turkishText": "Hayır! Kesin olarak bilseydiniz (bu hâlde olmazdınız)."},
        {"number": 6, "numberInSurah": 6, "text": "لَتَرَوُنَّ الْجَحِيمَ", "turkishText": "Andolsun cehennemi göreceksiniz."},
        {"number": 7, "numberInSurah": 7, "text": "ثُمَّ لَتَرَوُنَّهَا عَيْنَ الْيَقِينِ", "turkishText": "Sonra andolsun onu gözle görerek (kesin olarak) göreceksiniz."},
        {"number": 8, "numberInSurah": 8, "text": "ثُمَّ لَتُسْأَلُنَّ يَوْمَئِذٍ عَنِ النَّعِيمِ", "turkishText": "Sonra andolsun o gün nimetlerden sorgulanacaksınız."},
    ]
})

# ===========================================================================
# Surah 103 - Al-Asr (The Declining Day) - 3 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 103,
    "name": "العصر",
    "englishName": "Al-Asr",
    "turkishName": "Asr",
    "numberOfAyahs": 3,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَالْعَصْرِ", "turkishText": "Asra (ikindi vaktine veya zamana) andolsun ki,"},
        {"number": 2, "numberInSurah": 2, "text": "إِنَّ الْإِنسَانَ لَفِي خُسْرٍ", "turkishText": "Gerçekten insan hüsran içindedir."},
        {"number": 3, "numberInSurah": 3, "text": "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ", "turkishText": "Ancak iman edip salih ameller işleyenler, birbirlerine hakkı ve sabrı tavsiye edenler bunun dışındadır."},
    ]
})

# ===========================================================================
# Surah 104 - Al-Humazah (The Slanderer) - 9 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 104,
    "name": "الهمزة",
    "englishName": "Al-Humazah",
    "turkishName": "Hümeze",
    "numberOfAyahs": 9,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "وَيْلٌ لِّكُلِّ هُمَزَةٍ لُّمَزَةٍ", "turkishText": "Her (arkadan) çekiştiren ve kaş göz hareketleriyle alay edip duran kimsenin vay hâline!"},
        {"number": 2, "numberInSurah": 2, "text": "الَّذِي جَمَعَ مَالًا وَعَدَّدَهُ", "turkishText": "O ki mal topladı ve onu saydı durdu."},
        {"number": 3, "numberInSurah": 3, "text": "يَحْسَبُ أَنَّ مَالَهُ أَخْلَدَهُ", "turkishText": "Malının onu ebedî kılacağını sanıyor."},
        {"number": 4, "numberInSurah": 4, "text": "كَلَّا ۖ لَيُنبَذَنَّ فِي الْحُطَمَةِ", "turkishText": "Hayır! Andolsun o Hutame'ye (parçalayıcı ateşe) atılacak."},
        {"number": 5, "numberInSurah": 5, "text": "وَمَا أَدْرَاكَ مَا الْحُطَمَةُ", "turkishText": "Hutame'nin ne olduğunu sana ne bildirdi?"},
        {"number": 6, "numberInSurah": 6, "text": "نَارُ اللَّهِ الْمُوقَدَةُ", "turkishText": "O, Allah'ın tutuşturulmuş ateşidir."},
        {"number": 7, "numberInSurah": 7, "text": "الَّتِي تَطَّلِعُ عَلَى الْأَفْئِدَةِ", "turkishText": "Ki o, kalplere kadar yükselip işler."},
        {"number": 8, "numberInSurah": 8, "text": "إِنَّهَا عَلَيْهِم مُّؤْصَدَةٌ", "turkishText": "Şüphesiz o (ateş) onların üzerine kapanmıştır."},
        {"number": 9, "numberInSurah": 9, "text": "فِي عَمَدٍ مُّمَدَّدَةٍ", "turkishText": "Uzatılmış sütunlar içinde."},
    ]
})

# ===========================================================================
# Surah 105 - Al-Fil (The Elephant) - 5 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 105,
    "name": "الفيل",
    "englishName": "Al-Fil",
    "turkishName": "Fil",
    "numberOfAyahs": 5,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "أَلَمْ تَرَ كَيْفَ فَعَلَ رَبُّكَ بِأَصْحَابِ الْفِيلِ", "turkishText": "Rabbinin fil ashâbına ne yaptığını görmedin mi?"},
        {"number": 2, "numberInSurah": 2, "text": "أَلَمْ يَجْعَلْ كَيْدَهُمْ فِي تَضْلِيلٍ", "turkishText": "Onların tuzaklarını (planlarını) boşa çıkarmadı mı?"},
        {"number": 3, "numberInSurah": 3, "text": "وَأَرْسَلَ عَلَيْهِمْ طَيْرًا أَبَابِيلَ", "turkishText": "Üzerlerine sürü sürü kuşlar gönderdi."},
        {"number": 4, "numberInSurah": 4, "text": "تَرْمِيهِم بِحِجَارَةٍ مِّن سِجِّيلٍ", "turkishText": "Onlara pişirilmiş çamurdan taşlar atıyorlardı."},
        {"number": 5, "numberInSurah": 5, "text": "فَجَعَلَهُمْ كَعَصْفٍ مَّأْكُولٍ", "turkishText": "Böylece onları yenilmiş ekine döndürdü."},
    ]
})

# ===========================================================================
# Surah 106 - Quraysh (Quraysh) - 4 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 106,
    "name": "قريش",
    "englishName": "Quraysh",
    "turkishName": "Kureyş",
    "numberOfAyahs": 4,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "لِإِيلَافِ قُرَيْشٍ", "turkishText": "Kureyş'i alıştırdığından dolayı,"},
        {"number": 2, "numberInSurah": 2, "text": "إِيلَافِهِمْ رِحْلَةَ الشِّتَاءِ وَالصَّيْفِ", "turkishText": "Onları kış ve yaz yolculuklarına alıştırdığından dolayı,"},
        {"number": 3, "numberInSurah": 3, "text": "فَلْيَعْبُدُوا رَبَّ هَٰذَا الْبَيْتِ", "turkishText": "Şu Beyt'in (Kâbe'nin) Rabbine kulluk etsinler."},
        {"number": 4, "numberInSurah": 4, "text": "الَّذِي أَطْعَمَهُم مِّن جُوعٍ وَآمَنَهُم مِّنْ خَوْفٍ", "turkishText": "O ki onları açlıktan doyurdu ve korkudan emin kıldı."},
    ]
})

# ===========================================================================
# Surah 107 - Al-Maun (Small Kindnesses) - 7 ayahs - Meccan
# ===========================================================================
surahs.append({
    "number": 107,
    "name": "الماعون",
    "englishName": "Al-Maun",
    "turkishName": "Mâûn",
    "numberOfAyahs": 7,
    "revelationType": "Meccan",
    "ayahs": [
        {"number": 1, "numberInSurah": 1, "text": "أَرَأَيْتَ الَّذِي يُكَذِّبُ بِالدِّينِ", "turkishText": "Dini (hesap ve ceza gününü) yalanlayanı gördün mü?"},
        {"number": 2, "numberInSurah": 2, "text": "فَذَٰلِكَ الَّذِي يَدُعُّ الْيَتِيمَ", "turkishText": "İşte odur yetimi itip kakan."},
        {"number": 3, "numberInSurah": 3, "text": "وَلَا يَحُضُّ عَلَىٰ طَعَامِ الْمِسْكِينِ", "turkishText": "Ve yoksulu doyurmaya teşvik etmez."},
        {"number": 4, "numberInSurah": 4, "text": "فَوَيْلٌ لِّلْمُصَلِّينَ", "turkishText": "Vay o namaz kılanların hâline ki,"},
        {"number": 5, "numberInSurah": 5, "text": "الَّذِينَ هُمْ عَن صَلَاتِهِمْ سَاهُونَ", "turkishText": "Onlar namazlarından gafildirler."},
        {"number": 6, "numberInSurah": 6, "text": "الَّذِينَ هُمْ يُرَاءُونَ", "turkishText": "Onlar gösteriş yaparlar."},
        {"number": 7, "numberInSurah": 7, "text": "وَيَمْنَعُونَ الْمَاعُونَ", "turkishText": "Ve küçük yardımları (zekât, sadaka) engellerler."},
    ]
})


# ===========================================================================
# Write all surah JSON files
# ===========================================================================
os.makedirs(OUTPUT_DIR, exist_ok=True)

for surah in surahs:
    surah_number = surah["number"]
    filename = os.path.join(OUTPUT_DIR, f"surah_{surah_number}.json")
    with open(filename, "w", encoding="utf-8") as f:
        json.dump(surah, f, ensure_ascii=False, indent=2)
    ayah_count = len(surah["ayahs"])
    expected_count = surah["numberOfAyahs"]
    status = "OK" if ayah_count == expected_count else f"MISMATCH (expected {expected_count}, got {ayah_count})"
    print(f"Written: {filename}  [{ayah_count} ayahs] {status}")

print("\nAll done!")
