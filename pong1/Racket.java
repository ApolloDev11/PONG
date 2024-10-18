/**
 * This class represents a racket in the Pong game. It extends the Rectangle class and defines
 * properties and behaviors specific to the racket, such as movement and appearance.
 */
package com.example.pong1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Racket extends Rectangle {
    public static int RACKET_HEIGHT = 100; // Default height of the racket
    private static final int RACKET_WIDTH = 10; // Width of the racket
    private static final Color RACKET_COLOR = Color.BLUE; // Default color of the racket
    private static final int MOVEMENT_SPEED = 5; // Speed at which the racket moves
    private boolean movingUp = false; // Flag to indicate if the racket is moving up
    private boolean movingDown = false; // Flag to indicate if the racket is moving down

    /**
     * Constructs a new racket with the specified position.
     * @param x The x-coordinate of the racket.
     * @param y The y-coordinate of the racket.
     */
    public Racket(double x, double y) {
        super(x, y, RACKET_WIDTH, RACKET_HEIGHT);
        setFill(RACKET_COLOR); // Set the fill color of the racket
    }

    /**
     * Sets the flag to indicate that the racket should move up.
     */
    public void moveUp() {
        movingUp = true;
    }

    /**
     * Sets the flag to indicate that the racket should move down.
     */
    public void moveDown() {
        movingDown = true;
    }

    /**
     * Stops the movement of the racket.
     */
    public void stopMovement() {
        movingUp = false;
        movingDown = false;
    }

    /**
     * Updates the position of the racket based on its movement flags.
     */
    public void update() {
        // Move the racket up if the movingUp flag is true and it's not at the top edge
        if (movingUp && getY() > 0) {
            setY(getY() - MOVEMENT_SPEED);
        }
        // Move the racket down if the movingDown flag is true and it's not at the bottom edge
        if (movingDown && getY() < Panel.HEIGHT - RACKET_HEIGHT) {
            setY(getY() + MOVEMENT_SPEED);
        }
    }
}
