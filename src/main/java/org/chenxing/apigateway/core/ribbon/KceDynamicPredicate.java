package org.chenxing.apigateway.core.ribbon;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.PredicateKey;
import org.springframework.cloud.consul.discovery.ConsulServer;

import java.util.Map;

/**
 * KceDynamicPredicate
 *
 * @author Chenxing Li
 * @date 10/11/2017 10:07
 */
public class KceDynamicPredicate extends AbstractServerPredicate {

    @Override
    public boolean apply(PredicateKey input) {
        Object loadBalancerKey = input.getLoadBalancerKey();
        if (loadBalancerKey == null) {
            return true;
        }
        ConsulServer consulServer = (ConsulServer) input.getServer();
        Map<String, String> metadata = consulServer.getMetadata();

        if (metadata == null || metadata.isEmpty()) {
            return true;
        }
        return metadata.containsKey(loadBalancerKey);
    }

}
