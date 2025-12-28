package musicstreaming.playlists;

import java.util.ArrayList;
import java.util.List;

// PLACEHOLDER - To be implemented by Member 3
 
public class Playlist<T> {
    private String name;
    private List<T> items;
    
    public Playlist(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }
    
    
    public void add(T item) {
        items.add(item);
        System.out.println("Added to playlist '" + name + "'");
    }
    
    
    public void remove(T item) {
        items.remove(item);
        System.out.println("Removed from playlist '" + name + "'");
    }
    
   
    public List<T> getAll() {
        return new ArrayList<>(items); // Return copy for safety
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public int size() {
        return items.size();
    }
}