package com.cv.graph;

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
