package exercise;

// BEGIN
public interface Home {

    double getArea();

    default int compareTo(Home obj) {
        if (this.getArea() > obj.getArea()) {
            return 1;
        } else if (obj.getArea() == this.getArea()) {
            return 0;
        }
        return -1;
    }
}

// END
