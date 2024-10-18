/**
 * This class represents the main frame of the Pong game. It handles the initialization of the game,
 * including setting up player names, final score, game controls, and UI elements such as buttons.
 * It also manages user interactions and scene resizing.
 */
package com.example.pong1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import java.util.Optional;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;

public class GameFrame extends Application {
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 1000;
    private String leftPlayerName;
    private String rightPlayerName;
    private int finalScore;

    /**
     * The entry point for launching the Pong game application.
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Asking for the player on the left hand side their name
        TextInputDialog leftPlayerDialog = new TextInputDialog();
        leftPlayerDialog.setTitle("Left Player Name");
        leftPlayerDialog.setHeaderText("Enter left player's name:");
        Optional<String> leftPlayerResult = leftPlayerDialog.showAndWait();
        leftPlayerResult.ifPresent(name -> leftPlayerName = name);

        // Asking for the player on the right hand side their name
        TextInputDialog rightPlayerDialog = new TextInputDialog();
        rightPlayerDialog.setTitle("Right Player Name");
        rightPlayerDialog.setHeaderText("Enter right player's name:");
        Optional<String> rightPlayerResult = rightPlayerDialog.showAndWait();
        rightPlayerResult.ifPresent(name -> rightPlayerName = name);

        // Asking the user for the final score
        TextInputDialog finalScoreDialog = new TextInputDialog();
        finalScoreDialog.setTitle("Final Score");
        finalScoreDialog.setHeaderText("Enter the final score:");
        Optional<String> finalScoreResult = finalScoreDialog.showAndWait();
        finalScoreResult.ifPresent(score -> finalScore = Integer.parseInt(score));

        Platform.runLater(() -> {
            // Asking the user to set a ball speed
            TextInputDialog ballSpeedDialog = new TextInputDialog();
            ballSpeedDialog.setTitle("Ball Speed");
            ballSpeedDialog.setHeaderText("Change Ball Speed");
            ballSpeedDialog.setContentText("Enter the amount by which you want to increase the ball speed:");
            Optional<String> ballSpeedResult = ballSpeedDialog.showAndWait();
            ballSpeedResult.ifPresent(speed -> {
                int ballSpeedIncrease = Integer.parseInt(speed);

                // Access the Panel instance from the root of the scene
                StackPane root = (StackPane) primaryStage.getScene().getRoot();
                Panel panel = (Panel) root.getChildren().get(0); // Assuming Panel is the first child
                panel.increaseBallSpeed(ballSpeedIncrease);
            });
        });

        Platform.runLater(() -> {
            // Asking the user to change the racket size
            TextInputDialog racketSizeDialog = new TextInputDialog();
            racketSizeDialog.setTitle("Racket Size");
            racketSizeDialog.setHeaderText("Change Racket Size");
            racketSizeDialog.setContentText("Enter the amount by which you want to increase the racket size:");
            Optional<String> racketSizeResult = racketSizeDialog.showAndWait();
            racketSizeResult.ifPresent(size -> {
                int racketSizeIncrease = Integer.parseInt(size);

                // Access the Panel instance from the root of the scene
                StackPane root = (StackPane) primaryStage.getScene().getRoot();
                Panel panel = (Panel) root.getChildren().get(0); // Assuming Panel is the first child
                panel.increaseRacketHeight(racketSizeIncrease);
            });
        });

        // Created Panel with player names and scores
        Panel panel = new Panel(leftPlayerName, rightPlayerName, finalScore);
        panel.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        StackPane root = new StackPane(panel);
        root.setAlignment(Pos.CENTER); // Centers the panel
        root.setStyle("-fx-background-color: black;"); // Sets bg colour to black

        // Create pause and continue buttons
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(event -> {
            Panel p = (Panel) root.getChildren().get(0); // Assuming Panel is the first child
            p.pauseGame();
        });

        Button continueButton = new Button("Continue");
        continueButton.setOnAction(event -> {
            Panel p = (Panel) root.getChildren().get(0); // Assuming Panel is the first child
            p.resumeGame();
        });

        // Add buttons to HBox
        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(pauseButton, continueButton);
        buttonBox.setAlignment(Pos.TOP_CENTER); // Position of the Pause and Continue buttons

        // Add the button box to the root
        root.getChildren().add(buttonBox);

        // Create scene
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        primaryStage.setTitle("Pong Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Listeners for resizing (responsiveness)
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            double factor = newValue.doubleValue() / oldValue.doubleValue();
            panel.resizeX(factor);
        });

        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            double factor = newValue.doubleValue() / oldValue.doubleValue();
            panel.resizeY(factor);
        });

        // Key pressed listeners
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W) {
                panel.moveLeftRacketUp();
            } else if (code == KeyCode.S) {
                panel.moveLeftRacketDown();
            } else if (code == KeyCode.O) {
                panel.moveRightRacketUp();
            } else if (code == KeyCode.L) {
                panel.moveRightRacketDown();
            }
        });

        scene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W || code == KeyCode.S) { // key W and S for the movement of the left player
                panel.stopLeftRacketMovement();
            } else if (code == KeyCode.O || code == KeyCode.L) { //  key O and L for the movement of the left player
                panel.stopRightRacketMovement();
            }
        });


    }
}
