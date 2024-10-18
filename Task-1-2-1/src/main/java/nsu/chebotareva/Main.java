package nsu.chebotareva;

public class Main {
    public static void main(String[] args) {
        Graph g = new AdjacencyMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(2, 1));
        g.addVertex();
        g.addEdge(new Edge(1, 3));
        g.removeVertex(2);
        System.out.println(g.neighbors(3));
        g.addVertex();
        g.addEdge(new Edge(3, 4));
        System.out.println(g.neighbors(3));
        //g.removeVertex(4);
        //g.removeVertex(3);
        //g.removeVertex(1);
        g.print();

        /*Graph g = new IncidenceMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(1, 2));
        g.addEdge(new Edge(3, 2));
        //g.removeEdge(new Edge(1, 2));
        g.removeEdge(new Edge(1, 2));
        g.addEdge(new Edge(1, 2));
        g.print();*/

        /*Graph g = new AdjacencyListGraph();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(1, 2));
        g.addEdge(new Edge(3, 2));
        g.removeEdge(new Edge(1, 2));
        g.removeVertex(2);
        g.addVertex();
        g.addEdge(new Edge(3, 4));
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(3, 5));
        g.addEdge(new Edge(3, 6));
        g.addEdge(new Edge(6, 5));
        g.removeEdge(new Edge(3, 6));
        g.print();*/

        /*Graph g = new AdjacencyMatrixGraph();
        g.read(System.in, " ");
        g.print();*/
    }
}