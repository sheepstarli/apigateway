package org.chenxing.apigateway.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.chenxing.apigateway.filters.support.ApiGatewayFilterConstants;

/**
 * CanaryFilter
 *
 * 灰度api使用的Pre Filter
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:17
 */
public class CanaryFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return ApiGatewayFilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return ApiGatewayFilterConstants.PRE_CANARY_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext currentContext = RequestContext.getCurrentContext();
//        currentContext.getRequest()
        return null;
    }
}
