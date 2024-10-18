package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Интерфейс графа.
 */
public interface Graph {
    /**
     * Добавляет вершину "в конец".
     */
    public void addVertex();

    /**
     * Удаляет вершину v.
     * @param v -- вершина.
     * @return -- удалось ли удалить вершину (0 -- успешно, 1 -- нет).
     */
    public int removeVertex(int v);

    /**
     * Добавить ребро.
     * @param e -- ребро с параметрами from-to.
     * @return -- удалось ли добавить вершину (0 -- успешно, 1 -- нет).
     */
    public int addEdge(Edge e);

    /**
     * Удалить ребро.
     * @param e -- ребро с параметрами from-to.
     * @return -- удалось ли удалить ребро (0 -- успешно, 1 -- нет).
     */
    public int removeEdge(Edge e);

    /**
     * Получить список соседей вершины v.
     * @param v -- вершина.
     * @return -- список соседей.
     */
    public ArrayList<Integer> neighbors(int v);

    /**
     * Считать граф.
     * @param input -- входной поток.
     * @param regex -- разделитель.
     */
    public void read(InputStream input, String regex);

    public void topologicalSort();

    /**
     * Вывести граф.
     */
    public void print();
    public void isEqual();
}