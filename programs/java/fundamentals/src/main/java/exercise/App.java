package exercise;

class App {
    public static void numbers() {
        // BEGIN
        int result1 = 8 / 2;
        int result2 = 100 % 3;
        System.out.println(result1 + result2);
        // END
    }

    public static void strings() {
        String language = "Java";
        // BEGIN
        System.out.println(language + " works on JVM");
        // END
    }

    public static void converting() {
        Number soldiersCount = 300;
        String name = "spartans";
        // BEGIN
        System.out.println(soldiersCount + " " + name);
        // END
    }
}
