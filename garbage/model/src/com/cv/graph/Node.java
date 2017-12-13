package com.cv.graph;

public class Node 
{
	
	private String m_id;
	
	public Node(String nodeID)
	{
		m_id = new String(nodeID);
	}

	public String getID() 
	{
		return m_id;
	}
	
//	public boolean equals(Object other)
//	{
//		if (other instanceof Node)
//			return m_id.contentEquals(((Node)other).getID());
//		return false;
//	}
}
