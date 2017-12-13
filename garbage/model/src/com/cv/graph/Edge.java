package com.cv.graph;

public class Edge 
{
	
	private Node m_A, m_B;
	
	public Edge(Node first, Node second)
	{
		m_A = first;
		m_B = second;
	}
	
	public Node getA()
	{
		return m_A;
	}
	
	public Node getB()
	{
		return m_B;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof Edge)
		{
			Edge otherEdge = (Edge)other;
			return m_A.equals(otherEdge.getA()) && m_B.equals(otherEdge.getB())
				|| m_A.equals(otherEdge.getB()) && m_B.equals(otherEdge.getA());
		}
		return false;
	}
}
