package org.tspec.dom;

import org.tspec.runtime.EnsureClosure;

import groovy.lang.Closure;

public class Then {

    private String name;
    private Closure body;
    private String label;

    public Then(String label, String name, Closure body) {
        this.label = label;
        this.name = name;
        this.body = body;
    }

    public void init(Scenario sc) {
        this.body.setProperty("รับรองว่าขว้าง", new EnsureClosure(sc));
    }

    public Closure getBody() {
        return this.body;
    }

    public String getLabel() {
        return this.label;
    }

    public String getName() {
        return name;
    }

}
