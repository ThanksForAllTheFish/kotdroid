package org.t4atf.orientdbspringbootstarterdata.configuration

import com.orientechnologies.orient.server.OServer
import com.orientechnologies.orient.server.OServerMain
import com.orientechnologies.orient.server.config.*
import com.orientechnologies.orient.server.network.protocol.binary.ONetworkProtocolBinary
import com.orientechnologies.orient.server.network.protocol.http.ONetworkProtocolHttpDb
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.t4atf.orientdbspringbootstarterdata.OrientDBRunner

@Configuration
@ConditionalOnClass(OServer::class)
open class EmbeddedOrientDBAutoConfiguration {

    @Bean
    open fun orientRunner(orientServer: OServer) = OrientDBRunner(orientServer)

    @Bean(destroyMethod = "shutdown")
    open fun orientServer(): OServer {
        val server = OServerMain.create()
        val cfg = OServerConfiguration()
        cfg.network = OServerNetworkConfiguration()
        cfg.network.protocols = listOf(
                OServerNetworkProtocolConfiguration("binary", ONetworkProtocolBinary::class.java.name),
                OServerNetworkProtocolConfiguration("http", ONetworkProtocolHttpDb::class.java.name))
        cfg.network.listeners = listOf(binaryListener(), httpListener())
        cfg.users = arrayOf(OServerUserConfiguration("root", "root", "*"))
        cfg.properties = arrayOf(OServerEntryConfiguration("server.cache.staticResources", "false"), OServerEntryConfiguration("log.console.level", "info"),
                OServerEntryConfiguration("log.file.level", "fine"), OServerEntryConfiguration("plugin.dynamic", "false"))
        server.startup(cfg)
        return server
    }

    private fun binaryListener() = OServerNetworkListenerConfiguration()

    private fun httpListener(): OServerNetworkListenerConfiguration {
        val httpListener = OServerNetworkListenerConfiguration()
        httpListener.protocol = "http"
        httpListener.portRange = "2480-2490"
        return httpListener
    }
}
