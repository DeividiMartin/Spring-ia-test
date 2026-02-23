package com.example.chatgpt.controller;

import com.example.chatgpt.model.MensagemDTO;
import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
