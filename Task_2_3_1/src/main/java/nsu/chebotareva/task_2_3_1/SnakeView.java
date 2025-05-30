package nsu.chebotareva.task_2_3_1;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Objects;

public class SnakeView {
    static private String releasedButton = "-fx-background-color: royalblue; " +
            "-fx-background-radius: 15; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-pref-width: 150px; " +
            "-fx-pref-height: 50px; " +
            "-fx-effect: innershadow(gaussian, white, 10, 0, 0, 0);" +
            "-fx-transition: background-color 0.6s ease;";

    static private String pressedButton = "-fx-background-color: royalblue; " +
            "-fx-background-radius: 15; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10px 20px; " +
            "-fx-pref-width: 150px; " +
            "-fx-pref-height: 50px; " +
            "-fx-effect: innershadow(gaussian, black, 10, 0, 0, 0);" +
            "-fx-transition: background-color 0.6s ease;";

    static private final List<Image> foodImages = List.of(
            new Image(Objects.requireNonNull(SnakeView.class.getResourceAsStream("/nsu/chebotareva/task_2_3_1/apple.png"))),
            new Image(Objects.requireNonNull(SnakeView.class.getResourceAsStream("/nsu/chebotareva/task_2_3_1/banana.png"))),
            new Image(Objects.requireNonNull(SnakeView.class.getResourceAsStream("/nsu/chebotareva/task_2_3_1/cherry.png")))
    );

    static private final List<Color[]> snakeColors = List.of(
            new Color[]{Color.ORANGERED, Color.DARKRED, Color.MAROON}, // Игрок
            new Color[]{Color.BLUE, Color.DARKBLUE, Color.NAVY},       // Робот 1
            new Color[]{Color.PURPLE, Color.DARKMAGENTA, Color.DARKVIOLET}, // Робот 2
            new Color[]{Color.YELLOW, Color.GOLD, Color.DARKGOLDENROD} // Робот 3
    );

    public static String getReleasedButton() {
        return releasedButton;
    }

    public static String getPressedButton() {
        return pressedButton;
    }

    public static void backgroundRender(GraphicsContext gc, SnakeModel game, int cellSize) {
        for (int x = 0; x < game.getWidth(); x++) {
            for (int y = 0; y < game.getHeight(); y++) {
                Color color = (x + y) % 2 == 0 ? Color.LAWNGREEN : Color.GREENYELLOW;
                gc.setFill(color.desaturate());
                gc.fillRoundRect(x * cellSize, y * cellSize, cellSize * 0.97, cellSize * 0.97, 10, 10);
            }
        }
    }

    public static void gameIsOverRender(GraphicsContext gc, Canvas gameCanvas) {
        gc.setFill(Color.BLACK);
        gc.fillRoundRect(50, gameCanvas.getHeight() / 2 - 40, gameCanvas.getWidth() - 100, 60, 15, 15);
        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font(30));
        gc.fillText("Game Over", gameCanvas.getWidth() / 2 - 75, gameCanvas.getHeight() / 2);
    }

    public static void victoryRender(GraphicsContext gc, Canvas gameCanvas) {
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(50, gameCanvas.getHeight() / 2 - 40, gameCanvas.getWidth() - 100, 60, 15, 15);
        gc.setFill(Color.WHITE);
        gc.setFont(new javafx.scene.text.Font(30));
        gc.fillText("You win!", gameCanvas.getWidth() / 2 - 70, gameCanvas.getHeight() / 2);
    }

    public static void foodRender(GraphicsContext gc, SnakeModel game, int i, int cellSize) {
        int[] food = game.getFood(i);
        Image foodImage = foodImages.get(food[2]);
        gc.drawImage(foodImage, food[0] * cellSize, food[1] * cellSize, cellSize, cellSize);
    }

    public static void snakeRender(GraphicsContext gc, SnakeModel.Snake snake, int cellSize, int snakeIndex) {
        if (!snake.isAlive()) return;
        Color[] colors = snakeColors.get(snakeIndex % snakeColors.size());
        for (int i = 0; i < snake.getBody().size(); i++) {
            int[] segment = snake.getBody().get(i);
            if (i == 0) {
                gc.setFill(colors[0]);
            } else if (i == snake.getBody().size() - 1) {
                gc.setFill(colors[2]);
            } else {
                gc.setFill(colors[1]);
            }
            gc.fillRoundRect(segment[0] * cellSize, segment[1] * cellSize, cellSize * 0.95, cellSize * 0.95, 5, 5);
        }
    }

    public static void render(GraphicsContext gc, SnakeModel game, Canvas gameCanvas, int cellSize) {
        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        backgroundRender(gc, game, cellSize);
        for (int i = 0; i < game.getAmountOfFood(); i++) {
            foodRender(gc, game, i, cellSize);
        }
        for (int i = 0; i < game.getSnakes().size(); i++) {
            snakeRender(gc, game.getSnakes().get(i), cellSize, i);
        }
        gc.setFill(Color.BLACK);
        gc.setFont(new javafx.scene.text.Font("Arial", 20));
        gc.fillText("Score: " + game.getSnakes().get(0).getScore(), 10, 30);

        if (game.isVictory()) {
            victoryRender(gc, gameCanvas);
        } else if (game.isGameOver()) {
            gameIsOverRender(gc, gameCanvas);
        }
    }

    public static int sizeOfFoodImages() {
        return foodImages.size();
    }
}