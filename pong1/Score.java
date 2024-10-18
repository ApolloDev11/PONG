/**
 * This class represents the score display in the Pong game. It extends the Pane class and manages
 * the display and update of the game score. It provides methods to increment the score, retrieve
 * the current score, and resize the score display.
 */
package com.example.pong1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Pane {
    public int score; // The current score
    public Text scoreText; // Text representing the score
    private int winningScore; // The winning score of the game

    /**
     * Constructs a new Score display with specified coordinates, color, and winning score.
     * @param x The x-coordinate of the score display.
     * @param y The y-coordinate of the score display.
     * @param color The color of the score text.
     * @param finalScore The winning score of the game.
     */
    public Score(double x, double y, Color color, int finalScore) {
        this.score = 0; // Initialize the score to 0
        this.scoreText = new Text("Score: " + score); // Create the score text with initial value
        this.scoreText.setFill(color); // Set the color of the score text
        this.scoreText.setFont(Font.font(24)); // Set the font size of the score text
        this.scoreText.setLayoutX(x); // Set the layout position of the score text along the x-axis
        this.scoreText.setLayoutY(y); // Set the layout position of the score text along the y-axis
        this.winningScore = winningScore; // Set the winning score of the game
        getChildren().add(scoreText); // Add the score text to the display
    }

    /**
     * Adjusts the layout of the score display horizontally based on the specified factor.
     * @param factor The factor by which to adjust the layout horizontally.
     */
    public void resizeX(double factor) {
        scoreText.setLayoutX(scoreText.getLayoutX() * factor);
    }

    /**
     * Adjusts the layout of the score display vertically based on the specified factor.
     * @param factor The factor by which to adjust the layout vertically.
     */
    public void resizeY(double factor) {
        scoreText.setLayoutY(scoreText.getLayoutY() * factor);
    }

    /**
     * Increments the score by 1 and updates the score display.
     */
    public void incrementScore() {
        score++; // Increment the score
        scoreText.setText("Score: " + score); // Update the score text
    }

    /**
     * Retrieves the current score.
     * @return The current score.
     */
    public int getScore() {
        return score;
    }
}
