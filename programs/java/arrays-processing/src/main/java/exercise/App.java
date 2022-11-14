package exercise;

import java.util.Arrays;

class App {
    // BEGIN
    public static int getIndexOfMaxNegative(int[] array) {
        int index = -1;

        for (int i = 0, max = Integer.MIN_VALUE; i < array.length; i++) {
            if (array[i] < 0 && array[i] > max) {
                index = i;
                max = array[i];
            }
        }
        return index;
    }

    public static int[] getElementsLessAverage(int[] array) {
        int sum = 0;

        if (array.length == 0) {
            return array;
        }

        // sum of elements in array
        for (int a : array) {
            sum += a;
        }
        int average = sum / array.length;
        int length = 0;

        // find the length of new array
        for (int i = 0; i < array.length; i++) {
            if (array[i] <= average) {
                length++;
            }
        }

        int[] newArray = new int[length];

        for (int i = 0; i < array.length; i++) {
            if (array[i] <= average) {
                newArray[i] = array[i];
            }
        }
        return newArray;
    }

    public static int getSumBetweenMinAndMax(int[] array) {
        int minIndex = 0;
        int maxIndex = 0;
        int sum = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] <= array[minIndex]) {
                minIndex = i;
            } else if (array[i] >= array[maxIndex]) {
                maxIndex = i;
            }
        }

        if (minIndex > maxIndex) {
            minIndex += maxIndex;
            maxIndex = minIndex - maxIndex;
            minIndex = minIndex - maxIndex;
        }

        int[] newArray = Arrays.copyOfRange(array, minIndex + 1, maxIndex);

        for (int i = 0; i < newArray.length; i++) {
            sum += newArray[i];
        }

        return sum;
    }
    // END
}
