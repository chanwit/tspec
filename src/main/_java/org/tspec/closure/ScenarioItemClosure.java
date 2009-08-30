package org.tspec.closure;

import org.tspec.dom.Scenario;

import groovy.lang.Closure;

public class ScenarioItemClosure extends Closure {

    private Scenario sc;

    public ScenarioItemClosure(Object owner) {
        super(owner);
        this.sc = ((ScenarioClosure)this.getDelegate()).getScenario();
    }

    public Scenario getScenario() {
        return this.sc;
    }

}
