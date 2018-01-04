package com.unitbv.cv.aggrafuri.graph;

import java.util.*;
import java.util.function.BiPredicate;

public class Algorithms {

    public static class GenericGraphTraversalResult {
        private HashMap<Node, Node> predecessors = new HashMap<>();
        private HashMap<Node, Integer> orders = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Integer> getOrders() {
            return orders;
        }
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
    public static class DepthFirstTraversalResult {
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
    public static class MinimumPartialTreeResult {
        private ArrayList<WeightedEdge> treeEdges = new ArrayList<>();

        public ArrayList<WeightedEdge> getTreeEdges() {
            return treeEdges;
        }
    }
    public static class BellmanFordDijkstraResult {
        private HashMap<Node, Integer> distances = new HashMap<>();
        private HashMap<Node, Integer> predecessors = new HashMap<>();

        public HashMap<Node, Integer> getDistances() {
            return distances;
        }

        public HashMap<Node, Integer> getPredecessors() {
            return predecessors;
        }
    }
    public static class FloydWarshallResult {
        private HashMap<Node, HashMap<Node, Integer>> distances = new HashMap<>();
        private HashMap<Node, HashMap<Node, Integer>> predecessors = new HashMap<>();

        public HashMap<Node, HashMap<Node, Integer>> getDistances() {
            return distances;
        }

        public HashMap<Node, HashMap<Node, Integer>> getPredecessors() {
            return predecessors;
        }
    }
    public static class EulerianResult {
        private ArrayList<Arc> eulerianRoad = new ArrayList<>();
        private  ArrayList<Node> eulerianNodes = new ArrayList<>();

        public ArrayList<Arc> getEulerianRoad() {
            return eulerianRoad;
        }

        public ArrayList<Node> getEulerianNodes()
        {
            return eulerianNodes;
        }
    }

    public static GenericGraphTraversalResult GenericGraphTraversal(AbstractGraph graph, Node startNode) {
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
                                    || edge.getB().equals(x) && unvisited.contains(edge.getA());
                        })
                        .findAny().orElse(null);

                // daca exista arc/muchie (x, y) din A cu y din U
                if (foundEdge != null) {
                    Node y = foundEdge.getOtherEnd(x);
                    unvisited.remove(y);
                    visited.add(y);
                    result.getPredecessors().put(y, x);
                    ++pas;
                    result.getOrders().put(y, pas);
                } else {
                    visited.remove(x);
                    analyzed.add(x);
                }
            }

            if (!unvisited.isEmpty()) {
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
    public static BreadthFirstTraversalResult BreadthFirstTraversal(AbstractGraph graph, Node startNode) {
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
                            return edge.getA().equals(x) || edge.getB().equals(x);
                        })
                        .forEach(edge -> {
                            Node y = edge.getOtherEnd(x);
                            if (unvisited.contains(y)) {
                                unvisited.remove(y);
                                visited.add(y);
                                result.getPredecessors().put(y, x);
                                result.getRoadLengths().put(y, result.getRoadLengths().get(x) + 1);
                            }
                        });

                //visited.remove(x);
                analyzed.add(x);
            }

            if (!unvisited.isEmpty()) {
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
    public static DepthFirstTraversalResult DepthFirstTraversal(AbstractGraph graph, Node startNode) {
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
                                    || edge.getB().equals(x) && unvisited.contains(edge.getA());
                        })
                        .findAny().orElse(null);

                // daca exista arc/muchie (x, y) din A cu y din U
                if (foundEdge != null) {
                    Node y = foundEdge.getOtherEnd(x);
                    unvisited.remove(y);
                    visited.push(y);
                    result.getPredecessors().put(y, x);
                    ++timp;
                    result.getVisitedTime().put(y, timp);
                } else {
                    visited.pop();
                    analyzed.add(x);
                    ++timp;
                    result.getAnalizedTime().put(x, timp);
                }
            }

            if (!unvisited.isEmpty()) {
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
    public static MinimumPartialTreeResult PrimsAlgorithm(UndirectedWeightedGraph graph, Node startNode) {
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

        while (!N1.containsAll(graph.getNodes())) {
            int min = Integer.MAX_VALUE;
            Node y = null;

            // gasirea nodului cu costul minim din v
            for (Map.Entry<Node, Integer> entry : v.entrySet()) {
                if (nonN1.contains(entry.getKey()) && entry.getValue() < min) {
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
                if ((edge.getA().equals(y) || edge.getB().equals(y)) && nonN1.contains(edge.getOtherEnd(y))) {
                    WeightedEdge wEdge = (WeightedEdge) edge;
                    Node nonY = edge.getOtherEnd(y);
                    if (v.get(nonY) > wEdge.getWeight()) {
                        v.put(nonY, wEdge.getWeight());
                        e.put(nonY, wEdge);
                    }
                }
        }

        return result;
    }
    public static MinimumPartialTreeResult KruskalAlgorithm(UndirectedWeightedGraph graph) {
        MinimumPartialTreeResult result = new MinimumPartialTreeResult();

        // Procedura Sortare: sortarea arcelor dupa cost
        PriorityQueue<WeightedEdge> sortedEdges = new PriorityQueue<>
                (graph.getEdges().size(), Comparator.comparing(WeightedEdge::getWeight));
        graph.getEdges().forEach(edge -> sortedEdges.add((WeightedEdge) edge));

        // creez un graph auxiliar de test prin care verific daca adaugarea unei muchii
        // produce un ciclu sau nu
        UndirectedWeightedGraph testGraph = new UndirectedWeightedGraph();
        graph.getNodes().forEach(node -> testGraph.addNode(node));

        int numberOfEdges = graph.getEdges().size();
        for (int i = 0; i < numberOfEdges; ++i) {
            WeightedEdge testEdge = sortedEdges.poll();
            testGraph.addEdge(testEdge);
            if (testGraph.isCyclic())
                testGraph.removeEdge(testEdge);
            else
                result.getTreeEdges().add(testEdge);
        }

        return result;
    }
    public static MinimumPartialTreeResult BoruvkaAlgorithm(UndirectedWeightedGraph graph) {
        MinimumPartialTreeResult result = new MinimumPartialTreeResult();

        // multimea M reprezinta multimea de multimi de noduri
        // acele multimi de noduri contin noduri legate prin muchii de cost minim
        // M e partitie
        // M o sa contina duplicate pe parcursul algoritmului pe masura ce se reunesc multimile de noduri
        HashMap<String, HashSet<Node>> M = new HashMap<>();

        // initial fiecare nod e in propria sa multime
        for (Node node : graph.getNodes()) {
            HashSet<Node> Ni = new HashSet<>();
            Ni.add(node);
            M.put(node.getID(), Ni);
        }

        while (result.getTreeEdges().size() < graph.getNodes().size() - 1) {
            HashMap<String, String> reuniuni = new HashMap<>();

            for (Map.Entry<String, HashSet<Node>> Ni : M.entrySet()) {
                // se selecteaza muchia de cost minim care leaga nodurile din Ni de un alt nod
                Edge minimumCostEdge = graph.getEdges().stream()
                        .filter(edge -> {
                            BiPredicate<Node, Node> verificare = (y, nonY) ->
                                    Ni.getValue().contains(y) && !Ni.getValue().contains(nonY);

                            return verificare.test(edge.getA(), edge.getB())
                                    || verificare.test(edge.getB(), edge.getA());

                        }).min((edge1, edge2) -> {
                            WeightedEdge wEdge1 = (WeightedEdge) edge1;
                            WeightedEdge wEdge2 = (WeightedEdge) edge2;

                            return wEdge1.getWeight().compareTo(wEdge2.getWeight());
                        }).orElse(null);

                if (minimumCostEdge != null) {
                    Node nonY = Ni.getValue().contains(minimumCostEdge.getA()) ? minimumCostEdge.getB() : minimumCostEdge.getA();

                    reuniuni.put(Ni.getKey(), nonY.getID());

                    if (result.getTreeEdges().contains(minimumCostEdge) == false)
                        result.getTreeEdges().add((WeightedEdge) minimumCostEdge);
                }
            }

            reuniuni.forEach((i, j) -> {
                M.get(i).addAll(M.get(j));
                M.put(j, M.get(i));
            });

        }

        return result;
    }
    public static BellmanFordDijkstraResult DijkstraAlgorithm(DirectedWeightedGraph graph, Node startNode) {
        BellmanFordDijkstraResult result = new BellmanFordDijkstraResult();

        // here algorithm

        return result;
    }
    public static BellmanFordDijkstraResult BellmanFordAlgorithm(DirectedWeightedGraph graph, Node startNode) {
        BellmanFordDijkstraResult result = new BellmanFordDijkstraResult();

        // here algorithm

        return result;
    }
    public static FloydWarshallResult FloydWarshallAlgorithm(DirectedWeightedGraph graph) {
        FloydWarshallResult result = new FloydWarshallResult();

        // do your thing

        return result;
    }
    public static EulerianResult EulerianCircuit(DirectedGraph graph, Node startNode) {
        EulerianResult result = new EulerianResult();

        DepthFirstTraversalResult ptdf = DepthFirstTraversal(graph, startNode);

        // W - lista de noduri care formeaza circuitul
        ArrayList<Node> W = result.getEulerianNodes();

        // b - vector in care marcam daca un arc e din arborele parcurgere
        HashMap<Arc, Integer> b = new HashMap<>();

        // V-x  = lista nodurilor adiacente catre interiorul lui x
        HashMap<Node, ArrayList<Node>> V_x = new HashMap<>();

        // i - vector de indecsi pt fiecare nod
        HashMap<Node, Integer> index = new HashMap<>();


        // Initializari

        // daca arcul apartine arborelui parcurgere  [ (x, y) e A' ]
        for (Edge edge1 : graph.getEdges())
        {
            Arc arc = (Arc) edge1;
            Node y_predecessor = ptdf.getPredecessors().get(arc.getB());
            if (y_predecessor != null && y_predecessor.equals(arc.getA()))
                b.put(arc, 1);
            else b.put(arc, 0);
        }

        for (Node node : graph.getNodes())
        {
            V_x.put(node, new ArrayList<>());
            index.put(node, -1);
        }

        graph.getEdges().stream().map(edge -> (Arc) edge).forEach(arc -> {
            if (b.get(arc) == 0)
                V_x.get(arc.getB()).add(0, arc.getA());
            else
                V_x.get(arc.getB()).add(arc.getA());
        });

        Node x = startNode;

        // i(x) <= RO-(x)
        while (index.get(x) < V_x.get(x).size())
        {
            W.add(0, x);
            index.put(x, index.get(x) + 1 );
            if (index.get(x) < V_x.get(x).size())
                x = V_x.get(x).get(index.get(x));
        }


        Node first = W.get(0);
        for (int i = 1; i < W.size(); ++i)
        {
            Node second = W.get(i);
            result.eulerianRoad.add(new Arc(first, second));
            first = second;
        }

        return result;
    }
}
