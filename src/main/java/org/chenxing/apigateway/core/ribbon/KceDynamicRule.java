package org.chenxing.apigateway.core.ribbon;

import com.netflix.loadbalancer.AbstractServerPredicate;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PredicateBasedRule;

/**
 * KceDynamicRule
 *
 * @author Chenxing Li
 * @date 09/11/2017 21:29
 */
public class KceDynamicRule extends PredicateBasedRule {

    private AbstractServerPredicate predicate;

    public KceDynamicRule() {
        super();
        this.predicate = new KceDynamicPredicate();
    }

    @Override
    public AbstractServerPredicate getPredicate() {
        return predicate;
    }

}
