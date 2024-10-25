package nsu.chebotareva;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IncidenceMatrixGraphTest {

    @Test
    void test() {
        Graph g = new IncidenceMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(1, 2));
        g.addEdge(new Edge(3, 2));
        g.removeEdge(new Edge(1, 2));
        g.addEdge(new Edge(1, 2));
        g.print();
        ArrayList<Integer> mustBe = new ArrayList<>();
        mustBe.add(2);
        assertEquals(mustBe, g.neighbors(1));
        assertEquals(1, g.removeEdge(new Edge(4, 2)));
        g.removeEdge(new Edge(1, 2));
        mustBe.remove((Integer) 2);
        assertEquals(mustBe, g.neighbors(1));
        g.removeVertex(1);
        assertEquals(1, g.removeVertex(1));
        g.removeVertex(2);
        assertEquals(1, g.getVertexAmount());
    }

    @Test
    void readAndEqual() {
        Graph g1 = new IncidenceMatrixGraph();
        String str = "3\n0 -1\n1 1\n-1 0";
        InputStream stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
        g1.read(stream, " ");
        g1.print();
        Graph g = new IncidenceMatrixGraph();
        g.addVertex();
        g.addVertex();
        g.addVertex();
        g.addEdge(new Edge(1, 2));
        g.addEdge(new Edge(3, 2));
        g.removeEdge(new Edge(1, 2));
        g.addEdge(new Edge(1, 2));
        assertEquals(true, g.isEqual(g1));
        g.removeVertex(1);
        assertEquals(false, g.isEqual(g1));
    }

    @Test
    void topSort() {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        for (int i = 0; i < 6; i++) {
            graph.addVertex();
        }
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(3, 4));
        graph.addEdge(new Edge(2, 5));
        graph.addEdge(new Edge(5, 6));
        ArrayList<Integer> list = graph.topologicalSort();
        ArrayList<Integer> mustBe = new ArrayList<>();
        mustBe.add(1);
        mustBe.add(2);
        mustBe.add(3);
        mustBe.add(5);
        mustBe.add(4);
        mustBe.add(6);
        assertEquals(mustBe, list);
    }
}