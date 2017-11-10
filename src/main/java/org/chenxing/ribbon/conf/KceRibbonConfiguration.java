package org.chenxing.ribbon.conf;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.chenxing.apigateway.core.ribbon.KceDynamicRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.PropertiesFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * KceRibbonConfiguration
 *
 * excluded by SpringBootApplication
 * {@link org.chenxing.apigateway.ApigatewayApplication}
 *
 * @author Chenxing Li
 * @date 09/11/2017 21:18
 */
@Configuration
public class KceRibbonConfiguration {


    @Value("${ribbon.client.name}")
    private String name = "client";

    @Autowired
    private PropertiesFactory propertiesFactory;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        if (this.propertiesFactory.isSet(IRule.class, name)) {
            return this.propertiesFactory.get(IRule.class, config, name);
        }
//        ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
//        rule.initWithNiwsConfig(config);
        KceDynamicRule rule = new KceDynamicRule();
//        rule.initWithNiwsConfig(config);
        return rule;
    }

}
