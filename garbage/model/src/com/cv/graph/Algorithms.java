package com.cv.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Algorithms {

    public static class GenericGraphTraversalResult
    {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> orders = new HashMap<>();

        public HashMap<Node, Node> getPredecessors()
        {
            return predecessors;
        }

        public HashMap<Node, Integer> getOrders()
        {
            return orders;
        }
    }

    public static GenericGraphTraversalResult GenericGraphTraversal(AbstractGraph graph, Node startNode)
    {
        if (graph instanceof  UndirectedGraph || graph instanceof  DirectedGraph)
        {
            GenericGraphTraversalResult result = new Algorithms.GenericGraphTraversalResult();

            // multimea nodurilor nevizitate
            HashSet<Node> unvisited = new HashSet<>(graph.getNodes());
            unvisited.remove(startNode);

            // multimea nodurilor vizitate
            HashSet<Node> visited = new HashSet<>();
            visited.add(startNode);

            // multimea nodurilor analizate
            HashSet<Node> analyzed = new HashSet<>();

            // nodul de start nu are predecesor, restul nodurilor nu sunt prezente in mapa inca
            result.getPredecessors().put(startNode, null);

            // pasul numara cate noduri parcurge algoritmul
            int pas = 1;

            // nodul de start e primul vizitat, restul nodurilor nu sunt prezente in mapa inca
            result.getOrders().put(startNode, pas);

            while (!analyzed.containsAll(graph.getNodes())) // W != N
            {
                while (!visited.isEmpty()) // V != multimea vida
                {
                    // se selecteaza un nod x din V
                    Node x = visited.stream().findAny().get();

                    // arc/muchie (x, y) din A cu y din U
                    Edge foundEdge = graph.getEdges().stream()
                    .filter(edge -> {
                        if (edge instanceof Arc)
                            return edge.getA().equals(x) && unvisited.contains(edge.getB());
                        return edge.getA().equals(x) && unvisited.contains(edge.getB())
                            || edge.getB().equals(x) && unvisited.contains(edge.getA());})
                    .findAny().orElse(null);

                    // daca exista arc/muchie (x, y) din A cu y din U
                    if (foundEdge != null)
                    {
                        Node y = foundEdge.getOtherEnd(x);
                        unvisited.remove(y);
                        visited.add(y);
                        result.getPredecessors().put(y, x);
                        ++pas;
                        result.getOrders().put(y, pas);
                    }
                    else
                    {
                        visited.remove(x);
                        analyzed.add(x);
                    }
                }

                if (!unvisited.isEmpty())
                {
                    Node newStartNode = unvisited.stream().findAny().get();
                    unvisited.remove(newStartNode);
                    visited.clear();
                    visited.add(newStartNode);
                    result.getPredecessors().put(newStartNode, null);
                    ++pas;
                    result.getOrders().put(newStartNode, pas);
                }
            }
            return result;
        }
        return null;
    }

    public static class BreadthFirstTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> roadLengths = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Integer> getRoadLengths() {
            return roadLengths;
        }
    }

    public static  BreadthFirstTraversalResult BreadthFirstTraversal(AbstractGraph graph, Node startNode)
    {
        if (graph instanceof DirectedGraph || graph instanceof UndirectedGraph)
        {
            BreadthFirstTraversalResult result = new BreadthFirstTraversalResult();

            // multimea nodurilor nevizitate
            HashSet<Node> unvisited = new HashSet<>(graph.getNodes());
            unvisited.remove(startNode);

            // multimea nodurilor vizitate
            ArrayDeque<Node> visited = new ArrayDeque<>(graph.getNodes().size());
            visited.add(startNode);

            // multimea nodurilor analizate
            HashSet<Node> analyzed = new HashSet<>();

            // nodul de start nu are predecesor, restul nodurilor nu sunt prezente in mapa inca
            result.getPredecessors().put(startNode, null);

            // nodul de start are lungimea drumului 0
            result.getRoadLengths().put(startNode, 0);

            while (!analyzed.containsAll(graph.getNodes())) // W != N
            {
                while (!visited.isEmpty()) // V != multimea vida
                {
                    // se selecteaza cel mai vechi nod x din V
                    Node x = visited.poll();

                    // pentru toate arcele/muchiile din x
                    graph.getEdges().stream()
                    .filter(edge -> {
                        if (edge instanceof Arc)
                            return edge.getA().equals(x);
                        return edge.getA().equals(x) || edge.getB().equals(x);})
                    .forEach(edge -> {
                        Node y = edge.getOtherEnd(x);
                        if (unvisited.contains(y))
                        {
                            unvisited.remove(y);
                            visited.add(y);
                            result.predecessors.put(y, x);
                            result.getRoadLengths().put(y, result.getRoadLengths().get(x) + 1);
                        }
                    });

                    visited.remove(x);
                    analyzed.add(x);
                }

                if (!unvisited.isEmpty())
                {
                    Node newStartNode = unvisited.stream().findAny().get();
                    unvisited.remove(newStartNode);
                    visited.clear();
                    visited.add(newStartNode);
                    result.getPredecessors().put(newStartNode, null);
                    result.getRoadLengths().put(newStartNode, 0);
                }
            }
            return result;
        }
        return null;
    }

    public class DepthFirstTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> visitedTimes = new HashMap<>();
        private HashMap<Node, Integer> analizedTimes = new HashMap<>();

        public HashMap<Node, Node> getPredecessor() {
            return predecessors;
        }

        public HashMap<Node, Integer> getVisitedTime() {
            return visitedTimes;
        }

        public HashMap<Node, Integer> getAnalizedTime() {
            return analizedTimes;
        }
    }

    public class MinimumPartialTreeResult {
        private ArrayList<WeightedEdge> treeEdges = new ArrayList<>();

        public ArrayList<WeightedEdge> getTreeEdges() {
            return treeEdges;
        }
    }

    public class BellmanFordDijkstraResult {
        private HashMap<Node, Integer> distances = new HashMap<>();
        private HashMap<Node, Integer> predecessors = new HashMap<>();

        public HashMap<Node, Integer> getDistances() {
            return distances;
        }

        public HashMap<Node, Integer> getPredecessors() {
            return predecessors;
        }
    }

    public class FloydWarshallResult {
        private HashMap<Node, HashMap<Node, Integer>> distances = new HashMap<>();
        private HashMap<Node, HashMap<Node, Integer>> predecessors = new HashMap<>();

        public HashMap<Node, HashMap<Node, Integer>> getDistances() {
            return distances;
        }

        public HashMap<Node, HashMap<Node, Integer>> getPredecessors() {
            return predecessors;
        }
    }

    public class EulerianResult {
        private ArrayList<Arc> eulerianRoad = new ArrayList<>();

        public ArrayList<Arc> getEulerianRoad() {
            return eulerianRoad;
        }
    }
}
