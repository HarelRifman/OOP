package ScoreAndListiners;

/**
 * The HitNotifier interface should be implemented by any object that can notify
 * listeners of hit events (collisions).
 */
public interface HitNotifier {
    /**
     * Adds a HitListener to the list of listeners to be notified when a hit event occurs.
     *
     * @param hl the HitListener to add
     */
    void addHitListener(HitListener hl);

    /**
     * Removes a HitListener from the list of listeners.
     *
     * @param hl the HitListener to remove
     */
    void removeHitListener(HitListener hl);
}
