package com.cv.graph;

public class WeightedArc extends Arc
{
	
	private Integer m_weight = null;

	public WeightedArc(Node first, Node second, int weight) 
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
		if (other instanceof WeightedArc)
		{
			WeightedArc otherWArc = (WeightedArc)other;
			return ((Arc)this).equals(other)
				&& m_weight.intValue() == otherWArc.getWeight().intValue();
		}
		return false;
	}
	
}
