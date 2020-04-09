import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Teachers {
	private int id;
	private String name;
	private int code;
	private int max_hours_per_day;
	private int max_hours_per_week;
	private final int limit_weekly_working_hours;
	public static ArrayList<Teachers> teachers = new ArrayList<Teachers>();
	private static Queue<Teachers> teachers_queue = new LinkedList<Teachers>();


	public Teachers(){
		this.limit_weekly_working_hours = 0;
	}

	public Teachers(int id, String name, int code, int max_hours_per_day, int max_hours_per_week) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.max_hours_per_day = max_hours_per_day;
		this.max_hours_per_week = max_hours_per_week;
		this.limit_weekly_working_hours = max_hours_per_week;
	}
	
	public int get_limit_weekly_hours() {
		return limit_weekly_working_hours;
	}
	
	public static int teachers_real_size() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for( Teachers t: teachers) {
			if(!list.contains(t.getId())) {
				list.add(t.getId());
			}
		}
		return list.size();
	}




	public static ArrayList<Teachers> getTeachers() {
		return teachers;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	

	public void setName(String name) {

		this.name = name;
	}


    public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getMax_hours_per_day() {
		return this.max_hours_per_day;
	}

	public void setMax_hours_per_day(int max_hours_per_day) {
		this.max_hours_per_day = max_hours_per_day;
	}

	public int getMax_hours_per_week() {
		return this.max_hours_per_week;
	}

	public void setMax_hours_per_week(int max_hours_per_week) {
		this.max_hours_per_week = max_hours_per_week;
	}

	public static Queue<Teachers> getTeachers_queue() {
		return teachers_queue;
	}
	

	public String toString() {
		return getId() + " " + getName()+ " " + getCode()+ " " + getMax_hours_per_day()+ " " + getMax_hours_per_week() ;
		
	}

	

}

