package com.fuse.bean;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BodyAppenderAggregator implements AggregationStrategy {
    private static final Logger LOG = LoggerFactory.getLogger(BodyAppenderAggregator.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        LOG.info("\n\nOld: " + (oldExchange == null ? "null" : oldExchange.getIn().getBody()) + "\nnew: " + newExchange.getIn().getBody());
        if (oldExchange == null) {
            Exchange exchange = new DefaultExchange(newExchange);
            exchange.getIn().setBody(newExchange.getIn().getBody());
            return exchange;
        } else {
            String newBody =
                    oldExchange.getIn().getBody(String.class)
                            + newExchange.getIn().getBody(String.class);
            oldExchange.getIn().setBody(newBody, String.class);
            return oldExchange;
        }
    }
}