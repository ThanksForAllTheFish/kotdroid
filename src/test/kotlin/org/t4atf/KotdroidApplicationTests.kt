package org.t4atf

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(KotdroidApplication::class))
class KotdroidApplicationTests {

	@Test
	fun contextLoads() {
	}

}
