import java.util.Calendar;
import java.util.Scanner;
import java.io.*;

public class Reader {
	Scanner kb = new Scanner(System.in);


		public static void printDay(int month, int day, int year, File file) {
		boolean end = false;

		System.out.println("-----" + month + "/" + day + "/" + year + "-----");

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();


			while (line != null) {
				if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
					while (!(line.equals("")) && line != null) {
						System.out.println(line);
						line = reader.readLine();
					}
				}
				line = reader.readLine();
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();

		}


		System.out.println("--------------------");
		System.out.println();
	}

	public static void printToday(File file) {
		Calendar calendar = Calendar.getInstance();
		int month = (calendar.get(Calendar.MONTH) + 1);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		printDay(month, day, year, file);
	}

	public static void printWeek(File file) {
		int WEEK = 7;
		int day = 0;
		Calendar calendar = Calendar.getInstance();

		while (day < WEEK) {
			printDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR), file);
			calendar.add(Calendar.DATE, 1);
			day++;
		}
	}
}