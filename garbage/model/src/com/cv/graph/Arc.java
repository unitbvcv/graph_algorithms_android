package com.cv.graph;

public class Arc extends Edge
{
	public enum ArcDirection
	{
		AB,
		BA,
		BOTH
	}

	private ArcDirection m_direction = null;
	
	public Arc(Node first, Node second, ArcDirection direction) 
	{
		super(first, second);
		m_direction = direction;
	}

	public ArcDirection getDirection() 
	{
		return m_direction;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof Arc)
		{
			Arc otherArc = (Arc)other;
			return this.getA().equals(otherArc.getA())
				&& this.getB().equals(otherArc.getB())
				&& m_direction.compareTo(otherArc.getDirection()) == 0
				// A = A2, B = B2 si au acelasi sens
				|| // sau
				// A = B2, B = A2 si au sensuri opuse sau au ambele sensul BOTH
				this.getA().equals(otherArc.getB())
				&& this.getB().equals(otherArc.getA())
				&& (m_direction == ArcDirection.AB && otherArc.getDirection() == ArcDirection.BA
				||  m_direction == ArcDirection.BA && otherArc.getDirection() == ArcDirection.AB
				||  m_direction == ArcDirection.BOTH && otherArc.getDirection() == ArcDirection.BOTH);
		}
		return false;
	}
	
}
