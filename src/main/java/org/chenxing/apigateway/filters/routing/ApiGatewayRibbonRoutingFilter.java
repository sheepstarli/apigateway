package org.chenxing.apigateway.filters.routing;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.chenxing.apigateway.core.ribbon.KceRibbonCommandContext;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.List;

import static org.chenxing.apigateway.filters.support.ApiGatewayFilterConstants.CTX_LOAD_BALANCER_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.RETRYABLE_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * ApiGatewayRibbonRoutingFilter
 *
 * @author Chenxing Li
 * @date 09/11/2017 20:11
 */
public class ApiGatewayRibbonRoutingFilter extends RibbonRoutingFilter {

    public ApiGatewayRibbonRoutingFilter(ProxyRequestHelper helper, RibbonCommandFactory<?> ribbonCommandFactory, List<RibbonRequestCustomizer> requestCustomizers) {
        super(helper, ribbonCommandFactory, requestCustomizers);
    }


    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        this.helper.addIgnoredHeaders();
        try {
            KceRibbonCommandContext commandContext = buildCommandContext(context);
            ClientHttpResponse response = forward(commandContext);
            setResponse(response);
            return response;
        }
        catch (ZuulException ex) {
            throw new ZuulRuntimeException(ex);
        }
        catch (Exception ex) {
            throw new ZuulRuntimeException(ex);
        }
    }

    @Override
    protected KceRibbonCommandContext buildCommandContext(RequestContext context) {
        HttpServletRequest request = context.getRequest();

        MultiValueMap<String, String> headers = this.helper
                .buildZuulRequestHeaders(request);
        MultiValueMap<String, String> params = this.helper
                .buildZuulRequestQueryParams(request);
        String verb = getVerb(request);
        InputStream requestEntity = getRequestBody(request);
        if (request.getContentLength() < 0 && !verb.equalsIgnoreCase("GET")) {
            context.setChunkedRequestBody();
        }

        String serviceId = (String) context.get(SERVICE_ID_KEY);
        Boolean retryable = (Boolean) context.get(RETRYABLE_KEY);

        String uri = this.helper.buildZuulRequestURI(request);

        // remove double slashes
        uri = uri.replace("//", "/");

        long contentLength = request.getContentLengthLong();

        return new KceRibbonCommandContext(serviceId, verb, uri, retryable, headers, params,
                requestEntity, this.requestCustomizers, contentLength, context.get(CTX_LOAD_BALANCER_KEY));
    }
}
