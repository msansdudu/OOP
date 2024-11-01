package nsu.chebotareva;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Интерфейс графа.
 */
public interface Graph {
    /**
     * Возвращает количество вершин.
     *
     * @return -- количество вершин.
     */
    int getVertexAmount();

    /**
     * Добавляет вершину "в конец".
     */
    void addVertex();

    /**
     * Удаляет вершину v.
     *
     * @param v -- вершина.
     * @throws Exception -- при неудаче.
     */
    void removeVertex(int v) throws Exception;

    /**
     * Добавить ребро.
     *
     * @param e -- ребро с параметрами from-to.
     * @throws Exception -- при неудаче.
     */
    void addEdge(Edge e) throws Exception;

    /**
     * Удалить ребро.
     *
     * @param e -- ребро с параметрами from-to.
     * @throws Exception -- при неудаче.
     */
    void removeEdge(Edge e) throws Exception;

    /**
     * Получить список соседей вершины v.
     *
     * @param v -- вершина.
     * @return -- список соседей.
     */
    ArrayList<Integer> neighbors(int v);

    /**
     * Считать граф.
     *
     * @param input -- входной поток.
     * @param regex -- разделитель.
     */
    void read(InputStream input, String regex);

    /**
     * Возращает топологически отсортированные вершины.
     *
     * @return -- список либо null при ошибке.
     */
    ArrayList<Integer> topologicalSort();

    /**
     * Вывести граф.
     */
    void print();
}