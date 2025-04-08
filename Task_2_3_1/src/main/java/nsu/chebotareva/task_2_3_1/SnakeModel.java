package nsu.chebotareva.task_2_3_1;

import java.util.LinkedList;
import java.util.Random;

public class SnakeModel {
    private final int victoryScore = 40;
    private final int maxFoodScore = 2;
    private final int width;
    private final int height;
    private final LinkedList<int[]> snake;
    private final LinkedList<int[]> food;
    private final int[] foodScore = new int[SnakeView.sizeOfFoodImages()];
    private String direction;
    private boolean gameOver;
    private boolean victory;
    private final Random random;
    private int score;
    private final int amountOfFood = 3;

    public SnakeModel(int width, int height) {
        this.width = width;
        this.height = height;
        this.snake = new LinkedList<>();
        this.snake.add(new int[]{width / 2, height / 2});
        this.food = new LinkedList<>();
        this.direction = "RIGHT";
        this.gameOver = false;
        this.random = new Random();
        for(int i = 0; i < amountOfFood; i++){
            spawnFood(-1);
        }
        this.score = 0;
        this.victory = false;
        for (int i = 0; i < SnakeView.sizeOfFoodImages(); i++) {
            foodScore[i] = random.nextInt(maxFoodScore) + 1;
        }
    }

    public void changeDirection(String newDirection) {
        if ((direction.equals("UP") && !newDirection.equals("DOWN")) ||
                (direction.equals("DOWN") && !newDirection.equals("UP")) ||
                (direction.equals("LEFT") && !newDirection.equals("RIGHT")) ||
                (direction.equals("RIGHT") && !newDirection.equals("LEFT"))) {
            direction = newDirection;
        }
    }

    public void move() {
        if (gameOver) return;

        int[] head = snake.getFirst();
        int newX = head[0], newY = head[1];

        switch (direction) {
            case "UP": newY--; break;
            case "DOWN": newY++; break;
            case "LEFT": newX--; break;
            case "RIGHT": newX++; break;
        }

        if (newX < 0 || newY < 0 || newX >= width || newY >= height || collidesWithSnake(newX, newY)) {
            gameOver = true;
            return;
        }

        snake.addFirst(new int[]{newX, newY});
        for (int i = 0; i < amountOfFood; i++) {
            if (newX == food.get(i)[0] && newY == food.get(i)[1]) {
                increaseScore(food.get(i)[3]);
                if (score >= victoryScore) {
                    victory = true;
                    return;
                }
                spawnFood(i);
                return;
            }
        }
        snake.removeLast();
    }

    private void spawnFood(int i) {
        int x, y;
        if (i != -1) {
            do {
                do {
                    x = random.nextInt(width);
                    y = random.nextInt(height);
                } while (foodCollision(x, y));
            } while (collidesWithSnake(x, y));
            int foodnum = random.nextInt(SnakeView.sizeOfFoodImages());
            food.set(i, new int[]{x, y, foodnum, foodScore[foodnum]});
        } else {
            do {
                do {
                    x = random.nextInt(width);
                    y = random.nextInt(height);
                } while (foodCollision(x, y));
            } while (collidesWithSnake(x, y));
            int foodnum = random.nextInt(SnakeView.sizeOfFoodImages());
            food.add(new int[]{x, y, foodnum, foodScore[foodnum]});
        }
    }

    private boolean foodCollision(int x, int y) {
        return food.stream().anyMatch(elem -> elem[0] == x && elem[1] == y);
    }

    private boolean collidesWithSnake(int x, int y) {
        return snake.stream().anyMatch(segment -> segment[0] == x && segment[1] == y);
    }

    public void increaseScore(int i) {
        this.score += i + 1;
    }

    public int getScore() {
        return score;
    }

    public LinkedList<int[]> getSnake() {
        return snake;
    }

    public int[] getFood(int i) {
        return food.get(i);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }
}