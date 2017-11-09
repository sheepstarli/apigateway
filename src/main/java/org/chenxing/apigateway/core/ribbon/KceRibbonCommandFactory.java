package org.chenxing.apigateway.core.ribbon;

import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommand;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandContext;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommandFactory;

import java.util.Collections;
import java.util.Set;

/**
 * KceRibbonCommandFactory
 * @see org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:00
 */
public class KceRibbonCommandFactory extends AbstractRibbonCommandFactory {


    private final SpringClientFactory clientFactory;

    private final ZuulProperties zuulProperties;

    public KceRibbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        this(clientFactory, zuulProperties, Collections.emptySet());
    }

    public KceRibbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties,
                                          Set<ZuulFallbackProvider> fallbackProviders) {
        super(fallbackProviders);
        this.clientFactory = clientFactory;
        this.zuulProperties = zuulProperties;
    }

    @Override
    public RibbonCommand create(RibbonCommandContext context) {
        ZuulFallbackProvider zuulFallbackProvider = getFallbackProvider(context.getServiceId());
        final String serviceId = context.getServiceId();
        final KceRibbonLoadBalancingHttpClient client = this.clientFactory.getClient(
                serviceId, KceRibbonLoadBalancingHttpClient.class);
        client.setLoadBalancer(this.clientFactory.getLoadBalancer(serviceId));
        return new KceRibbonCommand(serviceId, client, context, zuulProperties, zuulFallbackProvider,
                clientFactory.getClientConfig(serviceId));
    }

}
