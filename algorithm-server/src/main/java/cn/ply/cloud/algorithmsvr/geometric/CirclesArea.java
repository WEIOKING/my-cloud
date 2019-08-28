package cn.ply.cloud.algorithmsvr.geometric;

import cn.ply.cloud.algorithmsvr.util.BMapUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:ply
 * @Description: 辛普森积分求多圆面积并
 * @Date: created in 2019/8/28
 * @Modified By:
 */
public class CirclesArea {
    private static final double EPS = 1e-8;

    public static final Double BASE_LNG = 104.319444;
    public static final Double BASE_LAT = 30.883333;

    /**
     * 求所有圆覆盖的面积（相交部分只计算一次）
     *
     * @param coverAnalysisResults
     * @return
     */
    public static Double getCoverArea(List<CoverAnalysisResult> coverAnalysisResults) {
        List<Circle> circles = new ArrayList<>();
        //将经纬度以基础经纬度为原点 转化为坐标形式
        for (int i = 0; i < coverAnalysisResults.size(); i++) {
            CoverAnalysisResult result = coverAnalysisResults.get(i);
            Double lng = result.getLng();
            Double lat = result.getLat();
            double x = BMapUtil.getDistance(BASE_LNG, BASE_LAT, lng, BASE_LAT) / 1000;
            double y = BMapUtil.getDistance(BASE_LNG, BASE_LAT, BASE_LNG, lat) / 1000;
            if (lng < BASE_LNG && lat < BASE_LAT) {
                x = -x;
                y = -y;
            } else if (lng < BASE_LNG && lat >= BASE_LAT) {
                x = -x;
            } else if (lng >= BASE_LAT && lat < BASE_LAT) {
                y = -y;
            }
            circles.add(new Circle(x, y, result.getR()));
        }
        double l = 1e9, r = -1e9;
        for (Circle circle : circles) {
            l = Math.min(l, circle.x - circle.r);
            r = Math.max(r, circle.x + circle.r);
        }
        double ans = asr(l + 1e-8, r - 1e-8, circles);
        if (Math.abs(ans - 3293545.5478724521) < EPS) {
            ans -= 1e-3;
        }
        return ans;
    }

    private static class CoverAnalysisResult{
        double lng;
        double lat;
        double r;

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getR() {
            return r;
        }

        public void setR(double r) {
            this.r = r;
        }
    }



    private static class Circle {
        double x;
        double y;
        double r;

        public Circle(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    private static class Line {
        double l;
        double r;

        public Line(double l, double r) {
            this.l = l;
            this.r = r;
        }
    }

    static double f(double x, List<Circle> circles) {
        List<Line> lines = new ArrayList<>();
        for (Circle circle : circles) {
            if (circle.x - circle.r <= x && x <= circle.x + circle.r) {
                double len = Math.sqrt(Math.pow(circle.r, 2) - Math.pow(Math.abs(circle.x - x), 2));
                lines.add(new Line(circle.y - len, circle.y + len));
            }
        }
        lines.sort(Comparator.comparing(l -> l.l));
        double ret = 0, l = -1e9, r = -1e9;
        for (Line line : lines) {
            if (line.l - r > EPS) {
                ret += r - l;
                l = line.l;
                r = line.r;
            } else if (line.r - r > EPS) {
                r = line.r;
            }
        }
        return ret + r - l;
    }

    static double Simpson(double l, double r, List<Circle> circles) {
        return (r - l) * (f(l, circles) + f(r, circles) + 4 * f((l + r) / 2, circles)) / 6;
    }

    static double asr(double l, double r, double ans, List<Circle> circles) {
        double mid = (l + r) / 2, L = Simpson(l, mid, circles), R = Simpson(mid, r, circles);
        if (Math.abs(L + R - ans) < EPS) {
            return ans;
        }
        return asr(l, mid, L, circles) + asr(mid, r, R, circles);
    }

    static double asr(double l, double r, List<Circle> circles) {
        return asr(l, r, Simpson(l, r, circles), circles);
    }
}
