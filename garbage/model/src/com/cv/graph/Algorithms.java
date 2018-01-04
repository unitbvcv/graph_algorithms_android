package com.cv.graph;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

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
        private HashMap<Node, Double> roadLengths = new HashMap<>();

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }

        public HashMap<Node, Double> getRoadLengths() {
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
        private HashMap<Node, Double> distances = new HashMap<>();
        private HashMap<Node, Node> predecessors = new HashMap<>();

        public HashMap<Node, Double> getDistances() {
            return distances;
        }

        public HashMap<Node, Node> getPredecessors() {
            return predecessors;
        }
    }
    public static class FloydWarshallResult {
        private HashMap<Node, HashMap<Node, Double>> distances = new HashMap<>();
        private HashMap<Node, HashMap<Node, Node>> predecessors = new HashMap<>();

        public HashMap<Node, HashMap<Node, Double>> getDistances() {
            return distances;
        }

        public HashMap<Node, HashMap<Node, Node>> getPredecessors() {
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
        result.getRoadLengths().put(startNode, 0.0);

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
                result.getRoadLengths().put(newStartNode, 0.0);
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
        HashMap<Node, Double> v = new HashMap<>();

        // functia e mapeaza pentru fiecare nod arcul cu cost minim prin care se acceseaza respectivul nod; analog cu v
        HashMap<Node, WeightedEdge> e = new HashMap<>();

        // initializam v
        for (Node node : graph.getNodes())
            v.put(node, Double.POSITIVE_INFINITY);
        v.put(startNode, 0.0);

        while (!N1.containsAll(graph.getNodes())) {
            double min = Double.POSITIVE_INFINITY;
            Node y = null;

            // gasirea nodului cu costul minim din v
            for (Map.Entry<Node, Double> entry : v.entrySet()) {
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

        // W - nodurile grafului
        // d - distanta de la nodul de start pana la restul de noduri
        // p - predecesorul nodului curent
        ArrayList<Node> W = new ArrayList<>(graph.getNodes());
        HashMap<Node, Double> d = result.getDistances();
        HashMap<Node, Node> p = result.getPredecessors();

        for(Node y : W) {
            d.put(y, Double.POSITIVE_INFINITY);
            p.put(y, null);
        }

        d.put(startNode, 0.0);

        while(!W.isEmpty()) {
            Node x;
            Map.Entry entry = d.entrySet().stream().filter(pair -> W.contains(pair.getKey())).min((pair1, pair2) -> pair1.getValue().compareTo(pair2.getValue())).orElse(null);
            if (entry != null) {
                x = (Node)entry.getKey();
                W.remove(x);
                List<Edge> succesorEdgesList = graph.getEdges().stream().filter(edge -> edge.getA().equals(x) && W.contains(edge.getB())).collect(Collectors.toList());

                for(Edge edge : succesorEdgesList) {
                    Node y = edge.getB();

                    Double arcWeight = ((WeightedArc)edge).getWeight();

                    if (d.get(x) + arcWeight < d.get(y)) {
                        d.put(y, d.get(x) + arcWeight);
                        p.put(y, x);
                    }
                }
            }
        }

        return result;
    }
    public static BellmanFordDijkstraResult BellmanFordAlgorithm(DirectedWeightedGraph graph, Node startNode) {
        BellmanFordDijkstraResult result = new BellmanFordDijkstraResult();

        HashMap<Node, Double> d = result.getDistances();
        HashMap<Node, Double> dp = new HashMap<>();
        HashMap<Node, Node> p = result.getPredecessors();

        for(Node y : graph.getNodes()) {
            d.put(y, Double.POSITIVE_INFINITY);
            p.put(y, null);
        }

        d.put(startNode, 0.0);

        BiPredicate<HashMap<Node, Double>, HashMap<Node, Double>> doWhileCondition = (l_d, l_dp) -> {
            for (Map.Entry<Node, Double> pair : l_d.entrySet()) {
                Double cost1 = pair.getValue();
                Double cost2 = l_dp.get(pair.getKey());

                if (!cost1.equals(cost2))
                    return true;
            }

            return false;
        };

        do {
            dp.putAll(d);

            for (Node y : graph.getNodes()) {
                List<Edge> predecessorEdgesList = graph.getEdges().stream().filter(edge -> edge.getB().equals(y)).collect(Collectors.toList());
                if (!predecessorEdgesList.isEmpty()) {
                    ArrayList<Node> predecessorList = new ArrayList<>();

                    for (Edge edge : predecessorEdgesList) {
                        if(edge.getA() != null)
                            predecessorList.add(edge.getA());
                    }

                    BiFunction<Node, Node, Double> compareEquation = (node1, node2) -> {return dp.get(node1) + ((WeightedArc)graph.getEdge(node1, node2)).getWeight();};

                    Node x = predecessorList.stream().min((x1, x2) -> Double.compare(compareEquation.apply(x1, y), compareEquation.apply(x2, y))).orElse(null);

                    if (compareEquation.apply(x, y) < dp.get(y)) {
                        d.put(y, dp.get(x) + ((WeightedArc)graph.getEdge(x, y)).getWeight());
                        p.put(y, x);
                    }
                }
            }
        } while(doWhileCondition.test(d, dp));

        return result;
    }
    public static FloydWarshallResult FloydWarshallAlgorithm(DirectedWeightedGraph graph) {
        FloydWarshallResult result = new FloydWarshallResult();

        HashMap<Node, HashMap<Node, Double>> d = result.getDistances();
        HashMap<Node, HashMap<Node, Node>> p = result.getPredecessors();

        BiFunction<Node, Node, Double> bValueCalculator = (node1, node2) -> {
            if (node1.equals(node2)) {
                return 0.0;
            }

            else if (graph.getEdge(node1, node2) != null) {
                return ((WeightedArc)graph.getEdge(node1, node2)).getWeight();
            }

            else {
                return Double.POSITIVE_INFINITY;
            }
        };

        HashMap<Node, HashMap<Node, Double>> b = new HashMap<>();
        for (Node node : graph.getNodes()) {
            b.put(node, new HashMap<>());
            d.put(node, new HashMap<>());
            p.put(node, new HashMap<>());
        }

        for (Map.Entry<Node, HashMap<Node, Double>> i : b.entrySet()) {
            for (Node node : graph.getNodes()) {
                i.getValue().put(node, bValueCalculator.apply(i.getKey(), node));
            }
        }

        for (Map.Entry<Node, HashMap<Node, Double>> i : d.entrySet()) {
            for (Node node : graph.getNodes()) {
                i.getValue().put(node, bValueCalculator.apply(i.getKey(), node));
            }
        }

        for (Map.Entry<Node, HashMap<Node, Node>> i : p.entrySet()) {
            for (Node node : graph.getNodes()) {
                if (!i.getKey().equals(node) && d.get(i).get(node) < Double.POSITIVE_INFINITY) {
                    p.get(i).put(node, i.getKey());
                }
                else {
                    p.get(i).put(node, null);
                }
            }
        }

        return result;
    }
    public static EulerianResult EulerianCircuit(DirectedGraph graph, Node startNode) {
        EulerianResult result = new EulerianResult();

        DepthFirstTraversalResult ptdf = DepthFirstTraversal(graph, startNode);

        // W - lista de noduri care formeaza circuitul
        ArrayList<Node> W = result.getEulerianNodes();

        // b - vector in care marcam daca un arc e din arborele parcurgere
        HashMap<Arc, Double> b = new HashMap<>();

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
                b.put(arc, 1.0);
            else b.put(arc, 0.0);
        }

        for (Node node : graph.getNodes())
        {
            V_x.put(node, new ArrayList<>());
            index.put(node, -1);
        }

        graph.getEdges().stream().map(edge -> (Arc) edge).forEach(arc -> {
            if (b.get(arc) == 0.0)
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
