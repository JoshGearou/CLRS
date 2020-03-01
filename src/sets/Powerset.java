package sets;

import java.util.ArrayList;
import java.util.List;

public class Powerset {

    List<List<Integer>> powerset = new ArrayList<>();
    public List<List<Integer>> powerset(List<Integer> lst, List<Integer> acc, int index) {
        if (index == lst.size()) {
            powerset.add(new ArrayList<>(acc));
            return powerset;
        }

        acc.add(lst.get(index)); // include element
        powerset(lst, acc, index + 1);
        acc.remove(acc.size() - 1); // don't include element
        powerset(lst, acc, index + 1);

        return powerset;
    }

    public List<List<Integer>> powerset(List<Integer> lst, int index) {
        List<List<Integer>> res = new ArrayList<>();
        if (index == lst.size()) {
            List<Integer> empty = new ArrayList<>();
            res.add(empty);
        }
        else {
            List<List<Integer>> subLists = powerset(lst, index+1);
            for (List<Integer> subList: subLists) {
                List<Integer> add = new ArrayList<>(subList);
                add.add(0, lst.get(index));
                res.add(subList);
                res.add(add);
            }
        }
        return res;
    }
}
