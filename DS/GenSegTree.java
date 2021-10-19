package DS;

public class GenSegTree {
    static final int inf = (int)1e9;

    static class my_node{
        int mn = inf;
        int freq = 0;
        my_node(){}
        my_node(int val){
            mn = val;
            freq = 1;
        }
        void merge(final my_node l,final my_node r){ // store the thing you wanna query
            mn = Math.min(l.mn,r.mn);
            freq = 0;
            if(l.mn == mn) freq += l.freq;
            if(r.mn == mn) freq += r.freq;
     
            // if we wanted the maximum, then we would do
            // like v = max(l.v,r.v)
        } 
    }

    static class my_update{
        int v = 0; // 4
        // use more variables if you want more information
        // these default values should be identity_transformation
        my_update(){}
        my_update(int val){
            v = val; // 5
        }
        // combine the current my_update with the other my_update (see keynotes)
        void combine(my_update other,final int tl,final int tr){
            v += other.v; // 6
    
            // you can be sure that the "other" is newer than current
    
        }
        // store the correct information in the my_node x
        void apply(my_node x,final int tl,final int tr){
    
            // no change in freq
            x.mn += v;
    
        }
    }
    static class SegTree{
        static int len;
        static my_node[] t;
        static my_update[] u;
        static boolean[] lazy;
        static my_node identityElement;
        static my_update identityTransformation;

        SegTree(int l){
            len = l;
            t = new my_node[len*4];
            u = new my_update[len*4];
            lazy = new boolean[len*4];
            identityElement = new my_node();
            identityTransformation = new my_update();
        } 

        static void pushDown(final int v, final int tl,final int tr){
            if(lazy[v]){
                int tm = (tl + tr) >> 1;   
                apply(v<<1,tl,tm,u[v]);
                apply(v<<1|1,tm+1,tr,u[v]);
                u[v] = identityTransformation;
                lazy[v] = false;
            }
        }

        static void apply(final int v, final int tl, final int tr, my_update upd){
            if(tl != tr){
                lazy[v] = true;
                u[v].combine(upd,tl,tr);
            }
            upd.apply(t[v],tl,tr);
        }

        
    }
}
