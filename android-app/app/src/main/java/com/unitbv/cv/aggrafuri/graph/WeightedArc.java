package com.unitbv.cv.aggrafuri.graph;

public class WeightedArc extends Arc
{
	
	private Integer m_weight = null;

	public WeightedArc(Node first, Node second, int weight) 
	{
		super(first, second);
		m_weight = Integer.valueOf(weight);
	}

	public Integer getWeight() 
	{
		return m_weight;
	}

	public void setWeight(int weight)
	{
		m_weight = Integer.valueOf(weight);
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
