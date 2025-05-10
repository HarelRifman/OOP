import Run.Game;

/**
 * This is the main class for Assignment 3 Game.
 * It initializes and runs the game.
 *
 * @author Harel Rifman
 * ID 217398338
 **/
public class Ass5Game {
    /**
     * The main method to start the game.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
