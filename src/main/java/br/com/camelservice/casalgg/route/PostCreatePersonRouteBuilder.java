//package br.com.camelservice.casalgg.route;
//
//import br.com.camelservice.casalgg.processor.RecoverObjectIdProcessor;
//import org.apache.camel.Exchange;
//import org.apache.camel.LoggingLevel;
//import org.apache.camel.builder.RouteBuilder;
//import org.apache.camel.model.dataformat.JsonLibrary;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//
//public class PostCreatePersonRouteBuilder extends RouteBuilder {
//
//    @Value("${backend.url.get}")
//    private String urlGet;
//
//
//    private RecoverObjectIdProcessor recoverObjectIdProcessor;
//
//
//    @Override
//    public void configure() throws Exception {
//
//        from("direct:TO_PersonCreatePOST")
//                .doTry()
//                .routeId("id-To_TO_PersonCreatePOST")
//                .log(LoggingLevel.INFO, "starting route get list of people")
//                .removeHeaders("CamelHttp*")
//                .removeHeaders("Authorization")
//                .process(recoverObjectIdProcessor)
//
//                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
//                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.POST))
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
