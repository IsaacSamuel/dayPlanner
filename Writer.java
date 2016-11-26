import java.util.Calendar;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Writer {
	public static void addEvent(int month, int day, int year, File file) {
		Scanner kb = new Scanner(System.in);
		Scanner scanner = new Scanner(file).useDelimiter("\n");

		try {
			String line = scanner.nextLine();


			System.out.println();
			System.out.println("What time will the event take place? Format: HH:MM (ex: 13:00)");
			String eventTime[] = kb.nextLine().split(":", 2);

			System.out.println();
			System.out.println("Give a short description of the event. (ex: buy groceries)");
			String eventDescription = kb.nextLine();

			//Logic to place the schedule correctly in calendar.txt
			while (line != null) {
				line = scanner.readLine();
				if (line ==  null){
					//if date is not found

				}
				else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
					int tempHour = Integer.parseInt(eventTime[0]);
					int tempMinute = Integer.parseInt(eventTime[1]);

					line = scanner.readLine();
					int hour = Character.getNumericValue(line.charAt(0));
					int minute = Character.getNumericValue(line.charAt(2))*10 + Character.getNumericValue(line.CharAt(3));

					if (line.equals("")) {

					}
				}
			}
			scanner.close();

			System.out.println();
			Reader.printDay(month, day, year, file);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();

		}



	}
}