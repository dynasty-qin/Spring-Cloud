package com.example.demo.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;

//@Component
public class FirstFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
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

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String adminName = request.getHeader("AdminName");
        if (!"harry".equalsIgnoreCase(adminName)){
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
