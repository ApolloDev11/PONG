/**
 * This class represents the display panel in the Pong game, where player names and winner announcements
 * are shown. It extends the Pane class and manages the layout and appearance of text elements.
 */
package com.example.pong1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Display extends Pane {
    private final Text player1NameText; // Text representing player 1's name
    private final Text player2NameText; // Text representing player 2's name
    private final int winningScore; // The winning score of the game

    /**
     * Constructs a new Display with specified coordinates, player names, and winning score.
     * @param x The x-coordinate of the display panel.
     * @param y The y-coordinate of the display panel.
     * @param player1Name The name of player 1.
     * @param player2Name The name of player 2.
     * @param winningScore The winning score of the game.
     */
    public Display(double x, double y, String player1Name, String player2Name, int winningScore) {
        player1NameText = new Text(player1Name);
        player2NameText = new Text(player2Name);
        player1NameText.setFill(Color.BLUE); // Set the color of player 1's name text
        player2NameText.setFill(Color.RED); // Set the color of player 2's name text
        player1NameText.setFont(Font.font(24)); // Set the font size of player 1's name text
        player2NameText.setFont(Font.font(24)); // Set the font size of player 2's name text
        this.winningScore = winningScore;

        // Set the positions of player name texts
        player1NameText.setLayoutX(x);
        player1NameText.setLayoutY(y);
        player2NameText.setLayoutX(x + 800);
        player2NameText.setLayoutY(y);

        getChildren().addAll(player1NameText, player2NameText); // Add player name texts to the display
    }

    /**
     * Adjusts the layout of text elements horizontally based on the specified factor.
     * @param factor The factor by which to adjust the layout horizontally.
     */
    public void resizeX(double factor) {
        player1NameText.setLayoutX(player1NameText.getLayoutX() * factor);
        player2NameText.setLayoutX(player2NameText.getLayoutX() * factor);
    }

    /**
     * Adjusts the layout of text elements vertically based on the specified factor.
     * @param factor The factor by which to adjust the layout vertically.
     */
    public void resizeY(double factor) {
        player1NameText.setLayoutY(player1NameText.getLayoutY() * factor);
        player2NameText.setLayoutY(player2NameText.getLayoutY() * factor);
    }

    /**
     * Retrieves the name of player 1.
     * @return The name of player 1.
     */
    public String getLeftPlayerName() {
        return player1NameText.getText();
    }

    /**
     * Retrieves the name of player 2.
     * @return The name of player 2.
     */
    public String getRightPlayerName() {
        return player2NameText.getText();
    }

    /**
     * Announces the winner of the game by displaying a text message.
     * @param winner The name of the winning player.
     */
    public void announceWinner(String winner) {
        Text winnerText = new Text("Winner: " + winner);
        winnerText.setFill(Color.GREEN); // Set the color of the winner text
        winnerText.setFont(Font.font(36)); // Set the font size of the winner text
        // Set the position of the winner text at the center between player name texts
        winnerText.setLayoutX((player1NameText.getLayoutX() + player2NameText.getLayoutX() + player2NameText.getBoundsInLocal().getWidth()) / 2);
        winnerText.setLayoutY((player1NameText.getLayoutY() + player1NameText.getBoundsInLocal().getHeight()) / 2);
        getChildren().add(winnerText); // Add the winner text to the display
    }
}
