
package exercise;

class App {
    // BEGIN
    public static int[] reverse(int[] array) {
        int[] reversedArray = new int[array.length];

        for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
            reversedArray[j] = array[i];
        }
        return reversedArray;
    }

    public static int mult(int[] array) {
        int result = 1;
        if (array.length == 0) {
            return 0;
        } else {
            for (int i = 0; i < array.length; i++) {
                result *= array[i];
            }
        }
        return result;
    }

    public static int[] flattenMatrix(int[][] matrix) {
        int size;
        int[] array = {};
        if (matrix.length == 0) {
            return array;
        } else {
            size = matrix[0].length + matrix[1].length;
            array = new int[size];
            for (int i = 0, k = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    array[k] = matrix[i][j];
                    k++;
                }
            }
        }
        return array;
    }
    // END
}
