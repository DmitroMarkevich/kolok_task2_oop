package com.example.kolok_task2_oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;

public class HareAndFoxGame extends Application {
    private final double foxSpeed = 6.5;
    private final Set<KeyCode> pressedKeys = new HashSet<>();
    private double hareSpeed = 5.0;
    private int hareStep = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        Rectangle forest = new Rectangle(340, 50, 30, 30);
        forest.setFill(Color.GREEN);

        Rectangle hare = new Rectangle(50, 50, 20, 20);
        hare.setFill(Color.BLUE);

        Rectangle fox = new Rectangle(500, 300, 30, 30);
        fox.setFill(Color.RED);

        root.getChildren().addAll(forest, hare, fox);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        primaryStage.getScene().setOnKeyPressed(event -> {
            pressedKeys.add(event.getCode());
        });

        primaryStage.getScene().setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });

        new Thread(() -> {
            while (true) {
                if (pressedKeys.contains(KeyCode.UP)) {
                    hare.setY(hare.getY() - hareSpeed);
                }

                if (pressedKeys.contains(KeyCode.DOWN)) {
                    hare.setY(hare.getY() + hareSpeed);
                }

                if (pressedKeys.contains(KeyCode.LEFT)) {
                    hare.setX(hare.getX() - hareSpeed);
                }

                if (pressedKeys.contains(KeyCode.RIGHT)) {
                    hare.setX(hare.getX() + hareSpeed);
                }

                // Рух лиса
                if (pressedKeys.contains(KeyCode.W)) {
                    fox.setY(fox.getY() - foxSpeed);
                }

                if (pressedKeys.contains(KeyCode.S)) {
                    fox.setY(fox.getY() + foxSpeed);
                }

                if (pressedKeys.contains(KeyCode.A)) {
                    fox.setX(fox.getX() - foxSpeed);
                }

                if (pressedKeys.contains(KeyCode.D)) {
                    fox.setX(fox.getX() + foxSpeed);
                }

                if (hare.getBoundsInParent().intersects(fox.getBoundsInParent())) {
                    System.out.println("Лис виграв!");
                    System.exit(0);
                }

                if (hare.getBoundsInParent().intersects(forest.getBoundsInParent())) {
                    System.out.println("Заяць виграв!");
                    System.exit(0);
                }

                hareStep++;
                if (hareStep % 5 == 0) {
                    hareSpeed += 3;
                    hareSpeed -= 3;
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}