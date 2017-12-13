package com.cv.graph;

public class WeightedEdge extends Edge
{
	private Integer m_weight = null;

	public WeightedEdge(Node first, Node second, int cost) 
	{
		super(first, second);
		m_weight = new Integer(cost);
	}

	public Integer getWeight() 
	{
		return m_weight;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof WeightedEdge)
		{
			WeightedEdge otherWEdge = (WeightedEdge)other;
			return ((Edge)this).equals((Edge)other) 
				&& m_weight.intValue() == otherWEdge.getWeight().intValue();
		}
		return false;
	}
	
}
