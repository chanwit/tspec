package org.tspec.runtime;

import groovy.lang.Closure;
import groovy.lang.MissingMethodException;

import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.tspec.VerificationException;

public class EnsureClosure extends Closure {

    public EnsureClosure(Object owner) {
        super(owner);
        this.setResolveStrategy(Closure.DELEGATE_FIRST);
    }

    public void doCall(Class<Throwable> throwable, Closure body) throws Exception {
        if(throwable == null) {
            throw new VerificationException("ไม่ได้มีการประกาศ Exception ที่ขว้างออกมา");
        }
        try {
            body.setDelegate(this.getDelegate());
            body.setResolveStrategy(Closure.DELEGATE_FIRST);
            body.call();
            throw new VerificationException("ควรมีการขว้างข้อผิดพลาดชนิด " + throwable);
        }  catch(InvokerInvocationException e) {
            if(e.getCause() instanceof MissingMethodException) {
                throw new VerificationException(e.getCause().getMessage());
            } else if(e.getCause()!=null && throwable.isAssignableFrom(e.getCause().getClass())==false) {
                System.out.println(e.getCause().getClass());
                throw new VerificationException("ข้อผิดพลาดที่ขว้างออกมาไม่ใช่ " + throwable);
            }
        }  catch(VerificationException e) {
            throw (VerificationException)e;
        }
    }

}
