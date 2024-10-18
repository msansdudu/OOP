package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyListGraph implements Graph {
    private final ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    private final ArrayList<Integer> listOfVertex = new ArrayList<>();
    private int size = 0;

    @Override
    public void addVertex() {
        ArrayList<Integer> newV = new ArrayList<>();
        list.add(newV);
        size++;
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
        size--;
        listOfVertex.remove((Integer) u);
        list.remove(v);
        ArrayList<Integer> newList;
        for (int i = 0; i < size; i++) {
            newList = list.get(i);
            newList.remove((Integer) u);
            list.set(i, newList);
        }
        return 0;
    }

    @Override
    public int addEdge(Edge e) {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            return 1;
        }
        int v = listOfVertex.indexOf(e.getFrom());
        int u = listOfVertex.indexOf(e.getTo());
        ArrayList<Integer> newList = list.get(v);
        newList.add(e.getTo());
        list.set(v, newList);
        newList = list.get(u);
        newList.add(e.getFrom());
        list.set(u, newList);
        return 0;
    }

    @Override
    public int removeEdge(Edge e) {
        if (!listOfVertex.contains(e.getFrom()) || !listOfVertex.contains(e.getTo())) {
            return 1;
        }
        int v = listOfVertex.indexOf(e.getFrom());
        int u = listOfVertex.indexOf(e.getTo());
        if (!list.get(v).contains(e.getTo()) || !list.get(u).contains(e.getFrom())) {
            return 1;
        }
        ArrayList<Integer> newList = list.get(v);
        newList.remove((Integer) e.getTo());
        list.set(v, newList);
        newList = list.get(u);
        newList.remove((Integer) e.getFrom());
        list.set(u, newList);
        return 0;
    }

    @Override
    public ArrayList<Integer> neighbors(int v) {
        return list.get(listOfVertex.indexOf(v));
    }

    @Override
    public void read(InputStream input, String regex) {
        Scanner sc = new Scanner(input);
        sc.close();
    }

    @Override
    public void topologicalSort() {

    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(listOfVertex.get(i) + ":");
            for (int j = 0; j < list.get(i).size(); j++) {
                System.out.printf(" %d%s", list.get(i).get(j), j == list.get(i).size() - 1 ? "" : ",");
            }
            System.out.println();
        }
    }

    @Override
    public void isEqual() {

    }
}
