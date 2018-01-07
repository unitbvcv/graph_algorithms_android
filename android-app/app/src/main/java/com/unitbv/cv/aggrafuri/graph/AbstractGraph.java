package com.unitbv.cv.aggrafuri.graph;

import java.util.HashSet;
import java.util.Optional;

public abstract class AbstractGraph 
{

	protected HashSet<Node> m_nodes = null;
	protected HashSet<Edge> m_edges = null;

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

	public boolean containsNode(Node node) {
		for (Node currentNode : m_nodes) {
			if (currentNode.equals(node)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsEdge(Edge edge)
	{
		for (Edge currentEdge : m_edges)
			if (currentEdge.equals(edge))
				return true;
		return false;
	}
	
	public HashSet<Edge> getEdges() 
	{
		return m_edges;
	}
	
	public abstract Edge getEdge(Node a, Node b);
	public abstract boolean addEdge(Edge edge);
	public abstract boolean removeEdge(Edge edge);
	
}
