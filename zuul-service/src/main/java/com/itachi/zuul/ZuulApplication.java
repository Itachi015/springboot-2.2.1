package com.itachi.zuul;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author kong
 */
@Slf4j
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }


    /**
     * OkHttp的基本配置
     *
     * @return
     */
    @Bean
    public okhttp3.OkHttpClient.Builder okHttpClientBuilder() {
        return new OkHttpClient.Builder().addInterceptor(chain -> {
            Request request = chain.request();
            long t1 = System.nanoTime();
            log.info("Sending request {}", request.url());
            log.info("connection {}", chain.connection());
            log.info("request headers {}", request.headers());

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            log.info("Received response for {} on {}  {}", response.request().url(), (t2 - t1) / 1e6d, response.headers());
            return response;
        });
    }

}
