import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class main {
	public static void main(String args[]) {


		Lessons l = new Lessons();
		Teachers t = new Teachers();
		FilesHandling f = new FilesHandling();

		f.readLessonsFile(args[0]);
		f.readTeachersFile(args[1]);

		State initialState = new State();

		long start = System.currentTimeMillis();
		while(initialState.checkHours()==false)
		{
			System.out.println("Less hours,remaking initialState!\n");
			//initialState=null;
			Lessons.lessons.clear();
			Teachers.teachers.clear();
			l = new Lessons();
			t = new Teachers();
			f = new FilesHandling();
			f.readLessonsFile(args[0]);
			f.readTeachersFile(args[1]);
			initialState=new State();

		}
		//System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");



		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;
		terminalState=spaceSearcher.simulatedAnnealing(initialState);
		//System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		if(terminalState == null)
		{
			System.out.println("Could not find solution.");
		}
		else{
			//System.out.println(terminalState.getScore());
			System.out.println();
			System.out.println("Found one solution.");
			f.printSchedule(terminalState);
		}

		long end = System.currentTimeMillis();

		System.out.println((double)(end - start) / 1000 + " sec.");


	}
}
