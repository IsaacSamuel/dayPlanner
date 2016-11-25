import java.util.Calendar;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Planner {
	public static void main(String args[]) {

		Scanner kb = new Scanner(System.in);
		File file = new File("calendar.txt");
		boolean running = true;


		System.out.println("Welcome to the Samuel Dayplanner! Let's plan stuff!");
		System.out.println();


		//search in current directory for existing file, if not, set up or goodbye
		if (!file.exists()) {
			System.out.println("The Samuel Dayplanner cannot find calendar.txt in the current directory. It is required for planning! Would you like to create one? y/n?");

			char create = kb.next().charAt(0);

			if (create=='y') {
				try {
					file.createNewFile();
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
				case "today": Reader.printToday(file);
					break;
				case "help": help();
					break;
				case "exit": running = false;
					break;
				case "week": Reader.printWeek(file);
					break;
				case "print": Reader.printDay(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]), file);
					break;
				
				default: 
					break;
			}

			//Read a certain day's/days' schedule(s)
			//Add/Remove a certain day's event
			//Add a recurrent event
			//Remove a reccurent event
		}

	}

	public static void help() {
		System.out.println();
		System.out.println("To exit, type 'exit'.");
		System.out.println("To print today's schedule, enter 'today'.");
		System.out.println("To print this week's schedule, enter 'week'.");
		System.out.println("To print this a certain day's schedule, enter that day as 'print MM DD YYYY'.");
		System.out.println();
	}
}