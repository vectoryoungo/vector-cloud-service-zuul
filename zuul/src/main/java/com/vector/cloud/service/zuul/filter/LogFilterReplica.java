/**
 * @create 2019-08-15 11:32
 * @desc replica
 **/
package com.vector.cloud.service.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilterReplica extends ZuulFilter{


    private static final Logger logger = LoggerFactory.getLogger(LogFilterReplica.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("LogFilterReplica filter process ....");
        logger.info("request info is [{}],url [{}]",request.getMethod(),request.getRequestURL().toString());
        return null;
    }
}

