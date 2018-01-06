package com.unitbv.cv.aggrafuri.graph;

public class GraphModel {

    AbstractGraph graph;
    GraphType type = GraphType.NOT_SET;

    public GraphModel(GraphType type)
    {
        setType(type);
    }

    private void initGraph() {
        switch (this.type)
        {
            case NOT_SET:
                graph = null;
                break;
            case UNDIRECTED:
                graph = new UndirectedGraph();
                break;
            case DIRECTED:
                graph = new DirectedGraph();
                break;
            case UNDIRECTED_WEIGHTED:
                graph = new UndirectedWeightedGraph();
                break;
            case DIRECTED_WEIGHTED:
                graph = new DirectedWeightedGraph();
                break;
        }
    }

    public AbstractGraph getGraph() {
        return graph;
    }

    public void setGraph(AbstractGraph graph) {
        this.graph = graph;
    }

    public GraphType getType() {
        return type;
    }

    public void setType(GraphType type) {
        this.type = type;
        initGraph();
    }
}
