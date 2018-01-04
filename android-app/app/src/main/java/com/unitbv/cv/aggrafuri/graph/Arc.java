package com.unitbv.cv.aggrafuri.graph;

public class Arc extends Edge
{
	
	public Arc(Node first, Node second) 
	{
		super(first, second);
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof Arc)
		{
			Arc otherArc = (Arc)other;
			return this.getA().equals(otherArc.getA())
				&& this.getB().equals(otherArc.getB());
		}
		return false;
	}
	
}
