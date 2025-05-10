/**
 * @authour Harel Rifman
 * ID 217398338
 * **/

/**
 * The Velocity class represents a velocity with a change in position
 * on the x and y axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a Velocity instance with specified changes in x and y.
     *
     * @param dx the change in the x-axis
     * @param dy the change in the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Velocity instance from an angle and speed.
     *
     * @param angle the angle of the velocity in degrees
     * @param speed the speed of the velocity
     * @return a new Velocity instance based on the given angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Gets the change in the x-axis.
     *
     * @return the change in the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Gets the change in the y-axis.
     *
     * @return the change in the y-axis
     */
    public double getDy() {
        return dy;
    }

    /**
     * Applies this velocity to a given point, resulting in a new point.
     *
     * @param p the point to which the velocity is applied
     * @return a new Point with the updated position
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * Calculates the angle of the velocity in degrees, where 0 is upwards and angles increase clockwise.
     *
     * @return the angle of the velocity in degrees
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dx, -dy)); // Negative because 0 degrees is upwards
    }
}
