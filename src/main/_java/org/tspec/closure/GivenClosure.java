package org.tspec.closure;

import org.tspec.dom.Given;
import org.tspec.dom.Scenario;

import groovy.lang.Closure;


public class GivenClosure extends Closure {

    public GivenClosure(Object owner) {
        super(owner);
    }

    public void doCall(String s, Closure body) {
        Scenario sc = ((ScenarioClosure)this.getDelegate()).getScenario();
        sc.addGiven(new Given(s, body));
    }

}
