package com.company;

import java.util.ArrayList;

public class Attendance {
	private ArrayList<String> dates;
	private ArrayList<Integer> hours;
	public Attendance(ArrayList<String> dates, ArrayList<Integer> hours) {
		this.dates = dates;
		this.hours = hours;
	}

	public ArrayList<Integer> getHours() {
		return hours;
	}

	public ArrayList<String> getDates() {
		return dates;
	}
}
