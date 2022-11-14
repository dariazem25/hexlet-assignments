package exercise;

class Card {
    public static void printHiddenCard(String cardNumber, int starsCount) {
        // BEGIN
        String substring = cardNumber.substring(0,12);
        System.out.println(cardNumber.replace(substring, "*".repeat(starsCount)));
        // END
    }
}
