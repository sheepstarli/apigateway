package org.chenxing.apigateway.filters.support;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

/**
 * ApiGatewayFilterConstants
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:20
 */
public interface ApiGatewayFilterConstants extends FilterConstants {

    String CTX_LOAD_BALANCER_KEY = "CTX.loadBalancerKey";


    /**
     * pre filter: {@link org.chenxing.apigateway.filters.pre.CanaryFilter}
     */
    int PRE_CANARY_FILTER_ORDER = PRE_DECORATION_FILTER_ORDER + 10;

}
