package exercise;

class App {
    public static boolean isBigOdd(int number) {
        // BEGIN
        boolean isBigOddVariable = (number % 2 == 1) && (number >= 1001) ? true : false;
        // END
        return isBigOddVariable;
    }

    public static void sayEvenOrNot(int number) {
        // BEGIN
        if (number % 2 == 0){
            System.out.println("yes");
        }else {
            System.out.println("no");
        }
        // END
    }

    public static void printPartOfHour(int minutes) {
        // BEGIN
        if ((minutes >= 0) && (minutes <= 14)){
            System.out.println("First");
        } else if ( (minutes >= 15) && (minutes <= 30)){
            System.out.println("Second");
        } else if ( (minutes >= 31) && (minutes <= 45)){
            System.out.println("Third");
        } else if ( (minutes >= 46) && (minutes <= 59)){
            System.out.println("Fourth");
        } else {
            System.out.println("Incorrect value");
        }
        // END
    }
}
