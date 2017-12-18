package com.cv.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.BiPredicate;

public class UndirectedGraph extends AbstractGraph
{
	
	public UndirectedGraph()
	{
		super();
		m_edges = new HashSet<>();
		m_nodes = new HashSet<>();
	}

	/**
	 * Returns an Edge object.
	 */
	@Override
	public Edge getEdge(Node a, Node b) 
	{
		Edge edgeToFind = new Edge(a, b);
		Optional<Edge> foundEdge = m_edges.stream()
		.filter(edge -> edge.equals(edgeToFind))
		.findAny();
		return foundEdge.orElse(null);
	}

	@Override
	public boolean addEdge(Edge edge) 
	{
		return m_edges.add(edge);
	}

	@Override
	public boolean removeEdge(Edge edge) 
	{
		return m_edges.remove(edge);
	}

	public boolean isCyclic()
	{
		if (m_edges.size() != 0 && m_nodes.size() != 0)
		{
			Algorithms.DepthFirstTraversalResult result = Algorithms.DepthFirstTraversal(this, m_nodes.iterator().next());
			HashMap<Node, Integer> vizitat = result.getVisitedTime();
			HashMap<Node, Integer> analizat = result.getAnalizedTime();

            for (Edge edge : m_edges)
			{
                BiPredicate<Node, Node> isReturnEdge = (x, y) -> {
                    int t1_x = 	vizitat.get(x),
                        t2_x = analizat.get(x),
                        t1_y = vizitat.get(y),
                        t2_y = analizat.get(y);
                    Node yPredecessor = result.getPredecessors().get(y);
                    boolean xPredecessorOfY = yPredecessor != null && x.equals(yPredecessor);
                    //return t1_y < t1_x && t1_x < t2_x && t2_x < t2_y && !xPredecessorOfY;
                    return !xPredecessorOfY && (t1_y < t1_x && t1_x < t2_x && t2_x < t2_y);
                };

                if (isReturnEdge.test(edge.getA(), edge.getB())
                        || isReturnEdge.test(edge.getB(), edge.getA()))
					return true;
			}
		}
		return false;
	}

}
