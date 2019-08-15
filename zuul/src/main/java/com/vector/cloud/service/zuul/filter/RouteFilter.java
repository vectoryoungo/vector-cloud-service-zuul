/**
 * @create 2019-08-15 14:10
 * @desc route filter
 **/
package com.vector.cloud.service.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RouteFilter extends ZuulFilter{

    private static final Logger logger = LoggerFactory.getLogger(RouteFilter.class);

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("RouteFilter filter process ....");
        logger.info("request info is [{}],url [{}]",request.getMethod(),request.getRequestURL().toString());
        return null;
    }
}

