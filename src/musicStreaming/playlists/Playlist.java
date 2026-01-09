package musicstreaming.playlists;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic playlist class that can hold any type of media.
 * Uses generics to allow type-safe collections of songs or podcasts.
 * 
 * @author Arya Ali  - Playlist & Generics
 * @param <T> the type of media items in this playlist
 */
 
public class Playlist<T> {
    private String name;
    private List<T> items;
    
    /**
     * Constructor for a new empty playlist.
     * @param name the playlist name
     */
    public Playlist(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }
    
    
    /** 
     * method getName(): to
     *@return the playlist name
     */
    public String getName() {
        return name;
    }
    
    
    
    /**
     * method setName(): Set the playlist name
     * @param name the new name
     */
    public void setName(String name) {
        this.name= name;
    }
    
    
    /**
 * method contains(): Checks if the playlist contains a specific item
 * @param item the item to search for
 * @return true if the item exists in the playlist
 */
public boolean contains(T item) {
    return items.contains(item);
}
    
    
    /**
     * method add(): Adds an item to the playlist.
     * 
     * @param item the item to add
     * @return false if item is null then not added to playlist.
     * @return false if item duplicated
     * @return true if added successfully
     */
    public boolean add(T item) {
    if (item == null) {
        System.out.println("Cannot add null item to playlist '" + name + "'");
        return false;
    }
    
    if (items.contains(item)) {
        System.out.println("Item already exists in playlist '" + name + "'");
        return false;
    }
    
    items.add(item);
    System.out.println("Added to playlist '" + name );
    return true;
}    
    
    
    /**
     * method remove()
     * @param item removes item from the playlist
     */
    public void remove(T item) {
        items.remove(item);
        System.out.println("Removed from playlist '" + name + "'");
    }
    
        /**
         *  method getAll(): to
         * @return list of all items in the playlist
         */
    public List<T> getItems() {
        return new ArrayList<>(items); // Return copy for safety
    }
    
    /**
     * method size():get the number of items
     * @return item count
     */
    public int size() {
        return items.size();
     }   
    
    
    
/**
 * method isEmpty(): Checks if the playlist is empty
 * @return true if the playlist contains no items
 */
public boolean isEmpty() {
    return items.isEmpty();
}

// method displayPlaylist(): displays all items in the playlist to the console
public void displayPlaylist(){
        System.out.println("Playlist: '"+name+"' ("+items.size()+"items)");
        System.out.println("--------------------------------------");
    
    if(items.isEmpty()){
        System.out.println("(empty playlist)");
    }
    else {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("  "+(i+1)+". "+items.get(i)); 
        }
    }
    System.out.println("--------------------------------------");

    }


// method clear(): Clear all items from the playlist.
public void clear() {
    int count = items.size();
    items.clear();
    System.out.println("Cleared " + count + " items from playlist '" + name + "'");
}
}