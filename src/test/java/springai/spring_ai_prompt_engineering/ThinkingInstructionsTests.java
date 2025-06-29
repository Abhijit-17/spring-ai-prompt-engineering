package springai.spring_ai_prompt_engineering;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThinkingInstructionsTests extends BaseTestClass {
    // Test to summarize a story, translate it into Hindi, list names, and output JSON
        String story = """
        In a charming village, siblings Jack and Jill set out on
        a quest to fetch water from a hilltop well.
        As they climbed, singing joyfully, misfortune
        struck—Jack tripped on a stone and tumbled
        down the hill, with Jill following suit. 
        Though slightly battered, the pair returned home to
        comforting embraces. Despite the mishap, 
        their adventurous spirits remained undimmed, and they 
        continued exploring with delight.
        """;

    String prompt = """
            Perform the following actions:
            1 - Summarize the following text delimited by triple
            backticks with 1 sentence.
            2 - Translate the summary into Hindi.
            3 - List each name in the Hindi summary.
            4 - Output a json object that contains the following
            keys: hindi_summary, num_names.
            Separate your answers with line breaks.
            Text:
            ```{text}```
            """;
    @Test
    void testStorySummarization() {
        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt prompt = promptTemplate.create(Map.of("text", story));
        String response = chatModel.call(prompt).getResult().getOutput().getText();
        System.out.println("Response: \n" + response);
    }
}
