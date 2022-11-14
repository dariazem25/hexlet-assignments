package exercise;

// BEGIN
public class Cottage implements Home {
    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.getArea() + " метров";
    }
}

// END
