package org.tspec.runtime;

public class ShouldObject {

	private Object object;
	private boolean not;

	public ShouldObject(Object o) {
		this(o, false);
	}	
	
	public ShouldObject(Object o, boolean b) {
		this.object = o;
		this.not = b;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = this.object.equals(obj);
		if(this.not==true) {
			if(!result) ReportHelper.reportError(this.object, obj, "should not be");
			return !result;
		} else {
			if(result) ReportHelper.reportError(this.object, obj, "should be");
			return result;
		}
	}
	
	public ShouldObject getNot() {
		return new ShouldObject(this.object, true);
	}		

}
