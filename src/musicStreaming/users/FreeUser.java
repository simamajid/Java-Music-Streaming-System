package musicstreaming.users;

import musicstreaming.media.Media;



/**
 * CONCRETE CLASS: FreeUser
 * 
 * PURPOSE:
 * Represents a free-tier user in the music streaming system.
 * Free users have limitations compared to premium users.
 * 
 * SPECIAL CHARACTERISTICS:
 * 1. Advertisements appear during playback
 * 2. Limited number of skips per session/day
 * 3. Cannot download media for offline use
 * 
 * OOP CONCEPTS APPLIED:
 * - Inheritance: Extends the abstract User class
 * - Method Overriding: Provides specific implementations of abstract methods
 * - Polymorphism: Can be treated as a User type
 */
public class FreeUser extends User {
    
    // CONSTANTS
    private static final int MAX_SKIPS = 5; // Maximum skips allowed for free users
    
    // INSTANCE VARIABLES
    private int skipCount; // Tracks number of skips used
    
    /**
     * CONSTRUCTOR: FreeUser
     * 
     * @param userId   Unique identifier for the user
     * @param username Display name for the user
     * 
     * PURPOSE:
     * Initializes a new FreeUser with default skip count (0).
     */
    public FreeUser(String userId, String username) {
        super(userId, username); // Call parent class constructor
        this.skipCount = 0; // Initialize skip count to zero
    }
    
    /**
     * OVERRIDDEN METHOD: play
     * 
     * @param media The media item to be played
     * 
     * PURPOSE:
     * Implements media playback for free users with specific restrictions:
     * 1. Shows advertisements before playing
     * 2. Checks skip limits
     * 3. Plays the media with free-tier quality
     * 
     * METHOD OVERRIDING:
     * Provides FreeUser-specific implementation of the abstract play method.
     */
    @Override
    public void play(Media media) {
        System.out.println("--- Free User Playback ---");
        
        // Show advertisement (Free user specific)
        showAdvertisement();
        
        // Check if user can skip (if they've reached skip limit)
        if (skipCount >= MAX_SKIPS) {
            System.out.println("Skip limit reached! You cannot skip this track.");
            System.out.println("Consider upgrading to Premium for unlimited skips.");
        }
        
        // Play the media
        System.out.println("Now playing: " + media.getTitle());
        media.play();
        
        // Free user specific message
        System.out.println("(Audio quality: Standard)");
        System.out.println("----------------------------");
    }
    
    /**
     * METHOD: skipTrack
     * 
     * @return boolean indicating if skip was successful
     * 
     * PURPOSE:
     * Allows free users to skip tracks within their limit.
     * Increments skip count each time a skip is performed.
     */
    public boolean skipTrack() {
        if (skipCount < MAX_SKIPS) {
            skipCount++;
            System.out.println("Track skipped. Skips used: " + skipCount + "/" + MAX_SKIPS);
            return true;
        } else {
            System.out.println("Cannot skip! Maximum skips (" + MAX_SKIPS + ") reached.");
            System.out.println("Upgrade to Premium for unlimited skips.");
            return false;
        }
    }
    
    /**
     * METHOD: showAdvertisement
     * 
     * PURPOSE:
     * Simulates showing an advertisement to free users.
     * This is a FreeUser-specific behavior.
     */
    private void showAdvertisement() {
        System.out.println("Advertisement: Try Premium for ad-free experience!");
        // Simulate ad display time
        try {
            Thread.sleep(1000); // Simulate 1 second ad
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * OVERRIDDEN METHOD: canDownload
     * 
     * @return false (Free users cannot download)
     * 
     * PURPOSE:
     * Indicates that free users do not have download permissions.
     * 
     * METHOD OVERRIDING:
     * Returns false specifically for FreeUser class.
     */
    @Override
    public boolean canDownload() {
        return false;
    }
    
    /**
     * METHOD: resetSkipCount
     * 
     * PURPOSE:
     * Resets the skip counter (could be called daily or per session).
     * Useful for implementing daily skip limits.
     */
    public void resetSkipCount() {
        skipCount = 0;
        System.out.println("Skip counter reset. You now have " + MAX_SKIPS + " skips available.");
    }
    
    /**
     * GETTER: getSkipCount
     * 
     * @return Current number of skips used
     */
    public int getSkipCount() {
        return skipCount;
    }
    
    /**
     * GETTER: getMaxSkips
     * 
     * @return Maximum allowed skips
     */
    public int getMaxSkips() {
        return MAX_SKIPS;
    }
    
    /**
     * METHOD: getRemainingSkips
     * 
     * @return Number of skips remaining
     */
    public int getRemainingSkips() {
        return MAX_SKIPS - skipCount;
    }
    
    /**
     * OVERRIDDEN METHOD: displayUserInfo
     * 
     * PURPOSE:
     * Displays FreeUser-specific information along with basic user info.
     * 
     * METHOD OVERRIDING:
     * Extends parent class method with FreeUser-specific details.
     */
    @Override
    public void displayUserInfo() {
        super.displayUserInfo(); // Call parent class method
        System.out.println("Account Type: Free Tier");
        System.out.println("Skips Used: " + skipCount + "/" + MAX_SKIPS);
        System.out.println("Downloads Allowed: No");
        System.out.println("Ads: Yes");
    }
}