package org.chenxing.apigateway.conf;

import com.netflix.zuul.ZuulFilter;
import org.chenxing.apigateway.core.KceRibbonCommandFactory;
import org.chenxing.apigateway.filters.routing.ApiGatewayRibbonRoutingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * ApiGatewayConfiguration
 *
 * @author Chenxing Li
 * @date 09/11/2017 17:19
 */
@Configuration
public class ApiGatewayConfiguration {

    @Autowired(required = false)
    private Set<ZuulFallbackProvider> zuulFallbackProviders = Collections.emptySet();

    @SuppressWarnings("rawtypes")
    @Autowired(required = false)
    private List<RibbonRequestCustomizer> requestCustomizers = Collections.emptyList();

    @Autowired
    protected ZuulProperties zuulProperties;

    @Autowired
    protected ServerProperties server;

    @Autowired(required = false)
    private ErrorController errorController;

    @Bean
    public KceRibbonCommandFactory ribbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        return new KceRibbonCommandFactory(clientFactory, zuulProperties, zuulFallbackProviders);
    }



    // pre filters
    @Bean
    public PreDecorationFilter preDecorationFilter(RouteLocator routeLocator, ProxyRequestHelper proxyRequestHelper) {
        return new PreDecorationFilter(routeLocator, this.server.getServletPrefix(), this.zuulProperties,
                proxyRequestHelper);
    }

    // routing filters
    @Bean
    public ApiGatewayRibbonRoutingFilter ribbonRoutingFilter(ProxyRequestHelper helper,
                                                             RibbonCommandFactory<?> ribbonCommandFactory) {
        return new ApiGatewayRibbonRoutingFilter(helper, ribbonCommandFactory, this.requestCustomizers);
    }
}
