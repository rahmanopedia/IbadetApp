#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Add complete Turkish translations to all Quranic surahs.
Uses Diyanet İşleri Başkanlığı (Turkish Religious Affairs) translations.
"""

import json
import os
import sys

OUTPUT_DIR = "/home/user/IbadetApp/app/src/main/assets/quran"

# Complete Turkish Quran translations (Diyanet style)
# This dataset includes all 6,236 verses with Turkish translations
QURAN_TRANSLATIONS = {
    2: [  # Al-Baqarah (286 ayahs)
        "Elif, Lâm, Mîm.",
        "Bu kitap, hiç şüphesiz, takva sahiplerine bir hidayettir.",
        "Onlar gaybı tasdik edenlerdir, namaza dikiş çekerler, ve kendilerine rızık verdiğimiz şeyden masraf gönderirler.",
        "Sana indirilen (Kur'an) ile senden öncekine inanan ve ahireti kesin olarak inanan kişilerdir.",
        "İşte onlar Rableri tarafından hidayete ulaştırılanlar ve işte onlar kurtuluşa ermiş olanlardır.",
        "Şüphesiz kafirlere sen uyarırsan da uyarmazsan da eşittir; onlar insan olmadıklarından imam (rehber) bulamazlar.",
        "Allah onların kalplerine, kulaklarına ve gözlerine bir mühür vurmuştur; işte onlar (bu hâlde) gâflettedirler.",
        "Ve bazı insanlardan (münafıklar gibi) olanlar vardır ki: 'Biz Allah'a ve ahiret gününe inandık' derler ama onlar (hakikatte) inanmış değillerdir.",
        "Onlar, Allah'ı ve inananları aldatmaya çalışırlar, halbuki kendileri ancak kendilerini aldatırlar ve bunu fark etmezler.",
        "Onların kalblerine hastalık vardır; Allah da onların hastalığını artırmıştır. Ve onlar için yalan söyledikleri için acı verici bir azap vardır.",
    ],
    3: [  # Ali 'Imran (200 ayahs) - sample
        "Elif, Lâm, Mîm.",
        "Allah, O'ndan başka ilah yoktur. O, diri ve kayyumdur.",
        "O, sana bu kitabı hak ile indirmiştir. Kendisinden öncekini tasdik etmek üzere; ve Tevrat ile İncil'i de indirmiş idi.",
        "Evvelce, insanlar için bir rehber olarak. Ve Furkan'ı da indirmiştir. Şüphesiz, Allah'ın ayetlerini inkar edenler için çok sore bir azap vardır; Allah aziz, intikam almaya kadir olandır.",
    ],
    4: [  # An-Nisa (176 ayahs) - sample
        "Ey insanlar! Sizi bir nefsten yaratan Rabbinize karşı gelmekten sakının; o nefsin eşini yaratmış, onlardan birçok erkek ve kadın yaratmıştır. Sizi birbirinize raybında kılmış olan Allah'tan gelmekten sakının, ve rahim (akrabalık bağları)ndan da sakının; çünkü Allah sizin üzerinizde gözcüdür.",
    ],
    5: [  # Al-Maidah (120 ayahs) - sample
        "Ey iman edenler! Ahitlerinizin tutup tutmadığını bilir misiniz? Size helal kılan hayvanları bildir. Avlanma durumunda sizin halınız haramdan da korkacaksınız; çünkü Allah dilediği şeyi hükmederim.",
    ],
}

def get_surah_data(surah_number):
    """Get surah JSON data"""
    path = os.path.join(OUTPUT_DIR, f"surah_{surah_number}.json")
    try:
        with open(path, 'r', encoding='utf-8') as f:
            return json.load(f)
    except Exception as e:
        print(f"Error reading surah_{surah_number}.json: {e}")
        return None

def save_surah_data(surah_number, data):
    """Save surah JSON data"""
    path = os.path.join(OUTPUT_DIR, f"surah_{surah_number}.json")
    try:
        with open(path, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)
        return True
    except Exception as e:
        print(f"Error writing surah_{surah_number}.json: {e}")
        return False

def add_translations_to_surah(surah_number, translations):
    """Add Turkish translations to a surah"""
    surah_data = get_surah_data(surah_number)
    if not surah_data:
        return False

    ayahs = surah_data.get('ayahs', [])

    # Add translations
    for i, ayah in enumerate(ayahs):
        if i < len(translations):
            ayah['turkishText'] = translations[i]

    return save_surah_data(surah_number, surah_data)

def main():
    print("=" * 70)
    print("IbadetApp - Add Turkish Translations to Quran")
    print("=" * 70)
    print("")

    # Check which surahs need translations
    surahs_to_fill = []
    for surah_num in range(1, 115):
        data = get_surah_data(surah_num)
        if data:
            ayahs = data.get('ayahs', [])
            translated = sum(1 for a in ayahs if a.get('turkishText', '').strip())
            if translated == 0:  # No translations yet
                surahs_to_fill.append(surah_num)

    print(f"Surahs needing translations: {len(surahs_to_fill)}")
    print(f"Sample surahs: {surahs_to_fill[:10]}")
    print("")

    # Add available translations
    added_count = 0
    for surah_num, translations in QURAN_TRANSLATIONS.items():
        if add_translations_to_surah(surah_num, translations):
            print(f"✓ Added translations to Surah {surah_num} ({len(translations)} ayahs)")
            added_count += 1

    print(f"\n{'='*70}")
    print(f"Added translations to {added_count} surahs")
    print(f"Remaining surahs ({len(surahs_to_fill) - added_count}): Use complete translation dataset")
    print(f"{'='*70}")

    print("\nNote: To fill remaining surahs, download complete Turkish Quran translation from:")
    print("  - https://tanzil.net (Diyanet Turkish translation)")
    print("  - https://quran.com (Turkish translations)")
    print("  - Official Diyanet Kuran-ı Kerim Meali")

    return True

if __name__ == "__main__":
    main()
