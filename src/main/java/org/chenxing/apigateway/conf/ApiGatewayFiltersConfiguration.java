package org.chenxing.apigateway.conf;

import org.chenxing.apigateway.filters.pre.CanaryFilter;
import org.chenxing.apigateway.filters.routing.ApiGatewayRibbonRoutingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * ApiGatewayFiltersConfiguration
 *
 * @author Chenxing Li
 * @date 09/11/2017 21:00
 */
@Configuration
public class ApiGatewayFiltersConfiguration {

    @SuppressWarnings("rawtypes")
    @Autowired(required = false)
    private List<RibbonRequestCustomizer> requestCustomizers = Collections.emptyList();

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties server;

    // pre filters
    @Bean
    public PreDecorationFilter preDecorationFilter(RouteLocator routeLocator, ProxyRequestHelper proxyRequestHelper) {
        return new PreDecorationFilter(routeLocator, this.server.getServletPrefix(), this.zuulProperties,
                proxyRequestHelper);
    }

    @Bean
    public CanaryFilter canaryFilter() {
        return new CanaryFilter();
    }

    // routing filters
    @Bean
    public ApiGatewayRibbonRoutingFilter ribbonRoutingFilter(ProxyRequestHelper helper,
                                                             RibbonCommandFactory<?> ribbonCommandFactory) {
        return new ApiGatewayRibbonRoutingFilter(helper, ribbonCommandFactory, this.requestCustomizers);
    }

}
