package testing;

import com.cv.graph.*;
import com.cv.graph.Algorithms.MinimumPartialTreeResult;

public class Main
{
    public static void testAlgorithm1()
    {
        DirectedGraph graph = new DirectedGraph();
        Node node1 = new Node("1");
        graph.addNode(node1);
        Node node2 = new Node("2");
        graph.addNode(node2);
        Node node3 = new Node("3");
        graph.addNode(node3);
        Node node4 = new Node("4");
        graph.addNode(node4);
        Node node5 = new Node("5");
        graph.addNode(node5);
        Node node6 = new Node("6");
        graph.addNode(node6);
        Node node7 = new Node("7");
        graph.addNode(node7);
        Node node8 = new Node("8");
        graph.addNode(node8);

        Arc arc1 = new Arc(node1, node2);
        Arc arc2 = new Arc(node1, node3);
        Arc arc3 = new Arc(node2, node3);
        Arc arc4 = new Arc(node2, node4);
        Arc arc5 = new Arc(node2, node5);
        Arc arc6 = new Arc(node3, node5);
        Arc arc7 = new Arc(node4, node5);
        Arc arc8 = new Arc(node4, node6);
        Arc arc9 = new Arc(node5, node6);
        Arc arc10 = new Arc(node7, node3);
        Arc arc11 = new Arc(node7, node8);

        graph.addEdge(arc1);
        graph.addEdge(arc2);
        graph.addEdge(arc3);
        graph.addEdge(arc4);
        graph.addEdge(arc5);
        graph.addEdge(arc6);
        graph.addEdge(arc7);
        graph.addEdge(arc8);
        graph.addEdge(arc9);
        graph.addEdge(arc10);
        graph.addEdge(arc11);

        Algorithms.GenericGraphTraversalResult result = Algorithms.GenericGraphTraversal(graph, node1);

        System.out.println("Vector de predecesori:");
        result.getPredecessors().forEach( (nod, predecesor) ->
        {System.out.println("Nod: " + nod.getID() + " Predecesor: "
                + (predecesor == null ? "null" : predecesor.getID()));});

        System.out.println();
        System.out.println("Vector de ordine:");
        result.getOrders().forEach((nod, ordine) ->
        {System.out.println("Nod: " + nod.getID() + " Ordine: " + ordine);});
    }

    public static void testAlgorithm2()
    {
        DirectedGraph graph = new DirectedGraph();
        Node node1 = new Node("1");
        graph.addNode(node1);
        Node node2 = new Node("2");
        graph.addNode(node2);
        Node node3 = new Node("3");
        graph.addNode(node3);
        Node node4 = new Node("4");
        graph.addNode(node4);
        Node node5 = new Node("5");
        graph.addNode(node5);
        Node node6 = new Node("6");
        graph.addNode(node6);
        Node node7 = new Node("7");
        graph.addNode(node7);
        Node node8 = new Node("8");
        graph.addNode(node8);

        Arc arc1 = new Arc(node1, node2);
        Arc arc2 = new Arc(node1, node3);
        Arc arc3 = new Arc(node2, node3);
        Arc arc4 = new Arc(node2, node4);
        Arc arc5 = new Arc(node2, node5);
        Arc arc6 = new Arc(node3, node5);
        Arc arc7 = new Arc(node4, node5);
        Arc arc8 = new Arc(node4, node6);
        Arc arc9 = new Arc(node5, node6);
        Arc arc10 = new Arc(node7, node3);
        Arc arc11 = new Arc(node7, node8);

        graph.addEdge(arc1);
        graph.addEdge(arc2);
        graph.addEdge(arc3);
        graph.addEdge(arc4);
        graph.addEdge(arc5);
        graph.addEdge(arc6);
        graph.addEdge(arc7);
        graph.addEdge(arc8);
        graph.addEdge(arc9);
        graph.addEdge(arc10);
        graph.addEdge(arc11);

        Algorithms.BreadthFirstTraversalResult result = Algorithms.BreadthFirstTraversal(graph, node1);

        System.out.println("Vector de predecesori:");
        result.getPredecessors().forEach( (nod, predecesor) ->
        {System.out.println("Nod: " + nod.getID() + " Predecesor: "
                + (predecesor == null ? "null" : predecesor.getID()));});

        System.out.println();
        System.out.println("Vector de lungimi:");
        result.getRoadLengths().forEach((nod, lungime) ->
        {System.out.println("Nod: " + nod.getID() + " Lungime: " + lungime);});
    }

    public static void testAlgorithm3()
    {
        DirectedGraph graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");
        Node n8 = new Node("8");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);

        Arc a1 = new Arc(n1, n2);
        Arc a2 = new Arc(n1, n5);
        Arc a3 = new Arc(n2, n5);
        Arc a4 = new Arc(n2, n3);
        Arc a5 = new Arc(n3, n4);
        Arc a6 = new Arc(n4, n2);
        Arc a7 = new Arc(n5, n4);
        Arc a8 = new Arc(n6, n7);
        Arc a9 = new Arc(n6, n8);
        Arc a10 = new Arc(n7, n1);
        Arc a11 = new Arc(n7, n5);
        Arc a12 = new Arc(n8, n6);
        Arc a13 = new Arc(n8, n7);

        graph.addEdge(a1);
        graph.addEdge(a2);
        graph.addEdge(a3);
        graph.addEdge(a4);
        graph.addEdge(a5);
        graph.addEdge(a6);
        graph.addEdge(a7);
        graph.addEdge(a8);
        graph.addEdge(a9);
        graph.addEdge(a10);
        graph.addEdge(a11);
        graph.addEdge(a12);
        graph.addEdge(a13);


        Algorithms.DepthFirstTraversalResult result = Algorithms.DepthFirstTraversal(graph, n1);

        System.out.println("Vector de predecesori:");
        result.getPredecessors().forEach( (nod, predecesor) ->
        {System.out.println("Nod: " + nod.getID() + " Predecesor: "
                + (predecesor == null ? "null" : predecesor.getID()));});

        System.out.println();
        System.out.println("Vector de timpi vizitati:");
        result.getVisitedTime().forEach((nod, timp1) ->
        {System.out.println("Nod: " + nod.getID() + " Timp: " + timp1);});

        System.out.println();
        System.out.println("Vector de timpi analizati:");
        result.getAnalizedTime().forEach((nod, timp2) ->
        {System.out.println("Nod: " + nod.getID() + " Timp: " + timp2);});

    }

    public static void testAlgorithm4()
    {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        WeightedEdge edge1 = new WeightedEdge(n1, n2, 35);
        WeightedEdge edge2 = new WeightedEdge(n1, n3, 40);
        WeightedEdge edge3 = new WeightedEdge(n3, n2, 25);
        WeightedEdge edge4 = new WeightedEdge(n4, n2, 10);
        WeightedEdge edge5 = new WeightedEdge(n3, n4, 20);
        WeightedEdge edge6 = new WeightedEdge(n5, n3, 15);
        WeightedEdge edge7 = new WeightedEdge(n4, n5, 30);
        //WeightedEdge edge8 = new WeightedEdge(n5, n2,  5);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        //graph.addEdge(edge8);

        Algorithms.MinimumPartialTreeResult result = Algorithms.PrimsAlgorithm(graph, n1);

        result.getTreeEdges().stream()
        .forEach(edge -> System.out.println(edge.getA().getID()
            + " - " + edge.getB().getID() + " Cost: " + edge.getWeight()));
    }

    public static void testCyclic()
    {
    	UndirectedGraph graph = new UndirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");
        Node n8 = new Node("8");
        Node n9 = new Node("9");
        Node n10 = new Node("10");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);
        graph.addNode(n9);
        graph.addNode(n10);

        Edge e1 = new Edge(n1, n2);
        Edge e2 = new Edge(n1, n5);
        Edge e3 = new Edge(n1, n6);
        Edge e4 = new Edge(n2, n3);
        Edge e5 = new Edge(n2, n4);
        Edge e6 = new Edge(n6, n7);
        Edge e7 = new Edge(n6, n8);
        Edge e8 = new Edge(n6, n9);
        Edge e9 = new Edge(n9, n10);
        Edge e10 = new Edge(n9, n2);

        graph.addEdge(e1);
        graph.addEdge(e2);
        graph.addEdge(e3);
        graph.addEdge(e4);
        graph.addEdge(e5);
        graph.addEdge(e6);
        graph.addEdge(e7);
        graph.addEdge(e8);
        graph.addEdge(e9);
        graph.addEdge(e10);

        System.out.println(graph.isCyclic());
    }

    public static void testAlgorithm5()
    {
    	UndirectedWeightedGraph graph = new UndirectedWeightedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        WeightedEdge edge1 = new WeightedEdge(n1, n2, 35);
        WeightedEdge edge2 = new WeightedEdge(n1, n3, 40);
        WeightedEdge edge3 = new WeightedEdge(n3, n2, 25);
        WeightedEdge edge4 = new WeightedEdge(n4, n2, 10);
        WeightedEdge edge5 = new WeightedEdge(n3, n4, 20);
        WeightedEdge edge6 = new WeightedEdge(n5, n3, 15);
        WeightedEdge edge7 = new WeightedEdge(n4, n5, 30);
        WeightedEdge edge8 = new WeightedEdge(n5, n2,  5);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        graph.addEdge(edge8);

        MinimumPartialTreeResult result = Algorithms.KruskalAlgorithm(graph);

        result.getTreeEdges().stream()
        .forEach(edge -> System.out.println(edge.getA().getID()
            + " - " + edge.getB().getID() + " Cost: " + edge.getWeight()));
    }

    public static void testAlgorithm6()
    {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        WeightedEdge edge1 = new WeightedEdge(n1, n2, 35);
        WeightedEdge edge2 = new WeightedEdge(n1, n3, 40);
        WeightedEdge edge3 = new WeightedEdge(n3, n2, 25);
        WeightedEdge edge4 = new WeightedEdge(n4, n2, 10);
        WeightedEdge edge5 = new WeightedEdge(n3, n4, 20);
        WeightedEdge edge6 = new WeightedEdge(n5, n3, 15);
        WeightedEdge edge7 = new WeightedEdge(n4, n5, 30);
        WeightedEdge edge8 = new WeightedEdge(n5, n2,  5);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);
        graph.addEdge(edge8);

        MinimumPartialTreeResult result = Algorithms.BoruvkaAlgorithm(graph);

        result.getTreeEdges().stream()
                .forEach(edge -> System.out.println(edge.getA().getID()
                        + " - " + edge.getB().getID() + " Cost: " + edge.getWeight()));
    }

    public static void testAlgorithm7() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");
        Node n8 = new Node("8");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);

        WeightedArc a1 = new WeightedArc(n1, n2, 28);
        WeightedArc a2 = new WeightedArc(n1, n3, 1);
        WeightedArc a3 = new WeightedArc(n1, n4, 2);
        WeightedArc a4 = new WeightedArc(n2, n5, 9);
        WeightedArc a5 = new WeightedArc(n2, n7, 10);
        WeightedArc a6 = new WeightedArc(n3, n2, 8);
        WeightedArc a7 = new WeightedArc(n3, n7, 26);
        WeightedArc a8 = new WeightedArc(n4, n7, 24);
        WeightedArc a9 = new WeightedArc(n4, n8, 27);
        WeightedArc a10 = new WeightedArc(n5, n3, 5);
        WeightedArc a11 = new WeightedArc(n5, n6, 8);
        WeightedArc a12 = new WeightedArc(n5, n8, 7);
        WeightedArc a13 = new WeightedArc(n6, n8, 7);
        WeightedArc a14 = new WeightedArc(n7, n8, 1);

        graph.addEdge(a1);
        graph.addEdge(a2);
        graph.addEdge(a3);
        graph.addEdge(a4);
        graph.addEdge(a5);
        graph.addEdge(a6);
        graph.addEdge(a7);
        graph.addEdge(a8);
        graph.addEdge(a9);
        graph.addEdge(a10);
        graph.addEdge(a11);
        graph.addEdge(a12);
        graph.addEdge(a13);
        graph.addEdge(a14);

        Algorithms.BellmanFordDijkstraResult result = Algorithms.DijkstraAlgorithm(graph, n1);
        System.out.println("------------ DISTANCES -------------");
        result.getDistances().forEach((node, weight) -> System.out.println(n1.getID() + " -> " + node.getID() + " (" + weight + ")"));
        System.out.println("----------- PREDECESSORS -----------");
        result.getPredecessors().forEach((node1, node2) -> {
            if (node1 == null)
                System.out.println("null" + " <- " + node2.getID());
            else if (node2 == null)
                System.out.println(node1.getID() + " <- " + "null");
            else
                System.out.println(node1.getID() + " <- " + node2.getID());
        });
}

    public static void testAlgorithm8() {
        DirectedWeightedGraph graph = new DirectedWeightedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");
        Node n8 = new Node("8");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);
        graph.addNode(n6);
        graph.addNode(n7);
        graph.addNode(n8);

        WeightedArc a1 = new WeightedArc(n1, n2, 28);
        WeightedArc a2 = new WeightedArc(n1, n3, 1);
        WeightedArc a3 = new WeightedArc(n1, n4, 2);
        WeightedArc a4 = new WeightedArc(n2, n5, 9);
        WeightedArc a5 = new WeightedArc(n2, n7, 10);
        WeightedArc a6 = new WeightedArc(n3, n2, 8);
        WeightedArc a7 = new WeightedArc(n3, n7, 26);
        WeightedArc a8 = new WeightedArc(n4, n7, 24);
        WeightedArc a9 = new WeightedArc(n4, n8, 27);
        WeightedArc a10 = new WeightedArc(n5, n3, 5);
        WeightedArc a11 = new WeightedArc(n5, n6, 8);
        WeightedArc a12 = new WeightedArc(n5, n8, 7);
        WeightedArc a13 = new WeightedArc(n6, n8, 7);
        WeightedArc a14 = new WeightedArc(n7, n8, 1);

        graph.addEdge(a1);
        graph.addEdge(a2);
        graph.addEdge(a3);
        graph.addEdge(a4);
        graph.addEdge(a5);
        graph.addEdge(a6);
        graph.addEdge(a7);
        graph.addEdge(a8);
        graph.addEdge(a9);
        graph.addEdge(a10);
        graph.addEdge(a11);
        graph.addEdge(a12);
        graph.addEdge(a13);
        graph.addEdge(a14);

        Algorithms.BellmanFordDijkstraResult result = Algorithms.BellmanFordAlgorithm(graph, n1);
        System.out.println("------------ DISTANCES -------------");
        result.getDistances().forEach((node, weight) -> System.out.println(n1.getID() + " -> " + node.getID() + " (" + weight + ")"));
        System.out.println("----------- PREDECESSORS -----------");
        result.getPredecessors().forEach((node1, node2) -> {
            if (node1 == null)
                System.out.println("null" + " <- " + node2.getID());
            else if (node2 == null)
                System.out.println(node1.getID() + " <- " + "null");
            else
                System.out.println(node1.getID() + " <- " + node2.getID());
        });
    }

    public static void testAlgorithm10()
    {
        DirectedGraph graph = new DirectedGraph();

        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");

        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.addNode(n5);

        Arc a1 = new Arc(n1, n2);
        Arc a2 = new Arc(n1, n4);
        Arc a3 = new Arc(n2, n3);
        Arc a4 = new Arc(n2, n5);
        Arc a5 = new Arc(n3, n1);
        Arc a6 = new Arc(n3, n5);
        Arc a7 = new Arc(n4, n1);
        Arc a8 = new Arc(n4, n3);
        Arc a9 = new Arc(n5, n2);
        Arc a10 = new Arc(n5, n4);

        graph.addEdge(a1);
        graph.addEdge(a2);
        graph.addEdge(a3);
        graph.addEdge(a4);
        graph.addEdge(a5);
        graph.addEdge(a6);
        graph.addEdge(a7);
        graph.addEdge(a8);
        graph.addEdge(a9);
        graph.addEdge(a10);

        Algorithms.EulerianResult result = Algorithms.EulerianCircuit(graph, n3);

        System.out.print("W: ");
        result.getEulerianNodes().forEach(node -> System.out.print(node.getID() + " "));
        System.out.print("\n\nD:\n");
        result.getEulerianRoad().forEach(arc -> System.out.println(arc.getA().getID() + " "
                + arc.getB().getID()));
    }


    public static void main(String args[])
    {
    	testAlgorithm8();
    }

}
