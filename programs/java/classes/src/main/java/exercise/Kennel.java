package exercise;

import java.util.Arrays;

//BEGIN
class Kennel {

    private static String[][] puppies = new String[0][0];
    private static int uniqueNumber = 1;

    public static void addPuppy(String[] puppy) {
        String[][] array = Arrays.copyOf(puppies, puppies.length + 1);

        if (puppies.length == 0) {
            array = new String[1][2];
            checkPuppy(array, puppy);
        } else {
            array[array.length - 1] = Arrays.copyOf(puppies[puppies.length - 1], 2);
            checkPuppy(array, puppy);
        }

        puppies = array;
    }

    public static void checkPuppy(String[][] newPuppies, String[] puppy) {
        boolean exist = false;

        for (int i = 0; i < puppies.length; i++) {
            if (puppy[0] == newPuppies[i][0]) {
                exist = true;
                break;
            }
        }

        if (!exist && puppy.length == 2 && puppy[0] != null && puppy[1] != null) {
            newPuppies[newPuppies.length - 1][0] = puppy[0];
            newPuppies[newPuppies.length - 1][1] = puppy[1];

        } else if (!exist && puppy.length == 2 && puppy[0] == null && puppy[1] != null) {
            newPuppies[newPuppies.length - 1][0] = "" + uniqueNumber;
            newPuppies[newPuppies.length - 1][1] = puppy[1];

        } else if (!exist && puppy.length == 2 && puppy[0] != null) {
            newPuppies[newPuppies.length - 1][0] = puppy[0];
            newPuppies[newPuppies.length - 1][1] = "usual";

        } else if (!exist && puppy.length == 1 && Character.isUpperCase(puppy[0].charAt(0))) {
            newPuppies[newPuppies.length - 1][0] = puppy[0];
            newPuppies[newPuppies.length - 1][1] = "usual";

        } else if (!exist && puppy.length == 1 && Character.isLowerCase(puppy[0].charAt(0))) {
            newPuppies[newPuppies.length - 1][0] = "Rex" + (++uniqueNumber);
            newPuppies[newPuppies.length - 1][1] = puppy[0];

        } else if (exist && puppy.length == 1) {
            newPuppies[newPuppies.length - 1][0] = "Rex" + (++uniqueNumber);
            newPuppies[newPuppies.length - 1][1] = "usual";

        } else if (exist && puppy.length == 2) {
            newPuppies[newPuppies.length - 1][0] = "Rex" + (++uniqueNumber);
            newPuppies[newPuppies.length - 1][1] = "usual";

        } else if (puppy.length == 0 || (puppy[0] == null && puppy[1] == null)) {
            throw new RuntimeException("You didn't provide puppy");

        }
    }

    public static void addSomePuppies(String[][] newPuppies) {
        for (String[] newPuppy : newPuppies) {
            addPuppy(newPuppy);
        }
    }

    public static int getPuppyCount() {
        return puppies.length;
    }

    public static boolean isContainPuppy(String name) {
        for (int i = 0; i < puppies.length; i++) {
            if (puppies[i][0] == name) {
                return true;
            }
        }
        return false;
    }

    public static String[][] getAllPuppies() {
        return puppies;
    }

    public static String[] getNamesByBreed(String breed) {
        String[] specifiedKennel = new String[2];

        for (int i = 0, j = 0; i < puppies.length; i++) {
            if (puppies[i][1] == breed) {
                specifiedKennel[j] = puppies[i][0];
                j++;
            }
        }
        return specifiedKennel;
    }

    public static void resetKennel() {
        puppies = new String[0][0];
    }

    public static boolean removePuppy(String name) {
        for (int i = 0; i < puppies.length; i++) {
            if (puppies[i][0] == name) {
                puppies = Arrays.copyOf(puppies, puppies.length - 1);
                return true;
            }
        }
        return false;
    }
}

// END
