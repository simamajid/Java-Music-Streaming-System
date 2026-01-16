package musicstreaming.interfaces;

import musicStreaming.media.Media;
import java.util.ArrayList;

/**
 * Interface defining search capability contract.
 * 
 * WHY THIS INTERFACE EXISTS:
 * Multiple classes in our system need search functionality:
 * - MusicService needs to search ALL system media
 * - Library needs to search a user's personal collection
 * Rather than duplicate search code, we define a CONTRACT that says
 * "any class that is Searchable MUST implement search()"
 * 
 * DESIGN RATIONALE:
 * - Interface (not abstract class) because search is a BEHAVIOR, not identity
 * - Different classes will implement search DIFFERENTLY
 * - MusicService searches system-wide
 * - Library searches user's personal items
 * - This follows the Interface Segregation Principle
 * 
 * WHY NOT JUST PUT search() IN EACH CLASS:
 * - Polymorphism: we can treat different searchable things uniformly
 * - Contract: guarantees any Searchable object HAS a search method
 * - From Lecture 05: interfaces define "what" not "how"
 * 
 * REAL-WORLD ANALOGY:
 * Like a "Drivable" interface - cars and motorcycles both drivable,
 * but they drive differently. Interface says "must have drive()", 
 * each implements it their own way.
 * 
 * @author Member 4
 * @version 1.0
 */
public interface Searchable {
    
    /**
     * Search for media items matching the given keyword.
     * 
     * WHY THIS METHOD SIGNATURE:
     * - String keyword: simple, flexible search term
     * - Returns ArrayList<Media>: polymorphic return (can hold Song, Podcast)
     * - Uses Media (not Song/Podcast) for flexibility
     * 
     * WHY RETURN ArrayList NOT Array:
     * - Don't know how many results in advance
     * - Dynamic sizing based on matches found
     * - Standard Java collection from Lecture 04
     * 
     * WHY abstract (implied in interfaces):
     * - Interface provides NO implementation
     * - Each class implements search differently
     * - MusicService: searches all system media
     * - Library: searches user's personal collection
     * 
     * CONTRACT GUARANTEE:
     * Any class implementing Searchable MUST provide this method.
     * Compiler enforces this - won't compile without it.
     * 
     * USAGE EXAMPLE:
     * Searchable s1 = new MusicService();
     * Searchable s2 = new Library<Media>();
     * s1.search("Coldplay");  // Searches system
     * s2.search("Coldplay");  // Searches user library
     * 
     * THIS DEMONSTRATES:
     * - Polymorphism (Lecture 04): treat different types uniformly
     * - Interface as contract (Lecture 05)
     * - Separation of interface from implementation
     * 
     * @param keyword the search term to match against titles, artists, etc.
     * @return ArrayList of Media items that match the keyword
     */
    public abstract ArrayList<Media> search(String keyword);
}