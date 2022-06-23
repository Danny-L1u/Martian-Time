package Grade_11;
import java.io.*; 
import java.util.Scanner;

/** This program takes in Earth time and outputs the time on Mars. So the input is a number of days, number of hours,
 * and number of minutes and it will output the time that goes by in Mars. How this program does this is it finds the 
 * ratio of one Mars day to one Earth day in seconds. Then it finds the total number of seconds of the Earth time inputed
 * then finds the number of seconds that pass on Mars by using the ratio. Then the program finds the number of days, hours,
 * and minutes by diving and finding the remainder of days, hours, minutes, in seconds. 
 * I wrote most of the comments really late in the night so some of them might not make sense grammatically.
 * 
 * @author dliu
 */
public class MartianTime_Danny {

	public static void main(String args[]) throws IOException{
		Scanner inFile = new Scanner(new File("input.txt"));

		//File Input
		String line = "";
		//set up the print stream between the program and the file
		FileWriter out = new FileWriter ("report.txt");
		BufferedWriter bw  = new BufferedWriter(out);
		PrintWriter reportFile = new PrintWriter(bw);
		reportFile.println("The number of days is how many days passed on Mars\nThe most right numbers are what would show up on a 24 hour clock if it was on Mars time");

		//Loops through each line in the input file
		while(inFile.hasNextLine()){
			line = inFile.nextLine();   
			
			//Get the numbers inputed and put them into variables
			String times[] = line.split(" ");
			int days = Integer.parseInt(times[0]) - 1;
			int hours = Integer.parseInt(times[1]);
			int minutes = Integer.parseInt(times[2]);

			final int SEC = 60;
			final int MIN = 60;
			final int HOUR = 24;
			//Find the number of seconds in a Mars day
			final double Mars_Day_Seconds = HOUR * MIN * SEC + 37 * SEC + 22.663;
			//Find the ratio between an Earth day and a Mars day
			final double Ratio = (HOUR * MIN * SEC) / Mars_Day_Seconds;

			//Find the total number of seconds inputed
			final int Earth_days = days * HOUR * MIN * SEC;
			final int Earth_hours = hours * MIN * SEC;
			final int Earth_minutes = minutes * SEC;
			final int Total_seconds = Earth_days + Earth_hours + Earth_minutes;
			
			//find the number of seconds passed on Mars
			final double Mars_seconds = Total_seconds * Ratio;
			
			//Find the number of days, hours, and minutes passed on Mars
			int Mars_days = (int) (Mars_seconds / (SEC * MIN * HOUR));
			int Mars_hours = (int) (Mars_seconds - Mars_days * SEC * MIN * HOUR) / (MIN * SEC);
			int Mars_minutes = (int)((Mars_seconds - Mars_days * SEC * MIN * HOUR) - (Mars_hours * MIN * SEC)) / SEC;
			
			//Turn final hours and final minutes to string so I can add a "0" in front if necessary
			String final_hours = Integer.toString(Mars_hours);
			String final_minutes = Integer.toString(Mars_minutes);
			if (Mars_hours < 10) {
				final_hours = "0" + final_hours;
			}
			if (Mars_minutes < 10) {
				final_minutes = "0" + final_minutes;
			}
			
			//Output to the output file
			String word = ("Day " + (Mars_days + 1) + " " + final_hours + ":" + final_minutes); 
			reportFile.println("\n" + word);  

		}
		reportFile.close();
	}

}
