package org.tspec.v2.tests

import org.tspec.v2.*
import org.tspec.v2.story.*

class StackStoryTest extends StoryTestCase {

	void testStackStory() {
		def root = runTSpec('''
import org.tspec.stack.*

เรื่อง แสต็ก

สถานการณ์ เมื่อค่าว่างถูกวางเข้าไปยังแสต็กว่าง {

	กำหนดให้ แสด็กว่าง {
		stack = new Stack()
	}

}
''')
		assert root.class == Story
		assert root.title == "แสต็ก"
		assert root.scenario.size() == 1
		assert root.scenario[0].class == Scenario
		assert root.scenario[0].title == "เมื่อค่าว่างถูกวางเข้าไปยังแสต็กว่าง"
	}
}
