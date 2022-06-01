package me.wusel.wuselutils.utilitity;

public class StringCenter {

    public static Integer MOTD = 45;
    public static Integer CHAT = 80;

    public static String centerText(String text, int lineLength) {
        String finalText = "";
        Integer spaces = lineLength - text.length();
        StringBuilder message = new StringBuilder();

        for (int i = 0; i <= (spaces / 2); i++) {
            message.append(" ");
        }
        message.append(text);
        for (int i = 0; i <= (spaces / 2); i++) {
            message.append(" ");
        }
        return message.toString();
    }
}
