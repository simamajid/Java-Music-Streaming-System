/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musicStreaming.media;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PREDATOR
 */
/**
 * Represents a music album
 */
public class Album {
    // Fields
    private String albumId;
    private String title;
    private Artist artist;
    private List<Song> songs;
    private int releaseYear;
    
    // Constructor
    public Album(String albumId, String title, Artist artist, int releaseYear) {
        this.albumId = albumId;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.songs = new ArrayList<>();
    }
    
    // Add song to album
    public void addSong(Song song) {
        if (song != null && !songs.contains(song)) {
            songs.add(song);
        }
    }
    
    // Remove song
    public void removeSong(Song song) {
        songs.remove(song);
    }
    
    // Get total duration of album
    public int getTotalDuration() {
        int total = 0;
        for (Song song : songs) {
            total += song.getDuration();
        }
        return total;
    }
    
    // Getters
    public String getAlbumId() {
        return albumId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Artist getArtist() {
        return artist;
    }
    
    public List<Song> getSongs() {
        return new ArrayList<>(songs); // Return copy
    }
    
    public int getReleaseYear() {
        return releaseYear;
    }
    
    // Setters
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    // Display album info
    public void displayInfo() {
        System.out.println("Album: " + title);
        System.out.println("Artist: " + artist.getName());
        System.out.println("Year: " + releaseYear);
        System.out.println("Number of Songs: " + songs.size());
        System.out.println("Total Duration: " + getTotalDuration() + " seconds");
    }
    
    @Override
    public String toString() {
        return "Album{title='" + title + "', artist=" + artist.getName() + 
               ", songs=" + songs.size() + "}";
    }
}