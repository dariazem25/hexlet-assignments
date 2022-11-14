package exercise;

class App {
    // BEGIN
    public static String getTypeOfTriangle(int a, int b, int c) {
        if (a == b && b == c) {
            return "Равносторонний";
        } else if ((a == b) || (a == c) || (b == c)) {
            return "Равнобедренный";
        } else if ((a + b) < c || (a + c) < b || (b + c) < a) {
            return "Треугольник не существует";
        } else {
            return "Разносторонний";
        }
    }

    public static int getFinalGrade(int examGrade, int projectCount) {
        if (examGrade > 90 || projectCount > 10) {
            return 100;
        } else if (examGrade > 75 && projectCount >= 5) {
            return 90;
        } else if (examGrade > 50 && projectCount >= 2) {
            return 75;
        } else {
            return 0;
        }
    }
    // END
}
