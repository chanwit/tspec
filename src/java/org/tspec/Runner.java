package org.tspec;

import groovy.lang.GroovyShell;
import groovy.lang.Script;

import java.io.File;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.runtime.InvokerInvocationException;
import org.tspec.dom.Scenario;
import org.tspec.dom.Story;
import org.tspec.runtime.ErrorListener;


public class Runner {
	
	public static void main(String[] args) throws Throwable {
		CompilerConfiguration conf = new CompilerConfiguration();
		conf.setDebug(true);
		conf.setSourceEncoding("utf-8");
		GroovyShell sh = new GroovyShell(new ThaiSpecBinding(), conf);
		Script s = sh.parse(new File(args[0]));
		Story story = (Story)s.run();
		System.out.println("เรื่อง " + story.getName());		
		int i = 0;		
		try {			
			for(Scenario sc : story.getScenarios()) {
				sc.run();
				i++;
			}
		} catch(InvokerInvocationException e) {
			if(e.getCause()!=null) {
				for(StackTraceElement ste: e.getCause().getStackTrace()) {
					if(ste.getFileName()!=null && ste.getFileName().lastIndexOf("Story.groovy")!=-1) {
						ErrorListener.v().addError(
							ste.getFileName()+":"+ste.getLineNumber() + " / "
							+ e.getMessage()
						);
						break;
					}
				}
			}
		}
		if(ErrorListener.v().hasErrors()) {
			ErrorListener.v().print();
		} else {
			System.out.println("จำนวนสถานการณ์ทั้งหมด: " + i + " ผ่าน");
		}
	}
}