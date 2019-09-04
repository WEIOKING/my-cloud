package cn.ply.cloud.algorithmsvr.leetcode;

/**
 * @Author:ply
 * @Description:
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: created in 2019/9/4
 * @Modified By:
 */
public class LongestPalindrome {
    /**
     * 动态规划
     * 回文串的前后的字符如果相同，那么加上前后字符就是更长的回文串
     * 先找出1个和2个字符的回文串，再依次找出更长的
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.isEmpty()){
            return "";
        }
        int start = 0;
        int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = getPalindromeLen(s, i, i);
            int len2 = getPalindromeLen(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    /**
     * 判断当前回文串两端字符是否相同，是则继续判断
     * @param s 总字符串
     * @param start 当前回文串开始索引
     * @param end 当前回文串结束索引
     * @return 回文串长度
     */
    private static int getPalindromeLen(String s, int start, int end){
        int l = start;
        int r = end;
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l --;
            r ++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("qwerttrewqqweeewqwwettyuiopoiuytrewqwertyuiop"));
    }
}
