import biuoop.DrawSurface;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a rectangle defined by its upper-left corner, width, and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with the specified location and dimensions.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a (possibly empty) list of intersection points with the specified line.
     *
     * @param line the line to check for intersections with the rectangle
     * @return a list of intersection points with the specified line
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        Line[] sides = new Line[4];

        // Define the sides of the rectangle
        sides[0] = new Line(this.upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        sides[1] = new Line(new Point(upperLeft.getX(), upperLeft.getY() + height),
                   new Point(upperLeft.getX() + width, upperLeft.getY() + height));
        sides[2] = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        sides[3] = new Line(new Point(upperLeft.getX() + width, upperLeft.getY()),
                   new Point(upperLeft.getX() + width, upperLeft.getY() + height));

        // Check intersection of the given line with each side
        boolean enter = true;
        for (Line side : sides) {
            Point intersection = line.intersectionWith(side);
            for (Point point : intersections) {
                if (point.equals(intersection)) {
                    enter = false;
                }
            }
            if (intersection != null && enter) {
                intersections.add(intersection);
            }
            enter = true;
        }

        return intersections;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}