package musicStreaming.users;

import musicstreaming.users.User;
import musicstreaming.media.Media;

/**
 * CONCRETE CLASS: PremiumUser
 * 
 * PURPOSE:
 * Represents a premium-tier user in the music streaming system.
 * Premium users enjoy all features without limitations.
 * 
 * SPECIAL CHARACTERISTICS:
 * 1. No advertisements during playback
 * 2. Unlimited skips
 * 3. Can download media for offline use
 * 4. Higher audio quality (simulated)
 * 
 * OOP CONCEPTS APPLIED:
 * - Inheritance: Extends the abstract User class
 * - Method Overriding: Provides specific implementations of abstract methods
 * - Polymorphism: Can be treated as a User type
 */
public class PremiumUser extends User {
    
    // INSTANCE VARIABLES
    private boolean offlineModeEnabled; // Tracks if user has enabled offline mode
    
    /**
     * CONSTRUCTOR: PremiumUser
     * 
     * @param userId   Unique identifier for the user
     * @param username Display name for the user
     * 
     * PURPOSE:
     * Initializes a new PremiumUser with default settings.
     */
    public PremiumUser(String userId, String username) {
        super(userId, username); // Call parent class constructor
        this.offlineModeEnabled = false; // Start with offline mode disabled
    }
    
    /**
     * OVERRIDDEN METHOD: play
     * 
     * @param media The media item to be played
     * 
     * PURPOSE:
     * Implements media playback for premium users with enhanced features:
     * 1. No advertisements
     * 2. Unlimited skips
     * 3. High-quality audio
     * 
     * METHOD OVERRIDING:
     * Provides PremiumUser-specific implementation of the abstract play method.
     */
    @Override
    public void play(Media media) {
        System.out.println("--- Premium User Playback ---");
        
        // No advertisements for premium users
        
        // Play the media with premium features
        System.out.println("Now playing: " + media.getTitle());
        media.play();
        
        // Premium user benefits
        System.out.println("(Audio quality: High)");
        System.out.println("(No advertisements)");
        System.out.println("-------------------------------");
    }
    
    /**
     * METHOD: downloadMedia
     * 
     * @param media The media item to download
     * 
     * PURPOSE:
     * Allows premium users to download media for offline playback.
     * This is a PremiumUser-exclusive feature.
     */
    public void downloadMedia(Media media) {
        if (canDownload()) {
            System.out.println("Downloading '" + media.getTitle() + "' for offline use...");
            // Simulate download process
            try {
                Thread.sleep(500); // Simulate download time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Download complete! You can now listen offline.");
            offlineModeEnabled = true;
        }
    }
    
    /**
     * OVERRIDDEN METHOD: canDownload
     * 
     * @return true (Premium users can download)
     * 
     * PURPOSE:
     * Indicates that premium users have download permissions.
     * 
     * METHOD OVERRIDING:
     * Returns true specifically for PremiumUser class.
     */
    @Override
    public boolean canDownload() {
        return true;
    }
    
    /**
     * METHOD: enableOfflineMode
     * 
     * PURPOSE:
     * Enables offline playback mode for premium users.
     */
    public void enableOfflineMode() {
        if (canDownload()) {
            offlineModeEnabled = true;
            System.out.println("Offline mode enabled. You can play downloaded media.");
        }
    }
    
    /**
     * METHOD: disableOfflineMode
     * 
     * PURPOSE:
     * Disables offline playback mode.
     */
    public void disableOfflineMode() {
        offlineModeEnabled = false;
        System.out.println("Offline mode disabled.");
    }
    
    /**
     * METHOD: skipTrack
     * 
     * @return Always true for premium users
     * 
     * PURPOSE:
     * Premium users can skip tracks without limitations.
     * Always returns true (unlimited skips).
     */
    public boolean skipTrack() {
        System.out.println("Track skipped. (Unlimited skips available)");
        return true; // Premium users always can skip
    }
    
    /**
     * GETTER: isOfflineModeEnabled
     * 
     * @return Current offline mode status
     */
    public boolean isOfflineModeEnabled() {
        return offlineModeEnabled;
    }
    
    /**
     * OVERRIDDEN METHOD: displayUserInfo
     * 
     * PURPOSE:
     * Displays PremiumUser-specific information along with basic user info.
     * 
     * METHOD OVERRIDING:
     * Extends parent class method with PremiumUser-specific details.
     */
    @Override
    public void displayUserInfo() {
        super.displayUserInfo(); // Call parent class method
        System.out.println("Account Type: Premium Tier");
        System.out.println("Skips: Unlimited");
        System.out.println("Downloads Allowed: Yes");
        System.out.println("Offline Mode: " + (offlineModeEnabled ? "Enabled" : "Disabled"));
        System.out.println("Ads: No");
    }
}