package musicStreaming.enums;
/**
 * Represents the type of user account in the music streaming system.
 * Determines the features and restrictions available to the user.
 * 
 * @author Sima

 */
public enum UserType {
    /**
     * Free user account with advertisements, limited skips, and no download
     * capability. Basic access to the streaming service with feature
     * restrictions.
     */
    FREE("Free User", false, true, false),
    /**
     * Premium user account with no advertisements, unlimited skips, and
     * download capability. Full access to all streaming service features.
     */
    PREMIUM("Premium User", true, false, true);

    /**
     * The human-readable display name of the user type.
     */
    private final String displayName;

    /**
     * Whether this user type can download media for offline playback.
     */
    private final boolean canDownload;

    /**
     * Whether this user type sees advertisements during playback.
     */
    private final boolean hasAds;

    /**
     * Whether this user type has unlimited skip capability.
     */
    private final boolean unlimitedSkips;

    /**
     * Constructs a UserType enum with the specified characteristics.
     *
     * @param displayName the human-readable name of the user type
     * @param canDownload whether downloads are allowed
     * @param hasAds whether advertisements are shown
     * @param unlimitedSkips whether unlimited skips are allowed
     */
    UserType(String displayName, boolean canDownload, boolean hasAds, boolean unlimitedSkips) {
        this.displayName = displayName;
        this.canDownload = canDownload;
        this.hasAds = hasAds;
        this.unlimitedSkips = unlimitedSkips;
    }

    /**
     * Returns the display name of the user type.
     *
     * @return the human-readable user type name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Checks if this user type can download media.
     *
     * @return true if downloads are allowed, false otherwise
     */
    public boolean canDownload() {
        return canDownload;
    }

    /**
     * Checks if this user type sees advertisements.
     *
     * @return true if ads are shown, false otherwise
     */
    public boolean hasAds() {
        return hasAds;
    }

    /**
     * Checks if this user type has unlimited skips.
     *
     * @return true if unlimited skips are allowed, false otherwise
     */
    public boolean hasUnlimitedSkips() {
        return unlimitedSkips;
    }

    /**
     * Returns a string representation of the user type.
     *
     * @return the display name of the user type
     */
    @Override
    public String toString() {
        return displayName;
    }
}
