import requests
import json
from collections import defaultdict

# API base URL
BASE_URL = "http://localhost:8080/api"

def get_all_media():
    """Fetch all media from the database"""
    try:
        movies_response = requests.get(f"{BASE_URL}/movies")
        tvshows_response = requests.get(f"{BASE_URL}/tvshows")
        
        movies_result = movies_response.json()
        tvshows_result = tvshows_response.json()
        
        movies = movies_result.get('data', []) if movies_result.get('success') else []
        tvshows = tvshows_result.get('data', []) if tvshows_result.get('success') else []
        
        return movies + tvshows
    except Exception as e:
        print(f"❌ Error fetching media: {e}")
        return []

def find_duplicates(media_list):
    """Find duplicate entries based on title and type"""
    title_groups = defaultdict(list)
    
    for media in media_list:
        # Group by title (case-insensitive) and type
        key = (media['title'].lower().strip(), media['type'])
        title_groups[key].append(media)
    
    duplicates = []
    unique_media = []
    
    for (title, media_type), items in title_groups.items():
        if len(items) > 1:
            # Sort by ID to keep the first one (usually original)
            items.sort(key=lambda x: x['id'])
            unique_media.append(items[0])  # Keep the first one
            duplicates.extend(items[1:])   # Mark rest as duplicates
            print(f"🔍 Found {len(items)} duplicates of '{items[0]['title']}' ({media_type})")
        else:
            unique_media.append(items[0])
    
    return duplicates, unique_media

def delete_media(media_id):
    """Delete a media item by ID"""
    try:
        response = requests.delete(f"{BASE_URL}/media/{media_id}")
        return response.status_code == 200
    except Exception as e:
        print(f"❌ Error deleting media {media_id}: {e}")
        return False

def main():
    print("🧹 Digital Video Store - Duplicate Removal Tool")
    print("=" * 60)
    
    # Test API connection
    try:
        test_response = requests.get(f"{BASE_URL}/movies")
        if test_response.status_code != 200:
            print("❌ API is not accessible. Make sure the Spring Boot application is running.")
            return
    except requests.exceptions.RequestException:
        print("❌ Cannot connect to API. Make sure the Spring Boot application is running.")
        return
    
    # Get all media
    print("📥 Fetching all media from database...")
    all_media = get_all_media()
    print(f"📊 Total media items found: {len(all_media)}")
    
    if not all_media:
        print("❌ No media found in database")
        return
    
    # Find duplicates
    print("\n🔍 Analyzing for duplicates...")
    duplicates, unique_media = find_duplicates(all_media)
    
    print(f"\n📊 Analysis Results:")
    print(f"   • Total items: {len(all_media)}")
    print(f"   • Unique items: {len(unique_media)}")
    print(f"   • Duplicates to remove: {len(duplicates)}")
    
    if not duplicates:
        print("✅ No duplicates found! Your database is clean.")
        return
    
    # Show duplicates to be removed
    print(f"\n🗑️  Duplicates to be removed:")
    movies_to_remove = [d for d in duplicates if d['type'] == 'Movie']
    tvshows_to_remove = [d for d in duplicates if d['type'] == 'TV Show']
    
    if movies_to_remove:
        print(f"\n📽️  Movies ({len(movies_to_remove)}):")
        for movie in movies_to_remove:
            print(f"   • {movie['title']} (ID: {movie['id']})")
    
    if tvshows_to_remove:
        print(f"\n📺 TV Shows ({len(tvshows_to_remove)}):")
        for show in tvshows_to_remove:
            print(f"   • {show['title']} (ID: {show['id']})")
    
    # Confirm deletion
    print(f"\n⚠️  This will remove {len(duplicates)} duplicate entries.")
    confirm = input("Do you want to proceed? (yes/no): ").lower().strip()
    
    if confirm not in ['yes', 'y']:
        print("❌ Operation cancelled.")
        return
    
    # Remove duplicates
    print(f"\n🗑️  Removing duplicates...")
    removed_count = 0
    
    for duplicate in duplicates:
        if delete_media(duplicate['id']):
            print(f"✅ Removed: {duplicate['title']} ({duplicate['type']}) - ID: {duplicate['id']}")
            removed_count += 1
        else:
            print(f"❌ Failed to remove: {duplicate['title']} - ID: {duplicate['id']}")
    
    print(f"\n" + "=" * 60)
    print(f"🎉 Duplicate removal complete!")
    print(f"   ✅ Successfully removed: {removed_count}/{len(duplicates)} duplicates")
    print(f"   📊 Remaining unique items: {len(unique_media)}")
    
    # Show final summary
    remaining_movies = len([m for m in unique_media if m['type'] == 'Movie'])
    remaining_tvshows = len([m for m in unique_media if m['type'] == 'TV Show'])
    
    print(f"\n📊 Final Database Summary:")
    print(f"   📽️  Movies: {remaining_movies}")
    print(f"   📺 TV Shows: {remaining_tvshows}")
    print(f"   🎯 Total: {len(unique_media)}")
    
    print(f"\n🌐 You can now view the clean database at:")
    print(f"   • Frontend: http://localhost:3000")
    print(f"   • API Movies: http://localhost:8080/api/movies")
    print(f"   • API TV Shows: http://localhost:8080/api/tvshows")

if __name__ == "__main__":
    main()
