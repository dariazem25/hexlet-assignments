package exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class App {
    // BEGIN
    public static String buildList(String[] str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ul>");

        for (int i = 0; i < str.length; i++) {
            stringBuilder.append("\n\s\s<li>");
            stringBuilder.append(str[i]);
            stringBuilder.append("</li>");
        }
        stringBuilder.append("\n</ul>");

        if (str.length > 0) {
            return stringBuilder.toString();
        }

        return "";
    }

    public static String getUsersByYear(String[][] users, int year) {
        ArrayList<String> usersList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < users.length; i++) {
            LocalDate localDate = LocalDate.parse(users[i][1], formatter);
            if (year == localDate.getYear()) {
                usersList.add(users[i][0]);
            }
        }

        String [] arr = usersList.toArray(new String[0]);

        return buildList(arr);
    }

    public static String getYoungestUser(String[][] users, String date) {
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date, formatter1);
        String youngestUser = "";
        LocalDate yearOfYoungest = LocalDate.MIN;

        for (int i = 0; i < users.length; i++) {
            LocalDate localDate2 = LocalDate.parse(users[i][1], formatter2);
            if (localDate2.isBefore(localDate1) && localDate2.isAfter(yearOfYoungest)) {
                yearOfYoungest = localDate2;
                youngestUser = users[i][0];
            }
        }

        return youngestUser;
    }

    // END
}
