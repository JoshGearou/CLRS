package sets;

import java.util.ArrayList;
import java.util.List;

public class Subset {

    List<List<Integer>> subsets = new ArrayList<>();
    public List<List<Integer>> subsetsOfSpecificSize(int size, List<Integer> lst, List<Integer> acc, int index) {
        if (size == 0) {
            subsets.add(new ArrayList<>(acc));
            return subsets;
        }

        if (index >= lst.size()) {
            return subsets;
        }

        for (int i=index; i<lst.size(); i++) {
            acc.add(lst.get(i));
            subsetsOfSpecificSize(size-1, lst, acc, i+1);
            acc.remove(acc.size()-1);
        }
        return subsets;
    }
}
