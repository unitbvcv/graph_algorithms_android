package com.unitbv.cv.aggrafuri.graph;

public class WeightedEdge extends Edge
{
	private Double m_weight = null;

	public WeightedEdge(Node first, Node second, Double weight)
	{
		super(first, second);
		m_weight = new Double(weight);
	}

	public Double getWeight()
	{
		return m_weight;
	}

	public void setWeight(Double weight) { this.m_weight = weight; }
	
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
