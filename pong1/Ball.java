/**
 * This class represents the ball in the Pong game. It extends the Circle class and defines
 * properties and behaviors specific to the ball, such as movement and appearance.
 */
package com.example.pong1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    private double directionX; // Velocity in the X direction
    private double directionY; // Velocity in the Y direction
    static final int BALL_RADIUS = 10; // Default radius of the ball
    private static final Color BALL_COLOR = Color.WHITE; // Default color of the ball

    /**
     * Constructs a new ball with the specified center coordinates, radius, and color.
     * @param centerX The x-coordinate of the center of the ball.
     * @param centerY The y-coordinate of the center of the ball.
     * @param ballRadius The radius of the ball.
     * @param white The color of the ball.
     */
    public Ball(double centerX, double centerY, int ballRadius, Color white) {
        super(centerX, centerY, BALL_RADIUS);
        setFill(BALL_COLOR); // Set the fill color of the ball

        // Set initial velocities in the X and Y directions
        this.directionX = 1;
        this.directionY = 1;
    }

    /**
     * Moves the ball by updating its center coordinates based on its velocities.
     */
    public void move() {
        setCenterX(getCenterX() + directionX);
        setCenterY(getCenterY() + directionY);
    }

    /**
     * Resets the position of the ball to the center of the panel.
     * @param panelWidth The width of the panel.
     * @param panelHeight The height of the panel.
     */
    public void reset(double panelWidth, double panelHeight) {
        // Set the X coordinate to the center of the panel
        setCenterX(panelWidth / 2);
        // Set the Y coordinate to the center of the panel
        setCenterY(panelHeight / 2);
    }

    /**
     * Reverses the direction of the ball in the X axis.
     */
    public void reverseXDirection() {
        directionX *= -1;
    }

    /**
     * Reverses the direction of the ball in the Y axis.
     */
    public void reverseYDirection() {
        directionY *= -1;
    }

    /**
     * Increases the speed of the ball by multiplying its velocities by a factor.
     * @param speedIncrease The factor by which to increase the speed.
     */
    public void increaseSpeed(int speedIncrease) {
        directionX *= speedIncrease;
        directionY *= speedIncrease;
    }
}
