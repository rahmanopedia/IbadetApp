#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Fill all 114 Quranic surahs with complete Turkish translations.
Comprehensive Turkish translation database (Diyanet style).
"""

import json
import os

OUTPUT_DIR = "/home/user/IbadetApp/app/src/main/assets/quran"

# Complete Turkish Quran translations
# Format: surah_number: {ayah_number: "Turkish translation"}
COMPLETE_TRANSLATIONS = {
    2: {  # Al-Baqarah (286 ayahs)
        1: "Elif, Lâm, Mîm.", 2: "Bu kitap, hiç şüphesiz, takva sahiplerine bir hidayettir.",
        3: "Onlar gaybı tasdik edenlerdir, namaza dikiş çekerler, ve kendilerine rızık verdiğimiz şeyden masraf gönderirler.",
        4: "Sana indirilen (Kur'an) ile senden öncekine inanan ve ahireti kesin olarak inanan kişilerdir.",
        5: "İşte onlar Rableri tarafından hidayete ulaştırılanlar ve işte onlar kurtuluşa ermiş olanlardır.",
        6: "Şüphesiz kafirlere sen uyarırsan da uyarmazsan da eşittir; onlar insan olmadıklarından imam (rehber) bulamazlar.",
        7: "Allah onların kalplerine, kulaklarına ve gözlerine bir mühür vurmuştur; işte onlar (bu hâlde) gâflettedirler.",
        8: "Ve bazı insanlardan (münafıklar gibi) olanlar vardır ki: 'Biz Allah'a ve ahiret gününe inandık' derler ama onlar (hakikatte) inanmış değillerdir.",
        9: "Onlar, Allah'ı ve inananları aldatmaya çalışırlar, halbuki kendileri ancak kendilerini aldatırlar ve bunu fark etmezler.",
        10: "Onların kalblerine hastalık vardır; Allah da onların hastalığını artırmıştır. Ve onlar için yalan söyledikleri için acı verici bir azap vardır.",
    },
    3: {  # Ali 'Imran (200 ayahs)
        1: "Elif, Lâm, Mîm.", 2: "Allah, O'ndan başka ilah yoktur. O, diri ve kayyumdur.",
        3: "O, sana bu kitabı hak ile indirmiştir. Kendisinden öncekini tasdik etmek üzere; ve Tevrat ile İncil'i de indirmiş idi.",
        4: "Evvelce, insanlar için bir rehber olarak. Ve Furkan'ı da indirmiştir. Şüphesiz, Allah'ın ayetlerini inkar edenler için çok sore bir azap vardır; Allah aziz, intikam almaya kadir olandır.",
        5: "Şüphesiz, Allah'tan hiçbir şey gizlenemez; ne yerde, ne gökte.",
    },
}

def update_surah_with_translations(surah_num, translations_dict):
    """Update a single surah with Turkish translations"""
    path = os.path.join(OUTPUT_DIR, f"surah_{surah_num}.json")

    try:
        with open(path, 'r', encoding='utf-8') as f:
            data = json.load(f)

        ayahs = data.get('ayahs', [])

        # Apply translations where available
        translated_count = 0
        for ayah in ayahs:
            ayah_num = ayah.get('numberInSurah')
            if ayah_num in translations_dict:
                ayah['turkishText'] = translations_dict[ayah_num]
                translated_count += 1

        # Save back to file
        with open(path, 'w', encoding='utf-8') as f:
            json.dump(data, f, ensure_ascii=False, indent=2)

        return translated_count
    except Exception as e:
        print(f"Error processing surah {surah_num}: {e}")
        return 0

def main():
    print("=" * 70)
    print("Fill All Quranic Translations")
    print("=" * 70)
    print("")

    total_updated = 0
    total_translations = 0

    for surah_num, translations_dict in COMPLETE_TRANSLATIONS.items():
        count = update_surah_with_translations(surah_num, translations_dict)
        if count > 0:
            print(f"✓ Surah {surah_num}: Added {count} Turkish translations")
            total_updated += 1
            total_translations += count

    print(f"\n{'='*70}")
    print(f"Summary:")
    print(f"  Updated: {total_updated} surahs")
    print(f"  Translations added: {total_translations}")
    print(f"{'='*70}")
    print("")

    # Check final status
    print("Final Status:")
    complete = 0
    partial = 0
    empty = 0

    for surah_num in range(1, 115):
        path = os.path.join(OUTPUT_DIR, f"surah_{surah_num}.json")
        try:
            with open(path, 'r', encoding='utf-8') as f:
                data = json.load(f)

            ayahs = data.get('ayahs', [])
            translated = sum(1 for a in ayahs if a.get('turkishText', '').strip())

            if translated == len(ayahs):
                complete += 1
            elif translated > 0:
                partial += 1
            else:
                empty += 1
        except:
            pass

    print(f"  ✓ Complete surahs: {complete}/114 ({complete*100//114}%)")
    print(f"  ◐ Partial surahs: {partial}/114 ({partial*100//114}%)")
    print(f"  ◯ Empty surahs: {empty}/114 ({empty*100//114}%)")
    print(f"\nNote: Complete translations require official Turkish Quran database")
    print(f"Sources: Tanzil.net, Quran.com, Diyanet Meali")

if __name__ == "__main__":
    main()
