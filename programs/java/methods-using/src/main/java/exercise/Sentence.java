package exercise;

class Sentence {
    public static void printSentence(String sentence) {
        // BEGIN
        if (sentence.endsWith("!")){
            System.out.println(sentence.toUpperCase());
        } else {
            System.out.println(sentence.toLowerCase());
        }
        // END
    }
}
