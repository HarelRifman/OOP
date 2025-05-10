/**
 * @authour Harel Rifman
 * ID 217398338
 * **/
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * This class demonstrates drawing random lines and identifying intersections.
 */
public class AbstractArtDrawing {
    static final int AMOUNT = 10;
    static final int POINTSIZE = 3;
    static final int WIDTH = 400;
    static final int HEIGHT = 300;
    /**
     * Draws random lines on a GUI window and identifies intersections.
     */
    public static void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is WIDTH pixels wide and HEIGHT pixels high.
        GUI gui = new GUI("Random Circles Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[AMOUNT];
        // Draw random lines
        for (int i = 0; i < 10; ++i) {
            int x1 = rand.nextInt(WIDTH) + 1;
            int y1 = rand.nextInt(HEIGHT) + 1;
            int x2 = rand.nextInt(WIDTH) + 1;
            int y2 = rand.nextInt(HEIGHT) + 1;
            arr[i] = new Line(x1, y1, x2, y2);
            d.setColor(Color.black);
            d.drawLine(x1, y1, x2, y2);
        }
        // Mark middle points of each line with blue circles
        for (int i = 0; i < AMOUNT; ++i) {
            Point middle = arr[i].middle();
            int x = (int) middle.getX();
            int y = (int) middle.getY();
            d.setColor(Color.blue);
            d.fillCircle(x, y, POINTSIZE);
        }
        // Check for intersections between lines
        for (int i = 0; i < AMOUNT; ++i) {
            for (int j = i + 1; j < AMOUNT; ++j) {
                if (arr[i].isIntersecting(arr[j])) {
                    d.setColor(Color.red);
                    Point intersection = arr[i].intersectionWith(arr[j]);
                    if (intersection != null) {
                        int x = (int) intersection.getX();
                        int y = (int) intersection.getY();
                        d.fillCircle(x, y, POINTSIZE);
                    }
                }
                if (!arr[j].isIntersecting(arr[i])) {
                    continue;
                }
                for (int k = j + 1; k < AMOUNT; ++k) {
                    if (arr[i].isIntersecting(arr[k]) && arr[k].isIntersecting(arr[j])) {
                        d.setColor(Color.green);
                        Point intersection1 = arr[i].intersectionWith(arr[k]);
                        Point intersection2 = arr[i].intersectionWith(arr[j]);
                        Point intersection3 = arr[k].intersectionWith(arr[j]);
                        if (intersection2 == null || intersection1 == null || intersection3 == null) {
                            continue;
                        }
                        int x1 = (int) intersection1.getX();
                        int y1 = (int) intersection1.getY();
                        int x2 = (int) intersection2.getX();
                        int y2 = (int) intersection2.getY();
                        int x3 = (int) intersection3.getX();
                        int y3 = (int) intersection3.getY();
                        // Draw triangle between intersections
                        d.drawLine(x1, y1, x2, y2);
                        d.drawLine(x1, y1, x3, y3);
                        d.drawLine(x2, y2, x3, y3);
                    }
                }
            }
        }
        // Show the GUI window
        gui.show(d);
    }
    /**
     * Main method to run the example.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        drawRandomLines();
    }
}
