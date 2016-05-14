package org.t4atf.kotdroid

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringApplicationConfiguration(classes = arrayOf(KotdroidApplication::class))
@WebIntegrationTest(randomPort = true)
class KotdroidApplicationTests {

    @Autowired
    lateinit var context: WebApplicationContext
    lateinit var mockMvc: MockMvc;

    @Before
    fun setup() {
        mockMvc = webAppContextSetup(this.context)
                .alwaysDo<DefaultMockMvcBuilder>(print())
                .build()
    }

    @Test
    fun contextLoads() {
        mockMvc.perform(get("/api/cards"))
            .andExpect(status().isOk)
            .andReturn()
    }
}
