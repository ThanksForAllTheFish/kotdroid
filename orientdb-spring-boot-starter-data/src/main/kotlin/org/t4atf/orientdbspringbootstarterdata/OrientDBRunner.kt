package org.t4atf.orientdbspringbootstarterdata

import com.orientechnologies.orient.server.OServer
import org.springframework.boot.CommandLineRunner

class OrientDBRunner(val orientServer: OServer) : CommandLineRunner {

    override fun run(vararg args: String?) {
        orientServer.activate()
    }
}
