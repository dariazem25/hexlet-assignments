package exercise;

class Point {
    // BEGIN

    public static int[] makePoint(int a, int b) {
        return new int[]{a, b};
    }

    public static int getX(int[] point) throws Exception {
        if (point.length == 2) {
            return point[0];
        } else {
            throw new Exception("The point does not exist");
        }
    }

    public static int getY(int[] point) throws Exception {
        if (point.length == 2) {
            return point[1];
        } else {
            throw new Exception("The point does not exist");
        }
    }

    public static String pointToString(int[] point) throws Exception {
        if (point.length == 2) {
            return "(" + point[0] + ", " + point[1] + ")";
        } else {
            throw new Exception("The point does not exist");
        }
    }

    public static int getQuadrant(int[] point) throws Exception {
        int quadrant = 0;
        if (point.length == 0) {
            throw new Exception("The point does not exist");
        } else if (point[0] == 0 || point[1] == 0) {
            return 0;
        } else if (point[0] > 0 && point[1] > 0) {
            quadrant = 1;
        } else if (point[0] < 0 && point[1] > 0) {
            quadrant = 2;
        } else if (point[0] < 0 && point[1] < 0) {
            quadrant = 3;
        } else if (point[0] > 0 && point[1] < 0) {
            quadrant = 4;
        }
        return quadrant;
    }

    public static int[] getSymmetricalPointByX(int[] point) throws Exception {
        if (point.length == 2) {
            return new int[]{point[0], (-point[1])};
        } else {
            throw new Exception("The point does not exist");
        }
    }

    public static int calculateDistance(int[] point1, int[] point2) throws Exception {
        if (point1.length == 2 && point2.length == 2) {
            return (int) Math.sqrt(Math.pow((point2[0] - point1[0]), 2) + Math.pow((point2[1] - point1[1]), 2));
        } else {
            throw new Exception("The point does not exist");
        }
    }
    // END
}
