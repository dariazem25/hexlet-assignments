// BEGIN
package exercise.geometry;

public class Segment {

    public static double[][] makeSegment(double[] a, double[] b) {
        return new double[][]{a, b};
    }

    public static double[] getBeginPoint(double[][] segment) {
        return segment[0];
    }

    public static double[] getEndPoint(double[][] segment) {
        return segment[1];
    }
}

// END
