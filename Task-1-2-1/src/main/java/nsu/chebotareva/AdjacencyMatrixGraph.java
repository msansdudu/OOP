package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyMatrixGraph implements Graph {
    private Boolean[][] matrix;
    private final ArrayList<Integer> listOfVertex = new ArrayList<>();
    private int size = 0;

    @Override
    public void addVertex() {
        size++;
        Boolean[][] newMatrix = new Boolean[size][size];
        newMatrix[size - 1][size - 1] = false;
        for (int i = 0; i < size - 1; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, size - 1);
            newMatrix[i][size - 1] = false;
            newMatrix[size - 1][i] = false;
        }
        matrix = newMatrix;
        matrix[size - 1][size - 1] = true;
        int u;
        if (size == 1) {
            u = 1;
        } else {
            u = listOfVertex.getLast() + 1;
        }
        listOfVertex.add(u);
    }

    @Override
    public int removeVertex(int u) {
        if (!listOfVertex.contains(u)) {
            return 1;
        }
        int v = listOfVertex.indexOf(u);
        Boolean[][] newMatrix = new Boolean[size - 1][size - 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i < v) {
                    if (j < v) {
                        newMatrix[i][j] = matrix[i][j];
                    } else if (j > v) {
                        newMatrix[i][j - 1] = matrix[i][j];
                    }
                } else if (i > v) {
                    if (j < v) {
                        newMatrix[i - 1][j] = matrix[i][j];
                    } else if (j > v) {
                        newMatrix[i - 1][j - 1] = matrix[i][j];
                    }
                }
            }
        }
        size--;
        matrix = newMatrix;
        listOfVertex.remove((Integer) u);
        return 0;
    }

    @Override
    public int addEdge(Edge e) {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            return 1;
        }
        matrix[listOfVertex.indexOf(e.getFrom())][listOfVertex.indexOf(e.getTo())] = true;
        return 0;
    }

    @Override
    public int removeEdge(Edge e) {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo()) ||
                e.getFrom() == e.getTo()) {
            return 1;
        }
        if (!matrix[listOfVertex.indexOf(e.getFrom())][listOfVertex.indexOf(e.getTo())]) {
            return 1;
        }
        matrix[listOfVertex.indexOf(e.getFrom())][listOfVertex.indexOf(e.getTo())] = false;
        return 0;
    }

    @Override
    public ArrayList<Integer> neighbors(int v) {
        v = listOfVertex.indexOf(v);
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (matrix[v][i]) {
                neighbors.add(listOfVertex.get(i));
            }
        }
        return neighbors;
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        String line = sc.nextLine();
        String[] frst = line.split(regex);
        size = frst.length;
        matrix = new Boolean[size][size];
        for (int i = 0; i < size; i++) {
            matrix[0][i] = Integer.parseInt(frst[i]) == 1;
        }
        listOfVertex.add(1);
        int num;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                num = sc.nextInt();
                matrix[i][j] = num == 1;
            }
            listOfVertex.add(i + 1);
        }
        sc.close();
    }

    @Override
    public void topologicalSort() {

    }

    @Override
    public void print() {
        if (size > 0) {
            System.out.print("    |");
        }
        for (int i : listOfVertex) {
            System.out.printf("%3d %s", i, i == listOfVertex.getLast() ? "" : "|");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size + 1; k++) {
                System.out.print("-----");
            }
            System.out.println();
            System.out.printf("%3d |", listOfVertex.get(i));
            for (int j = 0; j < size; j++) {
                System.out.printf("%3d %s", matrix[i][j] ? 1 : 0, j == size - 1 ? "" : "|");
            }
            System.out.println();
        }
    }

    @Override
    public void isEqual() {

    }
}
