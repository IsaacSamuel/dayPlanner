import java.util.Calendar;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Writer {
	public static void addEvent(int month, int day, int year, File file) {
		Scanner kb = new Scanner(System.in);

		try {
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			Boolean eventExistsAlready = false;


			System.out.println();
			System.out.println("What time will the event take place? Format: HH:MM (ex: 13:00)");
			String eventTime[] = kb.nextLine().split(":", 2);

			System.out.println();
			System.out.println("Give a short description of the event. (ex: buy groceries)");
			String eventDescription = kb.nextLine();

			String event = eventTime[0] + ":" + eventTime[1] + " " + eventDescription;

			//Logic to place the schedule correctly in calendar.txt
			while (line != null && !(eventExistsAlready)) {
				line = reader.readLine();
				if (line ==  null){
					//if date is not found
					writer.newLine();
					writer.write(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year));
					writer.newLine();
					writer.write(event);
					writer.newLine();
					writer.flush();
				}
				else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
					line = reader.readLine();
					if (line.equals(event)) {
						System.out.println("That event already exists!");
						eventExistsAlready = true;
					}
				}
			}
			reader.close();
			writer.close();

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