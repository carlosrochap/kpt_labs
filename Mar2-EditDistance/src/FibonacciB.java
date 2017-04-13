import java.util.ArrayList;

/**
 * Created by Carlos on 3/2/2017.
 */
public class FibonacciB {
    ArrayList<Integer> fibCache = new ArrayList<>();

    public FibonacciB(){
        fibCache.add(0);
        fibCache.add(1);
    }

    public int dynamicFib(int n){

        if (n >= fibCache.size()) {
          fibCache.add(n, dynamicFib(n-1) + dynamicFib(n-2));
        }

        return fibCache.get(n);

//        return res;
    }
    public static void main(String[] args){
        FibonacciB fib = new FibonacciB();
        System.out.println(fib.dynamicFib(50));
    }
}
