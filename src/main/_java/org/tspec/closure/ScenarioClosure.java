package org.tspec.closure;

import org.tspec.dom.Scenario;
import org.tspec.dom.Story;

import groovy.lang.Closure;

public class ScenarioClosure extends Closure {

    private Scenario scenario;

    public ScenarioClosure(Object owner) {
        super(owner);
    }

    private void bind(Closure body) {
        body.setProperty("กำหนดให้", new GivenClosure(this));
        body.setProperty("เมื่อ", new WhenClosure(this));
        body.setProperty("แล้วควร", new ThenClosure("แล้วควร", this));
        body.setProperty("แล้ว", new ThenClosure("แล้ว", this));
        body.setProperty("และควร", new AndThenClosure("และควร", this));
        body.setProperty("และ", new AndThenClosure("และ", this));
        body.setProperty("และเมื่อ", new AndThenClosure("และเมื่อ", this));
    }

    public Story doCall(String name, Closure body) {
        this.scenario = new Scenario();
        this.scenario.setName(name);
        ((Story)this.getDelegate()).addScenario(this.scenario);
        bind(body);
        body.call();
        return (Story)this.getDelegate();
    }

    public Scenario getScenario() {
        return this.scenario;
    }

}
