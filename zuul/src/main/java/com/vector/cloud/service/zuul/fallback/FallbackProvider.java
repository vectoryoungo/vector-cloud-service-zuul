/**
 * @create 2019-08-15 15:14
 * @desc provider
 **/
package com.vector.cloud.service.zuul.fallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class FallbackProvider implements org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider{

    private static final Logger logger = LoggerFactory.getLogger(FallbackProvider.class);
    @Override
    public ClientHttpResponse fallbackResponse(Throwable cause) {
        logger.info("zuul ClientHttpResponse ....");
        List<String>  zuulFallback = new ArrayList<>();
        zuulFallback.add("zuul time out please retry later");

        ObjectMapper objectMapper = new ObjectMapper();
        String response = null;
        try {
            response = objectMapper.writeValueAsString(zuulFallback);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return exexuteFallback(HttpStatus.GATEWAY_TIMEOUT,response,"application","json","utf-8");
    }

    private ClientHttpResponse exexuteFallback(final HttpStatus status,String messageContent,String mediaType,String submediaType,String charsetName) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {

                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {
                //close resource should not close getBody
            }

            @Override
            public InputStream getBody() throws IOException {
                String content = messageContent;

                return new ByteArrayInputStream(content.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                MediaType mediaType1 = new MediaType(mediaType,submediaType, Charset.forName(charsetName));
                httpHeaders.setContentType(mediaType1);
                return httpHeaders;
            }
        };
    }

    @Override
    public String getRoute() {
        return "vector-cloud-service";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {

        logger.info("zuul ClientHttpResponse() ....");
        List<String>  zuulFallback = new ArrayList<>();
        zuulFallback.add("zuul time out please retry later");

        ObjectMapper objectMapper = new ObjectMapper();
        String response = null;
        try {
            response = objectMapper.writeValueAsString(zuulFallback);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return exexuteFallback(HttpStatus.GATEWAY_TIMEOUT,response,"application","json","utf-8");
    }
}

