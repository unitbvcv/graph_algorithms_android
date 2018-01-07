package com.unitbv.cv.aggrafuri.graph;

public class Edge 
{
	
	private Node m_A = null, m_B = null;
	
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

	public Node getOtherEnd(Node oneEnd)
	{
		return m_A.equals(oneEnd) ? m_B : (m_B.equals(oneEnd) ? m_A : null);
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

	@Override
	public int hashCode() {
		return (m_A.getID() + m_B.getID()).hashCode();
	}
}
