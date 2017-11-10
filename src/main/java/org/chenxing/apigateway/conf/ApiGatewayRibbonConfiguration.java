package org.chenxing.apigateway.conf;

import org.chenxing.apigateway.core.ribbon.KceRibbonCommandFactory;
import org.chenxing.ribbon.conf.KceRibbonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
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
@RibbonClients(defaultConfiguration = {KceRibbonConfiguration.class})
public class ApiGatewayRibbonConfiguration {

    @Autowired(required = false)
    private Set<ZuulFallbackProvider> zuulFallbackProviders = Collections.emptySet();

//    @Value("${ribbon.client.name}")
//    private String name = "client";
//
//    @Autowired
//    private PropertiesFactory propertiesFactory;
//
    @Bean
    public KceRibbonCommandFactory ribbonCommandFactory(SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
        return new KceRibbonCommandFactory(clientFactory, zuulProperties, zuulFallbackProviders);
    }
//
//    @Bean
//    public IRule ribbonRule(IClientConfig config) {
//        if (this.propertiesFactory.isSet(IRule.class, name)) {
//            return this.propertiesFactory.get(IRule.class, config, name);
//        }
//        ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
//        rule.initWithNiwsConfig(config);
//        return rule;
//    }
//
//    @Bean
//    public ILoadBalancer ribbonLoadBalancer(IClientConfig config,
//                                            ServerList<Server> serverList, ServerListFilter<Server> serverListFilter,
//                                            IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
//        if (this.propertiesFactory.isSet(ILoadBalancer.class, name)) {
//            return this.propertiesFactory.get(ILoadBalancer.class, config, name);
//        }
//        return new ZoneAwareLoadBalancer<>(config, rule, ping, serverList,
//                serverListFilter, serverListUpdater);
//    }



}
