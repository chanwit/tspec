package org.tspec;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.tspec.dom.Scenario;
import org.tspec.dom.Story;
import org.tspec.runtime.ErrorListener;
import org.tspec.runtime.ReportHelper;


public class Runner {

    public void runStory(File file) throws Throwable {
        CompilerConfiguration conf = new CompilerConfiguration();
        conf.setDebug(true);
        conf.setSourceEncoding("utf-8");
        GroovyShell sh = new GroovyShell(new ThaiSpecBinding(null), conf);
        Script s = sh.parse(file);
        Story story = (Story)s.run();
        //System.out.println("เรื่อง " + story.getName());
        ErrorListener.v().setStoryName(story.getName());
        int i = 0;
        try {
            for(Scenario sc : story.getScenarios()) {
                sc.run();
                i++;
            }
        } catch(InvokerInvocationException e) {
            ReportHelper.reportError(el, e.getStackTrace(), e.getMessage());
        }
        if(ErrorListener.v().hasErrors()) {
            ErrorListener.v().print();
        } else {
            System.out.println("จำนวนสถานการณ์ทั้งหมด: " + i + " ผ่าน");
        }
    }

    public static void main(String[] args) throws Throwable {
    }
}