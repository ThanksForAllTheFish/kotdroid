package org.t4atf.kotdroid

import com.orientechnologies.orient.server.OServerMain
import com.orientechnologies.orient.server.config.*
import com.orientechnologies.orient.server.network.protocol.binary.ONetworkProtocolBinary
import com.orientechnologies.orient.server.network.protocol.http.ONetworkProtocolHttpDb
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class KotdroidApplication

fun main(args: Array<String>) {
    val server = OServerMain.create();
    val cfg = OServerConfiguration();
    cfg.network = OServerNetworkConfiguration()
    cfg.network.protocols = listOf(
            OServerNetworkProtocolConfiguration("binary", ONetworkProtocolBinary::class.java.name),
            OServerNetworkProtocolConfiguration("http", ONetworkProtocolHttpDb::class.java.name))
    cfg.network.listeners = listOf(binaryListener(), httpListener())
    cfg.users = arrayOf(OServerUserConfiguration("root", "root", "*"))
    cfg.properties = arrayOf(OServerEntryConfiguration("server.cache.staticResources", "false"), OServerEntryConfiguration("log.console.level", "info"),
            OServerEntryConfiguration("log.file.level", "fine"), OServerEntryConfiguration("plugin.dynamic", "false"))
    server.startup(cfg);
    server.activate();

    val configurableApplicationContext = SpringApplication.run(KotdroidApplication::class.java, *args)

    configurableApplicationContext.addApplicationListener { server.shutdown() }
}

private fun binaryListener() = OServerNetworkListenerConfiguration()

private fun httpListener() : OServerNetworkListenerConfiguration {
    val httpListener = OServerNetworkListenerConfiguration()
    httpListener.protocol = "http"
    httpListener.portRange = "2480-2490"
    return httpListener
}
