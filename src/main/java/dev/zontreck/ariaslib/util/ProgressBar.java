package dev.zontreck.ariaslib.util;

import java.io.PrintStream;

/**
 * Utility to create an ascii progress bar
 */
public class ProgressBar
{

    private static final int DEFAULT_BAR_WIDTH = 50;

    /**
     * Reserved spaces for the brackets, and the carrot, and the percent value.
     */
    private static final int PROGRESS_BAR_RESERVED=5;

    /**
     * Always will return 80
     * @return 80
     */
    private static int getConsoleWidth() {
        return 80; // Default console width, can be adjusted for different consoles
    }

    /**
     * Build a progress bar
     * <br/><br/>
     * your text here [=========>             ] 40% your text here
     * @param percent The percentage
     * @param beforeText
     * @param afterText
     * @return ProgressBar as a String
     */
    public static String printProgressBar(int percent, String beforeText, String afterText) {
        StringBuilder sb = new StringBuilder();
        int consoleWidth = getConsoleWidth();
        int barWidth = Math.min(consoleWidth - beforeText.length() - afterText.length() - PROGRESS_BAR_RESERVED, DEFAULT_BAR_WIDTH);

        // Calculate progress
        int progressBarLength = (int) ((double) percent / 100 * barWidth);

        // Print before text
        sb.append(beforeText);

        // Print progress bar
        sb.append("[");
        for (int i = 0; i < barWidth; i++) {
            if (i < progressBarLength) {
                sb.append("=");
            }else if(i==progressBarLength) sb.append(">");
            else {
                sb.append(" ");
            }
        }
        sb.append("]");

        // Print percentage
        sb.append(" " + percent + "%");

        // Print after text
        sb.append(afterText);

        return sb.toString();
    }
}
