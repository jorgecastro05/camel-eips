package com.fuse.bean;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class SampleAggregator implements AggregationStrategy {

    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }
        Object oldBody = oldExchange.getIn().getBody();
        Object newBody = newExchange.getIn().getBody();
        oldExchange.getIn().setBody(oldBody + ":" + newBody);
        return oldExchange;
    }

}
