
package musicStreaming.enums;

/**
 * Represents the type of media content in the streaming system.
 * Distinguishes between different forms of audio content that can be played.
 * 
 * @author Sima

 */
public enum MediaType {
     /**
     * Musical song content with artist, album, and genre information.
     */
    SONG("Song"),
    
    /**
     * Podcast episode content with host and episode information.
     */
    PODCAST("Podcast"),
    
    /**
     * Audiobook content for longer-form narrated books.
     */
    AUDIOBOOK("Audiobook");
    
    /**
     * The human-readable display name of the media type.
     */
    private final String displayName;
    
    /**
     * Constructs a MediaType enum with the specified display name.
     * 
     * @param displayName the human-readable name of the media type
     */
    MediaType(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns the display name of the media type.
     * 
     * @return the human-readable media type name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns a string representation of the media type.
     * 
     * @return the display name of the media type
     */
    @Override
    public String toString() {
        return displayName;
    }
}


    

