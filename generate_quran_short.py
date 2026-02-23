import json
import os

OUTPUT_DIR = '/home/user/IbadetApp/app/src/main/assets/quran'

def write_surah(data):
    path = os.path.join(OUTPUT_DIR, f"surah_{data['number']}.json")
    with open(path, 'w', encoding='utf-8') as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
    print(f"Created surah_{data['number']}.json ({data['englishName']}, {data['numberOfAyahs']} ayahs)")

# Surah 78 - An-Naba (40 ayahs, Meccan)
write_surah({
  "number": 78, "name": "النبأ", "englishName": "An-Naba", "turkishName": "Nebe",
  "numberOfAyahs": 40, "revelationType": "Meccan",
  "ayahs": [
    {"number":1,"numberInSurah":1,"text":"عَمَّ يَتَسَاءَلُونَ","turkishText":"Birbirlerine neyi soruyorlar?"},
    {"number":2,"numberInSurah":2,"text":"عَنِ النَّبَإِ الْعَظِيمِ","turkishText":"O büyük haberi mi?"},
    {"number":3,"numberInSurah":3,"text":"الَّذِي هُمْ فِيهِ مُخْتَلِفُونَ","turkishText":"Ki onlar, bu hususta ayrılık içindedirler."},
    {"number":4,"numberInSurah":4,"text":"كَلَّا سَيَعْلَمُونَ","turkishText":"Hayır, yakında bilecekler."},
    {"number":5,"numberInSurah":5,"text":"ثُمَّ كَلَّا سَيَعْلَمُونَ","turkishText":"Yine hayır, yakında bilecekler."},
    {"number":6,"numberInSurah":6,"text":"أَلَمْ نَجْعَلِ الْأَرْضَ مِهَادًا","turkishText":"Biz yeri bir döşek yapmadık mı?"},
    {"number":7,"numberInSurah":7,"text":"وَالْجِبَالَ أَوْتَادًا","turkishText":"Dağları da birer kazık?"},
    {"number":8,"numberInSurah":8,"text":"وَخَلَقْنَاكُمْ أَزْوَاجًا","turkishText":"Sizi çift çift yarattık."},
    {"number":9,"numberInSurah":9,"text":"وَجَعَلْنَا نَوْمَكُمْ سُبَاتًا","turkishText":"Uykunuzu bir dinlenme yaptık."},
    {"number":10,"numberInSurah":10,"text":"وَجَعَلْنَا اللَّيْلَ لِبَاسًا","turkishText":"Geceyi bir örtü yaptık."},
    {"number":11,"numberInSurah":11,"text":"وَجَعَلْنَا النَّهَارَ مَعَاشًا","turkishText":"Gündüzü de geçim vakti yaptık."},
    {"number":12,"numberInSurah":12,"text":"وَبَنَيْنَا فَوْقَكُمْ سَبْعًا شِدَادًا","turkishText":"Üstünüzde yedi sağlam (gök) bina ettik."},
    {"number":13,"numberInSurah":13,"text":"وَجَعَلْنَا سِرَاجًا وَهَّاجًا","turkishText":"Işık saçan bir kandil yarattık."},
    {"number":14,"numberInSurah":14,"text":"وَأَنزَلْنَا مِنَ الْمُعْصِرَاتِ مَاءً ثَجَّاجًا","turkishText":"Sıkışık bulutlardan şarıl şarıl akan su indirdik."},
    {"number":15,"numberInSurah":15,"text":"لِّنُخْرِجَ بِهِ حَبًّا وَنَبَاتًا","turkishText":"Bununla tahıl ve bitkiler çıkaralım."},
    {"number":16,"numberInSurah":16,"text":"وَجَنَّاتٍ أَلْفَافًا","turkishText":"Ve bir-birine sarmaş dolaş bahçeler."},
    {"number":17,"numberInSurah":17,"text":"إِنَّ يَوْمَ الْفَصْلِ كَانَ مِيقَاتًا","turkishText":"Şüphesiz, hüküm günü belirlenmiş bir vakittir."},
    {"number":18,"numberInSurah":18,"text":"يَوْمَ يُنفَخُ فِي الصُّورِ فَتَأْتُونَ أَفْوَاجًا","turkishText":"O gün Sûr'a üfürülür de siz bölük bölük gelirsiniz."},
    {"number":19,"numberInSurah":19,"text":"وَفُتِحَتِ السَّمَاءُ فَكَانَتْ أَبْوَابًا","turkishText":"Gök açılır da kapı kapı olur."},
    {"number":20,"numberInSurah":20,"text":"وَسُيِّرَتِ الْجِبَالُ فَكَانَتْ سَرَابًا","turkishText":"Dağlar yürütülür de serap haline gelir."},
    {"number":21,"numberInSurah":21,"text":"إِنَّ جَهَنَّمَ كَانَتْ مِرْصَادًا","turkishText":"Şüphesiz cehennem bir pusu yeridir."},
    {"number":22,"numberInSurah":22,"text":"لِّلطَّاغِينَ مَآبًا","turkishText":"Azgınlar için bir varış yeridir."},
    {"number":23,"numberInSurah":23,"text":"لَّابِثِينَ فِيهَا أَحْقَابًا","turkishText":"Orada çağlar boyu kalacaklardır."},
    {"number":24,"numberInSurah":24,"text":"لَّا يَذُوقُونَ فِيهَا بَرْدًا وَلَا شَرَابًا","turkishText":"Orada ne bir serinlik ne de içecek tadarlar."},
    {"number":25,"numberInSurah":25,"text":"إِلَّا حَمِيمًا وَغَسَّاقًا","turkishText":"Ancak kaynar su ve irinli sıvı içerler."},
    {"number":26,"numberInSurah":26,"text":"جَزَاءً وِفَاقًا","turkishText":"Bu, yaptıklarına uygun bir karşılıktır."},
    {"number":27,"numberInSurah":27,"text":"إِنَّهُمْ كَانُوا لَا يَرْجُونَ حِسَابًا","turkishText":"Çünkü onlar hesaba çekilmeyi beklemiyorlardı."},
    {"number":28,"numberInSurah":28,"text":"وَكَذَّبُوا بِآيَاتِنَا كِذَّابًا","turkishText":"Ayetlerimizi de kesinlikle yalanladılar."},
    {"number":29,"numberInSurah":29,"text":"وَكُلَّ شَيْءٍ أَحْصَيْنَاهُ كِتَابًا","turkishText":"Her şeyi bir kitapta sayıp tespit etmişizdir."},
    {"number":30,"numberInSurah":30,"text":"فَذُوقُوا فَلَن نَّزِيدَكُمْ إِلَّا عَذَابًا","turkishText":"O hâlde tadın! Azabınızdan başka bir şey artırmayacağız."},
    {"number":31,"numberInSurah":31,"text":"إِنَّ لِلْمُتَّقِينَ مَفَازًا","turkishText":"Şüphesiz Allah'a karşı gelmekten sakınanlar için bir kurtuluş vardır."},
    {"number":32,"numberInSurah":32,"text":"حَدَائِقَ وَأَعْنَابًا","turkishText":"Bahçeler ve üzümler."},
    {"number":33,"numberInSurah":33,"text":"وَكَوَاعِبَ أَتْرَابًا","turkishText":"Göğüsleri tomurcuklanmış, yaşıt eşler."},
    {"number":34,"numberInSurah":34,"text":"وَكَأْسًا دِهَاقًا","turkishText":"Ve dolu kadehler."},
    {"number":35,"numberInSurah":35,"text":"لَّا يَسْمَعُونَ فِيهَا لَغْوًا وَلَا كِذَّابًا","turkishText":"Orada boş söz ve yalan işitmezler."},
    {"number":36,"numberInSurah":36,"text":"جَزَاءً مِّن رَّبِّكَ عَطَاءً حِسَابًا","turkishText":"Rabbinden bir ödül, yeterli bir bağış olarak."},
    {"number":37,"numberInSurah":37,"text":"رَّبِّ السَّمَاوَاتِ وَالْأَرْضِ وَمَا بَيْنَهُمَا الرَّحْمَٰنِ ۖ لَا يَمْلِكُونَ مِنْهُ خِطَابًا","turkishText":"Göklerin, yerin ve ikisi arasındakilerin Rabbi, Rahman'dır. Ondan hiçbiri söz alamaz."},
    {"number":38,"numberInSurah":38,"text":"يَوْمَ يَقُومُ الرُّوحُ وَالْمَلَائِكَةُ صَفًّا ۖ لَّا يَتَكَلَّمُونَ إِلَّا مَنْ أَذِنَ لَهُ الرَّحْمَٰنُ وَقَالَ صَوَابًا","turkishText":"Ruh ve melekler sıra sıra dikildiği gün, Rahman'ın izin verdikleri dışında kimse konuşamaz. Konuşan da doğruyu söyler."},
    {"number":39,"numberInSurah":39,"text":"ذَٰلِكَ الْيَوْمُ الْحَقُّ ۖ فَمَن شَاءَ اتَّخَذَ إِلَىٰ رَبِّهِ مَآبًا","turkishText":"İşte bu, gerçek gündür. Dileyen, Rabbine giden yolu tutsun."},
    {"number":40,"numberInSurah":40,"text":"إِنَّا أَنذَرْنَاكُمْ عَذَابًا قَرِيبًا يَوْمَ يَنظُرُ الْمَرْءُ مَا قَدَّمَتْ يَدَاهُ وَيَقُولُ الْكَافِرُ يَا لَيْتَنِي كُنتُ تُرَابًا","turkishText":"Şüphesiz biz sizi yakın bir azap ile uyardık. O gün kişi ellerinin önceden yaptıklarına bakar ve kâfir, 'Keşke toprak olsaydım!' der."}
  ]
})

# Surah 79 - An-Naziat (46 ayahs, Meccan)
write_surah({
  "number": 79, "name": "النازعات", "englishName": "An-Naziat", "turkishName": "Naziat",
  "numberOfAyahs": 46, "revelationType": "Meccan",
  "ayahs": [
    {"number":1,"numberInSurah":1,"text":"وَالنَّازِعَاتِ غَرْقًا","turkishText":"Şiddetle söküp çıkaranlara andolsun."},
    {"number":2,"numberInSurah":2,"text":"وَالنَّاشِطَاتِ نَشْطًا","turkishText":"Kolayca çekip alanlara."},
    {"number":3,"numberInSurah":3,"text":"وَالسَّابِحَاتِ سَبْحًا","turkishText":"Yüzüp gidenlere."},
    {"number":4,"numberInSurah":4,"text":"فَالسَّابِقَاتِ سَبْقًا","turkishText":"Yarışıp öne geçenlere."},
    {"number":5,"numberInSurah":5,"text":"فَالْمُدَبِّرَاتِ أَمْرًا","turkishText":"İşleri düzenleyip yönetenlere."},
    {"number":6,"numberInSurah":6,"text":"يَوْمَ تَرْجُفُ الرَّاجِفَةُ","turkishText":"O sarsıcı sarsıntının olacağı gün."},
    {"number":7,"numberInSurah":7,"text":"تَتْبَعُهَا الرَّادِفَةُ","turkishText":"Ardından bir sarsıntı daha gelecek."},
    {"number":8,"numberInSurah":8,"text":"قُلُوبٌ يَوْمَئِذٍ وَاجِفَةٌ","turkishText":"O gün kalpler yürek hoplatır."},
    {"number":9,"numberInSurah":9,"text":"أَبْصَارُهَا خَاشِعَةٌ","turkishText":"Gözler yerde."},
    {"number":10,"numberInSurah":10,"text":"يَقُولُونَ أَإِنَّا لَمَرْدُودُونَ فِي الْحَافِرَةِ","turkishText":"'Biz mi eski hâlimize döndürüleceğiz?' diyorlar."},
    {"number":11,"numberInSurah":11,"text":"أَإِذَا كُنَّا عِظَامًا نَّخِرَةً","turkishText":"'Çürümüş kemikler olduğumuzda mı?'"},
    {"number":12,"numberInSurah":12,"text":"قَالُوا تِلْكَ إِذًا كَرَّةٌ خَاسِرَةٌ","turkishText":"'O takdirde bu, zararlı bir dönüş olur.' diyorlar."},
    {"number":13,"numberInSurah":13,"text":"فَإِنَّمَا هِيَ زَجْرَةٙ وَاحِدَةٌ","turkishText":"Hâlbuki o, ancak tek bir sesleniştir."},
    {"number":14,"numberInSurah":14,"text":"فَإِذَا هُم بِالسَّاهِرَةِ","turkishText":"Bir de bakarsın, onlar yeryüzündedirler."},
    {"number":15,"numberInSurah":15,"text":"هَلْ أَتَاكَ حَدِيثُ مُوسَىٰ","turkishText":"Musa'nın haberi sana geldi mi?"},
    {"number":16,"numberInSurah":16,"text":"إِذْ نَادَاهُ رَبُّهُ بِالْوَادِ الْمُقَدَّسِ طُوًى","turkishText":"Hani Rabbi ona kutsal vadi Tuva'da seslenmişti."},
    {"number":17,"numberInSurah":17,"text":"اذْهَبْ إِلَىٰ فِرْعَوْنَ إِنَّهُ طَغَىٰ","turkishText":"'Firavun'a git. Çünkü o iyice azdı.'"},
    {"number":18,"numberInSurah":18,"text":"فَقُلْ هَل لَّكَ إِلَىٰ أَن تَزَكَّىٰ","turkishText":"'Ona de ki: Temizlenmek ister misin?'"},
    {"number":19,"numberInSurah":19,"text":"وَأَهْدِيَكَ إِلَىٰ رَبِّكَ فَتَخْشَىٰ","turkishText":"'Seni Rabbine ileteyim, böylece O'ndan korkasın.'"},
    {"number":20,"numberInSurah":20,"text":"فَأَرَاهُ الْآيَةَ الْكُبْرَىٰ","turkishText":"Musa ona büyük mucizeyi gösterdi."},
    {"number":21,"numberInSurah":21,"text":"فَكَذَّبَ وَعَصَىٰ","turkishText":"Ama o yalanladı ve isyan etti."},
    {"number":22,"numberInSurah":22,"text":"ثُمَّ أَدْبَرَ يَسْعَىٰ","turkishText":"Sonra ardını dönerek gitti."},
    {"number":23,"numberInSurah":23,"text":"فَحَشَرَ فَنَادَىٰ","turkishText":"Toplayıp bağırdı."},
    {"number":24,"numberInSurah":24,"text":"فَقَالَ أَنَا رَبُّكُمُ الْأَعْلَىٰ","turkishText":"'Ben sizin en yüce rabbinizim.' dedi."},
    {"number":25,"numberInSurah":25,"text":"فَأَخَذَهُ اللَّهُ نَكَالَ الْآخِرَةِ وَالْأُولَىٰ","turkishText":"Bunun üzerine Allah onu ahiret ve dünya azabıyla yakaladı."},
    {"number":26,"numberInSurah":26,"text":"إِنَّ فِي ذَٰلِكَ لَعِبْرَةً لِّمَن يَخْشَىٰ","turkishText":"Şüphesiz bunda, Allah'tan korkan kimse için büyük bir ibret vardır."},
    {"number":27,"numberInSurah":27,"text":"أَأَنتُمْ أَشَدُّ خَلْقًا أَمِ السَّمَاءُ ۚ بَنَاهَا","turkishText":"Sizi yaratmak mı daha zordur, yoksa göğü mü? Allah onu yaptı."},
    {"number":28,"numberInSurah":28,"text":"رَفَعَ سَمْكَهَا فَسَوَّاهَا","turkishText":"Onun tavanını yükseltip biçim verdi."},
    {"number":29,"numberInSurah":29,"text":"وَأَغْطَشَ لَيْلَهَا وَأَخْرَجَ ضُحَاهَا","turkishText":"Gecesini karanlık, kuşluğunu aydınlık kıldı."},
    {"number":30,"numberInSurah":30,"text":"وَالْأَرْضَ بَعْدَ ذَٰلِكَ دَحَاهَا","turkishText":"Bundan sonra yeri yayıp döşedi."},
    {"number":31,"numberInSurah":31,"text":"أَخْرَجَ مِنْهَا مَاءَهَا وَمَرْعَاهَا","turkishText":"Ondan suyunu ve otlağını çıkardı."},
    {"number":32,"numberInSurah":32,"text":"وَالْجِبَالَ أَرْسَاهَا","turkishText":"Dağları da yerleştirdi."},
    {"number":33,"numberInSurah":33,"text":"مَتَاعًا لَّكُمْ وَلِأَنْعَامِكُمْ","turkishText":"Bunları siz ve hayvanlarınız için bir faydalanma vesilesi olarak yaptı."},
    {"number":34,"numberInSurah":34,"text":"فَإِذَا جَاءَتِ الطَّامَّةُ الْكُبْرَىٰ","turkishText":"Büyük felâket geldiği zaman."},
    {"number":35,"numberInSurah":35,"text":"يَوْمَ يَتَذَكَّرُ الْإِنسَانُ مَا سَعَىٰ","turkishText":"O gün insan ne yaptığını hatırlar."},
    {"number":36,"numberInSurah":36,"text":"وَبُرِّزَتِ الْجَحِيمُ لِمَن يَرَىٰ","turkishText":"Cehennem, görecek olanlar için ortaya çıkarılır."},
    {"number":37,"numberInSurah":37,"text":"فَأَمَّا مَن طَغَىٰ","turkishText":"Azgınlık edene gelince,"},
    {"number":38,"numberInSurah":38,"text":"وَآثَرَ الْحَيَاةَ الدُّنْيَا","turkishText":"Dünya hayatını tercih edene gelince,"},
    {"number":39,"numberInSurah":39,"text":"فَإِنَّ الْجَحِيمَ هِيَ الْمَأْوَىٰ","turkishText":"Şüphesiz cehennem, onun barınağıdır."},
    {"number":40,"numberInSurah":40,"text":"وَأَمَّا مَنْ خَافَ مَقَامَ رَبِّهِ وَنَهَى النَّفْسَ عَنِ الْهَوَىٰ","turkishText":"Rabbinin makamından korkana ve nefsini kötü arzulardan alıkoyanlara gelince,"},
    {"number":41,"numberInSurah":41,"text":"فَإِنَّ الْجَنَّةَ هِيَ الْمَأْوَىٰ","turkishText":"Şüphesiz cennet, onun barınağıdır."},
    {"number":42,"numberInSurah":42,"text":"يَسْأَلُونَكَ عَنِ السَّاعَةِ أَيَّانَ مُرْسَاهَا","turkishText":"Sana kıyameti soruyorlar: 'Onun zamanı ne zaman?' diye."},
    {"number":43,"numberInSurah":43,"text":"فِيمَ أَنتَ مِن ذِكْرَاهَا","turkishText":"Onu bilmek sana mı düşüyor?"},
    {"number":44,"numberInSurah":44,"text":"إِلَىٰ رَبِّكَ مُنتَهَاهَا","turkishText":"Onun son bilgisi Rabbindedir."},
    {"number":45,"numberInSurah":45,"text":"إِنَّمَا أَنتَ مُنذِرُ مَن يَخْشَاهَا","turkishText":"Sen yalnızca ondan korkanları uyarıcısın."},
    {"number":46,"numberInSurah":46,"text":"كَأَنَّهُمْ يَوْمَ يَرَوْنَهَا لَمْ يَلْبَثُوا إِلَّا عَشِيَّةً أَوْ ضُحَاهَا","turkishText":"Onu gördükleri gün, sanki dünyada sadece bir akşam vakti ya da kuşluk vakti kadar kalmışlardır."}
  ]
})

# Surah 80 - Abasa (42 ayahs, Meccan)
write_surah({
  "number": 80, "name": "عبس", "englishName": "Abasa", "turkishName": "Abese",
  "numberOfAyahs": 42, "revelationType": "Meccan",
  "ayahs": [
    {"number":1,"numberInSurah":1,"text":"عَبَسَ وَتَوَلَّىٰ","turkishText":"Yüzünü ekşitti ve döndü."},
    {"number":2,"numberInSurah":2,"text":"أَن جَاءَهُ الْأَعْمَىٰ","turkishText":"Kendisine o kör kimse geldi diye."},
    {"number":3,"numberInSurah":3,"text":"وَمَا يُدْرِيكَ لَعَلَّهُ يَزَّكَّىٰ","turkishText":"Ne bilirsin, belki o arınacak."},
    {"number":4,"numberInSurah":4,"text":"أَوْ يَذَّكَّرُ فَتَنفَعَهُ الذِّكْرَىٰ","turkishText":"Ya da öğüt alacak da o öğüt kendisine yarar sağlayacak."},
    {"number":5,"numberInSurah":5,"text":"أَمَّا مَنِ اسْتَغْنَىٰ","turkishText":"Kendini muhtaç görmeyene gelince,"},
    {"number":6,"numberInSurah":6,"text":"فَأَنتَ لَهُ تَصَدَّىٰ","turkishText":"Sen onunla ilgileniyorsun."},
    {"number":7,"numberInSurah":7,"text":"وَمَا عَلَيْكَ أَلَّا يَزَّكَّىٰ","turkishText":"Hâlbuki onun arınmamasından sana ne?"},
    {"number":8,"numberInSurah":8,"text":"وَأَمَّا مَن جَاءَكَ يَسْعَىٰ","turkishText":"Sana koşarak gelene,"},
    {"number":9,"numberInSurah":9,"text":"وَهُوَ يَخْشَىٰ","turkishText":"Hem de Allah'tan korkarak,"},
    {"number":10,"numberInSurah":10,"text":"فَأَنتَ عَنْهُ تَلَهَّىٰ","turkishText":"Sen ondan yüz çeviriyorsun."},
    {"number":11,"numberInSurah":11,"text":"كَلَّا إِنَّهَا تَذْكِرَةٌ","turkishText":"Hayır! Şüphesiz bu, bir öğüttür."},
    {"number":12,"numberInSurah":12,"text":"فَمَن شَاءَ ذَكَرَهُ","turkishText":"Dileyen onu öğüt olarak alır."},
    {"number":13,"numberInSurah":13,"text":"فِي صُحُفٍ مُّكَرَّمَةٍ","turkishText":"O, değerli sayfalardadır."},
    {"number":14,"numberInSurah":14,"text":"مَّرْفُوعَةٍ مُّطَهَّرَةٍ","turkishText":"Yüce, tertemiz."},
    {"number":15,"numberInSurah":15,"text":"بِأَيْدِي سَفَرَةٍ","turkishText":"Elçiler olan yazıcıların ellerinde."},
    {"number":16,"numberInSurah":16,"text":"كِرَامٍ بَرَرَةٍ","turkishText":"Değerli ve erdemli."},
    {"number":17,"numberInSurah":17,"text":"قُتِلَ الْإِنسَانُ مَا أَكْفَرَهُ","turkishText":"İnsan kahrolası! Ne kadar da nankör!"},
    {"number":18,"numberInSurah":18,"text":"مِنْ أَيِّ شَيْءٍ خَلَقَهُ","turkishText":"Allah onu neden yarattı?"},
    {"number":19,"numberInSurah":19,"text":"مِن نُّطْفَةٍ خَلَقَهُ فَقَدَّرَهُ","turkishText":"Bir nutfeden yarattı onu, ona bir ölçü biçti."},
    {"number":20,"numberInSurah":20,"text":"ثُمَّ السَّبِيلَ يَسَّرَهُ","turkishText":"Sonra yolunu kolaylaştırdı."},
    {"number":21,"numberInSurah":21,"text":"ثُمَّ أَمَاتَهُ فَأَقْبَرَهُ","turkishText":"Sonra onu öldürdü ve kabre koydurdu."},
    {"number":22,"numberInSurah":22,"text":"ثُمَّ إِذَا شَاءَ أَنشَرَهُ","turkishText":"Sonra dilediği zaman onu diriltecek."},
    {"number":23,"numberInSurah":23,"text":"كَلَّا لَمَّا يَقْضِ مَا أَمَرَهُ","turkishText":"Hayır! İnsan henüz Allah'ın emrettiğini yerine getirmedi."},
    {"number":24,"numberInSurah":24,"text":"فَلْيَنظُرِ الْإِنسَانُ إِلَىٰ طَعَامِهِ","turkishText":"İnsan yiyeceğine bir baksın."},
    {"number":25,"numberInSurah":25,"text":"أَنَّا صَبَبْنَا الْمَاءَ صَبًّا","turkishText":"Biz suyu döke döke indirdik."},
    {"number":26,"numberInSurah":26,"text":"ثُمَّ شَقَقْنَا الْأَرْضَ شَقًّا","turkishText":"Sonra toprağı yardık."},
    {"number":27,"numberInSurah":27,"text":"فَأَنبَتْنَا فِيهَا حَبًّا","turkishText":"Onda tahıllar bitirdik."},
    {"number":28,"numberInSurah":28,"text":"وَعِنَبًا وَقَضْبًا","turkishText":"Üzüm ve yonca."},
    {"number":29,"numberInSurah":29,"text":"وَزَيْتُونًا وَنَخْلًا","turkishText":"Zeytin ve hurma."},
    {"number":30,"numberInSurah":30,"text":"وَحَدَائِقَ غُلْبًا","turkishText":"Sık ağaçlı bahçeler."},
    {"number":31,"numberInSurah":31,"text":"وَفَاكِهَةً وَأَبًّا","turkishText":"Meyve ve çayır."},
    {"number":32,"numberInSurah":32,"text":"مَّتَاعًا لَّكُمْ وَلِأَنْعَامِكُمْ","turkishText":"Bunlar sizin ve hayvanlarınızın yararlanması için."},
    {"number":33,"numberInSurah":33,"text":"فَإِذَا جَاءَتِ الصَّاخَّةُ","turkishText":"O kulakları sağır eden ses geldiği zaman."},
    {"number":34,"numberInSurah":34,"text":"يَوْمَ يَفِرُّ الْمَرْءُ مِنْ أَخِيهِ","turkishText":"O gün kişi kardeşinden kaçar."},
    {"number":35,"numberInSurah":35,"text":"وَأُمِّهِ وَأَبِيهِ","turkishText":"Annesinden ve babasından."},
    {"number":36,"numberInSurah":36,"text":"وَصَاحِبَتِهِ وَبَنِيهِ","turkishText":"Eşinden ve çocuklarından."},
    {"number":37,"numberInSurah":37,"text":"لِكُلِّ امْرِئٍ مِّنْهُمْ يَوْمَئِذٍ شَأْنٌ يُغْنِيهِ","turkishText":"O gün herkesin kendine yetecek bir derdi vardır."},
    {"number":38,"numberInSurah":38,"text":"وُجُوهٌ يَوْمَئِذٍ مُّسْفِرَةٌ","turkishText":"O gün bazı yüzler parıldar."},
    {"number":39,"numberInSurah":39,"text":"ضَاحِكَةٌ مُّسْتَبْشِرَةٌ","turkishText":"Güler, müjde dolu."},
    {"number":40,"numberInSurah":40,"text":"وَوُجُوهٌ يَوْمَئِذٍ عَلَيْهَا غَبَرَةٌ","turkishText":"O gün bazı yüzler ise tozlu."},
    {"number":41,"numberInSurah":41,"text":"تَرْهَقُهَا قَتَرَةٌ","turkishText":"Onları bir karartı bürür."},
    {"number":42,"numberInSurah":42,"text":"أُولَٰئِكَ هُمُ الْكَفَرَةُ الْفَجَرَةُ","turkishText":"İşte onlar, kâfir ve günahkârlardır."}
  ]
})

# Surah 81 - At-Takwir (29 ayahs, Meccan)
write_surah({
  "number": 81, "name": "التكوير", "englishName": "At-Takwir", "turkishName": "Tekvir",
  "numberOfAyahs": 29, "revelationType": "Meccan",
  "ayahs": [
    {"number":1,"numberInSurah":1,"text":"إِذَا الشَّمْسُ كُوِّرَتْ","turkishText":"Güneş dürüldüğünde."},
    {"number":2,"numberInSurah":2,"text":"وَإِذَا النُّجُومُ انكَدَرَتْ","turkishText":"Yıldızlar bulanıp söndüğünde."},
    {"number":3,"numberInSurah":3,"text":"وَإِذَا الْجِبَالُ سُيِّرَتْ","turkishText":"Dağlar yürütüldüğünde."},
    {"number":4,"numberInSurah":4,"text":"وَإِذَا الْعِشَارُ عُطِّلَتْ","turkishText":"On aylık gebe develer başıboş bırakıldığında."},
    {"number":5,"numberInSurah":5,"text":"وَإِذَا الْوُحُوشُ حُشِرَتْ","turkishText":"Vahşi hayvanlar bir araya toplandığında."},
    {"number":6,"numberInSurah":6,"text":"وَإِذَا الْبِحَارُ سُجِّرَتْ","turkishText":"Denizler tutuşturulduğunda."},
    {"number":7,"numberInSurah":7,"text":"وَإِذَا النُّفُوسُ زُوِّجَتْ","turkishText":"Ruhlar çiftleştirildiğinde."},
    {"number":8,"numberInSurah":8,"text":"وَإِذَا الْمَوْءُودَةُ سُئِلَتْ","turkishText":"Diri diri toprağa gömülen kız sorgulandığında."},
    {"number":9,"numberInSurah":9,"text":"بِأَيِّ ذَنبٍ قُتِلَتْ","turkishText":"'Hangi günahtan dolayı öldürüldü?' diye."},
    {"number":10,"numberInSurah":10,"text":"وَإِذَا الصُّحُفُ نُشِرَتْ","turkishText":"Amel defterleri açıldığında."},
    {"number":11,"numberInSurah":11,"text":"وَإِذَا السَّمَاءُ كُشِطَتْ","turkishText":"Gök soyulup alındığında."},
    {"number":12,"numberInSurah":12,"text":"وَإِذَا الْجَحِيمُ سُعِّرَتْ","turkishText":"Cehennem alevlendirildiğinde."},
    {"number":13,"numberInSurah":13,"text":"وَإِذَا الْجَنَّةُ أُزْلِفَتْ","turkishText":"Cennet yaklaştırıldığında."},
    {"number":14,"numberInSurah":14,"text":"عَلِمَتْ نَفْسٌ مَّا أَحْضَرَتْ","turkishText":"Her kişi ne getirdiğini bilir."},
    {"number":15,"numberInSurah":15,"text":"فَلَا أُقْسِمُ بِالْخُنَّسِ","turkishText":"Hayır! Döndükçe dönen yıldızlara yemin ederim."},
    {"number":16,"numberInSurah":16,"text":"الْجَوَارِ الْكُنَّسِ","turkishText":"Akıp giden, battıkça batan."},
    {"number":17,"numberInSurah":17,"text":"وَاللَّيْلِ إِذَا عَسْعَسَ","turkishText":"Kararıp bastıran geceye."},
    {"number":18,"numberInSurah":18,"text":"وَالصُّبْحِ إِذَا تَنَفَّسَ","turkishText":"Nefes alıp açılan sabaha."},
    {"number":19,"numberInSurah":19,"text":"إِنَّهُ لَقَوْلُ رَسُولٍ كَرِيمٍ","turkishText":"Şüphesiz bu, değerli bir elçinin sözüdür."},
    {"number":20,"numberInSurah":20,"text":"ذِي قُوَّةٍ عِندَ ذِي الْعَرْشِ مَكِينٍ","turkishText":"Arş sahibinin katında güçlü ve güvenilir bir konuma sahip."},
    {"number":21,"numberInSurah":21,"text":"مُّطَاعٍ ثَمَّ أَمِينٍ","turkishText":"Orada itaat edilen ve güvenilir."},
    {"number":22,"numberInSurah":22,"text":"وَمَا صَاحِبُكُم بِمَجْنُونٍ","turkishText":"Arkadaşınız deli değildir."},
    {"number":23,"numberInSurah":23,"text":"وَلَقَدْ رَآهُ بِالْأُفُقِ الْمُبِينِ","turkishText":"Andolsun onu açık ufukta gördü."},
    {"number":24,"numberInSurah":24,"text":"وَمَا هُوَ عَلَى الْغَيْبِ بِضَنِينٍ","turkishText":"O, gayb konusunda cimri değildir."},
    {"number":25,"numberInSurah":25,"text":"وَمَا هُوَ بِقَوْلِ شَيْطَانٍ رَّجِيمٍ","turkishText":"Bu, kovulmuş şeytanın sözü değildir."},
    {"number":26,"numberInSurah":26,"text":"فَأَيْنَ تَذْهَبُونَ","turkishText":"O hâlde nereye gidiyorsunuz?"},
    {"number":27,"numberInSurah":27,"text":"إِنْ هُوَ إِلَّا ذِكْرٌ لِّلْعَالَمِينَ","turkishText":"Bu, âlemler için bir öğütten başka bir şey değildir."},
    {"number":28,"numberInSurah":28,"text":"لِمَن شَاءَ مِنكُمْ أَن يَسْتَقِيمَ","turkishText":"İçinizden doğru yolda gitmek isteyenler için."},
    {"number":29,"numberInSurah":29,"text":"وَمَا تَشَاءُونَ إِلَّا أَن يَشَاءَ اللَّهُ رَبُّ الْعَالَمِينَ","turkishText":"Âlemlerin Rabbi Allah dilemedikçe siz dileyemezsiniz."}
  ]
})

# Surah 82 - Al-Infitar (19 ayahs, Meccan)
write_surah({
  "number": 82, "name": "الانفطار", "englishName": "Al-Infitar", "turkishName": "İnfitar",
  "numberOfAyahs": 19, "revelationType": "Meccan",
  "ayahs": [
    {"number":1,"numberInSurah":1,"text":"إِذَا السَّمَاءُ انفَطَرَتْ","turkishText":"Gök yarıldığında."},
    {"number":2,"numberInSurah":2,"text":"وَإِذَا الْكَوَاكِبُ انتَثَرَتْ","turkishText":"Yıldızlar döküldüğünde."},
    {"number":3,"numberInSurah":3,"text":"وَإِذَا الْبِحَارُ فُجِّرَتْ","turkishText":"Denizler birbirine katıldığında."},
    {"number":4,"numberInSurah":4,"text":"وَإِذَا الْقُبُورُ بُعْثِرَتْ","turkishText":"Kabirler alt üst edildiğinde."},
    {"number":5,"numberInSurah":5,"text":"عَلِمَتْ نَفْسٌ مَّا قَدَّمَتْ وَأَخَّرَتْ","turkishText":"Her kişi önceden ne yaptığını ve ne bıraktığını bilir."},
    {"number":6,"numberInSurah":6,"text":"يَا أَيُّهَا الْإِنسَانُ مَا غَرَّكَ بِرَبِّكَ الْكَرِيمِ","turkishText":"Ey insan! Seni o cömert Rabbine karşı aldatan nedir?"},
    {"number":7,"numberInSurah":7,"text":"الَّذِي خَلَقَكَ فَسَوَّاكَ فَعَدَلَكَ","turkishText":"O seni yarattı, şekillendirdi, düzgün bir biçime koydu."},
    {"number":8,"numberInSurah":8,"text":"فِي أَيِّ صُورَةٍ مَّا شَاءَ رَكَّبَكَ","turkishText":"Dilediği biçimde seni oluşturdu."},
    {"number":9,"numberInSurah":9,"text":"كَلَّا بَلْ تُكَذِّبُونَ بِالدِّينِ","turkishText":"Hayır! Siz ceza gününü yalanlıyorsunuz."},
    {"number":10,"numberInSurah":10,"text":"وَإِنَّ عَلَيْكُمْ لَحَافِظِينَ","turkishText":"Oysa üzerinizde gözetleyiciler vardır."},
    {"number":11,"numberInSurah":11,"text":"كِرَامًا كَاتِبِينَ","turkishText":"Değerli, yazan melekler."},
    {"number":12,"numberInSurah":12,"text":"يَعْلَمُونَ مَا تَفْعَلُونَ","turkishText":"Yaptıklarınızı bilirler."},
    {"number":13,"numberInSurah":13,"text":"إِنَّ الْأَبْرَارَ لَفِي نَعِيمٍ","turkishText":"Şüphesiz iyiler nimettedir."},
    {"number":14,"numberInSurah":14,"text":"وَإِنَّ الْفُجَّارَ لَفِي جَحِيمٍ","turkishText":"Şüphesiz günahkârlar cehennemdedir."},
    {"number":15,"numberInSurah":15,"text":"يَصْلَوْنَهَا يَوْمَ الدِّينِ","turkishText":"Ceza günü oraya girecekler."},
    {"number":16,"numberInSurah":16,"text":"وَمَا هُمْ عَنْهَا بِغَائِبِينَ","turkishText":"Onlar oradan ayrılamazlar."},
    {"number":17,"numberInSurah":17,"text":"وَمَا أَدْرَاكَ مَا يَوْمُ الدِّينِ","turkishText":"Ceza gününün ne olduğunu sana ne bildirdi?"},
    {"number":18,"numberInSurah":18,"text":"ثُمَّ مَا أَدْرَاكَ مَا يَوْمُ الدِّينِ","turkishText":"Yine, ceza gününün ne olduğunu sana ne bildirdi?"},
    {"number":19,"numberInSurah":19,"text":"يَوْمَ لَا تَمْلِكُ نَفْسٌ لِّنَفْسٍ شَيْئًا ۖ وَالْأَمْرُ يَوْمَئِذٍ لِّلَّهِ","turkishText":"O gün kimse kimseye hiçbir şey yapamaz. O gün emir, Allah'ındır."}
  ]
})

print("Batch 1 complete (78-82)")
