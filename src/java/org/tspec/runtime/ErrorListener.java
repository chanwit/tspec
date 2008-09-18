package org.tspec.runtime;

import java.util.ArrayList;
import java.util.List;

public class ErrorListener {

	private static ErrorListener _instance;
	private List<String> errors=new ArrayList<String>();
	private String storyName;
	private int count = 0;

	public ErrorListener(){
	}

	public static ErrorListener v() {
		if(_instance == null) {
			_instance = new ErrorListener();
		}
		return _instance;
	}

	public void setStoryName(String name){
		this.storyName = name;
	}

	public void reset() {
		this.errors.clear();
	}

	public void addError(String message) {
		this.errors.add(message);
	}

	public boolean hasErrors() {
		return this.errors.size() > 0;
	}

	public void count() {
		this.count++;
	}

	public void print() {
		System.out.println("เรื่อง " + storyName);
		if(hasErrors()) {
			System.out.println("เจอข้อผิดพลาด");
			System.out.println("");
			for(String s: this.errors) {
				System.out.println(s);
			}
		} else {
			System.out.println("จำนวนสถานการณ์ทั้งหมด: "+ count + "ผ่าน");
		}
	}

}
