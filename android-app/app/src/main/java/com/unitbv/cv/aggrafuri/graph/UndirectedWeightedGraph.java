package com.cv.graph;

import java.util.Optional;

public class UndirectedWeightedGraph extends UndirectedGraph
{
	
	/**
	 * Creates a graph in which every Edge object is an WeightedEdge object.
	 */
	public UndirectedWeightedGraph()
	{
		super();
	}

	/**
	 * Checks beforehand whether the provided Edge object is an WeightedEdge object or not.
	 */
	@Override
	public boolean addEdge(Edge edge) 
	{
		if (edge instanceof WeightedEdge)
			return m_edges.add(edge);
		return false;
	}

	/**
	 * Returns an Edge object.
	 */
	@Override
	public Edge getEdge(Node a, Node b)
	{
		Edge edgeToFind = new Edge(a, b);
		Optional<Edge> foundEdge = m_edges.stream()
				.filter(edge -> edgeToFind.equals(edge))
				.findAny();
		return foundEdge.orElse(null);
	}

	/**
	 * Checks beforehand whether the provided Edge object is an WeightedEdge object or not.
	 */
	@Override
	public boolean removeEdge(Edge edge) 
	{
		if (edge instanceof WeightedEdge)
			return m_edges.remove(edge);
		return false;
	}
}
