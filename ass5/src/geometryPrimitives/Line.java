package geometryPrimitives;
/**
 * @authour Harel Rifman
 * ID 217398338
 **/
import java.util.List;
/**
 * Represents a line segment in a 2D coordinate system.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructs a new Line with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * If this line intersects with the rectangle, returns the closest intersection point to the start of the line.
     * Otherwise, returns null.
     *
     * @param rect the rectangle to check for intersections with this line
     * @return the closest intersection point to the start of the line, or null if no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }

        Point closestPoint = intersections.get(0);
        double minDistance = start.distance(closestPoint);

        // finds the closest point to the start of the line
        for (Point point : intersections) {
            double distance = start.distance(point);
            if (distance < minDistance) {
                closestPoint = point;
                minDistance = distance;
            }
        }

        return closestPoint;
    }


    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Checks if this line intersects with another line.
     *
     * @param other the other line to check intersection with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // Calculate the orientations required for general and special cases
        int d1 = this.directionPointRelToLine(other.start());
        int d2 = this.directionPointRelToLine(other.end());
        int d3 = other.directionPointRelToLine(this.start());
        int d4 = other.directionPointRelToLine(this.end());

        // General case: Lines intersect if the orientations are different
        if (d1 != d2 && d3 != d4) {
            return true;
        }

        // Special cases: Handle collinear points that lie on the segment
        return ((d1 == 0) && this.isPointOnLine(other.start()))
                || ((d2 == 0) && this.isPointOnLine(other.end()))
                || ((d3 == 0) && other.isPointOnLine(this.start()))
                || ((d4 == 0) && other.isPointOnLine(this.end()))
                || this.start.equals(other.end())
                || this.start.equals(other.start)
                || this.end.equals(other.end())
                || this.end.equals(other.start());

        // No intersection found
    }

    /**
     * Determines the orientation of the ordered triplet (start, end, point).
     *
     * @param point the third point
     * @return 0 if start, end, and point are on the same line, 1 if to the right, 2 if to the left
     */
    private int directionPointRelToLine(Point point) {
        double slopeCalc = (this.end.getY() - this.start.getY()) * (point.getX() - this.end.getX())
                - (this.end.getX() - this.start.getX()) * (point.getY() - this.end.getY());
        if (slopeCalc == 0) {
            return 0; // three of them are on the same line
        }
        return (slopeCalc > 0) ? 1 : 2; // two options determine the direction relative to the line
    }

    /**
     * Checks if a given point lies on the segment defined by this line.
     *
     * @param point the point to check
     * @return true if the point lies on the segment, false otherwise
     */
    private boolean isPointOnLine(Point point) {
        double pointX = point.getX();
        double pointY = point.getY();
        double startX = this.start.getX();
        double startY = this.start.getY();
        double endX = this.end.getX();
        double endY = this.end.getY();
        double epsilon = 0.0001;

        if (point.equals(this.start) || point.equals(this.end)) {
            return true;
        }
        // Check if the point is within the bounding box of the line segment
        if (pointX > Math.max(startX, endX) || pointX < Math.min(startX, endX)) {
            return false;
        }
        if (pointY > Math.max(startY, endY) || pointY < Math.min(startY, endY)) {
            return false;
        }
        double bottomOfM = endX - startX;
        double topOfM = endY - startY;
        // Handle vertical and horizontal lines separately to avoid division by zero
        if (bottomOfM == 0) {
            return Math.abs(pointX - startX) < epsilon;
        }
        if (topOfM == 0) {
            return Math.abs(pointY - startY) < epsilon;
        }
        // For non-vertical and non-horizontal lines, use the slope comparison
        double slopeSegment =  topOfM / bottomOfM;
        double slopePointStart = (pointY - startY) / (pointX - startX);
        double slopePointEnd = (pointY - endY) / (pointX - endX);
        return Math.abs(slopeSegment - slopePointStart) < epsilon && Math.abs(slopeSegment - slopePointEnd) < epsilon;
    }
    /**
     * Checks if two line segments overlap.
     *
     * @param other The other line segment to check for overlap.
     * @return true if the segments overlap, false otherwise
     */
    private boolean isOverlapping(Line other) {
        if (this.start.equals(other.start)) {
            return this.isPointOnLine(other.end()) || other.isPointOnLine(this.end);
        }
        if (this.start.equals(other.end)) {
            return this.isPointOnLine(other.start()) || other.isPointOnLine(this.end);
        }
        if (this.end.equals(other.start)) {
            return this.isPointOnLine(other.end()) || other.isPointOnLine(this.start);
        }
        if (this.end.equals(other.end)) {
            return this.isPointOnLine(other.start()) || other.isPointOnLine(this.start);
        }
        return this.isPointOnLine(other.start) || this.isPointOnLine(other.end)
                || other.isPointOnLine(this.start) || other.isPointOnLine(this.end);
    }

    /**
     * Computes the intersection point between this line segment and another line segment.
     * Returns the intersection point if it exists, otherwise returns null.
     *
     * @param other The other line segment to find the intersection with.
     * @return The intersection point if it exists, otherwise null.
     */
    public Point intersectionWith(Line other) {
        double epsilon = 0.001;
        // Check if the lines intersect
        if (!this.isIntersecting(other)) {
            return null;
        }
        // Handle cases where either line segment is a single point
        if (this.start.equals(this.end)) {
            return other.isPointOnLine(this.start) ? this.start : null;
        }
        if (other.start.equals(other.end)) {
            return this.isPointOnLine(other.start) ? other.start : null;
        }

        // For non-vertical lines
        if (this.start.getX() != this.end.getX() && other.start.getX() != other.end.getX()) {
            // Calculate slopes and intercepts of both lines
            double m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double b1 = this.start.getY() - m1 * this.start.getX();
            double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            double b2 = other.start.getY() - m2 * other.start.getX();

            if (Math.abs(m1 - m2) < epsilon) {
                // Check if the lines are collinear
                if (Math.abs(b1 - b2) < epsilon) {
                    if (this.isOverlapping(other)) {
                        return null;
                    }
                    if (this.start.equals(other.start)) {
                        return this.start;
                    }
                    if (this.end.equals(other.end)) {
                        return this.end;
                    }
                    if (this.start.equals(other.end)) {
                        return this.start;
                    }
                    if (this.end.equals(other.start)) {
                        return this.end;
                    }
                }
                return null; // Parallel but not collinear
            }

            // Calculate intersection point coordinates
            double x = (b2 - b1) / (m1 - m2);
            double y = m1 * x + b1;

            return new Point(x, y);
        }

        // Handle case where this line is vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < epsilon && other.start.getX() != other.end.getX()) {
            double m2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
            double b2 = other.start.getY() - m2 * other.start.getX();
            double x = this.start.getX();
            double y = m2 * x + b2;

            Point intersection = new Point(x, y);
            return this.isPointOnLine(intersection) && other.isPointOnLine(intersection) ? intersection : null;
        }

        // Handle case where other line is vertical
        if (Math.abs(other.start.getX() - other.end.getX()) < epsilon && this.start.getX() != this.end.getX()) {
            double m1 = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
            double b1 = this.start.getY() - m1 * this.start.getX();
            double x = other.start.getX();
            double y = m1 * x + b1;
            Point intersection = new Point(x, y);
            return this.isPointOnLine(intersection) && other.isPointOnLine(intersection) ? intersection : null;
        }

        // Handle case where both lines are vertical
        if (Math.abs(this.start.getX() - this.end.getX()) < epsilon && other.start.getX() == other.end.getX()) {
            if (this.start.getX() != other.start.getX()) {
                return null; // Parallel vertical lines with different x coordinates
            }
            // Both lines are collinear and vertical
            if (this.isOverlapping(other)) {
                return null; // Overlapping lines intersect in infinite points
            }
            if (this.start.equals(other.start)) {
                return this.start;
            }
            if (this.end.equals(other.end)) {
                return this.end;
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
        }
        return null; // No intersection point found
    }
}
