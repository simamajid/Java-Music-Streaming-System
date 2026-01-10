package musicServices;

import java.util.ArrayList;
import java.util.List;
import musicStreaming.interfaces.Searchable;
import musicStreaming.media.Album;
import musicStreaming.media.Artist;
import musicStreaming.media.Media;
import musicStreaming.media.Podcast;
import musicStreaming.media.Song;

/**
 * Main service class that manages all media in the system
 * Acts as the "database" of the application
 */
public class MusicService implements Searchable {
    
    // Fields - Store all system data
    private List<Song> allSongs;
    private List<Podcast> allPodcasts;
    private List<Artist> allArtists;
    private List<Album> allAlbums;
    
    // Constructor
    public MusicService() {
        this.allSongs = new ArrayList<>();
        this.allPodcasts = new ArrayList<>();
        this.allArtists = new ArrayList<>();
        this.allAlbums = new ArrayList<>();
        
        // Initialize with some sample data
        initializeSampleData();
    }
    
    // Initialize sample data (for testing)
    private void initializeSampleData() {
        // Create artists
        Artist coldplay = new Artist("A001", "Coldplay");
        Artist adele = new Artist("A002", "Adele");
        addArtist(coldplay);
        addArtist(adele);
        
        // Create songs (assuming Song constructor exists)
        // Song song1 = new Song("S001", "Fix You", 295, coldplay, "Rock");
        // addSong(song1);
        
        System.out.println("Sample data initialized!");
    }
    
    // ==================== ADD METHODS ====================
    
    public void addSong(Song song) {
        if (song != null && !allSongs.contains(song)) {
            allSongs.add(song);
            System.out.println("Song added: " + song.getTitle());
        }
    }
    
    public void addPodcast(Podcast podcast) {
        if (podcast != null && !allPodcasts.contains(podcast)) {
            allPodcasts.add(podcast);
            System.out.println("Podcast added: " + podcast.getTitle());
        }
    }
    
    public void addArtist(Artist artist) {
        if (artist != null && !allArtists.contains(artist)) {
            allArtists.add(artist);
            System.out.println("Artist added: " + artist.getName());
        }
    }
    
    public void addAlbum(Album album) {
        if (album != null && !allAlbums.contains(album)) {
            allAlbums.add(album);
            System.out.println("Album added: " + album.getTitle());
        }
    }
    
    // ==================== SEARCH METHODS ====================
    
    /**
     * Main search method (implements Searchable interface)
     * Searches through both songs and podcasts
     */
    @Override
    public List<Media> search(String keyword) {
        List<Media> results = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        // Search songs
        for (Song song : allSongs) {
            if (song.getTitle().toLowerCase().contains(lowerKeyword) ||
                song.getArtist().toLowerCase().contains(lowerKeyword)) {
                results.add(song);
            }
        }
        
        // Search podcasts
        for (Podcast podcast : allPodcasts) {
            if (podcast.getTitle().toLowerCase().contains(lowerKeyword) ||
                podcast.getHost().toLowerCase().contains(lowerKeyword)) {
                results.add(podcast);
            }
        }
        
        return results;
    }
    
    /**
     * Search only songs
     */
    public List<Song> searchSongs(String keyword) {
        List<Song> results = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
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
     * Search only podcasts
     */
    public List<Podcast> searchPodcasts(String keyword) {
        List<Podcast> results = new ArrayList<>();
        
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
     * Search artists by name
     */
    public List<Artist> searchArtists(String keyword) {
        List<Artist> results = new ArrayList<>();
        
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
     * Search albums by title
     */
    public List<Album> searchAlbums(String keyword) {
        List<Album> results = new ArrayList<>();
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }
        
        String lowerKeyword = keyword.toLowerCase();
        
        for (Album album : allAlbums) {
            if (album.getTitle().toLowerCase().contains(lowerKeyword) ||
                album.getArtist().getName().toLowerCase().contains(lowerKeyword)) {
                results.add(album);
            }
        }
        
        return results;
    }
    
    // ==================== GETTER METHODS ====================
    
    public List<Song> getAllSongs() {
        return new ArrayList<>(allSongs);
    }
    
    public List<Podcast> getAllPodcasts() {
        return new ArrayList<>(allPodcasts);
    }
    
    public List<Artist> getAllArtists() {
        return new ArrayList<>(allArtists);
    }
    
    public List<Album> getAllAlbums() {
        return new ArrayList<>(allAlbums);
    }
    
    // ==================== UTILITY METHODS ====================
    
    /**
     * Get song by ID
     */
    public Song getSongById(String id) {
        for (Song song : allSongs) {
            if (song.getId().equals(id)) {
                return song;
            }
        }
        return null;
    }
    
    /**
     * Get artist by ID
     */
    public Artist getArtistById(String id) {
        for (Artist artist : allArtists) {
            if (artist.getArtistId().equals(id)) {
                return artist;
            }
        }
        return null;
    }
    
    /**
     * Display system statistics
     */
    public void displayStatistics() {
        System.out.println("\n===== MUSIC STREAMING SYSTEM STATS =====");
        System.out.println("Total Songs: " + allSongs.size());
        System.out.println("Total Podcasts: " + allPodcasts.size());
        System.out.println("Total Artists: " + allArtists.size());
        System.out.println("Total Albums: " + allAlbums.size());
        System.out.println("========================================\n");
    }
}