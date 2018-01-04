package com.unitbv.cv.aggrafuri.graph;

import java.util.HashSet;
import java.util.Optional;

public abstract class AbstractGraph 
{

	protected HashSet<Node> m_nodes = null;
	protected HashSet<com.unitbv.cv.aggrafuri.ui.Edge> m_edges = null;

	public HashSet<Node> getNodes() 
	{
		return m_nodes;
	}
	
	public Node getNode(String id)
	{
		Optional<Node> foundNode = m_nodes.stream()
		.filter(node -> node.getID().contentEquals(id))
		.findAny();
		return foundNode.orElse(null);
	}
	
	public boolean addNode(Node node) // sau cu id
	{
		return m_nodes.add(node);
	}
	
	public boolean removeNode(Node node) // sau cu id
	{
		return m_nodes.remove(node);
	}
	
	public HashSet<com.unitbv.cv.aggrafuri.ui.Edge> getEdges()
	{
		return m_edges;
	}
	
	public abstract com.unitbv.cv.aggrafuri.ui.Edge getEdge(Node a, Node b);
	public abstract boolean addEdge(com.unitbv.cv.aggrafuri.ui.Edge edge);
	public abstract boolean removeEdge(com.unitbv.cv.aggrafuri.ui.Edge edge);
	
}
