import java.io.*;
import java.lang.*;

public class FilesHandling {

	public FilesHandling() {}

	 public void readLessonsFile(String filename)
	 {
	        File f=null ;  // THE OBJECT.
	        BufferedReader reader=null; // ALLOW US TO READ THE LINES VIA READER.READLINE().
	        String line;

	        try {
	            f = new File(filename); // COULD'T FIND THE NAME.
	        } catch (NullPointerException e) {
	            System.err.println("File not found.");
	        }

	        try { //ABOUT LINES.
	            reader = new BufferedReader(new FileReader(filename));
	            reader.readLine(); //skip header
	            reader.readLine(); //skip empty row
	            line=reader.readLine();
	            while(line!=null) {
					//1001 Religion A 2 2 2 - - - - - -
					String code_str = line.trim().substring(0, line.indexOf(" "));
					String subline = line.substring(code_str.length()+1).trim();

					String name = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(name.length()+1).trim();

					String Class = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(Class.length()+1).trim();

					// They are either "-" or numbers
					String hA1_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hA1_str.length()+1).trim();

					String hA2_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hA2_str.length()+1).trim();

					String hA3_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hA3_str.length()+1).trim();

					String hB1_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hB1_str.length()+1).trim();

					String hB2_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hB2_str.length()+1).trim();

					String hB3_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hB3_str.length()+1).trim();

					String hC1_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hC1_str.length()+1).trim();

					String hC2_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hC2_str.length()+1).trim();

					String hC3_str = subline;


					// Convert to Integer all variables but name, Class
					int code = Integer.parseInt(code_str);
					int hA1, hA2, hA3, hB1, hB2, hB3, hC1, hC2, hC3; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					int[] hoursA = new int[3];
					int[] hoursB = new int[3];
					int[] hoursC = new int[3];
					//int hoursA = 0, hoursB = 0, hoursC = 0;
					if (hA1_str.equals("-")) {
						hoursA[0] =0; hoursA[1] =0; hoursA[2] =0;
					}else {
						// kane tis metatropes twn hA1,hA2,hA3
						hA1 = Integer.parseInt(hA1_str);
						hA2 = Integer.parseInt(hA2_str);
						hA3 = Integer.parseInt(hA3_str);
						//hoursA = hA1 + hA2 + hA3;
						hoursA[0] = hA1; hoursA[1] = hA2; hoursA[2] = hA3;
					//	System.out.println("hours of A class: "+ hoursA[0] + " "+ name);
					}
					if (hB1_str.equals("-")) {
						hoursB[0] =0; hoursB[1] =0; hoursB[2] =0;
					}else {
						// kane tis metatropes twn hB1,hB2,hB3
						hB1 = Integer.parseInt(hB1_str);
						hB2 = Integer.parseInt(hB2_str);
						hB3 = Integer.parseInt(hB3_str);
						//hoursB = hB1 + hB2 + hB3;
						hoursB[0] = hB1; hoursB[1] = hB2; hoursB[2] = hB3;
					//	System.out.println("hours of B class: "+ hoursB[0] + " "+ name);
					}
					if (hC1_str.equals("-")) {
						hoursC[0] =0; hoursC[1] =0; hoursC[2] =0;
					}else {
						// kane tis metatropes twn hC1,hC2,hC3
						hC1 = Integer.parseInt(hC1_str);
						hC2 = Integer.parseInt(hC2_str);
						hC3 = Integer.parseInt(hC3_str);
						//hoursC = hC1 + hC2 + hC3;
						/*
						hA = new int[3];
						hA[0] = hA1; hA[1] = hA2; hA[2] = hA3;
						hB = new int[3];
						hB[0] = hB1; hB[1] = hB2; hB[2] = hB3;

						//hC = new int[3]; */
						hoursC[0] = hC1; hoursC[1] = hC2; hoursC[2] = hC3;
					//	System.out.println("hours of C class: "+ hoursC[0] + " "+ name);


					}
					//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					Lessons l1 = new Lessons(code, name, Class, hoursA, hoursB, hoursC, 1);
					Lessons l2 = new Lessons(code, name, Class, hoursA, hoursB, hoursC, 2);
					Lessons l3 = new Lessons(code, name, Class, hoursA, hoursB, hoursC, 3);
					/* ftiaxnoume alla 2 gia ta tmimata
					 * kai na prosthesoume ena gnwrisma tupou tmima
					 *
					 */
					Lessons.lessons.add(l1);
					Lessons.lessons.add(l2);
					Lessons.lessons.add(l3);
					//System.out.println(l1.toString());

	                line = reader.readLine();

	            }// end of while
	        }catch (IOException e) {
	            System.out.println("Error opening file!");
	        }try {
				reader.close(); //COULDN'T CLOSE IT.
			} catch (IOException e) {
				System.err.println("Error closing file.");
			}
	    }



	 public void readTeachersFile(String filename) {
			File f = null; // THE OBJECT.
			BufferedReader reader = null; // ALLOW US TO READ THE LINES VIA READER.READLINE().
			String line;

			try {
				f = new File(filename); // COULD'T FIND THE NAME.
			} catch (NullPointerException e) {
				System.err.println("File not found.");
			}

			try { // ABOUT LINES.

				reader = new BufferedReader(new FileReader(filename));
				reader.readLine(); // skip header
				reader.readLine(); // skip empty row
				line = reader.readLine();
				while (line != null) {
					String subline;
					String id_str = line.substring(0, line.indexOf(" ")); // take the fields from the rows
					subline = line.substring(id_str.length() + 1).trim(); // we use the subline to use the first gap from
																			// the point that we want
					String name = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(name.length() + 1).trim();
					String code_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(code_str.length() + 1).trim();
					String hour_d_str = subline.substring(0, subline.indexOf(" "));
					subline = subline.substring(hour_d_str.length() + 1).trim();
					String hour_w_str = subline;
					//subline = subline.substring(hour_w_str.length()+1).trim();






					int id = Integer.parseInt(id_str); // convert string to integer
					int code = Integer.parseInt(code_str);
					int hour_d = Integer.parseInt(hour_d_str);
					int hour_w = Integer.parseInt(hour_w_str);
					Teachers t = new Teachers(id, name, code, hour_d, hour_w);
					Teachers.teachers.add(t);
					Teachers.getTeachers_queue().offer(t);
					line = reader.readLine();

				}

			} catch (IOException e) {
				System.out.println("Error opening file!");
			}
			try {
				reader.close(); // COULDN'T CLOSE IT.
			} catch (IOException e) {
				System.err.println("Error closing file.");
			}

		}
	 /*
	  * PrintStream fileStream = new PrintStream("filename.txt");
		System.setOut(fileStream);
		Then any println statement will go into the file.
	  */

	 public void printSchedule(State s) {
		 PrintStream previous = System.out;
		PrintStream fileStream = null;
		try {
			fileStream = new PrintStream("schedule.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.setOut(fileStream);
		 for(int k=0; k<9; k++) { //for every section
			 if (k == 0) System.out.print("A1: ");
			 else if (k == 1) System.out.print("A2: ");
			 else if (k == 2) System.out.print("A3: ");
			 else if (k == 3) System.out.print("B1: ");
			 else if (k == 4) System.out.print("B2: ");
			 else if (k == 5) System.out.print("B3: ");
			 else if (k == 6) System.out.print("C1: ");
			 else if (k == 7) System.out.print("C2: ");
			 else System.out.print("C3: ");
			 System.out.format("%30s%30s%30s%30s%30s", "Monday", "Tuesday", "Wednesday", "Thursday", " Friday\n");
			 System.out.format("%30s", "____________________________________________________________________________________________________________________________________________________________ \n");
			 for (int i = 0; i < 7; i++) { // for every day
				 if (i == 0) System.out.print("1st: ");
				 else if (i == 1) System.out.print("2nd: ");
				 else if (i == 2) System.out.print("3rd: ");
				 else System.out.print((i + 1) + "th: ");

				 for (int j = 0; j < 5; j++) {
					 if (s.getSchedule()[i][j][k] == null) {
						 System.out.format("%30s", "-");
						 continue;
					 }
					 System.out.format("%30s", s.getSchedule()[i][j][k].getLesson().getName() + " (" + s.getSchedule()[i][j][k].getTeacher().getName().substring(0, 4) + ".) ");
				 }
				 System.out.println("\n ___________________________________________________________________________________________________________________________________________________________");
			 }

			 System.out.println("\n");

		 }
		 fileStream.close();
		 System.setOut(previous);
	 }
}
