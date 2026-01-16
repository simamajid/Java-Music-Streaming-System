package musicstreaming.models;

import java.util.ArrayList;

/**
 * Represents a music artist in the streaming system.
 * WHY THIS CLASS EXISTS:
 * In any music system, artists are fundamental entities. We need to:
 * - Track which albums belong to which artist
 * - Search for artists by name
 * - Display artist information and discography
 * - Model the real-world relationship: Artist creates Albums
 * 
 * DESIGN RATIONALE:
 * - Uses composition (HAS-A) not inheritance: Artist HAS-A list of Albums
 * - This is correct because Album is not a type of Artist
 * - Private fields with public methods enforce encapsulation (Lecture 01)
 * - ArrayList chosen for dynamic growth (artists release new albums over time)
 * 
 * REAL-WORLD MODEL:
 * - Coldplay is an Artist
 * - Coldplay HAS albums "X&Y", "Parachutes", etc.
 * - This is composition, not inheritance
 * 
 * @author Member 4
 * @version 1.0
 */
public class Artist {
    
    // WHY private: Encapsulation principle from Lecture 01
    // Prevents direct external modification, forces use of methods
    // WHY String: Names are text-based identifiers
    private String name;
    
    // WHY ArrayList not Array:
    // - Artists release new albums over time (dynamic growth)
    // - Don't know in advance how many albums
    // - Easy add/remove operations
    // WHY Album type: Maintains strong typing, type safety
    private ArrayList<Album> albums;
    
    /**
     * Constructor creates a new artist with empty album collection.
     * 
     * WHY THIS DESIGN:
     * - Name is required (every artist must have a name)
     * - Initialize albums to empty list (not null) to prevent crashes
     * - Follows constructor pattern from Lecture 01
     * 
     * WHY INITIALIZE albums HERE:
     * - If left null, calling addAlbum() would crash (NullPointerException)
     * - Defensive programming: always initialize collections
     * - Principle: Object should be usable immediately after construction
     * 
     * @param name the artist's name (required, should not be null)
     */
    public Artist(String name) {
        this.name = name;
        // CRITICAL: Initialize to empty list, not null!
        this.albums = new ArrayList<Album>();
    }
    
    /**
     * Add an album to this artist's discography.
     * 
     * WHY THIS METHOD:
     * - Implements composition relationship: Artist HAS-MANY Albums
     * - Builds the connection between artist and their work
     * - Used when creating album catalog
     * 
     * WHY CHECK FOR NULL:
     * - Defensive programming: prevents NullPointerException
     * - Maintains data integrity
     * 
     * WHY CHECK contains():
     * - Prevents duplicate albums in discography
     * - Same album shouldn't appear twice for one artist
     * - Maintains clean data
     * 
     * USAGE EXAMPLE:
     * Artist coldplay = new Artist("Coldplay");
     * Album xy = new Album("X&Y", coldplay);
     * coldplay.addAlbum(xy);
     * 
     * @param album the album to add (validated for null and duplicates)
     */
    public void addAlbum(Album album) {
        if (album != null && !albums.contains(album)) {
            albums.add(album);
        }
    }
    
    /**
     * Remove an album from this artist's discography.
     * 
     * WHY THIS METHOD:
     * - Allows updating catalog if album is deleted
     * - Maintains referential integrity
     * - Provides full CRUD operations (Create, Read, Update, Delete)
     * 
     * WHEN TO USE:
     * - Album goes out of print
     * - Correction of data entry error
     * - Album moved to different artist (compilation scenarios)
     * 
     * @param album the album to remove
     */
    public void removeAlbum(Album album) {
        albums.remove(album);
    }
    
    /**
     * Get the artist's name.
     * * WHY THIS METHOD (CRITICAL UNDERSTANDING):
     * - name is PRIVATE (encapsulation principle from Lecture 01)
     * - Other classes NEED to read the name for:
     *   * Displaying artist info in UI
     *   * Searching by artist name
     *   * Sorting artists alphabetically
     * - We provide CONTROLLED access through this public method
     * - This is the "getter" pattern from Lecture 01
     * 
     * WHY NOT MAKE name PUBLIC:
     * - Would allow anyone to modify it: artist.name = "xyz"
     * - No validation possible
     * - Breaks encapsulation
     * - Can't track changes or enforce rules
     * 
     * THIS DEMONSTRATES: Encapsulation - hide data, expose behavior
     * 
     * @return the name of the artist
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get all albums by this artist.
     * 
     * WHY THIS METHOD:
     * - albums is private (encapsulation)
     * - Other parts of system need to:
     *   * Display artist's discography
     *   * Count number of albums
     *   * Search through artist's albums
     * - Provides controlled read access
     * 
     * WHY RETURN THE ACTUAL LIST (not a copy):
     * - Simplicity for educational project
     * - In production code, might return copy to prevent external modification
     * 
     * TRADE-OFF:
     * - Convenience vs. Safety
     * - We chose convenience for this student project
     * 
     * @return ArrayList of all albums by this artist
     */
    public ArrayList<Album> getAlbums() {
        return albums;
    }
    
    /**
     * Change the artist's name.
     * 
     * WHY THIS METHOD (SETTER):
     * - Artists sometimes change stage names
     * - Need ability to update without creating new object
     * - Follows "setter" pattern from Lecture 01
     * - Maintains encapsulation (can't modify private field directly)
     * 
     * WHY ALLOW NAME CHANGES:
     * - Stage name changes (e.g., "Prince" → "The Artist Formerly Known As Prince")
     * - Corrections of typos in database
     * - Rebranding
     * 
     * FUTURE ENHANCEMENT:
     * Could add validation here (e.g., name cannot be empty)
     * 
     * @param name the new name for the artist
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the number of albums this artist has.
     * 
     * WHY THIS METHOD:
     * - Common statistic needed frequently
     * - More readable than: artist.getAlbums().size()
     * - Encapsulates HOW we count albums
     * 
     * WHY ENCAPSULATE THE COUNT:
     * - If we change internal storage (array to ArrayList), 
     *   counting method might change
     * - This method hides that detail
     * - Callers don't need to know implementation
     * 
     * PRINCIPLE: Encapsulation of implementation details
     * 
     * @return number of albums released by this artist
     */
    public int getAlbumCount() {
        return albums.size();
    }
    
    /**
     * Display detailed information about the artist to console.
     * 
     * WHY THIS METHOD:
     * - Useful for debugging during development
     * - Quick way to see artist state
     * - Testing tool
     * - Demonstrates composition (iterates through albums)
     * 
     * WHY PRINT DIRECTLY (not return String):
     * - Simple console-based interface for student project
     * - In GUI application, would return formatted data instead
     * 
     * DESIGN NOTE:
     * - This violates separation of concerns (mixing logic and display)
     * - Acceptable for educational/testing purposes
     * - Production code would separate data retrieval from display
     * 
     * DEMONSTRATES:
     * - Composition: Artist HAS-A Albums
     * - Polymorphism: calls Album's getTitle() method
     */
    public void displayInfo() {
        System.out.println("Artist: " + name);
        System.out.println("Number of Albums: " + albums.size());
        if (albums.size() > 0) {
            System.out.println("Albums:");
            // Demonstrates composition and iteration
            for (Album album : albums) {
                System.out.println("  - " + album.getTitle());
            }
        }
    }
    
    /**
     * String representation of artist for display/printing.
     * 
     * WHY OVERRIDE toString (IMPORTANT):
     * - Java automatically calls toString() when:
     *   * Using System.out.println(artist)
     *   * Concatenating with strings: "Artist: " + artist
     *   * Debugging in IDE
     * - Without this override, output would be: "Artist@a3f5e2" (useless!)
     * - Best practice from Lecture 01
     * 
     * WHY THIS FORMAT:
     * - Concise but informative
     * - Shows both name and album count
     * - Human-readable
     * - Useful in debugging
     * 
     * WHEN CALLED AUTOMATICALLY:
     * System.out.println(coldplay);  // Calls toString() automatically
     * String info = "Artist: " + coldplay;  // Calls toString()
     * 
     * THIS DEMONSTRATES: Polymorphism (Object's toString() overridden)
     * 
     * @return human-readable string describing this artist
     */
    @Override
    public String toString() {
        return "Artist: " + name + " (" + albums.size() + " albums)";
    }
}