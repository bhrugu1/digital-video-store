package com.bhrugu.api.restapi.config;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private MediaRepository mediaRepository;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            // Initialize with your original db.json media data
            if (mediaRepository.count() == 0) {
                System.out.println("Initializing database with complete db.json media data...");
                
                // Original data from db.json - all 20+ items with featured flags
                mediaRepository.save(new Media("Inception", "Movie", 
                    "A thief who steals corporate secrets through use of dream-sharing technology.",
                    "https://m.media-amazon.com/images/I/51v5ZpFyaFL._AC_.jpg",
                    "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SL1500_.jpg",
                    3.99, 12.99, true)); // ⭐ Featured movie
                    
                mediaRepository.save(new Media("The Matrix", "Movie",
                    "A computer hacker learns about the true nature of his reality.",
                    "https://m.media-amazon.com/images/I/51EG732BV3L.jpg",
                    "https://m.media-amazon.com/images/I/71o7FpvfXBL._AC_SL1024_.jpg",
                    3.49, 11.99, true)); // ⭐ Featured movie
                
                mediaRepository.save(new Media("Breaking Bad", "TV Show",
                    "A chemistry teacher turned methamphetamine producer.",
                    "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SY679_.jpg",
                    "https://m.media-amazon.com/images/I/91nPxhU3bNL._AC_SL1500_.jpg",
                    2.49, 9.99, true)); // ⭐ Featured TV show
                
                mediaRepository.save(new Media("The Dark Knight", "Movie",
                    "Batman battles the Joker in Gotham's darkest hour.",
                    "https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                    "https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                    3.99, 13.99, true)); // ⭐ Featured movie
                
                mediaRepository.save(new Media("Game of Thrones", "TV Show",
                    "Nine noble families fight for control over the lands of Westeros.",
                    "https://image.tmdb.org/t/p/original/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                    "https://image.tmdb.org/t/p/original/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                    3.49, 14.49, true)); // ⭐ Featured TV show
                
                mediaRepository.save(new Media("Black Panther", "Movie",
                    "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward as king.",
                    "https://image.tmdb.org/t/p/original/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                    "https://image.tmdb.org/t/p/original/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                    3.75, 12.99, false)); // Not featured
                
                mediaRepository.save(new Media("The Office", "TV Show",
                    "A mockumentary sitcom about office life at Dunder Mifflin.",
                    "https://image.tmdb.org/t/p/original/qWnJzyZhyy74gjpSjIXWmuk0ifX.jpg",
                    "https://image.tmdb.org/t/p/original/qWnJzyZhyy74gjpSjIXWmuk0ifX.jpg",
                    2.99, 10.49, false)); // Not featured
                
                mediaRepository.save(new Media("Avatar: The Way of Water", "Movie",
                    "Jake Sully and his family return to Pandora in this spectacular sequel to the groundbreaking original film.",
                    "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                    "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                    5.99, 19.99, false)); // Not featured
                
                // Additional movies from original db.json
                mediaRepository.save(new Media("Top Gun: Maverick", "Movie",
                    "After thirty years, Maverick is still pushing the envelope as a top naval aviator, training a new generation of pilots.",
                    "https://image.tmdb.org/t/p/original/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
                    "https://image.tmdb.org/t/p/original/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
                    4.99, 16.99, false));
                
                mediaRepository.save(new Media("Spider-Man: No Way Home", "Movie",
                    "Peter Parker's secret identity is revealed, forcing him to ask Doctor Strange for help, unleashing the multiverse.",
                    "https://image.tmdb.org/t/p/original/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                    "https://image.tmdb.org/t/p/original/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                    4.99, 15.99, false));
                
                mediaRepository.save(new Media("Dune", "Movie",
                    "Paul Atreides leads nomadic tribes in a revolt against the galactic emperor and his father's evil nemesis.",
                    "https://image.tmdb.org/t/p/original/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                    "https://image.tmdb.org/t/p/original/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                    4.49, 14.99, false));
                
                mediaRepository.save(new Media("The Batman", "Movie",
                    "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues.",
                    "https://image.tmdb.org/t/p/original/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                    "https://image.tmdb.org/t/p/original/b0PlSFdDwbyK0cf5RxwDpaOJQvQ.jpg",
                    4.99, 16.99, false));
                
                mediaRepository.save(new Media("Encanto", "Movie",
                    "A Colombian teenage girl has to face the frustration of being the only member of her family without magical powers.",
                    "https://image.tmdb.org/t/p/original/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                    "https://image.tmdb.org/t/p/original/4j0PNHkMr5ax3IA8tjtxcmPU3QT.jpg",
                    3.99, 12.99, false));
                
                // Additional TV Shows from original db.json
                mediaRepository.save(new Media("Wednesday", "TV Show",
                    "Wednesday Addams' years as a student, when she attempts to master her emerging psychic ability and solve a mystery.",
                    "https://image.tmdb.org/t/p/original/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
                    "https://image.tmdb.org/t/p/original/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
                    3.49, 11.99, false));
                
                mediaRepository.save(new Media("House of the Dragon", "TV Show",
                    "The Targaryen civil war, set 200 years before the events of Game of Thrones.",
                    "https://image.tmdb.org/t/p/original/7QMsOTMUswlwxJP0rTTZfmz2tX2.jpg",
                    "https://image.tmdb.org/t/p/original/7QMsOTMUswlwxJP0rTTZfmz2tX2.jpg",
                    3.99, 13.99, false));
                
                mediaRepository.save(new Media("Stranger Things", "TV Show",
                    "When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one strange little girl.",
                    "https://image.tmdb.org/t/p/original/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
                    "https://image.tmdb.org/t/p/original/49WJfeN0moxb9IPfGn8AIqMGskD.jpg",
                    2.99, 10.99, false));
                
                mediaRepository.save(new Media("The Witcher", "TV Show",
                    "Geralt of Rivia, a mutated monster-hunter for hire, journeys toward his destiny in a turbulent world.",
                    "https://image.tmdb.org/t/p/original/cZ0d3rtvXPVvuiX22sP79K3Hmjz.jpg",
                    "https://image.tmdb.org/t/p/original/cZ0d3rtvXPVvuiX22sP79K3Hmjz.jpg",
                    3.29, 12.49, false));
                
                mediaRepository.save(new Media("Euphoria", "TV Show",
                    "A group of high school students navigate love and friendships in a world of drugs, sex, trauma and hope.",
                    "https://image.tmdb.org/t/p/original/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                    "https://image.tmdb.org/t/p/original/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                    3.49, 12.99, false));
                
                mediaRepository.save(new Media("Ozark", "TV Show",
                    "A financial advisor drags his family from Chicago to the Missouri Ozarks, where he must launder money to appease a drug boss.",
                    "https://image.tmdb.org/t/p/original/m73QYiIBxhHrrHSLvHe4vitBcEc.jpg",
                    "https://image.tmdb.org/t/p/original/m73QYiIBxhHrrHSLvHe4vitBcEc.jpg",
                    2.99, 11.49, false));
                
                System.out.println("✅ Complete db.json data initialized successfully!");
                System.out.println("📊 Total media items: " + mediaRepository.count());
                System.out.println("🌟 Featured items: 5 (2 movies + 2 TV shows + 1 extra featured)");
            } else {
                System.out.println("📊 Database already contains " + mediaRepository.count() + " media items");
            }
        } catch (Exception e) {
            System.out.println("❌ MongoDB connection failed. Skipping data initialization: " + e.getMessage());
        }
    }
}
