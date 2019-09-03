package cn.ply.cloud.algorithmsvr.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:ply
 * @Description:
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: created in 2019/9/3
 * @Modified By:
 */
public class LengthOfLongestSubstring {

    /**
     * 优化滑动窗口法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int m = 0;
        for (int i = 0, j = 0; i < length; i++) {
            if (hashMap.containsKey(s.charAt(i))){
                //存在，则检查存在的字符串的索引+1（注：索引放入map是已经+1，这里比较不用+1） 是否比当前j的位置大，更大则表示重复字符再当前窗口，替换j
                j = Math.max(hashMap.get(s.charAt(i)), j);
            }
            //将字符索引加1放入map，索引加1可以保证字符串只有一个字符是也能正确输出，索引加1保存使上一步可以直接获取结果，不用再加1
            hashMap.put(s.charAt(i), i + 1);
            //比价最大长度和当前串长度，取较大的
            m = Math.max(m, i - j + 1);
        }
        return m;
    }
    /**
     * 滑动窗口法
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j =0 , m =0;
        while (i < length && j < length){
            if (set.contains(s.charAt(j))){
                //有重复字符则remove掉最左边的一个，左窗右移，再进行下一次判断，直到没有重复的
                set.remove(s.charAt(i ++));
            } else {
                //没有重复字符则添加当前字符至set，并将右窗右移
                set.add(s.charAt(j ++));
                //比价最大长度和当前串长度，取较大的
                m = Math.max(set.size(), m);
            }
        }
        return m;
    }
}
