package cn.ply.cloud.algorithmsvr.sorted;

/**
 * @Author:ply
 * @Description: 插入排序
 * @Date: created in 2021/4/14
 * @Modified By:
 */
public class InsertSort {

    public static void sort(Comparable[] array){
        int j;
        //从索引1开始遍历
        for (int i = 1; i < array.length; i++) {
            //临时缓存
            Comparable c = array[i];
            //当前索引i的值和i之前的值依次比较
            for (j = i; j > 0 && c.compareTo(array[j - 1]) < 1; j --) {
                array[i] = array[j - 1];
            }
            array[j] = c;
        }
    }
}
