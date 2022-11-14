package exercise;

import exercise.exception.CustomExceptions;

// BEGIN
public class Circle {
    private Point point;
    private int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (getRadius() < 0) {
            throw CustomExceptions.NEGATIVE_RADIUS_EXCEPTION;
        } else {
            return Math.PI * radius * radius;
        }
    }
}
// END
