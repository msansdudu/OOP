package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AdjacencyListGraph extends GraphCl {
    private final ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public ArrayList<Integer> listOfVertex = new ArrayList<>();
    private int vertexAmount = 0;

    @Override
    public int getVertexAmount() {
        return vertexAmount;
    }

    @Override
    public void addVertex() {
        ArrayList<Integer> newV = new ArrayList<>();
        list.add(newV);
        vertexAmount++;
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
        vertexAmount--;
        listOfVertex.remove((Integer) u);
        list.remove(v);
        ArrayList<Integer> newList;
        for (int i = 0; i < vertexAmount; i++) {
            newList = list.get(i);
            newList.remove((Integer) u);
            list.set(i, newList);
        }
    }

    @Override
    public void addEdge(Edge e) throws Exception {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            throw new Exception("This vertices doesn't exist!");
        }
        int v = listOfVertex.indexOf(e.getFrom());
        int u = listOfVertex.indexOf(e.getTo());
        ArrayList<Integer> newList = list.get(v);
        newList.add(e.getTo());
        list.set(v, newList);
        newList = list.get(u);
        newList.add(e.getFrom());
        list.set(u, newList);
    }

    @Override
    public void removeEdge(Edge e) throws Exception {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            throw new Exception("This vertices doesn't exist!");
        }
        int v = listOfVertex.indexOf(e.getFrom());
        int u = listOfVertex.indexOf(e.getTo());
        if (!list.get(v).contains(e.getTo()) || !list.get(u).contains(e.getFrom())) {
            throw new Exception("This edge doesn't exist!");
        }
        ArrayList<Integer> newList = list.get(v);
        newList.remove((Integer) e.getTo());
        list.set(v, newList);
        newList = list.get(u);
        newList.remove((Integer) e.getFrom());
        list.set(u, newList);
    }

    @Override
    public ArrayList<Integer> neighbors(int v) {
        return list.get(listOfVertex.indexOf(v));
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        System.out.println("How many vertex do we have?");
        vertexAmount = Integer.parseInt(sc.nextLine());
        System.out.println("Print adjacency list:");
        String line;
        String[] splittedLine;
        ArrayList<Integer> newList;
        for (int i = 0; i < vertexAmount; i++) {
            line = sc.nextLine();
            splittedLine = line.split(regex);
            list.add(new ArrayList<>());
            newList = new ArrayList<>();
            for (String num : splittedLine) {
                newList.add(Integer.parseInt(num));
            }
            list.set(i, newList);
            listOfVertex.add(i + 1);
        }
        sc.close();
    }

    @Override
    public ArrayList<Integer> topologicalSort() {
        ArrayList<Integer> sortedList = new ArrayList<>();
        boolean[] visited = new boolean[vertexAmount];

        for (int i = 0; i < vertexAmount; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, sortedList);
            }
        }

        Collections.reverse(sortedList);
        return sortedList;
    }

    private void topologicalSortUtil(int v, boolean[] visited, ArrayList<Integer> sortedList) {
        visited[v] = true;
        for (Integer neighbor : this.neighbors(listOfVertex.get(v))) {
            int neighborIndex = listOfVertex.indexOf(neighbor);
            if (neighborIndex != v && !visited[neighborIndex]) {
                topologicalSortUtil(neighborIndex, visited, sortedList);
            }
        }
        sortedList.add(listOfVertex.get(v));
    }

    @Override
    public void print() {
        for (int i = 0; i < vertexAmount; i++) {
            System.out.print(listOfVertex.get(i) + ":");
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.printf(" %d%s", list.get(i).get(j),
                        j == list.get(i).size() - 1 ? "" : ",");
            }
            System.out.println();
        }
    }
}
