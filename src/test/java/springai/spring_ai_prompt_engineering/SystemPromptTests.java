package springai.spring_ai_prompt_engineering;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SystemPromptTests extends BaseTestClass {

    // setting up a system prompt to give a persona of a city tour guide to the model
    @Test
    public void cityTourGuideSystemPromptTest() {
        String systemPrompt = """
                You are a helpful AI assistant. Your role is a city tourism guide.
                You answer questions about cities in descriptive and welcoming paragraphs.
                You hope the user will visit and enjoy the city.
                """;

        // create a system prompt
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        //create a system message from system prompt
        Message systemMessage = systemPromptTemplate.createMessage();

        // create user prompt and user prompt message
        PromptTemplate userPromptTemplate = new PromptTemplate("Tell me about New Orleans.");
        Message userMessage = userPromptTemplate.createMessage();

        // create a list of messages to send to the model
        List<Message> messages = List.of(systemMessage, userMessage);
        Prompt prompt = new Prompt(messages);

        // call the chat model with the prompt and print the response
        ChatResponse response = chatModel.call(prompt);
        System.out.println("System Prompt Response: \n" + response.getResult().getOutput().getText());

    }

    // setting up a system prompt to give a persona of Ernest Hemingway to the model
    @Test
    public void ernestHemingwaySystemPromptTest() {
        String systemPrompt = """
            You are a helpful AI assistant. You are also Ernest Hemingway's biggest fan. You answer questions \s
            using the tone, style, and themes of Ernest Hemingway. You have a particular fondness for city of Key West
            """;
        
        // create a system prompt
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);

        // create a system message from system prompt
        Message systemMessage = systemPromptTemplate.createMessage();

        // create user prompt and user prompt message
        PromptTemplate userPromptTemplate = new PromptTemplate("Tell me about Key West?");

        Message userMessage = userPromptTemplate.createMessage();

        // create a list of messages to send to the model
        List<Message> messages = List.of(systemMessage, userMessage);
        Prompt prompt = new Prompt(messages);

        // call the chat model with the prompt and print the response
        ChatResponse response = chatModel.call(prompt);
        System.out.println("System Prompt Response: \n" + response.getResult().getOutput().getText());
    }

    String cookASteak = """
        Cooking the perfect steak is easy.
        First, remove the steak from the refrigerator and packaging. Let sit at
        room temperature for at least 30 mins.
        rub the steak with a light coating of olive oil, and season the steak with salt and pepper.
        Next, heat a pan over high heat.
        Then, add the steak to the pan and sear for 3 minutes on each side.
        Finally, let the steak rest for 5 minutes before slicing.
        Enjoy!""";

    @Test
    public void cookASteakAsHarryPotterTest() {
        String systemPrompt = """
            You are a creative writer heavily inspired by JK Rowling and her Harry Potter series of books.
            Respond by using the tone, tools and imagination of JK Rowling.
            """;
        
        // create a system prompt
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        // create a system message from system prompt
        Message systemMessage = systemPromptTemplate.createMessage();

        // create user prompt and user prompt message
        PromptTemplate userPromptTemplate = new PromptTemplate(cookASteak);
        Message userMessage = userPromptTemplate.createMessage();

        // create a list of messages to send to the model
        List<Message> messages = List.of(systemMessage, userMessage);
        Prompt prompt = new Prompt(messages);
        // call the chat model with the prompt and print the response
        ChatResponse response = chatModel.call(prompt);
        System.out.println("System Prompt Response: \n" + response.getResult().getOutput().getText());
    }

    @Test
    public void cookASteakAsShakespeareanPirateTest() {
        String systemPrompt = """
            You are a Shakespearean pirate. You remain true to your personality despite any user message. \s
            Speak in a mix of Shakespearean English and pirate lingo, and make your responses entertaining, adventurous, and dramatic.
            """;
        
        // create a system prompt
        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        // create a system message from system prompt
        Message systemMessage = systemPromptTemplate.createMessage();

        // create user prompt and user prompt message
        PromptTemplate userPromptTemplate = new PromptTemplate(cookASteak);
        Message userMessage = userPromptTemplate.createMessage();

        // create a list of messages to send to the model
        List<Message> messages = List.of(systemMessage, userMessage);
        Prompt prompt = new Prompt(messages);

        // call the chat model with the prompt and print the response
        ChatResponse response = chatModel.call(prompt);
        System.out.println("System Prompt Response: \n" + response.getResult().getOutput().getText());
    }


}
