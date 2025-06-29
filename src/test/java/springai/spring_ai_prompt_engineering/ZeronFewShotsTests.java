package springai.spring_ai_prompt_engineering;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.openai.autoconfigure.OpenAiChatProperties;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ZeronFewShotsTests extends BaseTestClass{

    @Autowired
    OpenAiChatProperties openAiChatProperties;

    // review string
    String review = """
        I get it. Everyone is buying these now after years of not caring about Stanley tumblers because of social media. The problem with viral crap like this is we get caught up in fitting in and jumping on the band wagon that we fail to see what's wrong with a product before buying it.
        THIS TUMBLER IS NOT LEAK PROOF. It's not even a little resistent to leaking. Even if you have the top fully closed and the straw taken out, the liquid inside will leak out like crazy if you tip it over even slightly. To me, if I'm going to carry around 30-40oz of hot or cold liquids then the tumbler MUST prevent said liquids from coming out. I understand it's not a water bottle, but that's a lame technicality that Stanley shouldn't cling to. At a MINIMUM the tumbler should be leak proof if I take out the straw and close the top. Furthermore, the sip top closing mechanism seems very flimsy and can be easily bended out of place, so beware of turning it too hard or especially dropping your tumbler.
        I am highly disappointed for being sucked into thinking this was a reliable tumbler that would replace others I have. Granted they are not as nice looking, but they do the job of holding AND containing the water I take with me all day to and from work in NYC.
        I do NOT recommend this tumbler and I would suggest that Stanley fix these important issues instead of focusing on more colors and patterns.""";

    // prompt
    String prompt = """
        Identify a list of emotions that the writer of the following reviews is expressing, and provide a brief summary of each review.
        
        Review: ```{review}```
        """;
    
    /**
     * Zero shot - send the model a single prompt with no hints or examples. Leverages the model's training to generate a response.
     */

    @DisplayName("Zero Shot Test without Chat Properties")
    @Test
    public void zeroShotTest() {
        // java loop 3 times
        for (int i = 0; i < 3; i++) {
            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            Prompt prompt = promptTemplate.create(Map.of("review", UUID.randomUUID() + review));
            ChatResponse response = chatModel.call(prompt);
            System.out.println("##############################################");
            System.out.println("Zero Shot Response: " + response.getResult().getOutput().getText() + "\n");
        }
    }

    @DisplayName("Zero Shot Test with Chat Properties")
    @Test
    public void zeroShotTestWithChatProperties() {
        OpenAiChatOptions chatOptions = new OpenAiChatOptions.Builder(openAiChatProperties.getOptions())
                .temperature(0.1) // default is 0.7
                .model("gpt-4-turbo") // default is gpt-3.5-turbo
                .build();
        // java loop 3 times
        for (int i = 0; i < 3; i++) {
            PromptTemplate promptTemplate = new PromptTemplate(prompt);
            Prompt prompt = promptTemplate.create(Map.of("review", UUID.randomUUID() + review), chatOptions);
            ChatResponse response = chatModel.call(prompt);
            System.out.println("##############################################");
            System.out.println("Zero Shot Response with Chat Properties: " + response.getResult().getOutput().getText() + "\n");
        }
    }


    /**
     * Few shot - send the model a few examples to help it understand the context of the prompt.
     *
     * Example from 'Language Models are Few-Shot Learners' paper: https://arxiv.org/abs/2005.14165
     */

    String whatpuPrompt = """
        A "whatpu" is a small, furry animal native to Tanzania. An example of a sentence that uses
        the word whatpu is:
        \s
        We were traveling in Africa and we saw these very cute whatpus.
        \s
        To do a "farduddle" means to jump up and down really fast. An example of a sentence that uses
                the word farduddle is:
    \s""";

    @DisplayName("Few Shot whatpu Test")
    @Test
    public void fewShotWhatpuTest() {
        PromptTemplate promptTemplate = new PromptTemplate(whatpuPrompt);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    String vacationPrompt = """
        John likes white sand beaches and warm weather.
        
        What are 5 locations John should consider for vacation?
        """;

    @DisplayName("Few Shot Vacation Test")
    @Test
    public void fewShotVacationTest() {
        PromptTemplate promptTemplate = new PromptTemplate(vacationPrompt);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    String mathPrompt = """
        2+2 = two two
        3+3 = three three
        4+5 = four five
        
        What is 5+7?
        """;

    @DisplayName("Few Shot Math Test")
    @Test
    public void fewShotMathTest() {
        PromptTemplate promptTemplate = new PromptTemplate(mathPrompt);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    @DisplayName("AI hallucination Test")
    @Test
    public void aiHallucinationTest() {
        Prompt prompt = new Prompt("\"Write sales copy for the new 'professional grade' \" +\n" + //
                        "\"Denali Advanced Toothbrush by GMC.\"");
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText() + "\n");
        // Note: This is an example of AI hallucination, where
    }



}
