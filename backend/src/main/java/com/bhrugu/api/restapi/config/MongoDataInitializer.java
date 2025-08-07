package com.bhrugu.api.restapi.config;

import com.bhrugu.api.restapi.model.Media;
import com.bhrugu.api.restapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * MongoDB Data Initializer
 * Populates the MongoDB Atlas database with sample media data
 * Runs automatically when the application starts
 * 
 * This initializer checks if the database is empty and loads
 * sample movies and TV shows for the digital video store
 */
@Component
public class MongoDataInitializer implements CommandLineRunner {
    
    @Autowired
    private MediaRepository mediaRepository;
    
    /**
     * Initialize MongoDB with sample data if empty
     * @param args Command line arguments (not used)
     */
    @Override
    public void run(String... args) throws Exception {
        
        // Check if database already has data
        if (mediaRepository.count() > 0) {
            System.out.println("MongoDB already contains " + mediaRepository.count() + " media items");
            return;
        }
        
        System.out.println("Initializing MongoDB Atlas with sample media data...");
        
        // Create sample media data
        List<Media> sampleMedia = Arrays.asList(
            
            // Movies
            new Media("The Matrix", "movie", 
                "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
                "https://image.tmdb.org/t/p/w500/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                "https://image.tmdb.org/t/p/w780/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
                3.99, 12.99),
                
            new Media("Inception", "movie",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.",
                "https://image.tmdb.org/t/p/w500/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                "https://image.tmdb.org/t/p/w780/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                4.99, 14.99),
                
            new Media("Interstellar", "movie",
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "https://image.tmdb.org/t/p/w500/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                "https://image.tmdb.org/t/p/w780/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg",
                4.99, 16.99),
                
            new Media("The Dark Knight", "movie",
                "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests.",
                "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                "https://image.tmdb.org/t/p/w780/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                3.99, 13.99),
                
            new Media("Avengers: Endgame", "movie",
                "After the devastating events of Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more.",
                "https://image.tmdb.org/t/p/w500/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                "https://image.tmdb.org/t/p/w780/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
                5.99, 19.99),
                
            new Media("Dune", "movie",
                "Paul Atreides leads nomadic tribes in a revolt against the galactic emperor and his father's evil nemesis when he's thrust into the presidency.",
                "https://image.tmdb.org/t/p/w500/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                "https://image.tmdb.org/t/p/w780/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                5.99, 17.99),
                
            new Media("Spider-Man: No Way Home", "movie",
                "Peter Parker seeks help from Doctor Strange when his secret identity is revealed, but a spell gone wrong brings dangerous foes from other dimensions.",
                "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "https://image.tmdb.org/t/p/w780/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                5.99, 18.99),
                
            new Media("Top Gun: Maverick", "movie",
                "After thirty years, Maverick is still pushing the envelope as a test pilot. When he's called to train a new generation of pilots, he must confront his past.",
                "https://image.tmdb.org/t/p/w500/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
                "https://image.tmdb.org/t/p/w780/62HCnUTziyWcpDaBO2i1DX17ljH.jpg",
                5.99, 16.99),
                
            new Media("Blade Runner 2049", "movie",
                "A young blade runner's discovery of a long-buried secret leads him to track down former blade runner Rick Deckard.",
                "https://image.tmdb.org/t/p/w500/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
                "https://image.tmdb.org/t/p/w780/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg",
                4.99, 15.99),
                
            new Media("Mad Max: Fury Road", "movie",
                "In a post-apocalyptic wasteland, Max teams up with a mysterious woman to try and survive against a warlord and his gang.",
                "https://image.tmdb.org/t/p/w500/hA2ple9q4qnwxp3hKVNhroipsir.jpg",
                "https://image.tmdb.org/t/p/w780/hA2ple9q4qnwxp3hKVNhroipsir.jpg",
                4.99, 14.99),
                
            new Media("John Wick", "movie",
                "An ex-hit-man comes out of retirement to track down the gangsters that took everything from him.",
                "https://image.tmdb.org/t/p/w500/fZPSd91yGE9fCcCe6OoQr6E3Bev.jpg",
                "https://image.tmdb.org/t/p/w780/fZPSd91yGE9fCcCe6OoQr6E3Bev.jpg",
                3.99, 12.99),
                
            new Media("The Mandalorian", "tv-show",
                "After the fall of the Empire, a lone gunfighter makes his way through the outer reaches of the galaxy.",
                "https://image.tmdb.org/t/p/w500/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "https://image.tmdb.org/t/p/w780/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                2.99, 24.99),
                
            // TV Shows
            new Media("House of the Dragon", "tv-show",
                "The Targaryen civil war, known as the Dance of the Dragons, tore apart the Seven Kingdoms 200 years before the events of Game of Thrones.",
                "https://image.tmdb.org/t/p/w500/7QMsOTMUswlwxJP0rTTZfmz2tX2.jpg",
                "https://image.tmdb.org/t/p/w780/7QMsOTMUswlwxJP0rTTZfmz2tX2.jpg",
                3.99, 29.99),
                
            new Media("The Rings of Power", "tv-show",
                "Set thousands of years before the events of The Lord of the Rings, this epic drama follows an ensemble cast of characters.",
                "https://image.tmdb.org/t/p/w500/mYLOqiStMxDK3fYZFirgrMt8z5d.jpg",
                "https://image.tmdb.org/t/p/w780/mYLOqiStMxDK3fYZFirgrMt8z5d.jpg",
                3.99, 34.99),
                
            new Media("Wednesday", "tv-show",
                "Follow Wednesday Addams' years as a student at Nevermore Academy, where she attempts to master her emerging psychic ability.",
                "https://image.tmdb.org/t/p/w500/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
                "https://image.tmdb.org/t/p/w780/9PFonBhy4cQy7Jz20NpMygczOkv.jpg",
                2.99, 19.99),
                
            new Media("The Bear", "tv-show",
                "A young chef from the fine dining world comes home to Chicago to run his family sandwich shop after a heartbreaking death.",
                "https://image.tmdb.org/t/p/w500/sHFlbKS3WLqMnp9t2ghADIJFnuQ.jpg",
                "https://image.tmdb.org/t/p/w780/sHFlbKS3WLqMnp9t2ghADIJFnuQ.jpg",
                2.99, 22.99),
                
            new Media("The Last of Us", "tv-show",
                "Twenty years after a fungal outbreak ravages the planet, survivors Joel and Ellie embark on a dangerous journey across a post-apocalyptic America.",
                "https://image.tmdb.org/t/p/w500/uKvVjHNqB5VmOrdxqAt2F7J78ED.jpg",
                "https://image.tmdb.org/t/p/w780/uKvVjHNqB5VmOrdxqAt2F7J78ED.jpg",
                3.99, 27.99),
                
            new Media("Abbott Elementary", "tv-show",
                "A workplace comedy following teachers in a Philadelphia public elementary school where, despite the odds stacked against them, they are determined to help their students succeed in life.",
                "https://image.tmdb.org/t/p/w500/fKWHqPHnp4dPhJjCkcrTaybBN18.jpg",
                "https://image.tmdb.org/t/p/w780/fKWHqPHnp4dPhJjCkcrTaybBN18.jpg",
                2.99, 18.99),
                
            new Media("The White Lotus", "tv-show",
                "The exploits of various guests and employees at an exclusive tropical resort over the span of a week.",
                "https://image.tmdb.org/t/p/w500/anNqNV3L7v3OGIDNJlpjyK7VBXt.jpg",
                "https://image.tmdb.org/t/p/w780/anNqNV3L7v3OGIDNJlpjyK7VBXt.jpg",
                3.99, 25.99),
                
            new Media("Succession", "tv-show",
                "The Roy family controls one of the biggest media and entertainment empires in the world, but their world changes when their father steps down.",
                "https://image.tmdb.org/t/p/w500/7HW47XbkNQ5fiwQFYGWdw9gs144.jpg",
                "https://image.tmdb.org/t/p/w780/7HW47XbkNQ5fiwQFYGWdw9gs144.jpg",
                3.99, 32.99),
                
            new Media("Only Murders in the Building", "tv-show",
                "Three strangers who share an obsession with true crime podcasts suddenly find themselves wrapped up in one when a murder occurs in their apartment building.",
                "https://image.tmdb.org/t/p/w500/yhx6PnU3L2a6FnEFGOlBKTkZTzE.jpg",
                "https://image.tmdb.org/t/p/w780/yhx6PnU3L2a6FnEFGOlBKTkZTzE.jpg",
                2.99, 21.99),
                
            new Media("Euphoria", "tv-show",
                "A group of high school students navigate drugs, sex, identity, trauma, social media, love and friendship in a modern world.",
                "https://image.tmdb.org/t/p/w500/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                "https://image.tmdb.org/t/p/w780/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                3.99, 26.99),
                
            new Media("The Boys", "tv-show",
                "A group of vigilantes set out to take down corrupt superheroes who abuse their superpowers.",
                "https://image.tmdb.org/t/p/w500/stTEycfG9928HYGEISBFaG1ngjM.jpg",
                "https://image.tmdb.org/t/p/w780/stTEycfG9928HYGEISBFaG1ngjM.jpg",
                3.99, 28.99),
                
            new Media("Better Call Saul", "tv-show",
                "The trials and tribulations of criminal lawyer Jimmy McGill in the years leading up to his fateful run-in with Walter White and Jesse Pinkman.",
                "https://image.tmdb.org/t/p/w500/fC2HDm5t0kHl7mTm7jxMR31cyZO.jpg",
                "https://image.tmdb.org/t/p/w780/fC2HDm5t0kHl7mTm7jxMR31cyZO.jpg",
                3.99, 35.99)
        );
        
        // Save all sample media to MongoDB
        mediaRepository.saveAll(sampleMedia);
        
        System.out.println("Successfully initialized MongoDB Atlas with " + sampleMedia.size() + " media items");
        System.out.println("Database: streamvault-db");
        System.out.println("Collection: media-catalog");
        System.out.println("Media types: movies and tv-shows");
    }
}
