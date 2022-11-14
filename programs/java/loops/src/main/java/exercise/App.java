package exercise;

class App {
    // BEGIN
    public static String getAbbreviation(String str) {
        String abbreviation = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 && Character.isLetter(str.charAt(i))) {
                abbreviation = abbreviation + Character.toUpperCase(str.charAt(i));
            } else if (Character.isLetter(str.charAt(i)) && Character.isWhitespace(str.charAt(i - 1))) {
                abbreviation = abbreviation + Character.toUpperCase(str.charAt(i));
            }
        }
        return abbreviation;
    }
    // END
}
