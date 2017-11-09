package org.chenxing.apigateway.core;

import com.netflix.client.config.IClientConfig;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommand;

/**
 * KceRibbonCommand
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:04
 */
public class KceRibbonCommand extends HttpClientRibbonCommand {


    public KceRibbonCommand(String commandKey, RibbonLoadBalancingHttpClient client, RibbonCommandContext context,
                            ZuulProperties zuulProperties, ZuulFallbackProvider zuulFallbackProvider, IClientConfig config) {
        super(commandKey, client, context, zuulProperties, zuulFallbackProvider, config);
    }

    @Override
    protected KceRibbonApacheHttpRequest createRequest() throws Exception {
        return new KceRibbonApacheHttpRequest(this.context);
    }
}
