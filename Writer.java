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

			String event = scheduleEvent();
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


	public static void addEvent(int month, int day, int year, File file, String event) {

		try {
			FileWriter fileWriter = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fileWriter);

			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			Boolean eventExistsAlready = false;

			//Logic to place the schedule correctly in calendar.txt
			while (line != null && !(eventExistsAlready)) {
				line = reader.readLine();
				if (line ==  null){
					//if date is not found
					writer.write(Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year));
					writer.newLine();
					writer.write(event);
					writer.newLine();
					writer.flush();
				}
				else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
					line = reader.readLine();
					if (line.equals(event)) {
						System.out.println("That event already exists on " + Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year) + "!");
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

	public static void addRecurrentEvent(int endMonth, int endDay, int endYear, String day, File file) {
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = reader.readLine();
			int dayValue = 0;
			Calendar calendar = Calendar.getInstance();
			Calendar setCalendar = Calendar.getInstance();

			switch (day) {
				case "Sun":
					dayValue = 1;
					break;
				case "Sunday":
					dayValue = 1;
					break;
				case "Mon":
					dayValue = 2;
					break;
				case "Monday":
					dayValue = 2;
					break;
				case "Tue":
					dayValue = 3;
					break;
				case "Tuesday":
					dayValue = 3;
					break;
				case "Wed":
					dayValue = 4;
					break;
				case "Wedsnday":
					dayValue = 4;
					break;
				case "Thu":
					dayValue = 5;
					break;
				case "Thur":
					dayValue = 5;
					break;
				case "Thursday":
					dayValue = 5;
					break;
				case "Fri":
					dayValue = 6;
					break;
				case "Friday":
					dayValue = 6;
					break;
				case "Sat":
					dayValue = 7;
					break;
				case "Saturday":
					dayValue = 7;
					break;
				default:
					System.out.println("Sorry, the day value was not recognized. ");
					System.exit(1);
			}

			setCalendar.set(endYear, endMonth-1, endDay);

			String event = scheduleEvent();

			while(calendar.get(Calendar.DAY_OF_WEEK) != dayValue) {
				calendar.add(Calendar.DATE, 1);
			}

			System.out.println(calendar.get(Calendar.YEAR));
			System.out.println(calendar.get(Calendar.MONTH));
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));

			System.out.println(setCalendar.get(Calendar.YEAR));
			System.out.println(setCalendar.get(Calendar.MONTH));
			System.out.println(setCalendar.get(Calendar.DAY_OF_MONTH));


			while( setCalendar.compareTo(calendar) >= 0) {
				addEvent(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR), file, event);
				calendar.add(Calendar.DATE, 7);

			}
		}

		catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void deleteEvent(int month, int day, int year, String event, File file) {
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader);

				RandomAccessFile accessor = new RandomAccessFile (file, "rw");

				boolean running = true;

				long position = 0;
				long linelength = 0;

				


				while (running == true) {

					String line = reader.readLine();

					if (line == null) {
						running = false;

					} 
					else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
						linelength = line.getBytes().length + 1;
						line = reader.readLine();
						linelength += line.getBytes().length + 1;

						if (line.equals(event) ) {
							long end = position + linelength;
							while (position < end-2) {
								accessor.seek(position);
								accessor.write(' ');
								position++;
							}
							accessor.seek(position);
							accessor.writeBytes(System.getProperty("line.separator"));
							running = false;
						}
					}
					else {
						linelength = line.getBytes().length + 1;
						line = reader.readLine();
						linelength += line.getBytes().length + 1;
					}
					position += (linelength);
				}

			reader.close();

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();

		}

	}

	public static void clear(int month, int day, int year, File file) {
		boolean fileEnd = false;	
		try {

			while (!fileEnd) {
				long position = 0;
				long linelength = 0;
				FileReader fileReader = new FileReader(file);
				BufferedReader reader = new BufferedReader(fileReader);
				boolean running = true;

				RandomAccessFile accessor = new RandomAccessFile (file, "rw");

				while (running == true) {

					String line = reader.readLine();

					if (line == null) {
						running = false;
						fileEnd = true;
					} 
					else if (line.equals((Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year)))) {
						linelength = line.getBytes().length + 1;
						line = reader.readLine();
						linelength += line.getBytes().length + 1;

						long end = position + linelength;
						while (position < end-2) {
							accessor.seek(position);
							accessor.write(' ');
							position++;
						}
						accessor.seek(position);
						accessor.writeBytes(System.getProperty("line.separator"));
						running = false;
					}
					else {
						linelength = line.getBytes().length + 1;
						line = reader.readLine();
						linelength += line.getBytes().length + 1;
					}
					position += (linelength);
				}
				reader.close();
				accessor.close();
				System.out.println("blue");
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();

		}
	}

	public static String scheduleEvent() {
		Scanner kb = new Scanner(System.in);

		System.out.println();
		System.out.println("What time will the event take place? Format: HH:MM (ex: 13:00)");
		String eventTime[] = kb.nextLine().split(":", 2);

		System.out.println();
		System.out.println("Give a short description of the event. (ex: buy groceries)");
		String eventDescription = kb.nextLine();

		String event = eventTime[0] + ":" + eventTime[1] + " " + eventDescription;

		return event;
	}
}