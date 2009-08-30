package org.tspec.stack;

import java.util.ArrayList;

public class Stack {

	private ArrayList<Object> data = new ArrayList<Object>();

	public void push(Object element) {
		if(element == null) throw new RuntimeException();
		data.add(element);
	}

	public Object pop() {
		if(isEmpty()) throw new RuntimeException();
		return data.remove(data.size()-1);
	}

	public Object peek() {
		return data.get(data.size()-1);
	}

	public int size() {
		return data.size();
	}

	public boolean isEmpty() {
		return data.size()==0;
	}

}
