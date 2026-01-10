package musicstreaming.playlists;

//import musicStreaming.interfaces.Searchable;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

/**
 * Generic library class for storing and managing a user's saved media collection.
 * This class implements the Searchable interface to provide search functionality
 * across all saved items. The library can store any type of media (songs, podcasts) using Java generics.
 * 
 * @author Arya Ali  - implemented Library & Generics
 * @param <T> the type of media items stored in this library
 */

public class Library<T> {
    //instance variable
    private List<T> savedItems;
    
    //constructor
    public Library() {
        this.savedItems = new ArrayList<>();
    }
    
    
   
    /**
     *  create method add().
     * @param item the item to add to the library (must not be null)
     * @return true if the item was added successfully, false if it was null or already exists
     */
    public boolean add(T item) {
        if (item == null) {
            System.out.println("Cannot add null item to library");
            return false;
        }
        
        if (savedItems.contains(item)) {
            System.out.println("Item already exists in library");
            return false;
        }
        
        savedItems.add(item);
                System.out.println("Saved to library");
                return true;
    }
    
    
    /**
     *  create method remove()
     * 
     * @param item the item to remove from the library
     */
    public void remove(T item) {
        savedItems.remove(item);
        System.out.println("Removed item from library");
    }
    
    
    /**
     * create method getSavedItems(): to
     * @return a new list containing all saved items
     */
    public List<T> getSavedItems() {
        return new ArrayList<>(savedItems); // Return copy for safety
    }
    
    
    /**
     * method size():to
     * @return the total count of saved items in the library.
     */
    public int size() {
        return savedItems.size();
    }
    
    /**
     *  create method isEmpty():to
     * 
     * @return true if the library contains no items, false otherwise
     */
    public boolean isEmpty() {
        return savedItems.isEmpty();
    }
    
    /**
     * method contains(): Checks if the library contains a specific item.
     * 
     * @param item the item to search for
     * @return true if the item exists in the library, false otherwise
     */
    public boolean contains(T item) {
        return savedItems.contains(item);
    }
    
    
    /**
     *method clear(): Removes all items from the library.
     * After calling this method, the library will be empty.
     */
    public void clear() {
        // STEP 1: Store the count before clearing (for user feedback)
        int count = savedItems.size();
        
        // STEP 2: Remove all elements from the list
        savedItems.clear();
        
        // STEP 3: Provide feedback on what was cleared
        System.out.println("Cleared " + count + " items from library");
    }
    
    
    
    // method displayLibrary(): Displays all items in the library to the console.
    public void displayLibrary() {
        // STEP 1: Print header with library size
        System.out.println(" Library (" + savedItems.size() + " items)");
        System.out.println("------------------------------------------");        
        // STEP 2: Check if library is empty
        if (savedItems.isEmpty()) {
            System.out.println("   (empty library)");
        } else {
            // STEP 3: Display each item with index (1-based for user readability)
            for (int i = 0; i < savedItems.size(); i++) {
                // i + 1 converts 0-based index to 1-based position
                System.out.println("   " + (i + 1) + ". " + savedItems.get(i));
            }
        }
        
    }
    
    
    
    
    
//     public void search(String keyword) {
//        System.out.println("Searching library for: " + keyword);
//        // Actual search logic to be added
//    }
//   
//    
//    /**
//     * Searches for items in the library that match the given keyword.
//     * This method implements the Searchable interface contract.
//     * 
//     * <p><b>Search Behavior:</b></p>
//     * <ul>
//     *   <li>Case-insensitive - "ROCK" will match "rock" and "Rock"</li>
//     *   <li>Partial matching - "beat" will match "Beatles"</li>
//     *   <li>Searches against toString() representation of items</li>
//     *   <li>Returns empty list if keyword is null or empty</li>
//     * </ul>
//     * 
//     * <p><b>Implementation Details:</b></p>
//     * Uses Java Streams API for functional-style filtering:
//     * <ol>
//     *   <li>Convert list to stream</li>
//     *   <li>Filter items containing keyword</li>
//     *   <li>Collect results back to list</li>
//     * </ol>
//     * 
//     * <p><b>Time Complexity:</b> O(n) - must check every item</p>
//     * 
//     * @param keyword the search term to look for (case-insensitive)
//     * @return a new list of items that contain the keyword; empty list if no matches
//     * 
//     * @see Searchable#search(String)
//     */
//    @Override // Indicates this method implements the Searchable interface
//    public List<T> search(String keyword) {
//        // STEP 1: Validate the keyword
//        if (keyword == null || keyword.trim().isEmpty()) {
//            System.out.println("⚠️ Search keyword cannot be empty");
//            return new ArrayList<>(); // Return empty list
//        }
//        
//        // STEP 2: Convert keyword to lowercase and remove extra spaces
//        String lowerKeyword = keyword.toLowerCase().trim();
//        
//        // STEP 3: Use Java Streams to filter matching items
//        List<T> results = savedItems.stream()
//            // Filter: keep only items whose string representation contains the keyword
//            .filter(item -> item.toString().toLowerCase().contains(lowerKeyword))
//            // Collect: gather all matching items into a new List
//            .collect(Collectors.toList());
//        
//        // STEP 4: Provide feedback on search results
//        System.out.println("Found " + results.size() + " item(s) matching '" + keyword + "'");
//        
//        // STEP 5: Return the results
//        return results;
//    }
    
}