package lesson5;

import kotlin.NotImplementedError;
import lesson5.impl.GraphBuilder;
import sun.security.provider.certpath.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        Set<Graph.Vertex> result = new HashSet<>();
        Set<Graph.Vertex> invertResult = new HashSet<>();
        Set<Graph.Vertex> vertices = graph.getVertices();
        Set<Graph.Edge> connections = graph.getEdges();
        Graph.Edge start = null;

        for(Graph.Edge v : connections){
            Graph.Vertex begin = v.getBegin();
            Graph.Vertex end = v.getEnd();
            if(result.isEmpty() || v.getBegin() == start.getBegin())start = v;
            if(!result.contains(start.getEnd())) {
                result.add(start.getEnd());
                invertResult.add(start.getBegin());
            }
            if(result.contains(v.getBegin())) invertResult.add(v.getEnd());
            if(invertResult.contains(v.getBegin())) result.add(v.getEnd());
        }
        if(result.size() > invertResult.size()) return result;
        else  return invertResult;
    }
//         Итог:    T=O(n) ,where n - edges in graph;
//                  R=O(n)
    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {

        Set<Graph.Vertex> vertices = graph.getVertices();
        Graph.Vertex first = vertices.iterator().next();
        Path best = new Path(first);
        Deque<Path> deq = new ArrayDeque<>();

        for(Graph.Vertex v : vertices) {
            deq.add(new Path(v));
        }

        while (!deq.isEmpty()){
            Path current = deq.pop();
            List<Graph.Vertex> v = current.getVertices();
            if(current.getLength() > best.getLength()){
                best = current;
                if(v.size() > vertices.size()) break;
            }
            Set<Graph.Vertex>  neighbors = graph.getNeighbors(v.get(v.size()-1));
            for(Graph.Vertex n : neighbors){
                if(!current.contains(n)){
                    deq.push(new Path(current,graph,n));
                }
            }
        }

        return best;
    }
}
// Итог: T=O(v)
//       R=O(v)    v - value of all vertices
