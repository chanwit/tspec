package org.tspec.runtime;

public class ShouldObject {

    private Object object;
    private boolean not;
    private ErrorListener el;

    public ShouldObject(Object o) {
        this(o, false);
    }

    public ShouldObject(Object o, boolean b) {
        this.object = o;
        this.not = b;
    }

    public void setEl(ErrorListener el) {
        this.el = el;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result;
        result = this.object.equals(obj);
        if(this.not==true) {
            if(!result) ReportHelper.reportError(el, Thread.currentThread().getStackTrace(), this.object + " should not be " + obj);
            return !result;
        } else { // not == false - meaning comparing true
            if(result) ReportHelper.reportError(el, Thread.currentThread().getStackTrace(), this.object + " should not be " + obj);
            return result;
        }
    }

    public ShouldObject getNot() {
        return new ShouldObject(this.object, true);
    }

}
