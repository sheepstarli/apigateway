package org.chenxing.apigateway.core.ribbon;

import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpRequest;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;

/**
 * KceRibbonApacheHttpRequest
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:38
 */
public class KceRibbonApacheHttpRequest extends RibbonApacheHttpRequest {

    public KceRibbonApacheHttpRequest(RibbonCommandContext context) {
        super(context);
        if (context instanceof KceRibbonCommandContext) {
            KceRibbonCommandContext kceRibbonCommandContext = (KceRibbonCommandContext) context;
            setLoadBalancerKey(kceRibbonCommandContext.getLoadBalancerKey());
        }
    }

}
