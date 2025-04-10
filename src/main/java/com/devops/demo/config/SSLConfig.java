package com.devops.demo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SSLConfig {

    @Bean
    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException {
        // ✅ Load SSL Configuration from server-ssl.properties
        Properties properties = new Properties();
        properties.load(new FileInputStream(System.getenv("MYSOCKS_HOME") + "/config/server-ssl.properties"));

        String sslEnabled = properties.getProperty("server.ssl.enabled", "false").trim();
        if ("true".equals(sslEnabled)) {
            String keyStorePath = properties.getProperty("server.ssl.key-store").trim();
            String keyStorePassword = properties.getProperty("server.ssl.key-store-password").trim();

            // ✅ Load KeyStore from PFX file
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            try (FileInputStream keyStoreStream = new FileInputStream(keyStorePath)) {
                keyStore.load(keyStoreStream, keyStorePassword.toCharArray());
            }

            // ✅ Create SSL Context with KeyStore
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(keyStore, keyStorePassword.toCharArray()) // ✅ Load Private Key
                    .build();

            // ✅ Use SSLConnectionSocketFactory
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext);

            // ✅ Register SSL & Plain Socket Factories
            org.apache.hc.core5.util.Timeout timeout = Timeout.ofSeconds(30);
            org.apache.hc.core5.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create()
                            .register("https", sslSocketFactory)
                            .register("http", PlainConnectionSocketFactory.getSocketFactory())
                            .build();

            // ✅ Create Connection Manager with SSL Registry
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connManager.setDefaultSocketConfig(SocketConfig.custom()
                    .setSoTimeout(timeout) // ✅ Set Socket Timeout
                    .build());

            // ✅ Create HttpClient
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(connManager) // ✅ Set Connection Manager
                    .build();

            // ✅ Use HttpClient in RestTemplate
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            return new RestTemplate(requestFactory);
        } else {
            // If SSL is not enabled, use the default RestTemplate without SSL configuration
            return new RestTemplate();
        }
    }
}
