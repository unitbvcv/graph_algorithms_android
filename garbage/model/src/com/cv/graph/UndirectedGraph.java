package com.cv.graph;

import java.util.HashSet;
import java.util.Optional;

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
	
}
