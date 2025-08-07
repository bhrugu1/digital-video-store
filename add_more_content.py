import requests
import json

# API base URL
BASE_URL = "http://localhost:8080/api"

# New movies to add
movies = [
    {
        "title": "The Dark Knight",
        "type": "Movie",
        "synopsis": "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
        "poster": "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        "rent": 4.99,
        "buy": 14.99,
        "featured": True
    },
    {
        "title": "Inception",
        "type": "Movie",
        "synopsis": "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
        "poster": "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
        "rent": 3.99,
        "buy": 12.99,
        "featured": True
    },
    {
        "title": "Interstellar",
        "type": "Movie",
        "synopsis": "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
        "poster": "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
        "rent": 4.99,
        "buy": 14.99,
        "featured": False
    },
    {
        "title": "The Matrix",
        "type": "Movie",
        "synopsis": "A computer programmer is led to fight an underground war against powerful computers who have constructed his entire reality with a system called the Matrix.",
        "poster": "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
        "rent": 3.99,
        "buy": 11.99,
        "featured": False
    },
    {
        "title": "Pulp Fiction",
        "type": "Movie",
        "synopsis": "The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.",
        "poster": "https://image.tmdb.org/t/p/w500/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
        "rent": 3.99,
        "buy": 12.99,
        "featured": False
    },
    {
        "title": "Avatar",
        "type": "Movie",
        "synopsis": "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
        "poster": "https://image.tmdb.org/t/p/w500/6EiRUJpuoeQPghrs3YNktKky3EY.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/6EiRUJpuoeQPghrs3YNktKky3EY.jpg",
        "rent": 4.99,
        "buy": 16.99,
        "featured": True
    },
    {
        "title": "Top Gun: Maverick",
        "type": "Movie",
        "synopsis": "After thirty years, Maverick is still pushing the envelope as a top naval aviator, but must confront ghosts of his past when he leads TOP GUN's elite graduates on a mission that demands the ultimate sacrifice from those chosen to fly it.",
        "poster": "https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
        "rent": 5.99,
        "buy": 19.99,
        "featured": True
    },
    {
        "title": "Spider-Man: No Way Home",
        "type": "Movie",
        "synopsis": "With Spider-Man's identity now revealed, Peter asks Doctor Strange for help. When a spell goes wrong, dangerous foes from other worlds start to appear, forcing Peter to discover what it truly means to be Spider-Man.",
        "poster": "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
        "rent": 5.99,
        "buy": 19.99,
        "featured": False
    }
]

# New TV shows to add
tv_shows = [
    {
        "title": "Breaking Bad",
        "type": "TV Show",
        "synopsis": "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
        "poster": "https://image.tmdb.org/t/p/w500/ggFHVNu6YYI5L9pCfOacjizRGt.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/ggFHVNu6YYI5L9pCfOacjizRGt.jpg",
        "rent": 2.99,
        "buy": 29.99,
        "featured": True
    },
    {
        "title": "Game of Thrones",
        "type": "TV Show",
        "synopsis": "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
        "poster": "https://image.tmdb.org/t/p/w500/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
        "rent": 2.99,
        "buy": 39.99,
        "featured": True
    },
    {
        "title": "The Office",
        "type": "TV Show",
        "synopsis": "A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.",
        "poster": "https://image.tmdb.org/t/p/w500/7DJKHzAi83BmQrWLrYYOqcoKfhR.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/7DJKHzAi83BmQrWLrYYOqcoKfhR.jpg",
        "rent": 1.99,
        "buy": 24.99,
        "featured": False
    },
    {
        "title": "Friends",
        "type": "TV Show",
        "synopsis": "Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.",
        "poster": "https://image.tmdb.org/t/p/w500/f496cm9enuEsZkSPzCwnTESEK5s.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/f496cm9enuEsZkSPzCwnTESEK5s.jpg",
        "rent": 1.99,
        "buy": 29.99,
        "featured": True
    },
    {
        "title": "Stranger Things",
        "type": "TV Show",
        "synopsis": "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces, and one strange little girl.",
        "poster": "https://image.tmdb.org/t/p/w500/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
        "rent": 2.99,
        "buy": 24.99,
        "featured": True
    },
    {
        "title": "The Crown",
        "type": "TV Show",
        "synopsis": "Follows the political rivalries and romance of Queen Elizabeth II's reign and the events that shaped the second half of the twentieth century.",
        "poster": "https://image.tmdb.org/t/p/w500/1M876KPjulVwppEpldhdc8V4o68.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/1M876KPjulVwppEpldhdc8V4o68.jpg",
        "rent": 2.99,
        "buy": 34.99,
        "featured": False
    },
    {
        "title": "The Mandalorian",
        "type": "TV Show",
        "synopsis": "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
        "poster": "https://image.tmdb.org/t/p/w500/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
        "rent": 3.99,
        "buy": 29.99,
        "featured": True
    },
    {
        "title": "House of Cards",
        "type": "TV Show",
        "synopsis": "A Congressman works with his equally conniving wife to exact revenge on the people who betrayed him.",
        "poster": "https://image.tmdb.org/t/p/w500/hKWxWjFwnMvkWQawbhvC0Y7ygQ8.jpg",
        "posterLarge": "https://image.tmdb.org/t/p/w1280/hKWxWjFwnMvkWQawbhvC0Y7ygQ8.jpg",
        "rent": 2.99,
        "buy": 24.99,
        "featured": False
    }
]

def add_content(content_list, content_type):
    """Add content to the database via API"""
    added_count = 0
    
    for item in content_list:
        try:
            response = requests.post(f"{BASE_URL}/media", json=item)
            
            if response.status_code == 201:
                print(f"✅ Successfully added {content_type}: {item['title']}")
                added_count += 1
            else:
                print(f"❌ Failed to add {content_type}: {item['title']} - Status: {response.status_code}")
                if response.text:
                    print(f"   Error: {response.text}")
                    
        except requests.exceptions.RequestException as e:
            print(f"❌ Network error adding {item['title']}: {e}")
    
    return added_count

if __name__ == "__main__":
    print("🎬 Adding new movies and TV shows to Digital Video Store...")
    print("=" * 60)
    
    # Test if API is accessible
    try:
        test_response = requests.get(f"{BASE_URL}/movies")
        if test_response.status_code != 200:
            print("❌ API is not accessible. Make sure the Spring Boot application is running on port 8080.")
            exit(1)
    except requests.exceptions.RequestException:
        print("❌ Cannot connect to API. Make sure the Spring Boot application is running on port 8080.")
        exit(1)
    
    print("\n📽️  Adding Movies...")
    movies_added = add_content(movies, "Movie")
    
    print("\n📺 Adding TV Shows...")
    tv_shows_added = add_content(tv_shows, "TV Show")
    
    print("\n" + "=" * 60)
    print(f"🎉 Content addition complete!")
    print(f"   📽️  Movies added: {movies_added}/{len(movies)}")
    print(f"   📺 TV Shows added: {tv_shows_added}/{len(tv_shows)}")
    print(f"   🎯 Total content added: {movies_added + tv_shows_added}")
    print("\n🌐 You can now view the updated content at:")
    print(f"   • Movies: http://localhost:3000")
    print(f"   • API Movies: http://localhost:8080/api/movies")
    print(f"   • API TV Shows: http://localhost:8080/api/tvshows")
