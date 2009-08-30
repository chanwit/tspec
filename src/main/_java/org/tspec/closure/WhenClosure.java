package org.tspec.closure;

import org.tspec.dom.When;

import groovy.lang.Closure;


public class WhenClosure extends ScenarioItemClosure {

    public WhenClosure(Object owner) {
        super(owner);
    }

    public void doCall(String name, Closure body) {
        When w = new When(name, body);
        getScenario().addWhen(w);
    }

}
