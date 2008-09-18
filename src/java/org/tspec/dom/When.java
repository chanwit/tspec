package org.tspec.dom;

import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;

public class When extends GroovyObjectSupport {

	private String name;
	private Closure body;

	public When(String name, Closure body) {
		this.name = name;
		this.body = body;
	}

	public Closure getBody() {
		return this.body;
	}

	public String getName() {
		return this.name;
	}

}
