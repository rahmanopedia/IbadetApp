#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Generate all 114 Quranic surahs for the IbadetApp.
Downloads Quranic text and creates JSON files for all missing surahs.
"""

import json
import urllib.request
import os
import sys

OUTPUT_DIR = "/home/user/IbadetApp/app/src/main/assets/quran"

def download_quran_data():
    """Download complete Quran data from GitHub"""
    print("Downloading Quran dataset...")
    try:
        url = "https://raw.githubusercontent.com/risan/quran-json/master/dist/quran.json"
        with urllib.request.urlopen(url, timeout=30) as response:
            data = json.loads(response.read().decode('utf-8'))
        print(f"✓ Successfully downloaded {len(data)} surahs")
        return data
    except Exception as e:
        print(f"✗ Error downloading Quran data: {e}")
        return None

def get_surah_metadata():
    """Get surah metadata from the app's surah_list.json"""
    list_path = os.path.join(OUTPUT_DIR, "surah_list.json")
    try:
        with open(list_path, 'r', encoding='utf-8') as f:
            metadata = json.load(f)
        return {s['number']: s for s in metadata}
    except Exception as e:
        print(f"✗ Error reading surah_list.json: {e}")
        return {}

def transform_surah_data(surah_from_api, metadata):
    """Transform API data to app format"""
    surah_num = surah_from_api['id']

    if surah_num not in metadata:
        return None

    meta = metadata[surah_num]

    return {
        "number": surah_num,
        "name": surah_from_api['name'],
        "englishName": surah_from_api.get('transliteration', meta.get('englishName', '')),
        "turkishName": meta.get('turkishName', ''),
        "numberOfAyahs": surah_from_api.get('total_verses', len(surah_from_api.get('verses', []))),
        "revelationType": meta.get('revelationType', 'Unknown'),
        "ayahs": [
            {
                "number": i + (surah_num - 1) * 1000 + verse['id'],  # Approximate global number
                "numberInSurah": verse['id'],
                "text": verse['text'],
                "turkishText": ""  # Will be added later
            }
            for i, verse in enumerate(surah_from_api.get('verses', []))
        ]
    }

def check_existing_surahs():
    """Check which surahs already have detailed files"""
    existing = set()
    for i in range(1, 115):
        path = os.path.join(OUTPUT_DIR, f"surah_{i}.json")
        if os.path.exists(path):
            existing.add(i)
    return existing

def write_surah_file(surah_data):
    """Write surah JSON file"""
    if not surah_data:
        return False

    path = os.path.join(OUTPUT_DIR, f"surah_{surah_data['number']}.json")
    try:
        with open(path, 'w', encoding='utf-8') as f:
            json.dump(surah_data, f, ensure_ascii=False, indent=2)
        return True
    except Exception as e:
        print(f"Error writing surah_{surah_data['number']}.json: {e}")
        return False

def main():
    print("=" * 60)
    print("IbadetApp - Quranic Data Generator")
    print("=" * 60)

    # Check existing files
    existing = check_existing_surahs()
    print(f"\nExisting detailed surah files: {len(existing)} surahs")
    if existing:
        existing_list = sorted(list(existing))
        print(f"  Surahs: {existing_list[:10]}{'...' if len(existing_list) > 10 else ''}")

    # Download Quran data
    quran_data = download_quran_data()
    if not quran_data:
        print("✗ Failed to download Quran data. Aborting.")
        return False

    # Get metadata
    metadata = get_surah_metadata()
    if not metadata:
        print("✗ Failed to read surah metadata. Aborting.")
        return False

    # Generate missing surahs
    print("\nGenerating missing surahs...")
    generated = 0
    skipped = 0

    for surah_api in quran_data:
        surah_num = surah_api['id']

        if surah_num in existing:
            print(f"  ⊘ Skipping surah {surah_num} (already exists)")
            skipped += 1
            continue

        # Transform and write
        surah_data = transform_surah_data(surah_api, metadata)
        if surah_data:
            if write_surah_file(surah_data):
                print(f"  ✓ Generated surah {surah_num}: {surah_api.get('transliteration', 'Unknown')} ({len(surah_api.get('verses', []))} ayahs)")
                generated += 1

    print(f"\n{'='*60}")
    print(f"Summary:")
    print(f"  Generated: {generated} new surahs")
    print(f"  Skipped: {skipped} existing surahs")
    print(f"  Total: {generated + skipped}/114 surahs")
    print(f"{'='*60}")

    if generated + skipped == 114:
        print("\n✓ All 114 surahs are now complete!")
        return True
    else:
        print(f"\n⚠ Only {generated + skipped}/114 surahs generated. {114 - (generated + skipped)} still missing.")
        return False

if __name__ == "__main__":
    success = main()
    sys.exit(0 if success else 1)
