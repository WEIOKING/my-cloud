package cn.ply.cloud.java.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author ply
 * @Description
 * @Date created in 2019/7/22
 * @ModifiedBy
 */
public class NewDateApi {
    /**
     Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。

     在旧版的 Java 中，日期时间 API 存在诸多问题，其中有：
        非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
        设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。
            java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
        时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。

     Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
        Local(本地) − 简化了日期时间的处理，没有时区的问题。
        Zoned(时区) − 通过制定的时区处理日期时间。
        新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
     */



    public static void main(String[] args) {
        //本地日期
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        //本地时间
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        //时间格式化
        String yyyyMMdd = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm:ss.SSS"));
        System.out.println(yyyyMMdd);

        //获取单个年月日
        System.out.println(localDateTime.getYear() + "年" + localDateTime.getMonthValue() + "月" + localDateTime.getDayOfMonth() + "日" + localDateTime.getHour() + "时" );

        //设置年月日,会返回一个新的时间对象，原有对象不会改变
        LocalDateTime localDateTime1 = localDateTime.withYear(2018).withHour(12);
        System.out.println(localDateTime1);

        //获取时间
        LocalDateTime parse = LocalDateTime.parse("2019-07-12 11:22:20", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parse);

        //系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(zoneId.getId());

        ZonedDateTime zonedDateTime = ZonedDateTime.now();
    }


}
