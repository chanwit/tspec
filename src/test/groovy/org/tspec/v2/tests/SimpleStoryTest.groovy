package org.tspec.v2.tests

import org.tspec.v2.StoryTestCase
import org.tspec.v2.ThaiSymbol
import org.tspec.v2.behaviour.*
import org.tspec.v2.story.Scenario
import org.tspec.v2.story.Story

class SimpleStoryTest extends StoryTestCase {

    void testSimpleStory() {
        def root = runTSpec('''
เรื่อง อะไร
สถานการณ์ ที่หนึ่ง {
  x = new Object()
}
''')
        assertEquals root.class, Story
        assertEquals root.title, "อะไร"
        assertEquals root.scenario.size(), 1

        def sc1 = root.scenario[0]
        assertEquals sc1.class, Scenario
        assertEquals sc1.title, "ที่หนึ่ง"
    }

    void testSimpleBehaviour() {
        def root = runTSpec('''
พฤติกรรมของ อะไร
วัตถุ ควรจะทำโน่นทำนี่ได้ {
  x = new Object()
}
''')
        assertEquals root.class, Behaviour
        assertEquals root.title, "อะไร"
        assertEquals root.cases.size(), 1
    }

}