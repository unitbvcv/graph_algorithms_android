package com.cv.graph;

import java.util.Optional;

public class DirectedWeightedGraph extends DirectedGraph
{
	/**
	 * Creates a graph in which every Edge object is an WeightedArc object.
	 */
	public DirectedWeightedGraph()
	{
		super();
	}
	
	/**
	 * Checks beforehand whether the provided Edge object is an WeightedArc object or not.
	 */
	@Override
	public boolean addEdge(Edge edge) 
	{
		if (edge instanceof WeightedArc)
			return m_edges.add(edge);
		return false;
	}

	/**
	 * Returns an Arc object.
	 */
	@Override
	public Edge getEdge(Node a, Node b)
	{
		Arc arcToFind = new Arc(a, b);
		Optional<Edge> arcFound = m_edges.stream()
				.filter(edge -> arcToFind.equals(edge) )
				.findAny();
		return arcFound.orElse(null);
	}

	/**
	 * Checks beforehand whether the provided Edge object is an WeightedArc object or not.
	 */
	@Override
	public boolean removeEdge(Edge edge) 
	{
		if (edge instanceof WeightedArc)
			return m_edges.remove(edge);
		return false;
	}
}
