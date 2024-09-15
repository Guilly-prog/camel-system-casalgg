package br.com.camelservice.casalgg.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class RecoverObjectIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String personId = exchange.getIn().getHeader("person_id", String.class);

    }
}
