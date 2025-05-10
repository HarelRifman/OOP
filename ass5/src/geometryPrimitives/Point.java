package geometryPrimitives; /**
 * @authour Harel Rifman
 * ID 217398338
 * **/

/**
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a new Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance from this point to another point.
     *
     * @param other the other point to which the distance is calculated
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks whether this point is equal to another point.
     *
     * @param other the other point to compare with
     * @return true if the points have the same coordinates, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = 0.000001;
        return Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }
}
