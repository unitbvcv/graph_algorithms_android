package com.unitbv.cv.aggrafuri.graph;

public class WeightedArc extends Arc
{
	
	private Double m_weight = null;

	public WeightedArc(Node first, Node second, Double weight)
	{
		super(first, second);
		m_weight = new Double(weight);
	}

	public Double getWeight()
	{
		return m_weight;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof WeightedArc)
		{
			WeightedArc otherWArc = (WeightedArc)other;
			return this.getA().equals(otherWArc.getA())
				&& this.getB().equals(otherWArc.getB())
				&& m_weight.intValue() == otherWArc.getWeight().intValue();
		}
		return false;
	}
	
}
