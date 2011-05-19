package org.tspec.v2.story

import org.tspec.v2.ThaiSymbol

class Scenario {

    String title    
	List<Clause> clause = []
	Binding binding

	def กำหนดให้ = { ThaiSymbol s ->
		clause << new Define(title: s.value)
		def action = s.action 
		action.delegate = binding
		action.resolveStrategy = Closure.DELEGATE_FIRST
		action.call()
	}

}