package org.t4atf.kotdroid.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.net.HttpURLConnection
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@Configuration
open class DroidConfiguration {

    @Bean
    open fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.requestFactory = object : SimpleClientHttpRequestFactory() {
            override fun prepareConnection(connection : HttpURLConnection, httpMethod : String) {
                if (connection is HttpsURLConnection) {
                    connection.hostnameVerifier = HostnameVerifier { p0, p1 -> true }
                    connection.sslSocketFactory = trustSelfSignedSSL().socketFactory
                }
                super.prepareConnection(connection, httpMethod);
            }

            private fun trustSelfSignedSSL(): SSLContext {
                val ctx = SSLContext.getInstance("TLS")
                ctx.init(null, arrayOf(DefaultX509TrustManager), null)
                SSLContext.setDefault(ctx)
                return ctx
            }
        }
        return restTemplate
    }

    object DefaultX509TrustManager : X509TrustManager {
        override fun checkClientTrusted(xcs: Array<X509Certificate>, string: String) {}
        override fun checkServerTrusted(xcs: Array<X509Certificate>, string: String) { }
        override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
    }
}
