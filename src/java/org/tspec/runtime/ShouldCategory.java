package org.tspec.runtime;

import java.util.Collection;

import org.tspec.VerificationException;

public class ShouldCategory {
	
	public static void ควรเท่ากับ(Object o, Object o2) throws VerificationException {
		if(!o.equals(o2)) {
			throw new VerificationException(o + " ควรเท่ากับ " + o2);
		}
	}

	public static void ควรเป็น(Object o, Object o2) throws VerificationException {
		if(!o.equals(o2)) {
			throw new VerificationException(o + " ควรเป็น " + o2);
		}
	}
	
	public static void ควรมีค่าเป็น(Object o, Object o2) throws VerificationException {
		if(!o.equals(o2)) {
			throw new VerificationException(o + " ควรมีค่าเป็น " + o2);
		}
	}		
	
	public static void ไม่ควรเท่ากับ(Object o, Object o2) throws VerificationException {
		if(o.equals(o2)) {
			throw new VerificationException(o + " ไม่ควรเท่ากับ " + o2);
		}
	}

	public static void ไม่ควรเป็น(Object o, Object o2) throws VerificationException {
		if(o.equals(o2)) {
			throw new VerificationException(o + " ไม่ควรเป็น " + o2);
		}
	}
		
	public static void ไม่ควรมีค่าเป็น(Object o, Object o2) throws VerificationException {
		if(o.equals(o2)) {
			throw new VerificationException(o + " ไม่ควรมีค่าเป็น " + o2);
		}
	}
	
	public static void บรรจุอยู่ใน(Object o, Collection<?> c) throws VerificationException {
		if(!c.contains(o)) {
			throw new VerificationException("ไม่มีวัตถุ " + o + " อยู่ใน " + c);
		}
	}
	
	public static ShouldObject getShould(Object o) {
		return new ShouldObject(o);
	}
	
}
