package br.com.camelservice.casalgg.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class RecoverObjectIdProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        String articlesId = exchange.getIn().getHeader("articlesId", String.class);

        // Set the extracted ID as a property or a header for further usage in the route
        exchange.setProperty("articlesId", articlesId);
    }
}
