package org.tspec.v2.tests;

import org.codehaus.groovy.control.CompilerConfiguration;
import org.tspec.v2.Setup;
import org.tspec.v2.ThaiSymbol;
import org.tspec.v2.behaviour.*;
import org.tspec.v2.story.Scenario;
import org.tspec.v2.story.Story;

import groovy.util.GroovyTestCase

class SimpleStoryTest extends GroovyTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp()
        Setup.setup()
    }

    void testSimpleStory() {
        def source ='''
เรื่อง อะไร
สถานการณ์ ที่หนึ่ง {
  x = new Object()
}
'''
        def conf = new CompilerConfiguration(debug:true, sourceEncoding:"utf-8")
        def sh = new GroovyShell(new Binding(), conf)
        def s = sh.parse(source)
        s.run()
        def root = s.binding.root
        assertEquals root.class, Story
        assertEquals root.title, "อะไร"
        assertEquals root.scenario.size(), 1

        def sc1 = root.scenario[0]
        assertEquals sc1.class, Scenario
        assertEquals sc1.title, "ที่หนึ่ง"
    }

    void testSimpleBehaviour() {
        def source ='''
พฤติกรรมของ อะไร
วัตถุ ควรจะทำโน่นทำนี่ได้ {
  x = new Object()
}
'''
        def conf = new CompilerConfiguration(debug:true, sourceEncoding:"utf-8")
        def sh = new GroovyShell(new Binding(), conf)
        def s = sh.parse(source)
        s.run()
        def root = s.binding.root
        assertEquals root.class, Behaviour
        assertEquals root.title, "อะไร"
        assertEquals root.cases.size(), 1
    }

}