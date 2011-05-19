package org.tspec.v2.story

import org.tspec.v2.ThaiSymbol

class Scenario {

	String title    
	List<Clause> clause = []
	Binding binding
	Throwable lastException = null

	def กำหนดให้ = { ThaiSymbol s ->
		clause << new Given(title: s.value)
		def action = s.action 
		action.delegate = binding
		action.resolveStrategy = Closure.DELEGATE_FIRST
		action.call()
	}
	
	def เมื่อ = { ThaiSymbol s ->
		clause << new When(title: s.value)
		def action = s.action
		action.delegate = binding
		action.resolveStrategy = Closure.DELEGATE_FIRST
		try {
			action.call()
		} catch(Throwable e) {
			// record exception
			lastException = e
		}
	}
	
	def แล้วควร = { obj ->
		if (obj instanceof ThaiSymbol) {
			
		} else if (obj instanceof Map) {
			// แล้วควร มีการขว้างข้อผิดพลาด: RuntimeException
			def e = obj.find { it.value instanceof Class }
			if(e) {
				assert lastException.class == e.value
				// TODO record e.key
			} else {
				// TODO error, Throwable is expected
			}
		}	
	}

}