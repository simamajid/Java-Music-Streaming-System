package musicstreaming.users;

import java.util.ArrayList;
import java.util.List;
import musicstreaming.media.Media;
import musicstreaming.playlists.Library;
import musicstreaming.playlists.Playlist;

/**
 * ABSTRACT CLASS: User
 * 
 * PURPOSE:
 * This abstract class serves as the base class for all users in the music streaming system.
 * It defines common properties and behaviors that all user types share, while enforcing
 * child classes to implement user-specific playback behaviors.
 * 
 * WHY ABSTRACT:
 * 1. We never instantiate a generic "User" object in the system
 * 2. We only create specific user types (FreeUser or PremiumUser)
 * 3. Different user types have different playback behaviors that must be implemented
 * 4. Provides a common interface while allowing polymorphic behavior
 * 
 * OOP CONCEPTS APPLIED:
 * - Abstract Class: Cannot be instantiated directly
 * - Encapsulation: Private fields with public getters/setters
 * - Polymorphism: Different implementations in child classes
 * - Inheritance: FreeUser and PremiumUser inherit from User
 */
public abstract class User {
    
    // PRIVATE FIELDS (ENCAPSULATION)
    private String userId;          // Unique identifier for each user
    private String username;        // Display name of the user
    private Library<Media> library; // User's personal media collection
    private List<Playlist<? extends Media>> playlists; // User's playlists
    
    /**
     * CONSTRUCTOR: User
     * 
     * @param userId   Unique identifier for the user
     * @param username Display name for the user
     * 
     * PURPOSE:
     * Initializes a new User object with basic information.
     * Note: This constructor is protected because User is abstract
     * and can only be called by child classes.
     */
    protected User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.library = new Library<>(); // Initialize empty library
        this.playlists = new ArrayList<>(); // Initialize empty playlist list
    }
    
    // ABSTRACT METHODS (MUST BE IMPLEMENTED BY CHILD CLASSES)
    
    /**
     * ABSTRACT METHOD: play
     * 
     * @param media The media item to be played
     * 
     * PURPOSE:
     * Defines how a user plays media. Implementation varies between
     * FreeUser (with ads and skip limits) and PremiumUser (no ads, unlimited skips).
     * 
     * POLYMORPHISM:
     * Each user type will provide its own implementation of this method.
     */
    public abstract void play(Media media);
    
    /**
     * ABSTRACT METHOD: canDownload
     * 
     * @return boolean indicating if user has download permission
     * 
     * PURPOSE:
     * Determines whether the user can download media for offline use.
     * Free users cannot download, while premium users can.
     */
    public abstract boolean canDownload();
    
    // CONCRETE METHODS (COMMON TO ALL USERS)
    
    /**
     * METHOD: createPlaylist
     * 
     * @param name The name of the new playlist
     * @return A new Playlist object
     * 
     * PURPOSE:
     * Creates a new empty playlist with the given name.
     * Note: The specific type of playlist (Song/Podcast) is determined
     * when adding items to it.
     */
    public Playlist<Media> createPlaylist(String name) {
        Playlist<Media> newPlaylist = new Playlist<>(name);
        playlists.add(newPlaylist);
        System.out.println("Playlist '" + name + "' created successfully.");
        return newPlaylist;
    }
    
    /**
     * METHOD: addToLibrary
     * 
     * @param media The media item to add to the library
     * 
     * PURPOSE:
     * Adds a media item (song or podcast) to the user's personal library.
     * This represents the user's saved/liked media collection.
     */
    public void addToLibrary(Media media) {
        library.add(media);
        System.out.println("Added '" + media.getTitle() + "' to your library.");
    }
    
    /**
     * METHOD: searchLibrary
     * 
     * @param keyword The search term
     * 
     * PURPOSE:
     * Searches for media in the user's personal library.
     * Delegates the search to the Library class.
     */
    public void searchLibrary(String keyword) {
        library.search(keyword);
    }
    
    /**
     * METHOD: displayUserInfo
     * 
     * PURPOSE:
     * Displays basic information about the user.
     * Used for debugging and user profile display.
     */
    public void displayUserInfo() {
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("User Type: " + this.getClass().getSimpleName());
        System.out.println("Playlists: " + playlists.size());
        System.out.println("Library Items: " + library.getSavedItems().size());
    }
    
    // GETTERS AND SETTERS (ENCAPSULATION)
    
    /**
     * GETTER: getUserId
     * 
     * @return The user's unique ID
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * SETTER: setUserId
     * 
     * @param userId New user ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * GETTER: getUsername
     * 
     * @return The user's display name
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * SETTER: setUsername
     * 
     * @param username New username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * GETTER: getLibrary
     * 
     * @return The user's media library
     */
    public Library<Media> getLibrary() {
        return library;
    }
    
    /**
     * GETTER: getPlaylists
     * 
     * @return List of user's playlists
     */
    public List<Playlist<? extends Media>> getPlaylists() {
        return playlists;
    }
    
    /**
     * METHOD: toString
     * 
     * @return String representation of the user
     * 
     * PURPOSE:
     * Provides a readable string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", type=" + this.getClass().getSimpleName() +
                '}';
    }
}