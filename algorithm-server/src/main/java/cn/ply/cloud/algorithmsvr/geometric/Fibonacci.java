package cn.ply.cloud.algorithmsvr.geometric;

/**
 * @Author ply
 * @Description
 * @Date created in 2022/3/22
 * @ModifiedBy
 */
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int i = 1000;
        long endTime = System.nanoTime();
        long result2 = fibonacci.fibonacci2(i);
        long endTime2 = System.nanoTime();
        System.out.println(endTime2 - endTime + "ns" + "  result2:" + result2);

    }

    public int fibonacci(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }

    }

    public long fibonacci2(int n) {
        if (n <= 1) {
            return 1;
        }
        long last = 1;
        long lastLast = 1;
        long a = 0;
        for (int i = 2; i <= n; i++) {
            a = last + lastLast;
            lastLast = last;
            last = a;
        }
        return a;
    }
}