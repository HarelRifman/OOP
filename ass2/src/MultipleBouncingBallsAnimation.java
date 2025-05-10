/**
 * @authour Harel Rifman
 * ID 217398338
 * **/

import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

/**
 * The MultipleBouncingBallAnimation class creates an animation of multiple balls bouncing
 * within a specified area.  */
public class MultipleBouncingBallsAnimation {
    static final int WIDTH = 400;
    static final int HEIGHT = 300;

    /**
     * The main method initializes the balls with random positions and velocities, then
     * starts the animation loop where the balls bounce around within the window.
     *
     * @param args the sizes of the balls to be created
     */
    public static void main(String[] args) {
        Random rand = new Random(); // create a random-number generator
        Ball[] arr = new Ball[args.length];
        for (int i = 0; i < arr.length; i++) {
            int r = Integer.parseInt(args[i]);
            int x = rand.nextInt(WIDTH - 2 * r) + r + 1;
            int y = rand.nextInt(HEIGHT - 2 * r) + r + 1;
            Ball ball = new Ball(x, y, r, Color.black);

            int randXVelChange = rand.nextInt(10) + 8;
            int randYVelChange = rand.nextInt(10) + 8;

            if (ball.getSize() >= 50) {
                ball.setVelocity(30 /  (double) randXVelChange, (double) 30 / randYVelChange);
            } else {
                ball.setVelocity((80 - r) /  (double) randXVelChange, (80 - r) /  (double) randYVelChange);
            }
            arr[i] = ball;
        }

        GUI gui = new GUI("title", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        while (true) {
            for (int i = 0; i < arr.length; i++) {
                arr[i].moveOneStep();
            }
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < arr.length; i++) {
                arr[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50); // wait for 50 milliseconds
        }
    }
}
