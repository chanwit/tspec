package org.tspec.dom;

import groovy.lang.Closure;

public class Given {

	private String name;
	private Closure body;

	public Given(String s, Closure body) {
		this.name = s;
		this.body = body;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Closure getBody() {
		return body;
	}

	public void setBody(Closure body) {
		this.body = body;
	}
	
	

}
