package cn.ply.cloud.algorithmsvr.util;

/**
 * @Author:liyang
 * @Description:
 * @Date: created in 2018/3/3
 * @Modified By:
 */
public class BMapUtil {

    private static final double EARTH_RADIUS = 6370996.81;

    /**
     * 把经纬度转为度
     *
     * @param d
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 计算每一经度间距离
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double getLngDistOneDegree(double lng, double lat) {
        //设定一个初始比较值
        double lng_ = lng + 0.3;
        double latDistOneDegree = BMapUtil.getDistance(lng, lat, lng_, lat);
        double distOneDegree = latDistOneDegree / 0.3;
        return distOneDegree;
    }

    /**
     * 计算每一纬度间距离
     *
     * @param lng
     * @param lat
     * @return
     */
    public static double getLatDistOneDegree(double lng, double lat) {
        //设定一个初始比较值
        double lat_ = lat + 0.3;
        double latDistOneDegree = BMapUtil.getDistance(lng, lat, lng, lat_);
        double distOneDegree = latDistOneDegree / 0.3;
        return distOneDegree;
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @return 两点间距 m
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
                Math.sqrt(
                        Math.pow(Math.sin(a / 2), 2)
                                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)
                )
        );
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000.0;
        return s;
    }
}
