import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing a ball in 2D space.
 */
public class Ball {
    static final int SCREEN_WIDTH = 400;
    static final int SCREEN_HEIGHT = 300;

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Constructor for creating a new Ball object.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructor to create a Ball object with specified coordinates, radius, and color.
     *
     * @param x the x-coordinate of the ball's center
     * @param y the y-coordinate of the ball's center
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(int x, int y, int r, Color color) {
        this(new Point(x, y), r, color);
    }
    /**
     * Sets the velocity of the ball.
     *
     * @param v the Velocity object to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball with specific dx and dy values.
     *
     * @param dx the change in x-direction
     * @param dy the change in y-direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * Gets the current velocity of the ball.
     *
     * @return the Velocity object representing the ball's velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * Moves the ball one step based on its current velocity, and reverses its velocity
     * if it collides with the edges of the screen.
     */
    public void moveOneStep() {
        if (this.center.getX() + this.radius >= SCREEN_WIDTH || this.center.getX() - this.radius <= 0) {
            this.setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (this.center.getY() + this.radius >= SCREEN_HEIGHT || this.center.getY() - this.radius <= 0) {
            this.setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moves the ball one step based on its current velocity and specified width and height.
     * Reverses its velocity if it collides with the edges of the specified area.
     *
     * @param width the width of the area
     * @param height the height of the area
     */
    public void moveOneStep(double width, double height) {
        if (this.center.getX() + this.radius >= width || this.center.getX() - this.radius <= 0) {
            this.setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (this.center.getY() + this.radius >= height || this.center.getY() - this.radius <= 0) {
            this.setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Handles collisions with a specified square, adjusting the ball's velocity based on
     * the side or corner it hits.
     *
     * @param leftSide the x-coordinate of the left side of the square
     * @param top the y-coordinate of the top side of the square
     * @param rightSide the x-coordinate of the right side of the square
     * @param bottom the y-coordinate of the bottom side of the square
     */
    public void collisionWithSquare(int leftSide, int top, int rightSide, int bottom) {
        // Check collision with yellow square
        boolean collision = false;
        boolean conerCollision = false;
        Point corner = null;
        Point[] corners = new Point[4];
        corners[0] = new Point(leftSide, top);
        corners[1] = new Point(rightSide, top);
        corners[2] = new Point(rightSide, bottom);
        corners[3] = new Point(leftSide, bottom);

        // Check for collision with the left and right sides of the square
        if (this.center.getX() + this.radius >= leftSide && this.center.getX() - this.radius <= rightSide) {
            // Check for collision with the top and bottom sides of the square
            if (this.center.getY() + this.radius >= top && this.center.getY() - this.radius <= bottom) {
                collision = true;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (this.center.distance(corners[i]) <= this.radius) {
                conerCollision = true;
                corner = corners[i];
            }
        }

        if (collision) {
            // Determine the distance to the square's edges
            double distToLeft = Math.abs(this.center.getX() - leftSide + radius);
            double distToRight = Math.abs(this.center.getX() - rightSide - radius);
            double distToTop = Math.abs(this.center.getY() - top + radius);
            double distToBottom = Math.abs(this.center.getY() - bottom - radius);

            double minDist = Math.min(Math.min(distToLeft, distToRight), Math.min(distToTop, distToBottom));
            // Adjust velocity based on which side was hit
            if (minDist == distToLeft || minDist == distToRight) {
                this.setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
            } else if (minDist == distToTop || minDist == distToBottom) {
                this.setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
            }
            return;
        }

        if (conerCollision) {
            this.setVelocity(new Velocity(-this.velocity.getDx(), -this.velocity.getDy()));
        }

    }

    /**
     * Moves the ball one step while checking for collisions with the predefined squares
     * and the edges of the screen.
     */
    public void moveOneStepForOutsideBalls() {
        collisionWithSquare(50, 50, 500, 500);
        collisionWithSquare(450, 450, 600, 600);
        //check collision with screen
        if (this.center.getX() + this.radius >= 800 || this.center.getX() - this.radius <= 0) {
            this.setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (this.center.getY() + this.radius >= 600 || this.center.getY() - this.radius <= 0) {
            this.setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moves the ball one step while checking for collisions with the predefined squares
     * and the edges of the screen.
     */
    public void moveOneStepForGrayFrame() {
        collisionWithSquare(450, 450, 600, 600);
        if (this.center.getX() + this.radius >= 500 || this.center.getX() - this.radius <= 50) {
            this.setVelocity(new Velocity(-this.velocity.getDx(), this.velocity.getDy()));
        }
        if (this.center.getY() + this.radius >= 500 || this.center.getY() - this.radius <= 50) {
            this.setVelocity(new Velocity(this.velocity.getDx(), -this.velocity.getDy()));
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Get the x-coordinate of the ball's center.
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the y-coordinate of the ball's center.
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the radius of the ball.
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get the color of the ball.
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
}
