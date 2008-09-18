package org.tspec.runtime;

public class ReportHelper {
	
	static void reportError(Object object, Object obj2, String message) {
		for(StackTraceElement ste: Thread.currentThread().getStackTrace()) {
			if(ste.getFileName()!=null && ste.getFileName().lastIndexOf("Story.groovy")!=-1) {
				ErrorListener.v().addError(
					ste.getFileName()+":"+ste.getLineNumber() + " / "
					+ object + " " + message + " " + obj2
				);
				break;
			}				
		}
	}

}
