package br.com.camelservice.casalgg.route;


import br.com.camelservice.casalgg.processor.RecoverObjectIdProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.http.MediaType;


@Component
public class GetArticlesByIdRouteBuilder extends RouteBuilder {

    @Value("${backend.url.get}")
    private String urlGet;

    @Autowired
    private RecoverObjectIdProcessor recoverObjectIdProcessor;

    @Override
    public void configure() throws Exception {

        from("direct:TO_ArticlesByIdGET")
                .doTry()
                .routeId("id-To_GetPersonById")
                .log(LoggingLevel.INFO, "starting route get person by id")
                .process(recoverObjectIdProcessor)
                .log(LoggingLevel.INFO, "Article id is: ${exchangeProperty.articlesId}")
//                .setBody(simple("SELECT * FROM articles WHERE id_artigo = :#articlesId"))
                .setBody(simple("SELECT * FROM artigo WHERE id_artigo = ${exchangeProperty.articlesId}"))// Use the ID in the query
                .to("jdbc:myDataSource")  // Execute the query against the database
                .log("Fetched article: ${body}")
                .log("End get")
                .doCatch(Exception.class)
                .log(LoggingLevel.ERROR, "Erro ao executar rota: ${exception.message}")
                .setBody(constant("Erro ao buscar artigo."))
                .end();

    }
}
