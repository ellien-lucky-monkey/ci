package com.el.gov.ci.web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Jiangkui
 * @date 2019年07月09日
 */
public class RestTemplateUtils {


    public static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtils.class);
    private static final int READ_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;

    public static RestTemplate defaultRestTemplate() {
        return buildRestTemplate(READ_TIMEOUT, CONNECT_TIMEOUT, StringUtils.EMPTY, NumberUtils.INTEGER_ZERO);
    }


    public static RestTemplate buildRestTemplate(int readTimeout, int connectTimeout, String proxyHost, int proxyPort) {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(readTimeout);
        httpRequestFactory.setConnectTimeout(connectTimeout);

        if (!StringUtils.isEmpty(proxyHost)) {
            SocketAddress address = new InetSocketAddress(proxyHost, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            httpRequestFactory.setProxy(proxy);
        }

        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        converterList.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);

        GsonHttpMessageConverter gsonConverter = new GsonHttpMessageConverter();
        gsonConverter.setSupportedMediaTypes(
                Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON));
        converterList.add(gsonConverter);
        return restTemplate;
    }


    public static <T> Optional<T> getForEntity(String url, Class<T> clazz, RestTemplate restTemplate) {
        if (restTemplate == null) {
            restTemplate = defaultRestTemplate();
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        stopWatch.stop();
        logger.info("current thread is {}", Thread.currentThread().getName());
        if (response == null) {
            logger.error("getForEntity finish " +
                    "url:{}\r\n" +
                    " cost:{}\r\n" +
                    " response: is null", url, stopWatch.getTotalTimeMillis());
            throw new RuntimeException("response is null");
        }
        if (!HttpStatus.OK.equals(response.getStatusCode())) {
            logger.error("response code is {}", response.getStatusCode());
            throw new RuntimeException("request error");
        }
        String body = response.getBody();
        if (org.apache.commons.lang3.StringUtils.isEmpty(body)) {
            throw new RuntimeException("response body is null");
        }
        return Optional.ofNullable(gson.fromJson(body, clazz));
    }


    public static void main(String[] args) throws InterruptedException {


        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(1);
        connManager.setDefaultMaxPerRoute(1);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setKeepAliveStrategy((httpResponse, httpContext) -> 1000);
        httpClientBuilder.setConnectionManager(connManager);
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler());
        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);


        restTemplate.getForObject("http://i.st15.anhouse.com.cn/modules/api/account/common/dict/list",String.class);
        Thread.sleep(1*60*1000L);

        restTemplate.getForObject("http://i.st15.anhouse.com.cn/modules/api/account/common/dict/list",String.class);


    }
}
