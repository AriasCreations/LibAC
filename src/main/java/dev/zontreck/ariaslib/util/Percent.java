package dev.zontreck.ariaslib.util;

import java.io.PrintStream;

public class Percent
{
	int current;
	int maximum;

	public Percent(int cur, int max)
	{
		current=cur;
		maximum=max;
	}


	public int get()
	{
		return ((current * 100) / maximum);
	}


	private static final int DEFAULT_BAR_WIDTH = 50;

	private static int getConsoleWidth() {
		return 80; // Default console width, can be adjusted for different consoles
	}
	public static void printProgressBar(int progress, int maxProgress, String beforeText, String afterText) {
		PrintStream out = System.out;
		int consoleWidth = getConsoleWidth();
		int barWidth = Math.min(consoleWidth - beforeText.length() - afterText.length() - 4, DEFAULT_BAR_WIDTH);

		// Calculate progress
		int progressBarLength = (int) ((double) progress / maxProgress * barWidth);

		// Print before text
		out.print(beforeText);

		// Print progress bar
		out.print("[");
		for (int i = 0; i < barWidth; i++) {
			if (i < progressBarLength) {
				out.print("=");
			} else {
				out.print(" ");
			}
		}
		out.print("]");

		// Print percentage
		out.print(" " + (progress * 100 / maxProgress) + "%");

		// Print after text
		out.print(afterText);

		// Move cursor to next line
		out.println();
	}
}
