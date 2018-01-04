package com.cv.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

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
			//HashMap<Node, Integer> analizat = result.getAnalizedTime();
			
            Predicate<Edge> isReturnEdge = (edge) -> {
            	
            	Node x, y;
            	
            	// se aleg nodurile muchiei (x, y) a.i. t1_x < t1_y
            	if (vizitat.get(edge.getA()) < vizitat.get(edge.getB()))
            	{
            		x = edge.getA();
            		y = edge.getB();
            	}
            	else
            	{
            		x = edge.getB();
            		y = edge.getA();
            	}
            	
                Node yPredecessor = result.getPredecessors().get(y);
                boolean xPredecessorOfY = x.equals(yPredecessor);
                return !xPredecessorOfY;
                };
                
            for (Edge edge : m_edges)			
                if (isReturnEdge.test(edge))
					return true;
		}
		return false;
	}

}
