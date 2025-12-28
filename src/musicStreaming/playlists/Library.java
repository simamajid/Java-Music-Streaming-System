package musicstreaming.playlists;

import java.util.ArrayList;
import java.util.List;

// PLACEHOLDER - To be implemented by Member 3

public class Library<T> {
    private List<T> savedItems;
    
    public Library() {
        this.savedItems = new ArrayList<>();
    }
    
    
    public void add(T item) {
        savedItems.add(item);
        System.out.println("Added item to library");
    }
    
    
    public void remove(T item) {
        savedItems.remove(item);
        System.out.println("Removed item from library");
    }
    
    
    public void search(String keyword) {
        System.out.println("Searching library for: " + keyword);
        // Actual search logic to be added
    }
    
    
    public List<T> getSavedItems() {
        return new ArrayList<>(savedItems); // Return copy for safety
    }
    
    
    public int size() {
        return savedItems.size();
    }
}