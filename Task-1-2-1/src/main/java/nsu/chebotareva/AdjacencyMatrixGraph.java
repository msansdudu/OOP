package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class AdjacencyMatrixGraph implements Graph {
    private Boolean[][] matrix;
    public ArrayList<Integer> listOfVertex = new ArrayList<>();
    private int vertexAmount = 0;

    @Override
    public int getVertexAmount() {
        return vertexAmount;
    }

    @Override
    public ArrayList<Integer> getListOfVertex() {
        return listOfVertex;
    }

    @Override
    public void setListOfVertex(ArrayList<Integer> listV) {
        listOfVertex = listV;
    }

    @Override
    public void addVertex() {
        vertexAmount++;
        Boolean[][] newMatrix = new Boolean[vertexAmount][vertexAmount];
        newMatrix[vertexAmount - 1][vertexAmount - 1] = false;
        for (int i = 0; i < vertexAmount - 1; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, vertexAmount - 1);
            newMatrix[i][vertexAmount - 1] = false;
            newMatrix[vertexAmount - 1][i] = false;
        }
        matrix = newMatrix;
        matrix[vertexAmount - 1][vertexAmount - 1] = true;
        int u;
        if (vertexAmount == 1) {
            u = 1;
        } else {
            u = listOfVertex.get(vertexAmount - 2) + 1;
        }
        listOfVertex.add(u);
    }

    @Override
    public int removeVertex(int u) {
        if (!listOfVertex.contains(u)) {
            return 1;
        }
        int v = listOfVertex.indexOf(u);
        Boolean[][] newMatrix = new Boolean[vertexAmount - 1][vertexAmount - 1];
        for (int i = 0; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
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
        vertexAmount--;
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
        for (int i = 0; i < vertexAmount; i++) {
            if (matrix[v][i] && v != i) {
                neighbors.add(listOfVertex.get(i));
            }
        }
        return neighbors;
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        System.out.println("Print adjacency matrix:");
        String line = sc.nextLine();
        String[] frst = line.split(regex);
        vertexAmount = frst.length;
        matrix = new Boolean[vertexAmount][vertexAmount];
        for (int i = 0; i < vertexAmount; i++) {
            matrix[0][i] = Integer.parseInt(frst[i]) == 1;
        }
        listOfVertex.add(1);
        int num;
        for (int i = 1; i < vertexAmount; i++) {
            for (int j = 0; j < vertexAmount; j++) {
                num = sc.nextInt();
                matrix[i][j] = num == 1;
            }
            listOfVertex.add(i + 1);
        }
        sc.close();
    }

    @Override
    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        boolean[] visited = new boolean[vertexAmount];
        boolean[] inProcess = new boolean[vertexAmount];

        boolean hasCycle = false;

        for (int i = 0; i < vertexAmount; i++) {
            if (!visited[i]) {
                if (dfsTopologicalSort(i, visited, inProcess, sortedList)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        if (hasCycle) {
            return null;
        }
        Collections.reverse(sortedList);
        return sortedList;
    }

    private boolean dfsTopologicalSort(int v, boolean[] visited, boolean[] inProcess, ArrayList<Integer> sortedList) {
        visited[v] = true;
        inProcess[v] = true;

        for (int i = 0; i < vertexAmount; i++) {
            if (v != i && matrix[v][i]) {
                if (inProcess[i]) {
                    return true;
                }
                if (!visited[i] && dfsTopologicalSort(i, visited, inProcess, sortedList)) {
                    return true;
                }
            }
        }

        inProcess[v] = false;
        sortedList.add(listOfVertex.get(v));
        return false;
    }

    @Override
    public void print() {
        if (vertexAmount > 0) {
            System.out.print("    |");
        }
        for (int i : listOfVertex) {
            System.out.printf("%3d %s", i, i == listOfVertex.get(vertexAmount - 1) ? "" : "|");
        }
        System.out.println();

        for (int i = 0; i < vertexAmount; i++) {
            for (int k = 0; k < vertexAmount + 1; k++) {
                System.out.print("-----");
            }
            System.out.println();
            System.out.printf("%3d |", listOfVertex.get(i));
            for (int j = 0; j < vertexAmount; j++) {
                System.out.printf("%3d %s", matrix[i][j] ? 1 : 0, j == vertexAmount - 1 ? "" : "|");
            }
            System.out.println();
        }
    }

    @Override
    public Boolean isEqual(Graph g) {
        if (vertexAmount != g.getVertexAmount()) {
            return false;
        }
        if (vertexAmount == 0) {
            return true;
        }
        ArrayList<Integer> listV = g.getListOfVertex();
        g.setListOfVertex(listOfVertex);
        for (int i = 1; i <= vertexAmount; i++) {
            if (!Objects.equals(this.neighbors(i), g.neighbors(i))) {
                g.setListOfVertex(listV);
                return false;
            }
        }
        g.setListOfVertex(listV);
        return true;
    }
}
