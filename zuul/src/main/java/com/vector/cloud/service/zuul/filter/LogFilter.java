/**
 * @create 2019-08-15 11:26
 * @desc log filter
 **/
package com.vector.cloud.service.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogFilter extends ZuulFilter{

    private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);


    /**
     * type could be:
     * 1.pre
     * 2.route
     * 3.error
     * 4.post
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * same filter order sequence
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * specific filter logic you need to do
     *
     * @return
     */
    @Override
    public Object run() {

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        System.out.println("LogFilter filter process ....");
        logger.info("request info is [{}],url [{}]",request.getMethod(),request.getRequestURL().toString());
        return null;
    }
}

