package com.cv.graph;

import java.util.*;
import java.util.function.BiPredicate;

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


    public static class BreadthFirstTraversalResult
    {
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
                            result.getPredecessors().put(y, x);
                            result.getRoadLengths().put(y, result.getRoadLengths().get(x) + 1);
                        }
                    });

                    //visited.remove(x);
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


    public static class DepthFirstTraversalResult
    {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> visitedTimes = new HashMap<>();
        private HashMap<Node, Integer> analysedTimes = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Integer> getVisitedTime() {
            return visitedTimes;
        }

        public HashMap<Node, Integer> getAnalizedTime() {
            return analysedTimes;
        }
    }

    public static DepthFirstTraversalResult DepthFirstTraversal(AbstractGraph graph, Node startNode)
    {
        if (graph instanceof  DirectedGraph || graph instanceof UndirectedGraph) // verificare nenecesara
        {
            DepthFirstTraversalResult result = new DepthFirstTraversalResult();

            // multimea nodurilor nevizitate
            HashSet<Node> unvisited = new HashSet<>(graph.getNodes());
            unvisited.remove(startNode);

            // multimea nodurilor vizitate
            ArrayDeque<Node> visited = new ArrayDeque<>(graph.getNodes().size());
            visited.push(startNode);

            // multimea nodurilor analizate
            HashSet<Node> analyzed = new HashSet<>();

            // nodul de start nu are predecesor, restul nodurilor nu sunt prezente in mapa inca
            result.getPredecessors().put(startNode, null);

            int timp = 1;

            // nodul de start e primul vizitat, restul nodurilor nu sunt prezente in mapa inca
            result.getVisitedTime().put(startNode, timp);

            while (!analyzed.containsAll(graph.getNodes())) // W != N
            {
                while (!visited.isEmpty()) // V != multimea vida
                {
                    // se selecteaza un nod x din V
                    Node x = visited.peek();

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
                        visited.push(y);
                        result.getPredecessors().put(y, x);
                        ++timp;
                        result.getVisitedTime().put(y, timp);
                    }
                    else
                    {
                        visited.pop();
                        analyzed.add(x);
                        ++timp;
                        result.getAnalizedTime().put(x, timp);
                    }
                }

                if (!unvisited.isEmpty())
                {
                    Node newStartNode = unvisited.stream().findAny().get();
                    unvisited.remove(newStartNode);
                    visited.clear();
                    visited.push(newStartNode);
                    result.getPredecessors().put(newStartNode, null);
                    ++timp;
                    result.getVisitedTime().put(newStartNode, timp);
                }
            }
            return result;
        }
        return null;
    }


    public static class MinimumPartialTreeResult
    {
        private ArrayList<WeightedEdge> treeEdges = new ArrayList<>();

        public ArrayList<WeightedEdge> getTreeEdges() {
            return treeEdges;
        }
    }

    public static MinimumPartialTreeResult PrimsAlgorithm(UndirectedWeightedGraph graph, Node startNode)
    {
        MinimumPartialTreeResult result = new MinimumPartialTreeResult();

        // multimea N1 reprezinta un fel de multime de noduri analizate, pornind cu nodul de start
        HashSet<Node> N1 = new HashSet<>();

        // multimea nonN1 reprezinta toate nodurile neanalizate inca
        HashSet<Node> nonN1 = new HashSet<>(graph.getNodes());

        // functia v mapeaza pentru fiecare nod valoarea arcului cu cost minim prin care se poate accesa
        // respectivul nod; aceasta valoare se schimba pe masura ce muchii cu cost mai mic devin vizitate si
        // nodul nu devine din neanalizat analizat
        HashMap<Node, Integer> v = new HashMap<>();

        // functia e mapeaza pentru fiecare nod arcul cu cost minim prin care se acceseaza respectivul nod; analog cu v
        HashMap<Node, WeightedEdge> e = new HashMap<>();

        // initializam v
        for (Node node : graph.getNodes())
            v.put(node, Integer.MAX_VALUE);
        v.put(startNode, 0);

        while (!N1.containsAll(graph.getNodes()))
        {
            int min = Integer.MAX_VALUE;
            Node y = null;

            // gasirea nodului cu costul minim din v
            for (Map.Entry<Node, Integer> entry : v.entrySet())
            {
                if (nonN1.contains(entry.getKey()) && entry.getValue() < min)
                {
                    min = entry.getValue();
                    y = entry.getKey();
                }
            }

            // y devine nod analizat
            N1.add(y);
            nonN1.remove(y);

            // daca y != startNode, am gasit arcul cu cost minim la nodul y
            if (!y.equals(startNode))
                result.getTreeEdges().add(e.get(y));

            // verificam daca nu exista drumuri mai scurte prin y fata de drumurile deja descoperite
            // parcurgem toate arcele care pleaca din y si merg intr-un nod neanalizat
            for (Edge edge : graph.getEdges())
                if ((edge.getA().equals(y) || edge.getB().equals(y)) && nonN1.contains(edge.getOtherEnd(y)))
                {
                    WeightedEdge wEdge = (WeightedEdge) edge;
                    Node nonY = edge.getOtherEnd(y);
                    if (v.get(nonY) > wEdge.getWeight())
                    {
                        v.put(nonY, wEdge.getWeight());
                        e.put(nonY, wEdge);
                    }
                }
        }

        return result;
    }

    public static MinimumPartialTreeResult KruskalAlgorithm(UndirectedWeightedGraph graph)
    {
        MinimumPartialTreeResult result = new MinimumPartialTreeResult();

        // Procedura Sortare: sortarea arcelor dupa cost
        PriorityQueue<WeightedEdge> sortedEdges = new PriorityQueue<>
                (graph.getEdges().size(), Comparator.comparing(WeightedEdge::getWeight));
        graph.getEdges().forEach(edge -> sortedEdges.add((WeightedEdge)edge));




        return result;
    }


    public static class BellmanFordDijkstraResult
    {
        private HashMap<Node, Integer> distances = new HashMap<>();
        private HashMap<Node, Integer> predecessors = new HashMap<>();

        public HashMap<Node, Integer> getDistances() {
            return distances;
        }

        public HashMap<Node, Integer> getPredecessors() {
            return predecessors;
        }
    }

    public static class FloydWarshallResult
    {
        private HashMap<Node, HashMap<Node, Integer>> distances = new HashMap<>();
        private HashMap<Node, HashMap<Node, Integer>> predecessors = new HashMap<>();

        public HashMap<Node, HashMap<Node, Integer>> getDistances() {
            return distances;
        }

        public HashMap<Node, HashMap<Node, Integer>> getPredecessors() {
            return predecessors;
        }
    }

    public static class EulerianResult
    {
        private ArrayList<Arc> eulerianRoad = new ArrayList<>();

        public ArrayList<Arc> getEulerianRoad() {
            return eulerianRoad;
        }
    }
}
