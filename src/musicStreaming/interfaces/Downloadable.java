package musicstreaming.interfaces;

/**
 * Interface for media content that can be downloaded for offline use.
 * 
 * WHY THIS INTERFACE EXISTS:
 * In our streaming system, only PREMIUM users can download media.
 * This interface marks which media types SUPPORT downloading.
 * Not all media can be downloaded (licensing, DRM, etc.)
 * 
 * DESIGN RATIONALE:
 * - Separates download CAPABILITY from media TYPE
 * - Song can be downloadable
 * - Podcast can be downloadable
 * - Future: Video could be downloadable
 * - Interface is flexible - any media type can implement it
 * 
 * WHY INTERFACE (not just a method in Media):
 * - Not all media is downloadable (licensing restrictions)
 * - Separates concerns: being playable ≠ being downloadable
 * - Optional capability, not core feature
 * - Follows Interface Segregation Principle (Lecture 05)
 * 
 * WHY THIS MATTERS:
 * - Premium users check: if (media instanceof Downloadable)
 * - Only attempt download if interface is implemented
 * - Prevents errors trying to download non-downloadable content
 * 
 * DESIGN PATTERN:
 * This is a "marker interface" pattern - marks capability
 * Java examples: Serializable, Cloneable
 * Our example: Downloadable
 * 
 * REAL-WORLD ANALOGY:
 * Like "Recyclable" - not all materials are recyclable,
 * but those that ARE implement specific recycling methods.
 * 
 * FUTURE-PROOF:
 * If we add Video, Photo, Document media types later,
 * each can independently decide if it's downloadable.
 * No changes needed to existing code.
 * 
 * @author Member 4
 * @version 1.0
 */
public interface Downloadable {
    
    /**
     * Download this media item for offline access.
     * 
     * WHY THIS METHOD:
     * - Premium users need offline access capability
     * - Downloads save media locally for playback without internet
     * - Core feature differentiating Free vs Premium users
     * 
     * WHY RETURN boolean:
     * - Indicates success or failure
     * - Downloads can fail (network issues, storage full, permissions)
     * - Caller needs to know if download succeeded
     * - Can show appropriate message to user
     * 
     * WHY NO PARAMETERS:
     * - Media object already knows its own data (title, file location)
     * - Download location could be system default
     * - Keep interface simple for basic use case
     * 
     * FUTURE ENHANCEMENT:
     * Could add parameters like:
     * - download(String destinationPath)
     * - download(Quality quality)  // HD, SD, Low
     * But keeping simple for now (YAGNI principle - You Aren't Gonna Need It)
     * 
     * IMPLEMENTATION VARIES BY CLASS:
     * - Song.download(): downloads audio file
     * - Podcast.download(): downloads episode audio
     * - Video.download(): would download video file
     * Each implements differently, but interface is same
     * 
     * USAGE EXAMPLE:
     * if (media instanceof Downloadable) {
     *     Downloadable d = (Downloadable) media;
     *     boolean success = d.download();
     *     if (success) {
     *         System.out.println("Downloaded!");
     *     }
     * }
     * 
     * PERMISSION CHECK:
     * In real implementation, this method would check:
     * - Is user Premium? (only Premium can download)
     * - Is there storage space?
     * - Is device online?
     * 
     * THIS DEMONSTRATES:
     * - Interface as capability marker (Lecture 05)
     * - Polymorphism (different media, same interface)
     * - Separation of concerns (download vs playback)
     * 
     * @return true if download successful, false if failed
     */
    public abstract boolean download();
}