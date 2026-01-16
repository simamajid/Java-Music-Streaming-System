package musicstreaming.services;

import musicStreaming.interfaces.Searchable;
import musicStreaming.media.*;
import java.util.ArrayList;

/**
 * MusicService - Central system controller for the music streaming platform
 * 
 * WHY THIS CLASS EXISTS:
 * In a music streaming system, we need a central place to store ALL available
 * media (songs, podcasts, artists, albums). This class acts as the "database"
 * or "brain" of the system. Without it, each user would need to maintain their
 * own copy of all media, which would be inefficient and inconsistent.
 * 
 * DESIGN RATIONALE:
 * - Implements Searchable because the system needs search capability
 * - Uses ArrayList for collections because we don't know how many items in advance
 * - Centralized storage ensures all users see the same catalog
 * - Similar to the Basket class pattern from Lecture 04
 * 
 * RESPONSIBILITIES:
 * - Store and manage all system media
 * - Provide search functionality across all media types
 * - Act as single source of truth for available content
 * 
 * @author Member 4
 * @version 1.0
 */
public class MusicService implements Searchable {
    
    // WHY private: Encapsulation - protect internal data structure
    // WHY ArrayList: Dynamic sizing, don't know how many items in advance
    // WHY separate lists: Different types need different search/filter logic
    private ArrayList<Song> allSongs;
    private ArrayList<Podcast> allPodcasts;
    private ArrayList<Artist> allArtists;
    private ArrayList<Album> allAlbums;
    
    /**
     * Constructor initializes empty collections.
     * 
     * WHY THIS APPROACH:
     * - Initialize all lists to empty (not null) to prevent NullPointerException
     * - User can immediately start adding items without additional setup
     * - Follows defensive programming principle
     * - Pattern from Lecture 01 (initialize in constructor)
     */
    public MusicService() {
        this.allSongs = new ArrayList<Song>();
        this.allPodcasts = new ArrayList<Podcast>();
        this.allArtists = new ArrayList<Artist>();
        this.allAlbums = new ArrayList<Album>();
        
        System.out.println("Music Streaming Service initialized!");
    }
    
    /**
     * Add a song to the system catalog.
     * 
     * WHY THIS METHOD:
     * - System needs to be populated with content before users can access it
     * - Validates input to maintain data integrity (no null, no duplicates)
     * - Provides feedback to confirm addition
     * 
     * WHY CHECK FOR NULL:
     * - Prevents NullPointerException later when iterating
     * - Defensive programming principle
     * 
     * WHY CHECK FOR DUPLICATES:
     * - Prevents same song appearing multiple times in searches
     * - Maintains clean catalog
     * 
     * @param song the song to add to the system
     */
    public void addSong(Song song) {
        if (song != null && !allSongs.contains(song)) {
            allSongs.add(song);
            System.out.println("Song added: " + song.getTitle());
        }
    }
    
    /**
     * Add a podcast to the system catalog.
     * 
     * WHY THIS METHOD:
     * - Same rationale as addSong but for podcasts
     * - Keeps songs and podcasts in separate lists for efficient filtering
     * 
     * @param podcast the podcast to add
     */
    public void addPodcast(Podcast podcast) {
        if (podcast != null && !allPodcasts.contains(podcast)) {
            allPodcasts.add(podcast);
            System.out.println("Podcast added: " + podcast.getTitle());
        }
    }
    
    /**
     * Add an artist to the system.
     * 
     * WHY THIS METHOD:
     * - Artists are searchable entities in the system
     * - Users want to find music by artist name
     * - Maintains artist catalog separate from media catalog
     * 
     * @param artist the artist to add
     */
    public void addArtist(Artist artist) {
        if (artist != null && !allArtists.contains(artist)) {
            allArtists.add(artist);
            System.out.println("Artist added: " + artist.getName());
        }
    }
    
    /**
     * Add an album to the system.
     * 
     * WHY THIS METHOD:
     * - Albums are browsable/searchable collections
     * - Users often search for albums, not individual songs
     * - Maintains album catalog for organized browsing
     * 
     * @param album the album to add
     */
    public void addAlbum(Album album) {
        if (album != null && !allAlbums.contains(album)) {
            allAlbums.add(album);
            System.out.println("Album added: " + album.getTitle());
        }
    }
    
    /**
     * Main search method - implements Searchable interface contract.
     * Searches across both songs and podcasts using polymorphism.
     * 
     * WHY THIS METHOD:
     * - Fulfills Searchable interface contract (required method)
     * - Provides unified search across all playable media
     * - Uses polymorphism (Lecture 04) - treats Song and Podcast uniformly as Media
     * - Case-insensitive for better user experience
     * 
     * WHY RETURN ArrayList<Media>:
     * - Polymorphic return type can hold both Song and Podcast
     * - Caller doesn't need to know specific types
     * - Demonstrates polymorphism from Lecture 04
     * 
     * WHY VALIDATE INPUT:
     * - Empty search would return all media (expensive, unhelpful)
     * - Prevents crashes from null keyword
     * 
     * WHY toLowerCase:
     * - Case-insensitive matching ("coldplay" finds "Coldplay")
     * - Better user experience
     * 
     * DESIGN PATTERN: Template Method (search both collections same way)
     * 
     * @param keyword the search term (searches titles and artists)
     * @return ArrayList of Media matching the keyword
     */
    @Override
    public ArrayList<Media> search(String keyword) {
        ArrayList<Media> results = new ArrayList<Media>();
        
        // Validate input - defensive programming
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Please enter a valid search keyword.");
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        // Search songs - polymorphism in action (Song treated as Media)
        for (Song song : allSongs) {
            if (song.getTitle().toLowerCase().contains(lowerKeyword)) {
                results.add(song);  // Upcasting to Media
            }
        }
        
        // Search podcasts - polymorphism (Podcast treated as Media)
        for (Podcast podcast : allPodcasts) {
            if (podcast.getTitle().toLowerCase().contains(lowerKeyword)) {
                results.add(podcast);  // Upcasting to Media
            }
        }
        
        System.out.println("Search for '" + keyword + "' found " + 
                         results.size() + " results.");
        return results;
    }
    
    /**
     * Search only songs (more specific than general search).
     * 
     * WHY THIS METHOD:
     * - Sometimes users want ONLY songs, not podcasts
     * - Searches more fields (title, artist, genre) for better results
     * - More specific return type (ArrayList<Song> not Media)
     * 
     * WHY SEPARATE FROM search():
     * - Different return type (Song vs Media)
     * - Different search fields (includes genre)
     * - More specific functionality for specific use case
     * 
     * @param keyword search term
     * @return ArrayList of Songs only
     */
    public ArrayList<Song> searchSongs(String keyword) {
        ArrayList<Song> results = new ArrayList<Song>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        // Search multiple fields for better results
        for (Song song : allSongs) {
            if (song.getTitle().toLowerCase().contains(lowerKeyword) ||
                song.getArtist().toLowerCase().contains(lowerKeyword) ||
                song.getGenre().toLowerCase().contains(lowerKeyword)) {
                results.add(song);
            }
        }
        
        return results;
    }
    
    /**
     * Search only podcasts.
     * 
     * WHY THIS METHOD:
     * - Parallel to searchSongs for consistency
     * - Searches podcast-specific fields (host name)
     * - Users may want to browse only podcasts
     * 
     * @param keyword search term
     * @return ArrayList of Podcasts only
     */
    public ArrayList<Podcast> searchPodcasts(String keyword) {
        ArrayList<Podcast> results = new ArrayList<Podcast>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        for (Podcast podcast : allPodcasts) {
            if (podcast.getTitle().toLowerCase().contains(lowerKeyword) ||
                podcast.getHost().toLowerCase().contains(lowerKeyword)) {
                results.add(podcast);
            }
        }
        
        return results;
    }
    
    /**
     * Search for artists by name.
     * 
     * WHY THIS METHOD:
     * - Users often search by artist, not song title
     * - Once found, can browse all artist's work
     * - Common use case in music apps
     * 
     * @param keyword artist name to search for
     * @return ArrayList of matching artists
     */
    public ArrayList<Artist> searchArtists(String keyword) {
        ArrayList<Artist> results = new ArrayList<Artist>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        for (Artist artist : allArtists) {
            if (artist.getName().toLowerCase().contains(lowerKeyword)) {
                results.add(artist);
            }
        }
        
        return results;
    }
    
    /**
     * Get all songs in the system.
     * 
     * WHY THIS METHOD:
     * - UI needs to display full catalog
     * - Browse functionality requires access to all items
     * - Encapsulation: provides controlled access to private field
     * 
     * WHY NOT RETURN COPY:
     * - For simplicity in student project
     * - Production code might return copy to prevent external modification
     * 
     * @return ArrayList of all songs
     */
    public ArrayList<Song> getAllSongs() {
        return allSongs;
    }
    
    /**
     * Get all podcasts in the system.
     * 
     * WHY THIS METHOD:
     * - Same rationale as getAllSongs
     * - Separate method for type safety
     * 
     * @return ArrayList of all podcasts
     */
    public ArrayList<Podcast> getAllPodcasts() {
        return allPodcasts;
    }
    
    /**
     * Get all artists in the system.
     * 
     * @return ArrayList of all artists
     */
    public ArrayList<Artist> getAllArtists() {
        return allArtists;
    }
    
    /**
     * Get all albums in the system.
     * 
     * @return ArrayList of all albums
     */
    public ArrayList<Album> getAllAlbums() {
        return allAlbums;
    }
    
    /**
     * Display system statistics summary.
     * 
     * WHY THIS METHOD:
     * - Useful for debugging and testing
     * - Quick overview of system state
     * - Demonstrates encapsulation (counts internal data)
     * 
     * WHY PRINT DIRECTLY:
     * - Simple text-based interface for educational project
     * - In GUI app, would return formatted data instead
     * 
     * DESIGN NOTE: In production, this would return a Statistics object
     * instead of printing directly (separation of concerns)
     */
    public void displayStatistics() {
        System.out.println("\n========================================");
        System.out.println("   MUSIC STREAMING SYSTEM STATISTICS");
        System.out.println("========================================");
        System.out.println("Total Songs:    " + allSongs.size());
        System.out.println("Total Podcasts: " + allPodcasts.size());
        System.out.println("Total Artists:  " + allArtists.size());
        System.out.println("Total Albums:   " + allAlbums.size());
        System.out.println("========================================\n");
    }
    
    /**
     * Display all artists in the system.
     * 
     * WHY THIS METHOD:
     * - Testing and demonstration purposes
     * - Shows toString() polymorphism (calls Artist's toString)
     * - Useful during development
     * 
     * WHY CHECK isEmpty:
     * - Better user experience (inform user if no data)
     * - Prevents confusing blank output
     */
    public void displayAllArtists() {
        System.out.println("\n=== ALL ARTISTS ===");
        if (allArtists.isEmpty()) {
            System.out.println("No artists available.");
        } else {
            for (int i = 0; i < allArtists.size(); i++) {
                System.out.println((i + 1) + ". " + allArtists.get(i));
            }
        }
        System.out.println();
    }
}