package br.com.camelservice.casalgg.route;

import br.com.camelservice.casalgg.processor.RecoverObjectIdProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;


@Component
public class GetListOfArticlesRouteBuilder extends RouteBuilder {

    @Value("${backend.url.get}")
    private String urlGet;


    private RecoverObjectIdProcessor recoverObjectIdProcessor;


    @Override
    public void configure() throws Exception {

        from("direct:getAllArtigos")
                .doTry()
                .routeId("id-To_GetListOfPeople")
                .log(LoggingLevel.INFO, "starting route get list of people")

                .setBody(simple("SELECT \n" +
                        "    sessao.titulo AS Sessao, \n" +
                        "    subsessao.titulo AS 'Sub-Sessao', \n" +
                        "    artigo.titulo AS Artigo, \n" +
                        "    artigo.conteudo AS Conteudo, \n" +
                        "    artigo.autor AS Autor, \n" +
                        "    artigo.data_publicacao AS 'Data de Publicacao'\n" +
                        "FROM \n" +
                        "    sessao\n" +
                        "JOIN \n" +
                        "    subsessao ON sessao.id_sessao = subsessao.id_sessao\n" +
                        "JOIN \n" +
                        "    artigo ON subsessao.id_subsessao = artigo.id_subsessao\n" +
                        "ORDER BY \n" +
                        "    sessao.titulo, subsessao.titulo, artigo.titulo;"))

                .to("jdbc:myDataSource")
                .log("Artigo: ${body}")

//                .unmarshal().json(JsonLibrary.Jackson, Object.class)
                .log("End get")
                .doCatch(Exception.class)
                .log(LoggingLevel.ERROR, "Erro ao executar rota: ${exception.message}")
                .setBody(constant("Erro ao buscar artigos."))
                .end();


    }
}
