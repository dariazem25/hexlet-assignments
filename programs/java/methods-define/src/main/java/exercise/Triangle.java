package exercise;

class Triangle {
    // BEGIN
    public static double getSquare(double firstSide, double secondSide, double angle) {
        double radian = (angle * Math.PI) / 180;
        return (firstSide * secondSide) / 2 * Math.sin(radian);
    }

    public static void main(String[] args) {
        System.out.println(getSquare(4, 5, 45));
    }
    // END
}
