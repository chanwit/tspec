package org.tspec;

import groovy.lang.Binding;
import groovy.lang.Closure;
import groovy.lang.ExpandoMetaClass;
import groovy.lang.ExpandoMetaClassCreationHandle;
import groovy.lang.GroovySystem;
import groovy.lang.MetaClassRegistry;

import org.tspec.closure.AfterClosure;
import org.tspec.closure.BeforeClosure;
import org.tspec.closure.ScenarioClosure;
import org.tspec.closure.SubjectClosure;
import org.tspec.dom.Story;
import org.tspec.runtime.MustObject;
import org.tspec.runtime.ShouldObject;


public class ThaiSpecBinding extends Binding {

	public ThaiSpecBinding() {
		super();
		init();
	}

	private void init() {
		Story root = new Story();
		setVariable("เรื่อง", new SubjectClosure(root));		
		setVariable("อธิบาย", new ScenarioClosure(root));
		setVariable("ก่อน", new BeforeClosure(root));
		setVariable("หลัง", new AfterClosure(root));
		setupShouldAndMust();			
	}

	private void setupShouldAndMust() {
		ExpandoMetaClassCreationHandle.enable();
		MetaClassRegistry mr = GroovySystem.getMetaClassRegistry();
		ExpandoMetaClass mc = (ExpandoMetaClass)mr.getMetaClass(Object.class);
		Closure shouldClosure = new Closure(null){
			@SuppressWarnings("unused")
			public ShouldObject doCall() {
				return new ShouldObject(this.getDelegate()); 				
			}
		};
		Closure mustClosure = new Closure(null){
			@SuppressWarnings("unused")
			public MustObject doCall() {
				return new MustObject(this.getDelegate()); 				
			}
		};		
		mc.setProperty("getShould", shouldClosure);
		mc.setProperty("getMust", mustClosure);
	}
	
}
