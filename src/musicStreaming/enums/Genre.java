
package musicStreaming.enums;

/**
 * Represents the different music genres available in the music streaming system.
 * This enum categorizes songs into distinct musical styles and classifications.
 * 
 * @author Sima
 * 
 */
public enum Genre {
    /**
     * Popular music genre characterized by catchy melodies and wide appeal.
     */
    POP("Pop"),
    
    /**
     * Rock music genre featuring electric guitars and strong rhythms.
     */
    ROCK("Rock"),
    
    /**
     * Hip-hop music genre featuring rhythmic vocal delivery and beats.
     */
    HIP_HOP("Hip-Hop"),
    
    /**
     * Jazz music genre known for improvisation and complex harmonies.
     */
    JAZZ("Jazz"),
    
    /**
     * Classical music genre from Western art music tradition.
     */
    CLASSICAL("Classical"),
    
    /**
     * Electronic music genre produced using electronic instruments and technology.
     */
    ELECTRONIC("Electronic"),
    
    /**
     * Country music genre rooted in American folk traditions.
     */
    COUNTRY("Country"),
    
    /**
     * R&B (Rhythm and Blues) music genre combining soul, funk, and pop elements.
     */
    RNB("R&B"),
    
    /**
     * Reggae music genre originating from Jamaica with distinctive rhythm.
     */
    REGGAE("Reggae"),
    
    /**
     * Blues music genre characterized by specific chord progressions and emotional expression.
     */
    BLUES("Blues"),
    
    /**
     * Metal music genre featuring heavy guitar riffs and aggressive sounds.
     */
    METAL("Metal"),
    
    /**
     * Folk music genre representing traditional music of cultures and communities.
     */
    FOLK("Folk"),
    
    /**
     * Indie music genre representing independent, alternative music styles.
     */
    INDIE("Indie"),
    
    /**
     * Other or unclassified music genres not fitting standard categories.
     */
    OTHER("Other");
    
    /**
     * The human-readable display name of the genre.
     */
    private final String displayName;
    
    /**
     * Constructs a Genre enum with the specified display name.
     * 
     * @param displayName the human-readable name of the genre
     */
    Genre(String displayName) {
        this.displayName = displayName;
    }
    
    /**
     * Returns the display name of the genre.
     * 
     * @return the human-readable genre name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns a string representation of the genre.
     * 
     * @return the display name of the genre
     */
    @Override
    public String toString() {
        return displayName;
    }
}
