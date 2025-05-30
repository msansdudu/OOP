package nsu.chebotareva.task_2_3_1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class SnakeController {
    @FXML private Canvas gameCanvas;
    @FXML private Button restartButton;
    @FXML private ComboBox<String> robotCountComboBox;
    private SnakeModel game;
    private GraphicsContext gc;
    private final int cellSize = 40;
    private Timeline gameLoop;
    private final int width = 18;
    private final int height = 15;

    @FXML
    public void initialize() {
        // Инициализация с 0 роботов по умолчанию
        game = new SnakeModel(width, height, 0);
        gc = gameCanvas.getGraphicsContext2D();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        restartButton.setStyle(SnakeView.getReleasedButton());

        robotCountComboBox.setOnAction(event -> {
            String selected = robotCountComboBox.getSelectionModel().getSelectedItem();
            if (selected != null) {
                int robotCount = Integer.parseInt(selected);
                restartGame(robotCount);
            }
        });

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
        restartButton.setOnAction(e -> {
            String selected = robotCountComboBox.getSelectionModel().getSelectedItem();
            int robotCount = (selected != null) ? Integer.parseInt(selected) : 0;
            restartGame(robotCount);
        });

        restartButton.setOnMousePressed(event -> {
            restartButton.setStyle(SnakeView.getPressedButton());
        });

        restartButton.setOnMouseReleased(event -> {
            restartButton.setStyle(SnakeView.getReleasedButton());
        });
    }

    private void handleKeyPress(KeyEvent event) {
        // Управление только змейкой игрока
        SnakeModel.Snake playerSnake = game.getSnakes().get(0);
        switch (event.getCode()) {
            case UP -> game.changeDirection("UP", playerSnake);
            case DOWN -> game.changeDirection("DOWN", playerSnake);
            case LEFT -> game.changeDirection("LEFT", playerSnake);
            case RIGHT -> game.changeDirection("RIGHT", playerSnake);
        }
    }

    void updateGame() {
        game.move();
        SnakeView.render(gc, game, gameCanvas, cellSize);

        if (game.isGameOver() || game.isVictory()) {
            gameLoop.stop();
        }
    }

    private void restartGame(int robotCount) {
        game = new SnakeModel(width, height, robotCount);
        gameLoop.stop();
        gameLoop = new Timeline(new KeyFrame(Duration.millis(200), e -> updateGame()));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
        SnakeView.render(gc, game, gameCanvas, cellSize);
        gameCanvas.requestFocus();
    }
}