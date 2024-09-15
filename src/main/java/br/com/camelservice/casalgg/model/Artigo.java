package br.com.camelservice.casalgg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artigo {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String titulo;

    @JsonProperty("conteudo")
    private String conteudo;

    @JsonProperty("autor")
    private String autor;

    @JsonProperty("status")
    private String status;

}
