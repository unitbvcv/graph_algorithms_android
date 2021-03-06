package com.unitbv.cv.aggrafuri.graph;

public class Node 
{
	private String m_id = null;
	
	public Node(String nodeID)
	{
		m_id = new String(nodeID);
	}

	public String getID() 
	{
		return m_id;
	}

    @Override
    public int hashCode() {
        return m_id.hashCode();
    }

    public boolean equals(Object other)
	{
		if (other instanceof Node)
			return m_id.contentEquals(((Node)other).getID());
		return false;
	}
}
