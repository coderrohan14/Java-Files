package DS;

public class DisjointSet {
    int rank[], parent[], n;

    // constructor
    public DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        this.n = n;
        makeSets();
    }

    // for making each node as a parent of itself
    void makeSets() {
        for (int i = 1; i <= n; i++) {
            parent[i] = -1;
            rank[i] = 1;
        }
    }

    // find function using path compression
    int find(int x) {
        if (parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    // Union function using rank
    void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y)
            return;
        if (rank[x] > rank[y]) {
            // x will become the parent
            parent[y] = x;
            rank[x] += rank[y];
        } else {
            // y will become the parent
            parent[x] = y;
            rank[y] += rank[x];
        }
    }
}
