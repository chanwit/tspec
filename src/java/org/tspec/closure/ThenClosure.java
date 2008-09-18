package org.tspec.closure;

import org.tspec.dom.Then;

import groovy.lang.Closure;


public class ThenClosure extends ScenarioItemClosure {
	
	private String label;

	public ThenClosure(String label, Object owner) {
		super(owner);
		this.label = label;
	}
	
	public void doCall(String name, Closure body) {
		Then then = new Then(this.label, name, body);
		getScenario().addThen(then);
	}

}
