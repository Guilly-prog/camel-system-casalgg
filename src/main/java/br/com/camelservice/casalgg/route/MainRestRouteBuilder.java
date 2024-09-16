package br.com.camelservice.casalgg.route;


import br.com.camelservice.casalgg.model.Article;
import br.com.camelservice.casalgg.service.ArtigoService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;



@Component
public class MainRestRouteBuilder extends RouteBuilder {


    @Autowired
    private ArtigoService artigoService;

    @Override
    public void configure() throws Exception {
        // Configuração REST para expor os endpoints
        restConfiguration()
                .bindingMode(RestBindingMode.json) // Formato JSON para request/response
                .dataFormatProperty("prettyPrint", "true") // Pretty print para a saída
                .apiContextPath("/api-doc") // Swagger docs

                .apiContextRouteId("casalgg")
                .component("servlet")
                .apiProperty("api.title", "casalgg person api")
                .apiProperty("api.description", "casalgg person")
                .apiProperty("api.version", "1.0.0")
//                .apiProperty("host", "localhost")
//                .apiProperty("port", "8080")
                .apiProperty("schemes", "http")

                .apiProperty("api.contact.name", "Guilherme Lopes")
                .apiProperty("api.contact.email", "glopes865@gmail.com");


        rest("/articles")
                .securityDefinitions()
                .apiKey("Authorization", "Bearer [replace with a valid token]").withHeader("Authorization").end()
                .end()

                .get()
                .id("id-MainRoute-list-all-articles")
                .description("Retrieves a list of all articles if their status are 'publicado'")
                .bindingMode(RestBindingMode.json)
                .skipBindingOnErrorCode(true)
                .security("Authorization")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(Article.class)
                .responseMessage()
                .code(200).message("OK")
                .endResponseMessage()
                .responseMessage()
                .code(400).message("Bad Request")
                .endResponseMessage()
                .responseMessage()
                .code(401).message("Unauthorized")
                .endResponseMessage()
                .responseMessage()
                .code(403).message("Forbidden")
                .endResponseMessage()
                .responseMessage()
                .code(404).message("Not Found")
                .endResponseMessage()
                .responseMessage()
                .code(406).message("Not Acceptable")
                .endResponseMessage()
                .responseMessage()
                .code(415).message("Unsupported Media Type")
                .endResponseMessage()
                .responseMessage()
                .code(422).message("Unprocessable Entity")
                .endResponseMessage()
                .responseMessage()
                .code(500).message("Internal Server Error")
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.GATEWAY_TIMEOUT.value()).message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
                .endResponseMessage()
                .to("direct:getAllArtigos");

        // Get a person by ID
        rest("/articles/{articlesId}")
                .securityDefinitions()
                .apiKey("Authorization", "Bearer [replace with a valid token]").withHeader("Authorization").end()
                .end()

                .get()
                .id("id-MainRoute-articles-getbyid")
                .description("Retrieve an articles by its specific id")
                .bindingMode(RestBindingMode.json)
                .skipBindingOnErrorCode(true)
                .security("Authorization")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(Article.class)
                .param()
                .name("articlesId")
                .type(RestParamType.path)
                .dataType("String")
                .endParam()
                .responseMessage()
                .code(200).message("OK")
                .endResponseMessage()
                .responseMessage()
                .code(400).message("Bad Request")
                .endResponseMessage()
                .responseMessage()
                .code(401).message("Unauthorized")
                .endResponseMessage()
                .responseMessage()
                .code(403).message("Forbidden")
                .endResponseMessage()
                .responseMessage()
                .code(404).message("Not Found")
                .endResponseMessage()
                .responseMessage()
                .code(406).message("Not Acceptable")
                .endResponseMessage()
                .responseMessage()
                .code(415).message("Unsupported Media Type")
                .endResponseMessage()
                .responseMessage()
                .code(422).message("Unprocessable Entity")
                .endResponseMessage()
                .responseMessage()
                .code(500).message("Internal Server Error")
                .endResponseMessage()
                .responseMessage()
                .code(HttpStatus.GATEWAY_TIMEOUT.value()).message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
                .endResponseMessage()
                .to("direct:TO_ArticlesByIdGET");

//
//                // Endpoint para recuperar um artigo pelo ID (GET)
//                .get("/{id}")
//                .to("direct:getArtigoById")
//
//                // Endpoint para criar um novo artigo (POST)
//                .post("/")
//                .type(Artigo.class)
//                .to("direct:createArtigo")
//
//                // Endpoint para atualizar um artigo (PUT)
//                .put("/{id}")
//                .type(Artigo.class)
//                .to("direct:updateArtigo")
//
//                // Endpoint para deletar um artigo pelo ID (DELETE)
//                .delete("/{id}")
//                .to("direct:deleteArtigo");
//
//        // Rotas para as operações CRUD
//
//        // Recuperar todos os artigos
//        from("direct:getAllArtigos")
//                .routeId("getAllArtigosRoute")
//                .log("Recebendo todos os artigos")
//                .to("bean:artigoService?method=findAll");
//
//        // Recuperar um artigo por ID
//        from("direct:getArtigoById")
//                .routeId("getArtigoByIdRoute")
//                .log("Recebendo artigo com ID: ${header.id}")
//                .to("bean:artigoService?method=findById(${header.id})");
//
//        // Criar um novo artigo
//        from("direct:createArtigo")
//                .routeId("createArtigoRoute")
//                .log("Criando um novo artigo: ${body}")
//                .to("bean:artigoService?method=create");
//
//        // Atualizar um artigo existente
//        from("direct:updateArtigo")
//                .routeId("updateArtigoRoute")
//                .log("Atualizando artigo com ID: ${header.id}")
//                .to("bean:artigoService?method=update(${header.id}, ${body})");
//
//        // Deletar um artigo por ID
//        from("direct:deleteArtigo")
//                .routeId("deleteArtigoRoute")
//                .log("Deletando artigo com ID: ${header.id}")
//                .to("bean:artigoService?method=delete(${header.id})");
//    }
//}


//        rest("/articles")
//                .securityDefinitions()
//                .apiKey("Authorization", "Bearer [replace with a valid token]").withHeader("Authorization").end()
//                .end()
//
//                .post()
//                .id("id-MainRoute-person-post")
//                .description("Create a new person")
//                .bindingMode(RestBindingMode.auto)
//                .skipBindingOnErrorCode(true)
//                .type(Person.class)
//                .security("Authorization")
//                .produces(MediaType.APPLICATION_JSON_VALUE)
//                .outType(Person.class)
//                .responseMessage()
//                .code(200).message("OK")
//                .endResponseMessage()
//                .responseMessage()
//                .code(400).message("Bad Request")
//                .endResponseMessage()
//                .responseMessage()
//                .code(401).message("Unauthorized")
//                .endResponseMessage()
//                .responseMessage()
//                .code(403).message("Forbidden")
//                .endResponseMessage()
//                .responseMessage()
//                .code(404).message("Not Found")
//                .endResponseMessage()
//                .responseMessage()
//                .code(406).message("Not Acceptable")
//                .endResponseMessage()
//                .responseMessage()
//                .code(415).message("Unsupported Media Type")
//                .endResponseMessage()
//                .responseMessage()
//                .code(422).message("Unprocessable Entity")
//                .endResponseMessage()
//                .responseMessage()
//                .code(500).message("Internal Server Error")
//                .endResponseMessage()
//                .responseMessage()
//                .code(HttpStatus.GATEWAY_TIMEOUT.value()).message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
//                .endResponseMessage()
//                .to("direct:TO_PersonCreatePOST");


        // Get a person by ID
//        rest("/person/{personId}")
//                .securityDefinitions()
//                .apiKey("Authorization", "Bearer [replace with a valid token]").withHeader("Authorization").end()
//                .end()

//                .get()
//                .id("id-MainRoute-person-getbyid")
//                .description("Retrieve a person by its specific id")
//                .bindingMode(RestBindingMode.json)
//                .skipBindingOnErrorCode(true)
//                .security("Authorization")
//                .produces(MediaType.APPLICATION_JSON_VALUE)
//                .outType(Person.class)
//                .param()
//                .name("personId")
//                .type(RestParamType.path)
//                .dataType("String")
//                .endParam()
//                .responseMessage()
//                .code(200).message("OK")
//                .endResponseMessage()
//                .responseMessage()
//                .code(400).message("Bad Request")
//                .endResponseMessage()
//                .responseMessage()
//                .code(401).message("Unauthorized")
//                .endResponseMessage()
//                .responseMessage()
//                .code(403).message("Forbidden")
//                .endResponseMessage()
//                .responseMessage()
//                .code(404).message("Not Found")
//                .endResponseMessage()
//                .responseMessage()
//                .code(406).message("Not Acceptable")
//                .endResponseMessage()
//                .responseMessage()
//                .code(415).message("Unsupported Media Type")
//                .endResponseMessage()
//                .responseMessage()
//                .code(422).message("Unprocessable Entity")
//                .endResponseMessage()
//                .responseMessage()
//                .code(500).message("Internal Server Error")
//                .endResponseMessage()
//                .responseMessage()
//                .code(HttpStatus.GATEWAY_TIMEOUT.value()).message(HttpStatus.GATEWAY_TIMEOUT.getReasonPhrase())
//                .endResponseMessage()
//                .to("direct:TO_PersonByIdGET");

        // Update a person by ID
//        rest("/api")
//                .put("/persons/{id}")
//                .type(Person.class)
//                .route()
//                .process(exchange -> {
//                    Long id = exchange.getIn().getHeader("id", Long.class);
//                    Person person = exchange.getIn().getBody(Person.class);
//                    person.setId(id);
//                    personMap.put(id, person);
//                    exchange.getMessage().setBody(person);
//                })
//                .endRest();

        // Delete a person by ID
//        rest("/api")
//                .delete("/persons/{id}")
//                .route()
//                .process(exchange -> {
//                    Long id = exchange.getIn().getHeader("id", Long.class);
//                    personMap.remove(id);
//                    exchange.getMessage().setBody(null);
//                })
//                .endRest();

    }
}