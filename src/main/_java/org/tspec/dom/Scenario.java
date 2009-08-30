package org.tspec.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.tspec.runtime.ShouldCategory;


import groovy.lang.Closure;
import groovy.lang.GroovyObjectSupport;

public class Scenario extends GroovyObjectSupport {

    private String name;
    private Given given;
    private When when;
    private List<Then> thenClauses = new ArrayList<Then>();
    private HashMap<String, Object> vars = new HashMap<String, Object>();

    public Scenario() {
        super();
        vars.put("จริง", true);
        vars.put("เท็จ", false);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addGiven(Given given) {
        this.given = given;
    }

    public void addWhen(When when) {
        this.when = when;
    }

    public void addThen(Then then) {
        this.thenClauses.add(then);
    }

    public void setProperty(String property, Object newValue) {
        if(newValue instanceof Closure) {
            ((Closure)newValue).setDelegate(this);
        }
        vars.put(property, newValue);
    }

    public Object getProperty(String property) {
        return vars.get(property);
    }

    public Object invokeMethod(String name, Object args) {
        if(vars.containsKey(name)) {
            Object c = vars.get(name);
            if(c instanceof Closure) {
                return ((Closure)c).call();
            }
        }
        return super.invokeMethod(name, args);
    }

    public void run() {
        System.out.println(name);
        if(given!=null && given.getBody()!=null) {
            given.getBody().setDelegate(this);
            given.getBody().setResolveStrategy(Closure.DELEGATE_FIRST);
            given.getBody().call();
            System.out.print("    กำหนดให้ " + given.getName());
        } else {
            System.out.println("    ไม่มีการระบุประโยค 'กำหนดให้'");
        }
        if(when !=null && when.getBody()!=null) {
            when.getBody().setDelegate(this);
            when.getBody().setResolveStrategy(Closure.DELEGATE_FIRST);
            when.getBody().call();
            System.out.println(" และเมื่อ" + when.getName());
        } else {
            System.out.println("    ไม่มีการระบุประโยค 'เมื่อ'");
        }
        if(thenClauses.size() > 0) {
            for (final Then then : thenClauses) {
                if(then.getBody() != null) {
                    then.init(this);
                    then.getBody().setDelegate(this);
                    then.getBody().setResolveStrategy(Closure.DELEGATE_FIRST);
                    DefaultGroovyMethods.use(this, ShouldCategory.class, then.getBody());
                    System.out.print("    " + then.getLabel() + then.getName());
                    System.out.println(" / ผ่าน");
                } else {
                    System.out.print("    " + then.getLabel() + then.getName());
                    System.out.println(" / ไม่ผ่าน");
                }
            }
        }
        System.out.println("จบ สถานการณ์");
    }

}
