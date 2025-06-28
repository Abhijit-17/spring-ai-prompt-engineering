package springai.spring_ai_prompt_engineering;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTestClass {

    @Autowired
    ChatModel chatModel;

    String chat(String prompt) {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt promptResponse = promptTemplate.create();

        return chatModel.call(promptResponse).getResult().getOutput().getText();
    }
}
