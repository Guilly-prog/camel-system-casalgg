//package br.com.camelservice.casalgg.route;
//
//
//import br.com.camelservice.casalgg.processor.RecoverObjectIdProcessor;
//import org.apache.camel.Exchange;
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.dataformat.JsonLibrary;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.http.MediaType;
//
//
//@Component
//public class GetPersonByIdRouteBuilder extends RouteBuilder {
//
//    @Value("${backend.url.get}")
//    private String urlGet;
//
//    @Override
//    public void configure() throws Exception {
////        log.info("Backend URL GET: " + urlGet);
//
//        from("direct:TO_PersonByIdGET")
//                .doTry()
//                .routeId("id-To_GetPersonById")
//                .log(LoggingLevel.INFO, "starting route get person by id")
//                .removeHeaders("CamelHttp*")
//                .removeHeaders("Authorization")
////                .process(recoverObjectIdProcessor)
//
//                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
//                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
////                .setHeader(Exchange.HTTP_PATH, simple("/${header.channel_id}/keys/${header.key_cod}"))
//                .removeHeader(Exchange.HTTP_URI)
//                .to(urlGet)
//                .removeHeaders("CamelHttp*")
//                .unmarshal().json(JsonLibrary.Jackson, Object.class)
//                .log("End get")
////                .process(responseProcessor)
//                .doCatch(Exception.class)
////                .wireTap(ErrorRouter.ERROR_LOG)
////                .process(new ErrorMessageProcessor())
//                .end();
//
//    }
//}
