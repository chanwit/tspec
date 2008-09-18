package org.tspec.runtime;

import java.util.ArrayList;
import java.util.List;

public class ErrorListener {
	
	private static ErrorListener _instance;
	private List<String> errors=new ArrayList<String>();

	private ErrorListener(){	}
	
	public static ErrorListener v() {
		if(_instance == null) {
			_instance = new ErrorListener();
		} 
		return _instance;
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

	public void print() {
		System.out.println("");
		for(String s: this.errors) {
			System.out.println(s);
		}
	}
	
}
