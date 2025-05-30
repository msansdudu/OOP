package nsu.chebotareva.task_2_3_1;

import java.util.LinkedList;
import java.util.Random;
import java.util.List;

public class SnakeModel {
    private final int victoryScore = 30;
    private final int maxFoodScore = 2;
    private final int width;
    private final int height;
    private final List<Snake> snakes;
    private final LinkedList<int[]> food;
    private final int[] foodScore;
    private final Random random;
    private final int amountOfFood = 3;
    private final List<AIStrategy> strategies;

    public static class Snake {
        private final LinkedList<int[]> body;
        private String direction;
        private boolean isAlive;
        private int score;
        private final String type;
        private final AIStrategy strategy;

        public Snake(int startX, int startY, String type, AIStrategy strategy) {
            this.body = new LinkedList<>();
            this.body.add(new int[]{startX, startY});
            this.direction = "RIGHT";
            this.isAlive = true;
            this.score = 0;
            this.type = type;
            this.strategy = strategy;
        }

        public LinkedList<int[]> getBody() {
            return body;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            this.isAlive = alive;
        }

        public int getScore() {
            return score;
        }

        public void increaseScore(int points) {
            this.score += points;
        }

        public String getType() {
            return type;
        }

        public AIStrategy getStrategy() {
            return strategy;
        }
    }

    public interface AIStrategy {
        String chooseDirection(Snake snake, SnakeModel model);
    }

    public static class RandomStrategy implements AIStrategy {
        private final Random random = new Random();

        @Override
        public String chooseDirection(Snake snake, SnakeModel model) {
            String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
            String current = snake.getDirection();
            String newDirection;
            do {
                newDirection = directions[random.nextInt(4)];
            } while (isOppositeDirection(current, newDirection));
            return newDirection;
        }

        private boolean isOppositeDirection(String current, String newDir) {
            return (current.equals("UP") && newDir.equals("DOWN")) ||
                    (current.equals("DOWN") && newDir.equals("UP")) ||
                    (current.equals("LEFT") && newDir.equals("RIGHT")) ||
                    (current.equals("RIGHT") && newDir.equals("LEFT"));
        }
    }

    public static class FoodSeekingStrategy implements AIStrategy {
        @Override
        public String chooseDirection(Snake snake, SnakeModel model) {
            int[] head = snake.getBody().getFirst();
            int[] closestFood = model.getClosestFood(head[0], head[1]);
            if (closestFood == null) return snake.getDirection();

            int dx = closestFood[0] - head[0];
            int dy = closestFood[1] - head[1];
            String current = snake.getDirection();

            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0 && !current.equals("LEFT")) return "RIGHT";
                if (dx < 0 && !current.equals("RIGHT")) return "LEFT";
            } else {
                if (dy > 0 && !current.equals("UP")) return "DOWN";
                if (dy < 0 && !current.equals("DOWN")) return "UP";
            }
            return current;
        }
    }

    public static class CautiousStrategy implements AIStrategy {
        @Override
        public String chooseDirection(Snake snake, SnakeModel model) {
            int[] head = snake.getBody().getFirst();
            String current = snake.getDirection();
            String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
            String bestDirection = current;

            for (String dir : directions) {
                if (isOppositeDirection(current, dir)) continue;
                int[] newHead = getNewHead(head, dir);
                if (!model.willCollideWithSnake(newHead[0], newHead[1], snake) &&
                        !model.isOutOfBounds(newHead[0], newHead[1])) {
                    bestDirection = dir;
                    break;
                }
            }
            return bestDirection;
        }

        private boolean isOppositeDirection(String current, String newDir) {
            return (current.equals("UP") && newDir.equals("DOWN")) ||
                    (current.equals("DOWN") && newDir.equals("UP")) ||
                    (current.equals("LEFT") && newDir.equals("RIGHT")) ||
                    (current.equals("RIGHT") && newDir.equals("LEFT"));
        }

        private int[] getNewHead(int[] head, String direction) {
            int newX = head[0], newY = head[1];
            switch (direction) {
                case "UP": newY--; break;
                case "DOWN": newY++; break;
                case "LEFT": newX--; break;
                case "RIGHT": newX++; break;
            }
            return new int[]{newX, newY};
        }
    }

    public SnakeModel(int width, int height, int robotCount) {
        this.width = width;
        this.height = height;
        this.snakes = new LinkedList<>();
        this.food = new LinkedList<>();
        this.random = new Random();
        this.strategies = List.of(new RandomStrategy(), new FoodSeekingStrategy(), new CautiousStrategy());
        this.foodScore = new int[SnakeView.sizeOfFoodImages()];

        // Инициализация змейки игрока
        snakes.add(new Snake(width / 2, height / 2, "PLAYER", null));

        // Инициализация роботов со случайными стратегиями
        int[][] startPositions = {
                {width / 4, height / 4},
                {3 * width / 4, height / 4},
                {width / 4, 3 * height / 4}
        };
        for (int i = 0; i < Math.min(robotCount, 3); i++) {
            int[] pos = startPositions[i];
            AIStrategy randomStrategy = strategies.get(random.nextInt(strategies.size()));
            snakes.add(new Snake(pos[0], pos[1], "ROBOT", randomStrategy));
        }

        // Инициализация очков за еду
        for (int i = 0; i < foodScore.length; i++) {
            foodScore[i] = random.nextInt(maxFoodScore) + 1;
        }

        // Создание еды с проверкой корректности очков
        for (int i = 0; i < amountOfFood; i++) {
            spawnFood(-1);
            if (food.get(i)[3] <= 0) {
                food.get(i)[3] = 1;
            }
        }
    }

    public void changeDirection(String newDirection, Snake snake) {
        if ((snake.getDirection().equals("UP") && !newDirection.equals("DOWN")) ||
                (snake.getDirection().equals("DOWN") && !newDirection.equals("UP")) ||
                (snake.getDirection().equals("LEFT") && !newDirection.equals("RIGHT")) ||
                (snake.getDirection().equals("RIGHT") && !newDirection.equals("LEFT"))) {
            snake.setDirection(newDirection);
        }
    }

    public void move() {
        List<Snake> deadRobots = new LinkedList<>();
        for (Snake snake : snakes) {
            if (!snake.isAlive()) {
                if (snake.getType().equals("ROBOT")) {
                    deadRobots.add(snake);
                }
                continue;
            }

            if (snake.getType().equals("ROBOT")) {
                String newDirection = snake.getStrategy().chooseDirection(snake, this);
                changeDirection(newDirection, snake);
            }

            int[] head = snake.getBody().getFirst();
            int newX = head[0], newY = head[1];

            switch (snake.getDirection()) {
                case "UP": newY--; break;
                case "DOWN": newY++; break;
                case "LEFT": newX--; break;
                case "RIGHT": newX++; break;
            }

            // Проверка столкновений со стенами
            if (newX < 0 || newY < 0 || newX >= width || newY >= height) {
                snake.setAlive(false);
                if (snake.getType().equals("ROBOT")) {
                    deadRobots.add(snake);
                }
                continue;
            }

            // Проверка столкновений со змейками
            if (willCollideWithSnake(newX, newY, snake)) {
                snake.setAlive(false);
                if (snake.getType().equals("ROBOT")) {
                    deadRobots.add(snake);
                }
                continue;
            }

            snake.getBody().addFirst(new int[]{newX, newY});
            boolean ateFood = false;
            for (int i = 0; i < amountOfFood; i++) {
                if (newX == food.get(i)[0] && newY == food.get(i)[1]) {
                    int points = food.get(i)[3];
                    if (points <= 0) {
                        points = 1;
                    }
                    snake.increaseScore(points);
                    if (snake.getScore() >= victoryScore) {
                        snake.setAlive(false);
                        if (snake.getType().equals("ROBOT")) {
                            deadRobots.add(snake);
                        }
                    }
                    spawnFood(i);
                    ateFood = true;
                    break;
                }
            }
            if (!ateFood) {
                snake.getBody().removeLast();
            }
        }

        // Возрождение мёртвых роботов
        for (Snake deadRobot : deadRobots) {
            int index = snakes.indexOf(deadRobot);
            snakes.set(index, respawnRobotSnake());
        }
    }

    private Snake respawnRobotSnake() {
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (collidesWithAnySnake(x, y) || foodCollision(x, y));
        AIStrategy randomStrategy = strategies.get(random.nextInt(strategies.size()));
        return new Snake(x, y, "ROBOT", randomStrategy);
    }

    private void spawnFood(int i) {
        int x, y;
        do {
            x = random.nextInt(width);
            y = random.nextInt(height);
        } while (foodCollision(x, y) || collidesWithAnySnake(x, y));
        int foodnum = random.nextInt(SnakeView.sizeOfFoodImages());
        if (foodnum >= foodScore.length) {
            foodnum = 0;
        }
        int points = foodScore[foodnum];
        if (points <= 0) {
            points = 1;
        }
        int[] newFood = new int[]{x, y, foodnum, points};
        if (i != -1) {
            food.set(i, newFood);
        } else {
            food.add(newFood);
        }
    }

    private boolean foodCollision(int x, int y) {
        return food.stream().anyMatch(elem -> elem[0] == x && elem[1] == y);
    }

    private boolean collidesWithAnySnake(int x, int y) {
        for (Snake snake : snakes) {
            if (snake.isAlive() && snake.getBody().stream().anyMatch(segment -> segment[0] == x && segment[1] == y)) {
                return true;
            }
        }
        return false;
    }

    private boolean willCollideWithSnake(int x, int y, Snake currentSnake) {
        for (Snake snake : snakes) {
            if (!snake.isAlive()) continue;
            for (int[] segment : snake.getBody()) {
                if (x == segment[0] && y == segment[1]) {
                    if (currentSnake.getType().equals("PLAYER")) {
                        return true;
                    }
                    if (currentSnake.getType().equals("ROBOT") && snake.getType().equals("PLAYER")) {
                        return true;
                    }
                    if (currentSnake.getType().equals("ROBOT") && currentSnake == snake && snake.getBody().getFirst() != segment) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[] getClosestFood(int x, int y) {
        int[] closest = null;
        double minDistance = Double.MAX_VALUE;
        for (int[] f : food) {
            double distance = Math.sqrt(Math.pow(f[0] - x, 2) + Math.pow(f[1] - y, 2));
            if (distance < minDistance) {
                minDistance = distance;
                closest = f;
            }
        }
        return closest;
    }

    public boolean isGameOver() {
        return !snakes.get(0).isAlive();
    }

    public boolean isVictory() {
        return snakes.get(0).getScore() >= victoryScore;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public int[] getFood(int i) {
        return food.get(i);
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

    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || y < 0 || x >= width || y >= height;
    }
}