// BEGIN
package exercise;

import exercise.geometry.Point;
import exercise.geometry.Segment;

class App {

    public static double[] getMidpointOfSegment(double[][] segment) {
        double[] beginPoint = Segment.getBeginPoint(segment);
        double[] endPoint = Segment.getEndPoint(segment);

        double midPointX = (Point.getX(beginPoint) + Point.getX(endPoint)) / 2;
        double midPointY = (Point.getY(beginPoint) + Point.getY(endPoint)) / 2;

        return new double[]{midPointX, midPointY};
    }

    public static double[][] reverse(double[][] segment) {
        double[] newBeginPoint = new double[]{Point.getX(Segment.getEndPoint(segment)), Point.getY(Segment.getEndPoint(segment))};
        double[] newEndPoint = new double[]{Point.getX(Segment.getBeginPoint(segment)), Point.getY(Segment.getBeginPoint(segment))};
        return new double[][]{newBeginPoint, newEndPoint};
    }

    public static boolean isBelongToOneQuadrant(double[][] segment) {
        double[] beginPoint = Segment.getBeginPoint(segment);
        double[] endPoint = Segment.getEndPoint(segment);

        double x1 = Point.getX(beginPoint);
        double y1 = Point.getY(beginPoint);

        double x2 = Point.getX(endPoint);
        double y2 = Point.getY(endPoint);


        if ((0 < x1) && (0 < x2) && (0 < y1) && (0 < y2)) {
            return true;
        } else if ((0 > x1) && (0 > x2) && (0 < y1) && (0 < y2)) {
            return true;
        } else if ((0 > x1) && (0 > x2) && (0 > y1) && (0 > y2)) {
            return true;
        } else return (0 < x1) && (0 < x2) && (0 > y1) && (0 > y2);
    }
}


// END
