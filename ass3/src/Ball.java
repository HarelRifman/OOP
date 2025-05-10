/**
 * @authour Harel Rifman
 * ID 217398338
 * **/
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * A class representing a ball in 2D space.
 */
public class Ball implements Sprite {
    private Point center;
    private final int radius;
    private final Color color;
    private Velocity velocity;
    private final GameEnvironment environment;

    /**
     * Constructor for creating a new Ball object.
     *
     * @param center      the center point of the ball
     * @param r           the radius of the ball
     * @param color       the color of the ball
     * @param environment the game environment
     */
    public Ball(Point center, int r, Color color, GameEnvironment environment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.environment = environment;
    }
    /**
     * Adds the ball to the game.
     *
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
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
     * Notifies the ball that time has passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Gets the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
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
     * Moves the ball one step based on its current velocity, and handles collisions
     * with obstacles or the edges of the screen.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, new Point(this.center.getX() + this.velocity.getDx(),
                this.center.getY() + this.velocity.getDy()));

        // Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        Point collisionPoint = collisionInfo.collisionPoint();
        Line centerToCol = new Line(this.center, collisionPoint);

        // Move the ball to "almost" the hit point, but just slightly before it
        double newPointX = (this.center.getX() * this.radius + collisionPoint.getX()
                * (centerToCol.length() - this.radius)) / (centerToCol.length());
        double newPointY = (this.center.getY() * this.radius + collisionPoint.getY()
                * (centerToCol.length() - this.radius)) / (centerToCol.length());
        // Update the ball's velocity according to the hit
        this.center = new Point(newPointX, newPointY);
        this.velocity = collisionInfo.collisionObject().hit(collisionPoint, this.velocity);
    }

    /**
     * Get the x-coordinate of the ball's center.
     *
     * @return the x-coordinate of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }


    /**
     * Get the y-coordinate of the ball's center.
     *
     * @return the y-coordinate of the center point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
}
