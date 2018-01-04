package com.unitbv.cv.aggrafuri.graph;

public class WeightedEdge extends Edge
{
	private Integer m_weight = null;

	public WeightedEdge(Node first, Node second, int weight) 
	{
		super(first, second);
		m_weight = new Integer(weight);
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
			return (getA().equals(otherWEdge.getA()) && getB().equals(otherWEdge.getB())
				|| getA().equals(otherWEdge.getB()) && getB().equals(otherWEdge.getA()))
				&& m_weight.intValue() == otherWEdge.getWeight().intValue();
		}
		return false;
	}
	
}
