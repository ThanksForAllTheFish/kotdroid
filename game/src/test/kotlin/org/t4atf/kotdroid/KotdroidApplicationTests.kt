package org.t4atf.kotdroid

import org.hamcrest.Matchers.greaterThan
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class KotdroidApplicationTests {

    @Autowired
    lateinit var context: WebApplicationContext
    lateinit var mockMvc: MockMvc;

    @Before
    fun setup() {
        mockMvc = webAppContextSetup(this.context)
                //.alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
                .build()
    }

    @Test
    fun contextLoads() {

        mockMvc.perform(get("/api/cards"))
                .andExpect { status().isOk }
                .andExpect { jsonPath("$.cards.length()").value(greaterThan(0)) }
                .andReturn()
    }
}
