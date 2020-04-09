//import java.lang.*;
import java.io.File;
import java.util.*;

public class State implements Comparable<State> {
	private Combination[][][] schedule; //weekly schedule that we have all the classes.
	private double score;
	private State father;
	private int[][] hours_per_section; //last column for boolean(0/1)
	private int[] teachers_week_hours; // probably arraylist
	private int strictConstraints;
	private int resilientConstraints;

	//constructor

	public State () {
		this.score=0;
		this.strictConstraints=0;
		this.resilientConstraints=0;
		this.schedule = new Combination[7][5][9]; // hour, day, section
		this.hours_per_section = new int[9][6]; //last column for boolean(0/1)
		this.teachers_week_hours = new int[Teachers.teachers_real_size()];
		Collections.shuffle(Teachers.getTeachers());
		Random r = new Random();
		Queue<Teachers> queue = new LinkedList<Teachers>(Teachers.getTeachers());
		for(int k=0; k<9; k++){ //section
			for(int i=0; i<7; i++){ //hourS
				for(int j=0; j<5; j++){ //day
					teachersloop:
					for(Teachers te: queue) {
						Teachers t = Teachers.getTeachers().get(r.nextInt(Teachers.getTeachers().size())); //takes randomly a teacher
						if(t.getMax_hours_per_week()>0) { //obvious constraint
							if(t.getdHours()[j]>0){ //obvious constraint
								for (int k2=0;k2<9; k2++){
									if(schedule[i][j][k2]==null) continue;
									if (schedule[i][j][k2].getTeacher().getId()==t.getId() && k!=k2)
										continue teachersloop; //obvious constraint
								}
								if(k==0) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),1, "A");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursA()[0]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursA(--l.getHoursA()[0],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be

								}
								else if(k==1) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),2, "A");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursA()[1]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursA(--l.getHoursA()[1],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==2) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),3, "A");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursA()[2]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursA(--l.getHoursA()[2],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==3) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),1, "B");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursB()[0]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursB(--l.getHoursB()[0],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==4) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),2, "B");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursB()[1]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursB(--l.getHoursB()[1],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==5) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),3, "B");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursB()[2]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursB(--l.getHoursB()[2],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==6) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),1, "C");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursC()[0]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursC(--l.getHoursC()[0],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==7) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),2, "C");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursC()[1]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursC(--l.getHoursC()[1],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
								else if(k==8) {
									Lessons l = Lessons.getLessonThroughCode(t.getCode(),3, "C");
									if (l==null) //this teacher doesn't teach in this class
										continue;
									//else he can teach in this class
									if(l.getHoursC()[2]>0) { //if this lesson is available
										schedule[i][j][k]=new Combination(t,l);
										t.setMax_hours_per_week(t.getMax_hours_per_week()-1);
										l.setHoursC(--l.getHoursC()[2],0);
										t.getdHours()[j]--;
										queue.add(queue.poll());
										break;
									} //this lesson has been taught as many hours as it should be
								}
							}

						}
					}
				}
			}
		}


	}

	public boolean checkHours()
	{
		int hours=0;
		for(int k=0; k<9; k++)
		{
			for(int j=0; j<5; j++)
			{
				for(int i=0; i<7; i++)
				{
					if(this.schedule[i][j][k]!=null) hours++;
					

				}

			}
			if(hours!=32) return false;
			hours=0;
		}

		return true;
	}


  // checking for obvious constraint
	public boolean quickCheck(){
		int c=0;
		for(int j=0; j<5; j++){
			for(int i=0; i<7; i++){
				for(int k=0; k<9; k++){
					if(schedule[i][j][k]!=null){
						for(int k2=0; k2<9; k2++){
							if(schedule[i][j][k2]!=null){
								if(schedule[i][j][k].getTeacher().getId()==schedule[i][j][k2].getTeacher().getId() && k!=k2){
									//System.out.println(schedule[i][j][k].getTeacher().getName() + " day: " + j + " hour: " + i + " section 1: " + k + " section 2: "+ k2);
									c++;
								}
							}
						}
					}

				}
			}
		}
		if(c!=0) return false;
		return true;
	}

	//copy constructor
	public State(State s) {
		this.schedule = new Combination[7][5][9];
		this.resilientConstraints=0;
		this.strictConstraints=0;
		this.hours_per_section = new int[9][6]; //last column for boolean(0/1)
		this.teachers_week_hours = new int[Teachers.teachers_real_size()];
		this.score = 0;
		//this.father=s.getFather();

		if(s==null) return;
		if(s.getSchedule()==null) return;


		for(int i=0; i<7; i++){
			for(int j=0; j<5; j++){
				for(int k=0; k<9; k++) {
					this.schedule[i][j][k] = s.getSchedule()[i][j][k];
				}
			}
		}

	}



	public int[] getEmptyTileRow() {
		return emptyTileRow;
	}

	public int[] getEmptyTileColumn() {
		return emptyTileColumn;
	}

	private int count_lesson_per_day(Combination com , int day, int section) {
		//int 0=Monday....
		int counter = 0;
		for (int i=0; i<7; i++) {
			if (schedule[i][day][section]==null) continue;
			if (com.getLesson().getCode() ==schedule[i][day][section].getLesson().getCode()) {
				counter++;
			}
		}
		return counter;
	}
	private int count_teacher_per_day(Combination com, int day) {
		int counter = 0;
		for (int i=0; i<7; i++) {
			for (int k=0; k<9; k++) {
				if (schedule[i][day][k]==null) continue;
				if (com.getTeacher().getId()==schedule[i][day][k].getTeacher().getId()) {
					counter++;
				}
			}
		}
		return counter;
	}

	@Override
	public boolean equals(Object obj)
	{

		if (obj==null) return false;
		Combination c1;
		Combination c2;
		for(int k=0; k<9; k++) {
			if(this.emptyTileRow[k]!= ((State)(obj)).getEmptyTileRow()[k]) return false;
			if(this.emptyTileColumn[k] != ((State)(obj)).getEmptyTileColumn()[k]) return false;
			for(int i=0; i<7; i++) {
				for(int j=0; j<5; j++) {
					c1 = this.schedule[i][j][k];
					c2 = ((State)(obj)).getSchedule()[i][j][k];

					if (c1==null && c2!=null) return false;
					if (c2==null && c1!=null) return false;
					if (c1==null && c2==null) continue;
					if (c1.getTeacher().getId()!=c2.getTeacher().getId()) return false;
					if (c1.getLesson().getCode()!=c2.getLesson().getCode()) return false;
					if (!c1.getLesson().getgymnasiumClass().equalsIgnoreCase(c2.getLesson().getgymnasiumClass())) return false;
				}
			}
		}
		return true;
	}


	public void heuristic() { // for every constraint that is valid, increase score
		int first=0; //strict
		int second=0; //resilient
		int third=0; //strict
		int fourth=0; //strict
		int fifth=0; //resilient
		int extra1=0; //very strict
		int extra2=0; //resilient
		int extra3=0; // resilient
		ArrayList<String> searched_lessons = new ArrayList<String>();
		int a1,a2,a3,b1,b2,b3,c1,c2,c3;
		a1=a2=a3=b1=b2=b3=c1=c2=c3=0;

		for(int j=0; j<5; j++){
			for(int i=0; i<7; i++){
				for(int k=0; k<9; k++) {
					if(this.schedule[i][j][k]!=null) { // not to have empty hour
						// the teacher should not work more than his/her max hours per day
						if (count_teacher_per_day(schedule[i][j][k], j)<=schedule[i][j][k].getTeacher().getMax_hours_per_day()) {
						    //	this.score+=10;
						}
						else {
							extra2++;
						}

						if (teachers_week_hours[schedule[i][j][k].getTeacher().getId()-1]<=schedule[i][j][k].getTeacher().get_limit_weekly_hours()) {
						//	this.score+=10;
						}
						else{
							extra3++;
						}




						//We don't want the same teacher to work in different sections at the same day, same hour


						for(int k2=0; k2<9; k2++){
							if(this.schedule[i][j][k2]!=null) {
								if (k != k2) {
									if (this.schedule[i][j][k].getTeacher().getId()==this.schedule[i][j][k2].getTeacher().getId()) {
										//System.out.println("Day: " + j + " Hour: " + i + " Sections: " + k + "," + k2 + ", Professor: " + this.schedule[i][j][k].getTeacher().getName());
										//this.score -= 1000;
										extra1++;
									}
								}
							}
						}


						//ABOUT THE 2ND CONSTRAINT
							if(i==0) {
								if(this.schedule[i+1][j][k]!=null && this.schedule[i+2][j][k]!=null) {
									if(!(this.schedule[i][j][k].getTeacher().getId()==this.schedule[i+1][j][k].getTeacher().getId() && this.schedule[i+1][j][k].getTeacher().getId() == this.schedule[i+2][j][k].getTeacher().getId()))
									{
										//this.score+=20;
									}
									else second++;
								}
							}
							else if(i==1) {
								if(this.schedule[i-1][j][k]!=null && this.schedule[i+1][j][k]!=null && this.schedule[i+2][j][k]!=null) {
									if(!(this.schedule[i][j][k].getTeacher().getId()==this.schedule[i-1][j][k].getTeacher().getId()  && this.schedule[i][j][k].getTeacher().getId()==this.schedule[i+1][j][k].getTeacher().getId() && this.schedule[i+1][j][k].getTeacher().getId() == this.schedule[i+2][j][k].getTeacher().getId()))
									{

										//this.score+=20;
									}
									else second++;
								}
							}
							else if(i==2 || i==3 || i==4) {
								if(this.schedule[i-2][j][k]!=null  && this.schedule[i-1][j][k]!=null && this.schedule[i+1][j][k]!=null && this.schedule[i+2][j][k]!=null) {
									if(!(this.schedule[i-1][j][k].getTeacher().getId()==this.schedule[i-2][j][k].getTeacher().getId() && this.schedule[i][j][k].getTeacher().getId()==this.schedule[i-1][j][k].getTeacher().getId()  && this.schedule[i][j][k].getTeacher().getId()==this.schedule[i+1][j][k].getTeacher().getId() && this.schedule[i+1][j][k].getTeacher().getId() == this.schedule[i+2][j][k].getTeacher().getId()))
									{

										//this.score+=20;
									}
									else second++;
								}
							}
							else if(i==5) {
								if(this.schedule[i-1][j][k]!=null && this.schedule[i+1][j][k]!=null && this.schedule[i-2][j][k]!=null) {
									if(!(this.schedule[i-2][j][k].getTeacher().getId()==this.schedule[i-1][j][k].getTeacher().getId()  && this.schedule[i][j][k].getTeacher().getId()==this.schedule[i+1][j][k].getTeacher().getId() && this.schedule[i][j][k].getTeacher().getId() == this.schedule[i-1][j][k].getTeacher().getId()))
									{

										//this.score+=20;
									}
									else second++;
								}
							}
							else {
								if(this.schedule[i-1][j][k]!=null && this.schedule[i-2][j][k]!=null) {
									if(!(this.schedule[i][j][k].getTeacher().getId()==this.schedule[i-1][j][k].getTeacher().getId() && this.schedule[i-1][j][k].getTeacher().getId() == this.schedule[i-2][j][k].getTeacher().getId()))
									{

										//this.score+=20;
									}
									else second++;
								}
							}


						//we are counting how many hours does each section has
						if(k==0) a1++;
						if(k==1) a2++;
						if(k==2) a3++;
						if(k==3) b1++;
						if(k==4) b2++;
						if(k==5) b3++;
						if(k==6) c1++;
						if(k==7) c2++;
						if(k==8) c3++;
					}
					//ABOUT THE 1ST CONSTRAINT
					else {
						int count = 0;
						for(int i1=i-1; i1>0; i1--) {
							if(schedule[i1][j][k]!=null) {
								count++;
							}
						}
						if (count!=0) {
							count=0;
							for(int i2=i+1; i2<7; i2++) {
								if(schedule[i2][j][k]!=null) count++;
							}
						}
						if(count==0) {
							//this.score+= 30;
						}
						else first++;
						continue; //since the object is null we should not do the following commands
					}

					//ABOUT 5TH CONSTRAINT
					teachers_week_hours[schedule[i][j][k].getTeacher().getId()-1]++; // increment teacher's working hours


				}

			}
			//we initialize the array of total hours per day for every section
			hours_per_section[0][j]=a1;
			hours_per_section[1][j]=a2;
			hours_per_section[2][j]=a3;
			hours_per_section[3][j]=b1;
			hours_per_section[4][j]=b2;
			hours_per_section[5][j]=b3;
			hours_per_section[6][j]=c1;
			hours_per_section[7][j]=c2;
			hours_per_section[8][j]=c3;
			a1=a2=a3=b1=b2=b3=c1=c2=c3=0;
		}
		//ABOUT 5TH CONSTRAINT
		//we took the largest teaching hours and compared it with the minimum
		if (Arrays.stream(teachers_week_hours).max().getAsInt()- Arrays.stream(teachers_week_hours).min().getAsInt() <=14) {
			//this.score+=100;
		}
		else {
			fifth++;
		}

		//< c1, a3>

		for(int i=0; i<9; i++) { //if the difference is less than 2, is wanted
			if(maxelement(9, hours_per_section)[i]-minelement(9, hours_per_section)[i]<=2) {
				//System.out.println("max element is: "+maxelement(9, hours_per_section)[i]+" and min element is "+minelement(9, hours_per_section)[i]);
				hours_per_section[i][5] =1;

			}
		}

		//check if the difference is less than 2 in every section
		int sum=0;
		for (int row=0; row<9; row++) {
			if(hours_per_section[row][5]==1) sum++;
		}

		if(sum==9) {
			//this.score+=300;
		}
		else {
			third++;
		}


		//ABOUT THE 4TH CONSTRAINT
		int totalHours=0;
		for(int k=0; k<9; k++) { // checks the section
			for(int j=0; j<5; j++) {//checks the day
				for(int i=0; i<7; i++) { //checks the hours
					if (schedule[i][j][k]==null) continue;
					if(!searched_lessons.contains(schedule[i][j][k].getLesson().getName())) { // if we haven't check the lesson....
						searched_lessons.add(schedule[i][j][k].getLesson().getName()); //put the lesson that we have checked
						int count =count_lesson_per_day(schedule[i][j][k] ,j,k); // hours per day for a specific lesson
						if (k<3) totalHours=schedule[i][j][k].getLesson().getRemaining_hours(); // a1, a2, a3
						else if (k>=3 && k<6) totalHours=schedule[i][j][k].getLesson().getRemaining_hours(); //b1, b2, b3
						else totalHours=schedule[i][j][k].getLesson().getRemaining_hours(); //c1, c2, c3

						if(totalHours==1 || totalHours==2) {
							//this.score+=70;
						}
						else if(totalHours==3 || totalHours==4) {
							if(count<totalHours) {
								//this.score+=70;
							}
							else fourth++;
						} else if(totalHours>4) {
							if(count<(int)totalHours/2) {
								//this.score+=70;
							}
							else fourth++;
						}
					}
				}
				searched_lessons.clear(); //to enter next day
			}
		}





		if(first==0) {
		//	System.out.println("First worked! (S)");
			this.strictConstraints++;
			this.score += 2000;
		}
		if(second==0) {
			//System.out.println("Second worked! (R)");
			this.resilientConstraints++;
			this.score+= 30;
		}
		if(third==0) {
		//	System.out.println("Third worked! (S)");
			this.strictConstraints++;
			this.score+= 300;
		}
		if(fourth==0) {
			//System.out.println("Fourth worked! (S)");
			this.strictConstraints++;
			this.score += 80;
		}
		if(fifth==0) {
			//System.out.println("Fifth worked! (R)");
			this.resilientConstraints++;
			this.score += 50;
		}
		if(extra1==0) {
			//System.out.println("Extra1 worked!!!!!!!!!!!!!!!!!!!!! (S)");
			this.strictConstraints++;
			this.score +=50000;
		}
		if(extra2==0) {
		//	System.out.println("Extra2 worked! (R)");
			this.resilientConstraints++;
			this.score+= 25;
		}
		if(extra3==0) {
			//System.out.println("Extra3 worked! (R)");
			this.resilientConstraints++;
			this.score+=15;
		}


		//System.out.println("------------------------------------------------");


	}





	public void setScore(int score) {
		this.score = score;
	}



	public int[][] getHours_per_section() {
		return hours_per_section;
	}


	



	private static int[] maxelement(int no_of_rows, int[][] arr) {
        int i = 0;
        // Initialize max to 0 at beginning
        // of finding max element of each row
        int max = 0;
        int[] result = new int[no_of_rows];
        while (i < no_of_rows) {
            for (int j = 0; j < arr[i].length-1; j++) {
                if (arr[i][j] > max) {
                    max = arr[i][j];
                }
            }
            result[i] = max;
            max =0;
            i++;
        }
        return result;
    }

	public static int[] minelement(int no_of_rows, int[][] arr) {
        int i = 0;
        // Initialize min to 10000 at beginning
        // of finding min element of each row
        int min = (int) Double.POSITIVE_INFINITY;  //just a huge number
        int[] result = new int[no_of_rows];
        while (i < no_of_rows) {
            for (int j = 0; j < arr[i].length-1; j++) {
                if (arr[i][j] < min) {
                    min = arr[i][j];
                }
            }
            result[i] = min;
            min =(int) Double.POSITIVE_INFINITY;
            i++;
        }
        return result;
    }

	

	

	public void swapTile(){
		Random r = new Random();
		int day1= r.nextInt(5);
		int day2 = r.nextInt(5);
		int hour1= r.nextInt(7);
		int hour2= r.nextInt(7);
		int section = r.nextInt(9);
		/*while(this.schedule[hour1][day1][section]==null || this.schedule[hour1][day1][section]==null){
			day1= r.nextInt(5);
			day2 = r.nextInt(5);
			hour1= r.nextInt(7);
			hour2= r.nextInt(7);
			section = r.nextInt(9);
		} */
		Combination tmp = this.schedule[hour1][day1][section];
		this.schedule[hour1][day1][section]=	this.schedule[hour2][day2][section];
		this.schedule[hour2][day2][section]= tmp;

	}


	public int[] getTeachers_week_hours() {
		return teachers_week_hours;
	}

	public ArrayList<State> getChildren(){
		ArrayList<State> children = new ArrayList<State>();
		State child = new State(this);
		for(int c=0; c<20; c++){
			child.swapTile();
			child.heuristic();
			//child.isTerminal();
			//child.setFather(this);
			//System.out.println(child.quickCheck());
			//System.out.println(child.getScore());
			children.add(child);
			child = new State(this);
		}
		return children;
	}

  

	public double getScore()
	{
		return this.score;
	}

	public Combination[][][] getSchedule() {
		return this.schedule;
	}

	public void setFather(State father) {
		this.father = father;
	}

	public State getFather() {
		return this.father;
	}


	

	@Override
	public int compareTo(State s) {
		return Double.compare(this.score,s.score);
	}

	




}
