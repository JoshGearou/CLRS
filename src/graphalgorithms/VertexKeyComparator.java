package graphalgorithms;

import java.util.Comparator;

public class VertexKeyComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex v1, Vertex v2) {
        return v1.getKey() - v2.getKey();
    }
}

