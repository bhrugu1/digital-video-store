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
        // Initialize with your existing media data from db.json
        if (mediaRepository.count() == 0) {
            System.out.println("Initializing database with sample media data...");
            
            // Add your 24 media items
            mediaRepository.save(new Media("Inception", "Movie", 
                "A thief who steals corporate secrets through use of dream-sharing technology.",
                "https://m.media-amazon.com/images/I/51v5ZpFyaFL._AC_.jpg",
                "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SL1500_.jpg",
                3.99, 12.99));
                
            mediaRepository.save(new Media("The Matrix", "Movie",
                "A computer hacker learns about the true nature of his reality.",
                "https://m.media-amazon.com/images/I/51EG732BV3L.jpg",
                "https://m.media-amazon.com/images/I/71o7FpvfXBL._AC_SL1024_.jpg",
                3.49, 11.99));
                
            mediaRepository.save(new Media("Breaking Bad", "TV Show",
                "A chemistry teacher turned methamphetamine producer.",
                "https://m.media-amazon.com/images/I/81p+xe8cbnL._AC_SY679_.jpg",
                "https://m.media-amazon.com/images/I/91nPxhU3bNL._AC_SL1500_.jpg",
                2.49, 9.99));
                
            mediaRepository.save(new Media("The Dark Knight", "Movie",
                "Batman battles the Joker in Gotham's darkest hour.",
                "https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                "https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                3.99, 13.99));
                
            mediaRepository.save(new Media("Game of Thrones", "TV Show",
                "Nine noble families fight for control over the lands of Westeros.",
                "https://image.tmdb.org/t/p/original/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "https://image.tmdb.org/t/p/original/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                3.49, 14.49));
                
            mediaRepository.save(new Media("Black Panther", "Movie",
                "T'Challa, heir to the hidden but advanced kingdom of Wakanda, must step forward as king.",
                "https://image.tmdb.org/t/p/original/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                "https://image.tmdb.org/t/p/original/uxzzxijgPIY7slzFvMotPv8wjKA.jpg",
                3.75, 12.99));
                
            mediaRepository.save(new Media("The Office", "TV Show",
                "A mockumentary sitcom about office life at Dunder Mifflin.",
                "https://image.tmdb.org/t/p/original/qWnJzyZhyy74gjpSjIXWmuk0ifX.jpg",
                "https://image.tmdb.org/t/p/original/qWnJzyZhyy74gjpSjIXWmuk0ifX.jpg",
                2.99, 10.49));
                
            // Add more items as needed...
            mediaRepository.save(new Media("Avatar: The Way of Water", "Movie",
                "Jake Sully and his family return to Pandora in this spectacular sequel to the groundbreaking original film.",
                "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                "https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg",
                5.99, 19.99));
                
            System.out.println("Sample data initialized successfully!");
        } else {
            System.out.println("Database already contains data. Skipping initialization.");
        }
    }
}
