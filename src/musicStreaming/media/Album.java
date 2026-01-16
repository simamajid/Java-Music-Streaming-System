package musicstreaming.models;

import musicStreaming.media.Song;
import java.util.ArrayList;

/**
 * Represents a music album containing a collection of songs.
 * 
 * WHY THIS CLASS EXISTS:
 * Albums are fundamental units in music organization:
 * - Artists release music as albums, not just individual songs
 * - Users browse and search by album
 * - Songs are grouped together thematically/chronologically
 * - Models real-world music industry structure
 * 
 * DESIGN RATIONALE:
 * - Uses DOUBLE composition:
 *   1. Album HAS-A Artist (every album has one artist)
 *   2. Album HAS-MANY Songs (albums contain multiple songs)
 * - This is composition, NOT inheritance
 * - Album is not a TYPE of Artist (not IS-A)
 * - Album is not a TYPE of Song (not IS-A)
 * - Correct relationship modeling from Lecture 02
 * 
 * WHY COMPOSITION OVER INHERITANCE:
 * - Album CONTAINS songs, doesn't inherit from Song
 * - Album BELONGS TO artist, isn't a kind of Artist
 * - Composition models real-world "HAS-A" relationships
 * - More flexible than inheritance for this use case
 * 
 * REAL-WORLD MODEL:
 * "25" is an Album
 * "25" HAS-A Artist (Adele)
 * "25" HAS-MANY Songs ("Hello", "When We Were Young", etc.)
 * 
 * @author Member 4
 * @version 1.0
 */
public class Album {
    
    // WHY private: Encapsulation (Lecture 01)
    // Prevents direct modification: album.title = "xyz"
    // Forces use of methods for controlled access
    private String title;
    
    // WHY Artist type (not String):
    // - Strong typing: compiler ensures we have real Artist object
    // - Can access artist's methods: album.getArtist().getName()
    // - Models composition relationship properly
    // - Type safety: can't accidentally assign wrong type
    private Artist artist;
    
    // WHY ArrayList<Song> not Array:
    // - Albums have varying numbers of songs (3-30+ songs)
    // - Dynamic sizing as songs are added
    // - Easy add/remove operations
    // WHY Song type (not Media):
    // - Albums contain ONLY songs, not podcasts
    // - Type safety: can't add podcast to album
    // - More specific than Media
    private ArrayList<Song> songs;
    
    // WHY int for year:
    // - Years are numeric values
    // - Simple type for simple data
    // - Could validate (year must be 1900-2025, etc.)
    private int releaseYear;
    
    /**
     * Constructor creates album with title and artist.
     * 
     * WHY THIS CONSTRUCTOR:
     * - Every album MUST have title and artist (required data)
     * - Initialize songs to empty (not null) to prevent crashes
     * - Year defaults to 0 (can be set later if unknown)
     * 
     * WHY REQUIRE title AND artist:
     * - No album exists without a title
     * - Every album belongs to an artist
     * - Enforces data integrity at creation
     * 
     * WHY INITIALIZE songs HERE:
     * - If left null, addSong() would crash (NullPointerException)
     * - Defensive programming principle
     * - Object should be usable immediately after construction
     * 
     * DESIGN PATTERN:
     * This follows the Constructor pattern from Lecture 01
     * 
     * @param title the album's title (required)
     * @param artist the artist who created this album (required)
     */
    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        // CRITICAL: Initialize to empty list, not null!
        this.songs = new ArrayList<Song>();
        this.releaseYear = 0;  // Default value
    }
    
    /**
     * Overloaded constructor with release year.
     * 
     * WHY OVERLOADED CONSTRUCTOR:
     * - Sometimes we know the release year at creation
     * - Sometimes we don't (can set later)
     * - Provides flexibility for different use cases
     * - Constructor overloading from Lecture 01
     * 
     * WHY THIS IS BETTER THAN MULTIPLE PARAMETERS:
     * - Clear intent: if you know year, use this constructor
     * - If you don't know year, use the other one
     * - Avoids passing 0 or -1 as placeholder
     * 
     * @param title the album's title
     * @param artist the artist who created this album
     * @param releaseYear the year the album was released
     */
    public Album(String title, Artist artist, int releaseYear) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
        this.releaseYear = releaseYear;
    }
    
    /**
     * Add a song to this album.
     * 
     * WHY THIS METHOD:
     * - Builds the composition relationship: Album HAS-MANY Songs
     * - Populates album with its tracks
     * - Used when creating catalog
     * 
     * WHY VALIDATE (null check, duplicate check):
     * - Defensive programming: prevents crashes
     * - Maintains data integrity
     * - Same song shouldn't appear twice in album
     * - Null songs would cause crashes later
     * 
     * USAGE SCENARIO:
     * Album album = new Album("25", adele, 2015);
     * album.addSong(hello);
     * album.addSong(whenWeWereYoung);
     * 
     * WHY PUBLIC:
     * - Other parts of system need to build album contents
     * - MusicService adds songs when loading catalog
     * - User might create custom compilations
     * 
     * @param song the song to add to this album
     */
    public void addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
        }
    }
    
    /**
     * Remove a song from this album.
     * 
     * WHY THIS METHOD:
     * - Allows correcting mistakes (wrong song added)
     * - Provides full CRUD operations (Create, Read, Update, Delete)
     * - Maintains referential integrity
     * 
     * WHEN TO USE:
     * - Deluxe edition removes songs from standard edition
     * - Data correction
     * - Custom compilation creation
     * 
     * @param song the song to remove
     */
    public void removeSong(Song song) {
        songs.remove(song);
    }
    
    /**
     * Calculate total duration of all songs in album.
     * 
     * WHY THIS METHOD:
     * - Common statistic users want to know
     * - "How long is this album?"
     * - Useful for display, sorting, filtering
     * 
     * WHY CALCULATE (not store as field):
     * - Duration changes as songs added/removed
     * - Storing would require updating on every change (error-prone)
     * - Calculation is fast (just loop and sum)
     * - Follows "derived data" principle
     * 
     * WHY RETURN int:
     * - Simple seconds count
     * - Could convert to minutes/hours in display layer
     * 
     * ALTERNATIVE DESIGN:
     * Could cache the result if calculation becomes expensive,
     * but for now simplicity > optimization (YAGNI principle)
     * 
     * @return total duration in seconds of all songs in album
     */
    public int getTotalDuration() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDuration();
        }
        return total;
    }
    
    /**
     * Get the album's title.
     * 
     * WHY THIS METHOD (GETTER):
     * - title is PRIVATE (encapsulation)
     * - Other classes need to READ title for:
     *   * Display in UI
     *   * Search functionality
     *   * Sorting albums
     * - Provides controlled read-only access
     * - Standard getter pattern from Lecture 01
     * 
     * WHY NOT MAKE title PUBLIC:
     * - Would allow uncontrolled modification
     * - No validation possible
     * - Breaks encapsulation
     * 
     * @return the title of the album
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Get the artist of this album.
     * 
     * WHY THIS METHOD:
     * - artist is PRIVATE
     * - Need to access artist for:
     *   * Displaying "Album by Artist"
     *   * Finding all albums by same artist
     *   * Accessing artist's other information
     * 
     * WHY RETURN Artist (not String name):
     * - Returns the actual Artist object
     * - Caller can access: album.getArtist().getName()
     * - Can navigate relationships: album.getArtist().getAlbums()
     * - Maintains object-oriented design
     * 
     * @return the Artist who created this album
     */
    public Artist getArtist() {
        return artist;
    }
    
    /**
     * Get all songs in this album.
     * 
     * WHY THIS METHOD:
     * - songs is PRIVATE (encapsulation)
     * - Need to access for:
     *   * Displaying track listing
     *   * Playing all songs in album
     *   * Counting number of tracks
     * 
     * WHY RETURN ArrayList (not Array):
     * - Matches internal storage type
     * - ArrayList has useful methods (size, get, etc.)
     * 
     * WHY RETURN ACTUAL LIST (not copy):
     * - Simplicity for educational project
     * - Production code might return unmodifiable list or copy
     * 
     * TRADE-OFF:
     * Convenience vs. Safety - we chose convenience
     * 
     * @return ArrayList of all songs in this album
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }
    
    /**
     * Get the release year.
     * 
     * WHY THIS METHOD:
     * - releaseYear is PRIVATE
     * - Needed for:
     *   * Display ("Released in 2015")
     *   * Sorting albums chronologically
     *   * Filtering by decade
     * 
     * WHY ALLOW 0:
     * - Some old albums have unknown release year
     * - 0 indicates "unknown" or "not set"
     * - Could use -1 or null, but 0 is simpler for int
     * 
     * @return the year the album was released (0 if unknown)
     */
    public int getReleaseYear() {
        return releaseYear;
    }
    
    /**
     * Change the album title.
     * 
     * WHY THIS METHOD (SETTER):
     * - Allows correcting typos
     * - Remastered editions might have different titles
     * - Maintains encapsulation (can't modify private field directly)
     * 
     * FUTURE ENHANCEMENT:
     * Could add validation: title cannot be null or empty
     * 
     * @param title the new title for the album
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Change or set the release year.
     * 
     * WHY THIS METHOD:
     * - Sometimes year is unknown at creation
     * - Can be set later when information is found
     * - Allows correction of errors
     * 
     * FUTURE ENHANCEMENT:
     * Could validate: year between 1900 and current year
     * 
     * @param releaseYear the year the album was released
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    /**
     * Get the number of songs in the album.
     * 
     * WHY THIS METHOD:
     * - Common statistic: "This album has 12 tracks"
     * - More readable than: album.getSongs().size()
     * - Encapsulates HOW we count songs
     * 
     * WHY ENCAPSULATE:
     * - If internal storage changes (ArrayList to Array),
     *   counting method might change
     * - This method hides that implementation detail
     * 
     * @return number of songs in this album
     */
    public int getSongCount() {
        return songs.size();
    }
    
    /**
     * Display comprehensive album information to console.
     * 
     * WHY THIS METHOD:
     * - Debugging tool during development
     * - Quick visualization of album data
     * - Testing and demonstration
     * - Shows composition relationships
     * 
     * WHY PRINT DIRECTLY (not return String):
     * - Simple console interface for educational project
     * - In GUI app, would return formatted data
     * 
     * DESIGN NOTE:
     * - Violates separation of concerns (mixes logic and display)
     * - Acceptable for testing/debugging purposes
     * - Production code would separate data from presentation
     * 
     * DEMONSTRATES:
     * - Composition: Album HAS-A Artist, HAS-MANY Songs
     * - Method calls on related objects
     * - Iteration through collections
     */
    public void displayInfo() {
        System.out.println("Album: " + title);
        System.out.println("Artist: " + artist.getName());
        if (releaseYear > 0) {
            System.out.println("Release Year: " + releaseYear);
        }
        System.out.println("Number of Songs: " + songs.size());
        System.out.println("Total Duration: " + getTotalDuration() + " seconds");
        
        if (songs.size() > 0) {
            System.out.println("Songs:");
            for (int i = 0; i < songs.size(); i++) {
                System.out.println("  " + (i+1) + ". " + songs.get(i).getTitle());
            }
        }
    }
    
    /**
     * String representation of album for display/printing.
     * 
     * WHY OVERRIDE toString:
     * - Java calls toString() automatically when:
     *   * System.out.println(album)
     *   * String concatenation: "Album: " + album
     *   * Debugging in IDE
     * - Without override: ugly output like "Album@a3f5e2"
     * - Best practice from Lecture 01
     * 
     * WHY THIS FORMAT:
     * - Concise but informative
     * - Shows title, artist, year (if known), and track count
     * - Human-readable
     * - Useful in debugging and logs
     * 
     * WHEN AUTOMATICALLY CALLED:
     * System.out.println(album);  // Calls toString()
     * String info = "Album: " + album;  // Calls toString()
     * 
     * DEMONSTRATES:
     * - Polymorphism (Object's toString() overridden)
     * - Composition (accesses artist.getName())
     * 
     * @return human-readable string describing this album
     */
    @Override
    public String toString() {
        String yearInfo = (releaseYear > 0) ? " (" + releaseYear + ")" : "";
        return "Album: " + title + " by " + artist.getName() + yearInfo + 
               " - " + songs.size() + " songs";
    }
}