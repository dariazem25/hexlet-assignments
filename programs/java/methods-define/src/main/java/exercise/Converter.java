package exercise;

class Converter {
    // BEGIN
    public static int convert(int number, String unit) {
        if (unit.equals("b")) {
            int kb = number * 1024;
            return kb;
        } else if (unit.equals("Kb")) {
            int b = number / 1024;
            return b;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("10 Kb = " + convert(10, "b") + " b");
    }
    // END
}
