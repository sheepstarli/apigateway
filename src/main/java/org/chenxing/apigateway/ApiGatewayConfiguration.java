package org.chenxing.apigateway;

import org.chenxing.apigateway.core.KceRibbonCommandFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.cloud.netflix.zuul.filters.route.apache.HttpClientRibbonCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
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

    @Bean
    public KceRibbonCommandFactory httpClientRibbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        return new KceRibbonCommandFactory(clientFactory, zuulProperties, zuulFallbackProviders);
    }
}
