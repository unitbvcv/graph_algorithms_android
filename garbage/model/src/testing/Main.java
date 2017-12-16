package testing;

import com.cv.graph.Algorithms;
import com.cv.graph.Arc;
import com.cv.graph.DirectedGraph;
import com.cv.graph.Node;

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

    public static void main(String args[])
    {
        testAlgorithm2();
    }

}
