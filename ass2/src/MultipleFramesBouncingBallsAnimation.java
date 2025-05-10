/**
 * @authour Harel Rifman
 * ID 217398338
 * **/
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * The MultipleFramesBouncingBallsAnimation class creates an animation of multiple balls bouncing
 * within two different frames on the same canvas.
 */
public class MultipleFramesBouncingBallsAnimation {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * The main method initializes the balls with random positions and velocities, then
     * starts the animation loop where the balls bounce around within their respective frames.
     *
     * @param args the sizes of the balls to be created; the first half bounce in the gray frame,
     *             and the second half bounce outside the yellow frame.
     */
    public static void main(String[] args) {
        if (args.length < 2 || args.length % 2 != 0) {
            System.out.println("There are not enough arguments or an odd amount of them.");
            return;
        }

        GUI gui = new GUI("title", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        Ball[] arr = new Ball[args.length];

        Random rand = new Random(); // create a random-number generator
        int randXVelChange = rand.nextInt(5) + 12;
        int randYVelChange = rand.nextInt(5) + 12;

        // Make the first half of the arguments bounce inside the gray frame
        for (int i = 0; i < args.length / 2; i++) {
            int r = Integer.parseInt(args[i]);
            int x = rand.nextInt(399 - 2 * r) + r + 51;
            int y = rand.nextInt(399 - 2 * r) + r + 51;
            Ball ball = new Ball(x, y, r, getRandomColor());

            if (ball.getSize() >= 50) {
                ball.setVelocity(30 / randXVelChange, 30 / randYVelChange);
            } else {
                ball.setVelocity((80 - r) / randXVelChange, (80 - r) / randYVelChange);
            }
            arr[i] = ball;
        }

        // Make the second half of the arguments bounce outside the yellow frame
        for (int i = 0; i < args.length / 2; i++) {
            int r = Integer.parseInt(args[i + args.length / 2]);
            int x = rand.nextInt(300 - 2 * r) + r + 500;
            int y = rand.nextInt(449 - 2 * r) + r;
            Ball ball = new Ball(x, y, r, getRandomColor());

            if (r >= 300) {
                System.out.println("The ball size you entered is too large.");
                return;
            }

            if (ball.getSize() >= 50) {
                ball.setVelocity(30 / randXVelChange, 30 / randYVelChange);
            } else {
                ball.setVelocity((80 - r) / randXVelChange, (80 - r) / randYVelChange);
            }
            arr[i + args.length / 2] = ball;
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Draw the gray frame from (50, 50) to (500, 500)
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);

            // Draw the yellow rectangle from (450, 450) to (600, 600)
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);

            for (int i = 0; i < arr.length / 2; i++) {
                arr[i].moveOneStepForGrayFrame();
                arr[i].drawOn(d);
                arr[i + arr.length / 2].moveOneStepForOutsideBalls();
                arr[i + arr.length / 2].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds
        }
    }

    /**
     * Generates a random color.
     *
     * @return a Color object with random red, green, and blue values
     */
    public static Color getRandomColor() {
        Random rand = new Random();
        int red = rand.nextInt(256);   // Generate a random value between 0 and 255
        int green = rand.nextInt(256); // Generate a random value between 0 and 255
        int blue = rand.nextInt(256);  // Generate a random value between 0 and 255
        return new Color(red, green, blue);
    }
}
