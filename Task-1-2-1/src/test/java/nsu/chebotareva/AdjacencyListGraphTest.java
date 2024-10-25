package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {
    @Test
    void test() {
        Graph g = new AdjacencyListGraph();
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
        g.print();

        ArrayList<Integer> mustBe = new ArrayList<>();
        mustBe.add(4);
        mustBe.add(5);
        assertEquals(mustBe, g.neighbors(3));
        assertEquals(1, g.removeVertex(7));
        assertEquals(1, g.removeEdge(new Edge(3, 1)));
        g.removeEdge(new Edge(4, 3));
        mustBe.remove((Integer) 4);
        assertEquals(mustBe, g.neighbors(3));
        g.removeVertex(6);
        assertEquals(4, g.getVertexAmount());
    }

    @Test
    void readAndEqual() {
        String str = "3\n2\n1, 3\n2";
        InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        AdjacencyListGraph g1 = new AdjacencyListGraph();
        g1.read(stream, ", ");

        AdjacencyListGraph g2 = new AdjacencyListGraph();
        g2.addVertex();
        g2.addVertex();
        g2.addVertex();
        g2.addEdge(new Edge(1, 2));
        g2.addEdge(new Edge(2, 3));
        assertEquals(true, g1.isEqual(g2));
    }

    @Test
    void topSort() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        for (int i = 0; i < 6; i++) {
            graph.addVertex();
        }

        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(2, 4));
        graph.addEdge(new Edge(3, 4));
        graph.addEdge(new Edge(4, 5));
        graph.addEdge(new Edge(5, 6));
        ArrayList<Integer> sortedVertices = graph.topologicalSort();
        ArrayList<Integer> mustBe = new ArrayList<>();
        mustBe.add(1);
        mustBe.add(3);
        mustBe.add(4);
        mustBe.add(5);
        mustBe.add(6);
        mustBe.add(2);
        assertEquals(mustBe, sortedVertices);
    }
}