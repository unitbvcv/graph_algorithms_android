package com.unitbv.cv.aggrafuri.graph;

import java.util.HashSet;
import java.util.Optional;

public class DirectedGraph extends AbstractGraph
{
	/**
	 * Creates a graph in which every Edge object is an Arc object.
	 */
	public DirectedGraph()
	{
		super();
		m_edges = new HashSet<>();
		m_nodes = new HashSet<>();
	}
	
	/**
	 * Returns an Arc object.
	 */
	@Override
	public Edge getEdge(Node a, Node b) 
	{
		Arc arcToFind = new Arc(a, b);
		Optional<Edge> arcFound = m_edges.stream()
				.filter(edge -> ((Arc)edge).equals(arcToFind) )
				.findAny();
		return arcFound.orElse(null);
	}

	/**
	 * Checks beforehand whether the provided Edge object is an Arc object or not.
	 */
	@Override
	public boolean addEdge(Edge edge) 
	{
		if (edge instanceof Arc)
			return m_edges.add(edge);
		return false;
	}

	/**
	 * Checks beforehand whether the provided Edge object is an Arc object or not.
	 */
	@Override
	public boolean removeEdge(Edge edge) 
	{
		if (edge instanceof Arc)
			return m_edges.remove(edge);
		return false;
	}
	
}
