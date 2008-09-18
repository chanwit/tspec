package org.tspec.runtime;

public class ReportHelper {

	static void reportError(ErrorListener el, StackTraceElement[] traces, String message) {
		for(StackTraceElement ste: traces) {
			if(ste.getFileName()!=null && ste.getFileName().lastIndexOf("Story.groovy")!=-1) {
				el.addError(
					ste.getFileName()+" at line "+ste.getLineNumber() + " : " + message
				);
				break;
			}
		}
	}

}
