package org.chenxing.apigateway.core.ribbon;

import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.util.MultiValueMap;

import java.io.InputStream;
import java.util.List;

/**
 * KceRibbonCommandContext
 *
 * @author Chenxing Li
 * @date 09/11/2017 20:15
 */
public class KceRibbonCommandContext extends RibbonCommandContext {

    private Object loadBalancerKey;


    public KceRibbonCommandContext(String serviceId, String method, String uri,
                                   Boolean retryable, MultiValueMap<String, String> headers,
                                   MultiValueMap<String, String> params, InputStream requestEntity,
                                   List<RibbonRequestCustomizer> requestCustomizers, Long contentLength, Object loadBalancerKey) {
        super(serviceId, method, uri, retryable, headers, params, requestEntity, requestCustomizers, contentLength);
        this.loadBalancerKey = loadBalancerKey;
    }

    public Object getLoadBalancerKey() {
        return loadBalancerKey;
    }
}
