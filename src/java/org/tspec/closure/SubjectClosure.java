package org.tspec.closure;

import org.tspec.dom.Story;

import groovy.lang.Closure;

public class SubjectClosure extends Closure {

	public SubjectClosure(Object owner) {
		super(owner);
	}
	
	public void doCall(String name) {
		((Story)this.getDelegate()).setName(name);
	}

}
