package cn.ply.cloud.algorithmsvr.leetcode;

/**
 * @Author:ply
 * @Description:
 * @Date: created in 2019/9/4
 * @Modified By:
 */
public class IntReverse {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10
                    || (rev == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)
                    || rev < Integer.MIN_VALUE / 10
                    || (rev == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)){
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println(reverse(Integer.MAX_VALUE));
    }
}
