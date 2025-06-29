package springai.spring_ai_prompt_engineering;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChainOfThoughtsTests extends BaseTestClass {

    // test for traditional prompts
    @Test
    public void traditionalPromptsTest() {
        String traditionalPrompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls. \s
                How many tennis balls does Roger have now? Answer in 1 word.
                """;
        Prompt prompt = new Prompt(traditionalPrompt);
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Traditional Prompt Response: " + response.getResult().getOutput().getText());
    }

    //test for chain of thought prompts
    @Test
    public void chainOfThoughtsTest() {
        String chainOfThoughtPrompt = """
        Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls. \s
        How many tennis balls does Roger have now? Answer in 1 word.
        
        A: Roger started with 5 balls. 2 cans of 3 balls each is 6 balls. 5 + 6 = 11. So Roger has 11 tennis balls.
        
        Q: The cafeteria had 23 apples originally. They used 20 apples to make lunch and bought 6 more. How many \s
        apples does the cafeteria have now? Answer in 1 word.
        """;

        Prompt prompt = new Prompt(chainOfThoughtPrompt);
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Chain of Thoughts Response: " + response.getResult().getOutput().getText());
    }


}
