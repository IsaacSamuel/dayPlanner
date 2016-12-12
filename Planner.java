import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Planner {
	public static void main(String args[]) {

		Scanner kb = new Scanner(System.in);
		File file = new File("calendar.txt");
		boolean running = true;
		String event = new String();

		int month;


		System.out.println("Welcome to the Samuel Dayplanner! Let's plan stuff!");
		System.out.println();


		//search in current directory for existing file, if not, set up or goodbye
		if (!file.exists()) {
			System.out.println("The Samuel Dayplanner cannot find calendar.txt in the current directory. It is required for planning! Would you like to create one? y/n?");

			char create = kb.next().charAt(0);

			if (create=='y') {
				try {

					file.createNewFile();

					RandomAccessFile accessor = new RandomAccessFile (file, "rw");
					accessor.seek(0);
					accessor.writeBytes(System.getProperty("line.separator"));

				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Alright, try again later.");
				System.exit(1);
			}

		}

		//Print Current day's Schedule
		Reader.printToday(file);

		while (running) {
			System.out.println("Please enter a command. Type help for options.");
			//Wait for user input
			String command[] = kb.nextLine().split(" ", -2);

			switch (command[0]) {
				case "today": 
					Reader.printToday(file);
					break;
				case "help": 
					help();
					break;
				case "exit": 
					running = false;
					break;
				case "week": 
					Reader.printWeek(file);
					break;
				case "print": 
					month = monthFormatter(command[1]);
					Reader.printDay(month, Integer.parseInt(command[2]), Integer.parseInt(command[3]), file);
					break;
				case "add": 
					month = monthFormatter(command[1]);
					Writer.addEvent(month, Integer.parseInt(command[2]), Integer.parseInt(command[3]), file);
					break;
				case "remove":
					month = monthFormatter(command[1]);
					System.out.println("Please enter the event you'd like to delete :");
					event = kb.nextLine();
					Writer.deleteEvent(month, Integer.parseInt(command[2]), Integer.parseInt(command[3]), event, file);
					break;
				case "clear":
					month = monthFormatter(command[1]);
					Writer.clear(month, Integer.parseInt(command[2]), Integer.parseInt(command[3]), file);
				case "recurrent":
					if (command[1].equals("add")) {
							month = monthFormatter(command[2]);
							Writer.addRecurrentEvent(month, Integer.parseInt(command[3]), Integer.parseInt(command[4]), command[5], file);
						break;
					}
					else if (command[1].equals("delete")) {
							System.out.println("Please enter the event you'd like to delete :");
							event = kb.nextLine();
							Writer.deleteEvent(event, file);
						break;
					}
					else {
						break;
					}	
				default: 
					break;
			}
		}

	}

	public static void help() {
		System.out.println();
		System.out.println("To exit, type 'exit'.");
		System.out.println("To print today's schedule, enter 'today'.");
		System.out.println("To print this week's schedule, enter 'week'.");
		System.out.println("To print this a certain day's schedule, enter that day as 'print MM DD YYYY'.");
		System.out.println("To add an event to the schedule, type 'add' followed by the date you want it to take place on (ex. 'add MM DD YYYY').");
		System.out.println("To add a recurrent event, type 'recurrent add' and the date you'd like the reccurrence to stop and the the day you want it to recur ex:'reccurrent add MM DD YYYY Monday'");
		System.out.println("To delete a singular event, type 'remove' followed by the date of the item you'd like to remove (ex 'remove MM DD YYYY'). You will be prompted next to give the event.");
		System.out.println("To delete a recurrent event, print 'recurrent delete'");
		System.out.println("To clear a day's schedule, type 'clear' and the date you want to clear (ex. clear 12 22 2016)");
		System.out.println();
	}

	public static int monthFormatter(String input) {
		if (input.charAt(0)=='0') {
			return Character.getNumericValue(input.charAt(1));
		}
		else {
			return Integer.parseInt(input);
		}
	}
}