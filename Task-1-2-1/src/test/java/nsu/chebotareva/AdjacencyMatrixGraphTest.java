package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyMatrixGraphTest {
    @Test
    void test() {
        Graph g = new AdjacencyMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(2, 1));
        g.addVertex();
        g.addEdge(new Edge(1, 3));
        g.removeVertex(2);
        ArrayList<Integer> mustBe = new ArrayList<>();
        assertEquals(mustBe, g.neighbors(3));
        g.addVertex();
        g.addEdge(new Edge(3, 4));
        mustBe.add(4);
        assertEquals(mustBe, g.neighbors(3));
        g.removeEdge(new Edge(3, 4));
        mustBe.remove((Integer) 4);
        assertEquals(mustBe, g.neighbors(3));
        assertEquals(1, g.removeVertex(2));
        assertEquals(1, g.removeEdge(new Edge(2, 3)));
        g.print();
        g.removeVertex(4);
        g.removeVertex(3);
        g.removeVertex(1);
        assertEquals(0, g.getVertexAmount());
    }

    @Test
    void readAndEquals() {
        AdjacencyMatrixGraph g = new AdjacencyMatrixGraph();
        String str = "1 0 1\n1 1 0\n0 0 1";
        InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        g.read(stream, " ");
        AdjacencyMatrixGraph g2 = new AdjacencyMatrixGraph();
        g2.addVertex();
        g2.addVertex();
        g2.addVertex();
        g2.addEdge(new Edge(2, 1));
        g2.addEdge(new Edge(1, 3));
        assertEquals(true, g2.isEqual(g));
        g2.removeVertex(2);
        assertEquals(false, g.isEqual(g2));
    }

    @Test
    void topSort() {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph();
        for (int i = 0; i < 6; i++) {
            graph.addVertex();
        }
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(3, 4));
        graph.addEdge(new Edge(2, 5));
        graph.addEdge(new Edge(5, 6));
        graph.removeVertex(4);
        ArrayList<Integer> list = graph.topologicalSort();
        ArrayList<Integer> mustBe = new ArrayList<>();
        mustBe.add(1);
        mustBe.add(3);
        mustBe.add(2);
        mustBe.add(5);
        mustBe.add(6);
        assertEquals(mustBe, list);
    }
}