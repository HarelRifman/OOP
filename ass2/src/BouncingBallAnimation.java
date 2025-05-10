/**
 * @authour Harel Rifman
 * ID 217398338
 * **/
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * The BouncingBallAnimation class is responsible for running an animation
 * where a ball bounces around within a defined area.
 */
public class BouncingBallAnimation {
    /**
     * Main method that runs the animation based on command line arguments.
     * The program expects four arguments: the initial x and y coordinates of the ball,
     * and the changes in x and y coordinates (velocity) per step.
     *
     * @param args Command line arguments where:
     *             args[0] is the initial x-coordinate of the ball,
     *             args[1] is the initial y-coordinate of the ball,
     *             args[2] is the change in x-coordinate (velocity) per step,
     *             args[3] is the change in y-coordinate (velocity) per step.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("not enough arguments");
            return;
        }

        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);

        Point center = new Point(x, y);
        drawAnimation(center, dx, dy);
    }

    /**
     * Draws an animation of a ball moving with a given velocity from a start point.
     * The animation continues until the ball's velocity becomes null.
     *
     * @param start The starting point of the ball.
     * @param dx    The change in the x-coordinate (velocity) of the ball per step.
     * @param dy    The change in the y-coordinate (velocity) of the ball per step.
     */
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (ball.getVelocity() != null) {
            ball.moveOneStep(200, 200);
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds.
        }
    }
}