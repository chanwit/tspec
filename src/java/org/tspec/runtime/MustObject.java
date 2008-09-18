package org.tspec.runtime;

public class MustObject {

	private Object object;
	private boolean not;

	public MustObject(Object o) {
		this(o, false);
	}	
	
	public MustObject(Object o, boolean b) {
		this.object = o;
		this.not = b;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = this.object.equals(obj);
		if(this.not==true) {
			if(!result) ReportHelper.reportError(this.object, obj, "must not be");
			return !result;
		} else {
			if(result) ReportHelper.reportError(this.object, obj, "must be");
			return result;
		}
	}
	
	public MustObject getNot() {
		return new MustObject(this.object, true);
	}		

}
