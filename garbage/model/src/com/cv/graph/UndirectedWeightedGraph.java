package com.cv.graph;

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
