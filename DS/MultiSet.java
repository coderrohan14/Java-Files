package DS;

import java.util.*;

class MultiSet {
    TreeMap<Long, Integer> map;

    public MultiSet() {
        map = new TreeMap<>();
    }

    public void add(long a) {
        if (!map.containsKey(a))
            map.put(a, 1);
        else
            map.put(a, map.get(a) + 1);
    }

    public void remove(long a) {
        map.put(a, map.get(a) - 1);
        if (map.get(a) == 0)
            map.remove(a);
    }

    public boolean contains(long a) {
        return map.containsKey(a);
    }

    public int count(long a) {
        if (!map.containsKey(a))
            return 0;
        else
            return map.get(a);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean equals(MultiSet ms) {
        for (long k : map.keySet()) {
            if (count(k) != ms.count(k))
                return false;
        }
        return true;
    }

    public String toString() {
        return map.toString();
    }
}