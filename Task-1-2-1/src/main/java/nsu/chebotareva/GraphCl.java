package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;

abstract public class GraphCl implements Graph{
    public boolean isEqual(Graph g) {
        if (this.getVertexAmount() != g.getVertexAmount()) {
            return false;
        }
        if (this.getVertexAmount() == 0) {
            return true;
        }
        for (int i = 1; i <= this.getVertexAmount(); i++) {
            if (!Objects.equals(this.neighbors(i), g.neighbors(i))) {
                return false;
            }
        }
        return true;
    }

    int hashcode() {
        return 1;
    }

    /**
     * Возвращает количество вершин.
     *
     * @return -- количество вершин.
     */
    abstract public int getVertexAmount();

    /**
     * Добавляет вершину "в конец".
     */
    abstract public void addVertex();

    /**
     * Удаляет вершину v.
     *
     * @param v -- вершина.
     * @throws Exception -- при неудаче.
     */
    abstract public void removeVertex(int v) throws Exception;

    /**
     * Добавить ребро.
     *
     * @param e -- ребро с параметрами from-to.
     * @throws Exception -- при неудаче.
     */
    abstract public void addEdge(Edge e) throws Exception;

    /**
     * Удалить ребро.
     *
     * @param e -- ребро с параметрами from-to.
     * @throws Exception -- при неудаче.
     */
    abstract public void removeEdge(Edge e) throws Exception;

    /**
     * Получить список соседей вершины v.
     *
     * @param v -- вершина.
     * @return -- список соседей.
     */
    abstract public ArrayList<Integer> neighbors(int v);

    /**
     * Считать граф.
     *
     * @param input -- входной поток.
     * @param regex -- разделитель.
     */
    abstract public void read(InputStream input, String regex);

    /**
     * Возращает топологически отсортированные вершины.
     *
     * @return -- список либо null при ошибке.
     */
    abstract public ArrayList<Integer> topologicalSort();

    /**
     * Вывести граф.
     */
    abstract public void print();
}
