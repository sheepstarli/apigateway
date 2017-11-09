package org.chenxing.apigateway.core.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import org.apache.http.client.HttpClient;
import org.springframework.cloud.netflix.ribbon.ServerIntrospector;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpRequest;
import org.springframework.cloud.netflix.ribbon.apache.RibbonApacheHttpResponse;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;

/**
 * KceRibbonLoadBalancingHttpClient
 *
 * @author Chenxing Li
 * @date 09/11/2017 19:07
 */
public class KceRibbonLoadBalancingHttpClient extends RibbonLoadBalancingHttpClient {

    @Deprecated
    public KceRibbonLoadBalancingHttpClient() {
        super();
    }

    @Deprecated
    public KceRibbonLoadBalancingHttpClient(final ILoadBalancer lb) {
        super(lb);
    }

    public KceRibbonLoadBalancingHttpClient(IClientConfig config, ServerIntrospector serverIntrospector) {
        super(config, serverIntrospector);
    }

    public KceRibbonLoadBalancingHttpClient(HttpClient delegate, IClientConfig config, ServerIntrospector serverIntrospector) {
        super(delegate, config, serverIntrospector);
    }

    @Override
    protected void customizeLoadBalancerCommandBuilder(RibbonApacheHttpRequest request, IClientConfig config, LoadBalancerCommand.Builder<RibbonApacheHttpResponse> builder) {
        builder.withServerLocator(request.getLoadBalancerKey());
    }
}
