package exercise;

class App {
    // BEGIN
    public static int[] sort(int[] array) {
        int sorted;

        for (int i = 0; i < array.length - 1; i++) {
            sorted = 0;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] += array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    sorted = 1;
                }
            }
            if (sorted == 0) {
                return array;
            }
        }
        return array;
    }


    public static int[] modifiedSort(int[] array) {
        int sorted;
        int min;

        for (int i = 0; i < array.length - 1; i++) {
            sorted = 0;
            min = i;
            for (int j = i; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] += array[j + 1];
                    array[j + 1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    sorted = 1;
                }
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            if (sorted == 0) {
                return array;
            }

            if (min != i) {
                array[min] += array[i];
                array[i] = array[min] - array[i];
                array[min] = array[min] - array[i];
            }
        }
        return array;
    }
// END
}
