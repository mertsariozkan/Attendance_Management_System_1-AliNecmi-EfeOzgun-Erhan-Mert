package com.company;

public class Attendance {

	private int attendanceId;
	private String date;
	private int sectionId;
	private int studentId;
	private int hour;
	private int isAttend;

	public Attendance(int attendanceId, String date, int sectionId, int studentId, int hour, int isAttend) {
		this.attendanceId = attendanceId;
		this.date = date;
		this.setSectionId(sectionId);
		this.hour = hour;
		this.isAttend = isAttend;
		this.setStudentId(studentId);
	}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getIsAttend() {
		return isAttend;
	}

	public void setIsAttend(int isAttend) {
		this.isAttend = isAttend;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
