package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class IncidenceMatrixGraph extends GraphCl {
    private int[][] matrix;
    private int vertexAmount = 0;
    private int edgeAmount = 0;
    public ArrayList<Integer> listOfVertex = new ArrayList<>();
    private final ArrayList<Integer> listOfEdges = new ArrayList<>();

    @Override
    public int getVertexAmount() {
        return vertexAmount;
    }

    @Override
    public void addVertex() {
        int[][] newMatrix = new int[vertexAmount + 1][edgeAmount];
        for (int i = 0; i < vertexAmount; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeAmount);
        }
        for (int j = 0; j < edgeAmount; j++) {
            newMatrix[vertexAmount][j] = 0;
        }
        vertexAmount++;
        matrix = newMatrix;
        int u;
        if (vertexAmount == 1) {
            u = 1;
        } else {
            u = listOfVertex.get(vertexAmount - 2) + 1;
        }
        listOfVertex.add(u);
    }

    @Override
    public void removeVertex(int u) throws Exception {
        if (!listOfVertex.contains(u)) {
            throw new Exception("This vertices doesn't exist!");
        }
        int v = listOfVertex.indexOf(u);
        int[][] newMatrix = new int[vertexAmount - 1][edgeAmount];
        for (int i = 0; i < vertexAmount - 1; i++) {
            if (i < v) {
                System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeAmount);
            } else if (i > v) {
                System.arraycopy(matrix[i - 1], 0, newMatrix[i], 0, edgeAmount);
            }
        }
        vertexAmount--;
        matrix = newMatrix;
        listOfVertex.remove((Integer) u);
    }

    @Override
    public void addEdge(Edge e) throws Exception {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            throw new Exception("This vertices doesn't exist!");
        }
        int[][] newMatrix = new int[vertexAmount][edgeAmount + 1];
        for (int i = 0; i < vertexAmount; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, edgeAmount);
            newMatrix[i][edgeAmount] = 0;
        }
        newMatrix[e.getFrom() - 1][edgeAmount] = -1;
        newMatrix[e.getTo() - 1][edgeAmount] = 1;
        edgeAmount++;
        matrix = newMatrix;
        int u;
        if (edgeAmount == 1) {
            u = 1;
        } else {
            u = listOfEdges.get(edgeAmount - 2) + 1;
        }
        listOfEdges.add(u);
    }

    @Override
    public void removeEdge(Edge e) throws Exception {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            throw new Exception("This vertices doesn't exist!");
        }
        int[][] newMatrix = new int[vertexAmount][edgeAmount - 1];
        int num = -1;
        for (int i = 0; i < edgeAmount; i++) {
            if (matrix[e.getFrom() - 1][i] == -1 && matrix[e.getTo() - 1][i] == 1) {
                num = i;
            }
        }
        if (num == -1) {
            throw new Exception("This edge doesn't exist!");
        }
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < edgeAmount; j++) {
                if (j < num) {
                    newMatrix[i][j] = matrix[i][j];
                } else if (j > num) {
                    newMatrix[i][j - 1] = matrix[i][j];
                }
            }
        }
        edgeAmount--;
        matrix = newMatrix;
        listOfEdges.remove(num);
    }

    @Override
    public ArrayList<Integer> neighbors(int v) {
        v--;
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < edgeAmount; i++) {
            if (matrix[v][i] == -1) {
                for (int j = 0; j < vertexAmount; j++) {
                    if (matrix[j][i] == 1) {
                        neighbors.add(j + 1);
                    }
                }
            }
        }
        return neighbors;
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        System.out.println("How many vertex do we have?");
        vertexAmount = Integer.parseInt(sc.nextLine());
        System.out.println("Print incidence matrix:");
        String line = sc.nextLine();
        String[] frst = line.split(regex);
        edgeAmount = frst.length;
        matrix = new int[vertexAmount][edgeAmount];
        for (int i = 0; i < edgeAmount; i++) {
            matrix[0][i] = Integer.parseInt(frst[i]);
        }
        listOfVertex.add(1);
        listOfEdges.add(1);
        for (int i = 1; i < vertexAmount; i++) {
            for (int j = 0; j < edgeAmount; j++) {
                matrix[i][j] = sc.nextInt();
            }
            listOfVertex.add(i + 1);
            listOfEdges.add(i + 1);
        }
        sc.close();
    }

    @Override
    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> sortedList = new ArrayList<>();

        int[] inDegree = new int[vertexAmount];

        for (int i = 0; i < edgeAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                if (matrix[j][i] == 1) {
                    inDegree[j]++;
                }
            }
        }

        ArrayList<Integer> queue = new ArrayList<>();
        for (int i = 0; i < vertexAmount; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.remove(0);
            sortedList.add(listOfVertex.get(v));
            for (int i = 0; i < edgeAmount; i++) {
                if (matrix[v][i] == -1) {
                    for (int j = 0; j < vertexAmount; j++) {
                        if (matrix[j][i] == 1) {
                            inDegree[j]--;
                            if (inDegree[j] == 0) {
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }

        if (sortedList.size() != vertexAmount) {
            return null;
        }
        return sortedList;
    }

    @Override
    public void print() {
        System.out.print(" v\\e|");
        for (int i = 1; i < edgeAmount + 1; i++) {
            System.out.printf("%3d %s", listOfEdges.get(i - 1), i == edgeAmount ? "" : "|");
        }
        System.out.println();

        for (int i = 0; i < vertexAmount; i++) {
            for (int k = 0; k < edgeAmount + 1; k++) {
                System.out.print("-----");
            }
            System.out.println();
            System.out.printf("%3d |", i + 1);
            for (int j = 0; j < edgeAmount; j++) {
                System.out.printf("%3d %s", matrix[i][j], j == edgeAmount - 1 ? "" : "|");
            }
            System.out.println();
        }
    }
}
