/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package musicStreaming.media;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fro Wrya M Salih
 */
/**
 * Represents a music artist
 */
public class Artist {
    // Fields
    private String artistId;
    private String name;
    private List<Album> albums;
    
    // Constructor
    public Artist(String artistId, String name) {
        this.artistId = artistId;
        this.name = name;
        this.albums = new ArrayList<>();
    }
    
    // Add album to artist
    public void addAlbum(Album album) {
        if (album != null && !albums.contains(album)) {
            albums.add(album);
        }
    }
    
    // Remove album
    public void removeAlbum(Album album) {
        albums.remove(album);
    }
    
    // Getters
    public String getArtistId() {
        return artistId;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Album> getAlbums() {
        return new ArrayList<>(albums); // Return copy for encapsulation
    }
    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    
    // Display artist info
    public void displayInfo() {
        System.out.println("Artist: " + name);
        System.out.println("Number of Albums: " + albums.size());
    }
    
    @Override
    public String toString() {
        return "Artist{name='" + name + "', albums=" + albums.size() + "}";
    }

    String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}