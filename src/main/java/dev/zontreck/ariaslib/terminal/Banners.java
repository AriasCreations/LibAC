package dev.zontreck.ariaslib.terminal;

import java.util.ArrayList;
import java.util.List;

public class Banners
{

    public static String generateBanner(String text) {
        int maxLength = calculateMaxLength(text);
        List<String> bannerLines = new ArrayList<>();
        StringBuilder border = new StringBuilder();
        for (int i = 0; i < maxLength + 4; i++) {
            border.append("*");
        }
        bannerLines.add(border.toString());
        bannerLines.add("* " + centerText(text, maxLength) + " *");
        bannerLines.add(border.toString());
        return String.join("\n", bannerLines);
    }

    private static String centerText(String text, int maxLength) {
        StringBuilder centeredText = new StringBuilder();
        int spacesToAdd = (maxLength - text.length()) / 2;
        for (int i = 0; i < spacesToAdd; i++) {
            centeredText.append(" ");
        }
        centeredText.append(text);
        for (int i = 0; i < spacesToAdd; i++) {
            centeredText.append(" ");
        }
        if (centeredText.length() < maxLength) {
            centeredText.append(" ");
        }
        return centeredText.toString();
    }

    private static int calculateMaxLength(String text) {
        int maxLength = 0;
        for (String line : text.split("\n")) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }
        return maxLength;
    }
}
