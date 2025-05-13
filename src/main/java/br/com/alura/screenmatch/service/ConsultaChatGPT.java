package br.com.alura.screenmatch.service;

import com.theokanning.openai.OpenAiHttpException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
  public static String obterTraducao(String texto) {
    OpenAiService service = new OpenAiService("Digite sua apikey");

    try {
    CompletionRequest requisicao = CompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .prompt("traduza para o português o texto: " + texto)
            .maxTokens(1000)
            .temperature(0.7)
            .build();


    var resposta = service.createCompletion(requisicao);
    return resposta.getChoices().get(0).getText();
    } catch (OpenAiHttpException e) {
      System.err.println("Erro ao chamar a API da OpenAI: " + e.getMessage());
      return "Tradução indisponível (limite de API excedido)";
    }
  }
}
