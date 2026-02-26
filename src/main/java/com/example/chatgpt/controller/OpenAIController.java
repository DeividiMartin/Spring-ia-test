package com.example.chatgpt.controller;

import com.example.chatgpt.model.MensagemDTO;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class OpenAIController {

    @Autowired
    ChatLanguageModel model;

    @Autowired
    Environment env;

    @PostMapping("/chat")
    public String conversarComGPT(@RequestBody MensagemDTO mensagem) {

        return model.generate(mensagem.mensagem());
    }

    @PostMapping("/chat4")
    public String conversarComGPT4(@RequestBody MensagemDTO mensagem) {

        ChatLanguageModel model = new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey("openai.api.key")
                .temperature(0.5)
                .modelName("gpt-4o").build();
        return model.generate(mensagem.mensagem());
    }

    @PostMapping("/tamplate")
    public String conversarComGPT4Tamplate (@RequestBody MensagemDTO mensagem) {
        PromptTemplate tamplate = PromptTemplate.from("Você é um programador Java. Vovê deve responder essa pergunta: {{question}}");
         Map<String, Object > mapa = new HashMap<String, Object>();
        mapa.put("question", mensagem.mensagem());
        Prompt prompt = tamplate.apply(mapa);
        return model.generate(prompt.text());
    }

    @PostMapping("/review")
    public String revisarCodigo(@RequestBody MensagemDTO mensagem) {

        String prompt = """
            Você é um especialista em Java backend.
            Analise o seguinte código e sugira melhorias:
            
            %s
            """.formatted(mensagem.mensagem());

        return model.generate(prompt);
    }


}
