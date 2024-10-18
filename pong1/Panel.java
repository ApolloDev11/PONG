/**
 * This class represents the game panel where all the logic of the Pong game happens. It manages the
 * initialization of game elements such as rackets, ball, scores, and display. It also handles collision
 * detection, user input for racket movement, and game state updates.
 */
package com.example.pong1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;


// This is where all the logic happens
public class Panel extends Pane {
    private static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    private Score leftPlayerScore;
    private Score rightPlayerScore;
    private Racket leftRacket;
    private Racket rightRacket;
    private Ball ball;
    private Display display;
    private int finalScore;
    private boolean gameEnded = false;
    private AnimationTimer gameLoop;
    private Button pauseButton;
    private Button continueButton;

    /**
     * Constructs a new Panel with specified player names and final score.
     * @param player1Name The name of the left player.
     * @param player2Name The name of the right player.
     * @param finalScore The final score at which the game ends.
     */
    public Panel(String player1Name, String player2Name, int finalScore) {
        this.finalScore = finalScore;
        display = new Display(50, 50, player1Name, player2Name, finalScore);
        leftPlayerScore = new Score(50, 150, Color.BLUE, finalScore);
        rightPlayerScore = new Score(900, 150, Color.RED, finalScore);
        leftRacket = new Racket(10, getHeight() / 2 - Racket.RACKET_HEIGHT / 2);
        rightRacket = new Racket(getWidth() - 20, getHeight() / 2 - Racket.RACKET_HEIGHT / 2);
        setPrefSize(WIDTH, HEIGHT);
        initRackets();
        initBall();
        getChildren().addAll(display, leftPlayerScore, rightPlayerScore);

        // Start game loop
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        gameLoop.start();
    }

    /**
     * Pauses the game.
     */
    // Method to pause the game
    void pauseGame() {
        gameLoop.stop();
    }

    // Method to continue the game
    void resumeGame() {
        gameLoop.start();
    }

    /**
     * Initializes the left and right rackets.
     */
    private void initRackets() {
        // Initialize left and right rackets
        leftRacket = new Racket(10, HEIGHT / 2 - Racket.RACKET_HEIGHT / 2);
        rightRacket = new Racket(WIDTH - 20, HEIGHT / 2 - Racket.RACKET_HEIGHT / 2);
        rightRacket.setFill(Color.RED);

        getChildren().addAll(leftRacket, rightRacket);
    }

    /**
     * Initializes the ball.
     */
    private void initBall() {
        // Initialize the ball
        ball = new Ball(WIDTH / 2, HEIGHT / 2, Ball.BALL_RADIUS, Color.WHITE);
        getChildren().add(ball);
    }

    /**
     * Resizes the game elements along the X-axis by a specified factor.
     * @param factor The factor by which to resize the game elements along the X-axis.
     */
    public void resizeX(double factor){
        leftRacket.setX(leftRacket.getX() * factor);
        rightRacket.setX(rightRacket.getX() * factor);
        ball.setCenterX(ball.getCenterX() * factor);
        display.resizeX(factor);
        leftPlayerScore.resizeX(factor);
        rightPlayerScore.resizeX(factor);
    }

    /**
     * Resizes the game elements along the Y-axis by a specified factor.
     * @param factor The factor by which to resize the game elements along the Y-axis.
     */
    public void resizeY(double factor){
        leftRacket.setY(leftRacket.getY() * factor);
        rightRacket.setY(rightRacket.getY() * factor);
        ball.setCenterY(ball.getCenterY() * factor);
        display.resizeY(factor);
        leftPlayerScore.resizeY(factor);
        rightPlayerScore.resizeY(factor);
    }

    /**
     * Checks for collisions with the walls of the game panel and handles them accordingly.
     */
    private void checkWallCollision() {
        double ballX = ball.getCenterX();
        double ballY = ball.getCenterY();
        double ballRadius = ball.getRadius();
        double panelWidth = getWidth();
        double panelHeight = getHeight();

        // Left wall collision
        if (ballX - ballRadius <= 0){
            // When the ball hits the left edge, increment the score for the right player
            rightPlayerScore.incrementScore();
            // Reset the ball position
            ball.reset(panelWidth, panelHeight);
            return;
        }

        // Right wall collision
        if (ballX + ballRadius >= panelWidth){
            // When the ball hits the right edge, increment the score for the left player
            leftPlayerScore.incrementScore();

            // Reset the ball position
            ball.reset(panelWidth, panelHeight);
            return;
        }

        // Top and bottom wall collision
        if (ballY - ballRadius <= 0 || ballY + ballRadius >= panelHeight){
            ball.reverseYDirection();
            ball.increaseSpeed(1); // Increase ball speed by 1 every time it bounces
        }
    }

    /**
     * Checks for collisions with the rackets and handles them accordingly.
     */
    private void checkRacketCollision(){
        if (ball.intersects(leftRacket.getBoundsInLocal()) || ball.intersects(rightRacket.getBoundsInLocal())) {
            ball.reverseXDirection();
        }
    }

    /**
     * Increases the speed of the ball by a specified amount.
     * @param speedIncrease The amount by which to increase the ball speed.
     */
    public void increaseBallSpeed(int speedIncrease){
        ball.increaseSpeed(speedIncrease);
    }

    /**
     * Increases the height of the rackets by a specified amount.
     * @param heightIncrease The amount by which to increase the height of the rackets.
     */
    public void increaseRacketHeight(double heightIncrease){
        double newHeight = leftRacket.getHeight() + heightIncrease;
        leftRacket.setHeight(newHeight);
        rightRacket.setHeight(newHeight);
    }

    /**
     * Moves the left racket upwards.
     */
    public void moveLeftRacketUp(){
        leftRacket.moveUp();
    }

    /**
     * Moves the left racket downwards.
     */
    public void moveLeftRacketDown(){
        leftRacket.moveDown();
    }

    /**
     * Stops the movement of the left racket.
     */
    public void stopLeftRacketMovement(){
        leftRacket.stopMovement();
    }

    /**
     * Moves the right racket upwards.
     */
    public void moveRightRacketUp(){
        rightRacket.moveUp();
    }

    public void moveRightRacketDown(){
        rightRacket.moveDown();
    }

    public void stopRightRacketMovement(){
        rightRacket.stopMovement();
    }


    /**
     * Announces the winner of the game and stops the game loop.
     */
    private void announceWinner() {
        String winner;
        if (leftPlayerScore.getScore() > rightPlayerScore.getScore()) {
            winner = display.getLeftPlayerName();
        } else {
            winner = display.getRightPlayerName();
        }
        display.announceWinner(winner);
        stopGameLoop();
    }

    /**
     * Stops the game loop when the game has ended.
     */
    private void stopGameLoop() {
        gameLoop.stop();
    }

    /**
     * Updates the game state by moving the ball, checking for collisions, updating racket movement,
     * and checking if the game has ended.
     */
    private void update(){
        // Move the ball.
        ball.move();

        // Check for collisions.
        checkWallCollision();
        checkRacketCollision();

        // Update racket movement.
        leftRacket.update();
        rightRacket.update();

        // Check if the game has ended.
        if (!gameEnded) {
            if (leftPlayerScore.getScore() >= finalScore || rightPlayerScore.getScore() >= finalScore) {
                announceWinner();
                gameEnded = true;
            }
        }
    }
}
