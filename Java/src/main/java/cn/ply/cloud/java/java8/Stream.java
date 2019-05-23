package cn.ply.cloud.java.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author:ply
 * @Description: stream测试
 * @Date: created in 2019/5/23
 * @Modified By:
 */
public class Stream {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 3, 4, 1, 2, 3, 5, 6, 9, 8);

        System.out.println("初始状态:");
        integers.forEach(System.out::print);
        System.out.println();

        //sorted 方法对数据流进行排序
        System.out.println("排序后:");
        integers.stream().sorted(Comparator.comparing(Function.identity())).forEach(System.out::print);
        System.out.println();

        //filter 方法对数据流进行过滤
        System.out.println("筛选大于4的结果:");
        integers.stream().filter(i -> i > 4).forEach(System.out::print);
        System.out.println();

        //parallelStream 流并行处理
        System.out.println("并行流筛选大于4的结果:");
        integers.parallelStream().filter(i -> i > 4).forEach(System.out::print);
        System.out.println();

        //map 映射结果
        System.out.println("每个数求平方:");
        integers.stream().map(i -> i * i).forEach(System.out::print);
        System.out.println();

        //distinct 去重
        System.out.println("去重:");
        integers.stream().distinct().forEach(System.out::print);
        System.out.println();

        //Collectors 可用于返回列表或字符串
        System.out.println("合并字符串:");
        List<String> list = integers.stream().map(i -> Integer.toString(i * i)).collect(Collectors.toList());
        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        //统计
        System.out.println("统计:");
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(i -> i).summaryStatistics();
        System.out.println("max: " + intSummaryStatistics.getMax());
        System.out.println("count: " + intSummaryStatistics.getCount());
    }
}
