package graphalgorithms;

import java.util.Comparator;

public class VertexDComparator implements Comparator<Vertex> {
    @Override
    public int compare(Vertex v1, Vertex v2) {
        return v1.getD() - v2.getD();
    }
}
