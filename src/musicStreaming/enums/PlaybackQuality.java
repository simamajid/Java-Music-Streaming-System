
package musicStreaming.enums;

/**
 * Represents the audio quality level for media playback in the streaming system.
 * Different quality levels affect bandwidth usage and audio fidelity.
 * 
 * @author Sima

 */
enum PlaybackQuality {
    /**
     * Low quality audio - 64 kbps bitrate, minimal bandwidth usage.
     * Suitable for limited data connections.
     */
    LOW("Low", 64),
    
    /**
     * Normal quality audio - 128 kbps bitrate, balanced quality and bandwidth.
     * Default quality for free users.
     */
    NORMAL("Normal", 128),
    
    /**
     * High quality audio - 256 kbps bitrate, good audio fidelity.
     * Recommended quality for premium users.
     */
    HIGH("High", 256),
    
    /**
     * Very high quality audio - 320 kbps bitrate, excellent audio fidelity.
     * Maximum quality available for premium users.
     */
    VERY_HIGH("Very High", 320);
    
    /**
     * The human-readable display name of the quality level.
     */
    private final String displayName;
    
    /**
     * The bitrate in kilobits per second (kbps) for this quality level.
     */
    private final int bitrate;
    
    /**
     * Constructs a PlaybackQuality enum with the specified display name and bitrate.
     * 
     * @param displayName the human-readable name of the quality level
     * @param bitrate the audio bitrate in kbps
     */
    PlaybackQuality(String displayName, int bitrate) {
        this.displayName = displayName;
        this.bitrate = bitrate;
    }
    
    /**
     * Returns the display name of the quality level.
     * 
     * @return the human-readable quality name
     */
    public String getDisplayName() {
        return displayName;
    }
    
    /**
     * Returns the bitrate of this quality level.
     * 
     * @return the bitrate in kbps
     */
    public int getBitrate() {
        return bitrate;
    }
    
    /**
     * Returns a string representation of the playback quality.
     * 
     * @return a formatted string with quality name and bitrate
     */
    @Override
    public String toString() {
        return displayName + " (" + bitrate + " kbps)";
    }
}
