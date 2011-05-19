package org.tspec.v2

import groovy.util.GroovyTestCase
import org.tspec.v2.Setup
import org.codehaus.groovy.control.CompilerConfiguration

abstract class StoryTestCase extends GroovyTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp()
		Setup.setup()
	}
	
	protected runTSpec(source) {
		def conf = new CompilerConfiguration(debug:true, sourceEncoding:"utf-8")
		def sh = new GroovyShell(new Binding(), conf)
		def s = sh.parse(source)
		s.run()
		return s.binding
	}

}
