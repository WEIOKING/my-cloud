package cn.ply.cloud.algorithmsvr.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:ply
 * @Description:
 * 6. Z 字形变换
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Date: created in 2019/9/4
 * @Modified By:
 */
public class ZConvert {
    public static String convert(String s, int numRows) {
        if (numRows == 1){
            return s;
        }
        //创建stringBuilder的数组，长度为字符串长度和转换行数较小的一个，并初始化数组
        int min = Math.min(s.length(), numRows);
        List<StringBuilder> builders = new ArrayList<>(min);
        for (int i = 0; i < min; i++) {
            builders.add(new StringBuilder());
        }
        int curRow = 0;
        boolean flag = false;
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //将字符放入对应builder中
            builders.get(curRow).append(s.charAt(i));
            //如果行数为第一或最后一行，标志取反
            if (curRow == 0 || curRow == builders.size() - 1){
                flag = !flag;
            }
            //根据标志状态判断当前行加1或减1
            curRow += flag ? 1 : -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder builder : builders) {
            stringBuilder.append(builder);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("ab", 1));
    }
}
