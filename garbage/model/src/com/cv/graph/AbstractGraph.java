package com.cv.graph;

import java.util.HashSet;
import java.util.Optional;

public abstract class AbstractGraph 
{

	private HashSet<Node> m_nodes = null;
	private HashSet<Edge> m_edges = null;
	
	public HashSet<Node> getNodes() 
	{
		return m_nodes;
	}
	
	public Node getNode(String id)
	{
		Optional<Node> foundNode = m_nodes.stream()
		.filter(node -> node.getID().contentEquals(id))
		.findAny();
		return foundNode.isPresent() ? foundNode.get() : null;
	}
	
	public boolean addNode(Node node) // sau cu id
	{
		return m_nodes.add(node);
	}
	
	public boolean removeNode(Node node) // sau cu id
	{
		return m_nodes.remove(node);
	}
	
	public HashSet<Edge> getEdges() 
	{
		return m_edges;
	}
	
	public abstract Edge getEdge(Node a, Node b);
	public abstract boolean addEdge(Edge edge);
	public abstract boolean removeEdge(Edge edge);
	
}
