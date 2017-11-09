package org.chenxing.apigateway.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.TraceRepository;
import org.springframework.cloud.netflix.zuul.RoutesEndpoint;
import org.springframework.cloud.netflix.zuul.RoutesMvcEndpoint;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.cloud.netflix.zuul.filters.TraceProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayRoutesEndpointConfiguration {

	@Autowired(required = false)
	private TraceRepository traces;

	@Bean
	public RoutesEndpoint zuulEndpoint(RouteLocator routeLocator) {
		return new RoutesEndpoint(routeLocator);
	}

	@Bean
	public RoutesMvcEndpoint zuulMvcEndpoint(RouteLocator routeLocator, RoutesEndpoint endpoint) {
		return new RoutesMvcEndpoint(endpoint, routeLocator);
	}

	@Bean
	public ProxyRequestHelper proxyRequestHelper(ZuulProperties zuulProperties) {
		TraceProxyRequestHelper helper = new TraceProxyRequestHelper();
		if (this.traces != null) {
			helper.setTraces(this.traces);
		}
		helper.setIgnoredHeaders(zuulProperties.getIgnoredHeaders());
		helper.setTraceRequestBody(zuulProperties.isTraceRequestBody());
		return helper;
	}
}