package com.cv.graph;

public class WeightedEdge extends Edge
{
	private Integer m_cost = null;

	public WeightedEdge(Node first, Node second, int cost) 
	{
		super(first, second);
		m_cost = new Integer(cost);
	}

	public Integer getCost() 
	{
		return m_cost;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof WeightedEdge)
		{
			WeightedEdge otherWEdge = (WeightedEdge)other;
			return ((Edge)this).equals((Edge)other) 
				&& m_cost.intValue() == otherWEdge.getCost().intValue();
		}
		return false;
	}
	
}
