package org.tspec.dom;

import java.util.ArrayList;
import java.util.List;

public class Story {

    private String name;
    private Before beforeEach;
    private Before beforeAll;
    private After afterEach;
    private After afterAll;
    private List<Scenario> scenarios = new ArrayList<Scenario>();

    public void setName(String name) {
        this.name = name;
    }

    public void addScenario(Scenario scenario) {
        this.scenarios.add(scenario);
    }

    public String getName() {
        return name;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public Before getBeforeEach() {
        return beforeEach;
    }

    public void setBeforeEach(Before beforeEach) {
        this.beforeEach = beforeEach;
    }

    public Before getBeforeAll() {
        return beforeAll;
    }

    public void setBeforeAll(Before beforeAll) {
        this.beforeAll = beforeAll;
    }

    public After getAfterEach() {
        return afterEach;
    }

    public void setAfterEach(After afterEach) {
        this.afterEach = afterEach;
    }

    public After getAfterAll() {
        return afterAll;
    }

    public void setAfterAll(After afterAll) {
        this.afterAll = afterAll;
    }

}
