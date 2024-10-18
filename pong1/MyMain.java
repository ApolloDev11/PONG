/**
 * This class serves as the entry point for the Pong game application. It contains the main method
 * that launches the JavaFX application by invoking the launch method of the Application class with
 * the specified GameFrame class.
 */
package com.example.pong1;

import javafx.application.Application;

public class MyMain {
    /**
     * The main method that launches the Pong game application.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Application.launch(GameFrame.class, args); // Launch the JavaFX application with GameFrame
    }
}
