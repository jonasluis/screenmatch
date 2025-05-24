package br.com.alura.screenmatch.service;

import com.google.genai.ResponseStream;
import com.google.genai.types.Content;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import java.util.List;

public class ConsultaGemini {
  public static String obterTraducao(String texto) {
    if (texto == null || texto.isEmpty()) {
      throw new IllegalArgumentException("Texto não pode ser nulo ou vazio");
    }

    String apiKey = System.getenv("GEMINI_APIKEY");
    if (apiKey == null || apiKey.isEmpty()) {
      throw new IllegalStateException("Chave API não configurada");
    }

    try (Client client = Client.builder().apiKey(apiKey).build()) {
      String model = "gemini-2.0-flash";

      // Construindo o conteúdo com o texto de entrada
      List<Content> contents = List.of(
              Content.builder()
                      .role("user")
                      .parts(List.of(
                              Part.fromText("Traduza para português: " + texto)
                      ))
                      .build()
      );

      GenerateContentConfig config = GenerateContentConfig
              .builder()
              .responseMimeType("text/plain")
              .build();

      StringBuilder traducao = new StringBuilder();

      try (ResponseStream<GenerateContentResponse> responseStream =
                   client.models.generateContentStream(model, contents, config)) {

        for (GenerateContentResponse res : responseStream) {
          if (res.candidates().isEmpty() ||
                  res.candidates().get().get(0).content().isEmpty() ||
                  res.candidates().get().get(0).content().get().parts().isEmpty()) {
            continue;
          }

          List<Part> parts = res.candidates().get().get(0).content().get().parts().get();
          for (Part part : parts) {
            if (part.text() != null) {
              traducao.append(part.text());
            }
          }
        }
      }

      if (traducao.length() == 0) {
        throw new RuntimeException("Não foi possível obter a tradução");
      }

      return traducao.toString();
    } catch (Exception e) {
      throw new RuntimeException("Erro ao consultar Gemini: " + e.getMessage(), e);
    }
  }
}