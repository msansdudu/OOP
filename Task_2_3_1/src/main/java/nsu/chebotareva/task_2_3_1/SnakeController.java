package nsu.chebotareva.task_2_3_1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class SnakeController {
    @FXML private Canvas gameCanvas;
    @FXML private Button restartButton; // Добавили кнопку рестарта
    private SnakeModel game;
    private GraphicsContext gc;
    private final int cellSize = 40;
    private Timeline gameLoop;
    private final int width = 18;
    private final int height = 15;

    @FXML
    public void initialize() {
        game = new SnakeModel(width, height);
        gc = gameCanvas.getGraphicsContext2D();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        restartButton.setStyle(
                SnakeView.getReleasedButton()
        );

        gameCanvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    gameCanvas.requestFocus();
                    handleKeyPress(event);
                });
                gameCanvas.requestFocus();
            }
        });
        restartButton.setFocusTraversable(false);
        restartButton.setOnAction(e -> restartGame());

        restartButton.setOnMousePressed(event -> {
            restartButton.setStyle(
                    SnakeView.getPressedButton()
            );
        });

        restartButton.setOnMouseReleased(event -> {
            restartButton.setStyle(
                    SnakeView.getReleasedButton()
            );
        });
    }
    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> game.changeDirection("UP");
            case DOWN -> game.changeDirection("DOWN");
            case LEFT -> game.changeDirection("LEFT");
            case RIGHT -> game.changeDirection("RIGHT");
        }
    }

    private void updateGame() {
        game.move();
        SnakeView.render(gc, game, gameCanvas, cellSize);

        if (game.isGameOver() || game.isVictory()) {
            gameLoop.stop();
        }
    }

    private void restartGame() {
        game = new SnakeModel(width, height);
        gameLoop.stop();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        SnakeView.render(gc, game, gameCanvas, cellSize);
        gameCanvas.requestFocus();
    }
}