package springai.spring_ai_prompt_engineering;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClearInstructionsTests extends BaseTestClass {

    // Test to clear instructions and generate a list of made-up cars
    // Respond in JSON format with attributes: make, model, year, and color
    @Test
    public void testClearInstructionsJSON() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a JSON format
                with the following attributes: make, model, year, and color. Return the JSON string.
                """;
        String response = chat(prompt);
        System.out.println("Response: " + response);
        // Add assertions as needed
    }

    // Test to clear instructions and generate a list of made-up cars
    // Respond in XML format with attributes: make, model, year, and color
    @Test
    public void testClearInstructionsXML() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a XML format
                with the following attributes: make, model, year, and color. Return the XML string.
                """;
        String response = chat(prompt);
        System.out.println("Response: " + response);
        // Add assertions as needed
    }

    // Test to clear instructions and generate a list of made-up cars
    // Respond in YAML format with attributes: make, model, year, and color
    @Test
    public void testClearInstructionsYAML() {
        String prompt = """
                Generate a list of 4 made up cars. Provide them in a YAML format
                with the following attributes: make, model, year, and color. Return the YAML string.
                """;
        String response = chat(prompt);
        System.out.println("Response: " + response);
        // Add assertions as needed
    }

    // Prompt to ask the model to check if the conditions are met
    String directionsPrompt = """
        You will be provided with text delimited by triple quotes.
        If it contains a sequence of instructions,
        re-write those instructions in the following format:
                    
        Step 1 - ...
        Step 2 - ...
        Step N - ...
                    
        If the text does not contain a sequence of instructions, then simply write \\"No steps provided.\\"
                    
        \\"\\"\\"{text_1}\\"\\"\\"
        """;
    
    // Example text to be processed by the directionsPrompt
    String cookASteak = """
        Cooking the perfect steak is easy.
        First, remove the steak from the refrigerator and packaging. Let sit at
        room temperature for at least 30 mins.
        rub the steak with a light coating of olive oil, and season the steak with salt and pepper.
        Next, heat a pan over high heat.
        Then, add the steak to the pan and sear for 3 minutes on each side.
        Finally, let the steak rest for 5 minutes before slicing.
        Enjoy!""";
    
    
    // One more example text to be processed by the directionsPrompt
    String bookDescription = """
            Book Elon Musk
            When Elon Musk was a kid in South Africa, he was regularly beaten by bullies. One day a group pushed him down some concrete steps and kicked him until his face was a swollen ball of flesh. He was in the hospital for a week. But the physical scars were minor compared to the emotional ones inflicted by his father, an engineer, rogue, and charismatic fantasist.
                        
            His father’s impact on his psyche would linger. He developed into a tough yet vulnerable man-child, prone to abrupt Jekyll-and-Hyde mood swings, with an exceedingly high tolerance for risk, a craving for drama, an epic sense of mission, and a maniacal intensity that was callous and at times destructive.
                        
            At the beginning of 2022—after a year marked by SpaceX launching thirty-one rockets into orbit, Tesla selling a million cars, and him becoming the richest man on earth—Musk spoke ruefully about his compulsion to stir up dramas. “I need to shift my mindset away from being in crisis mode, which it has been for about fourteen years now, or arguably most of my life,” he said.""";

    
    // Test to give directions as well as the cookASteak text
    @Test
    public void testCookASteak() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt);
        Prompt prompt = promptTemplate.create(Map.of("text_1", cookASteak));
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    // Test to give directions as well as the bookDescription text
    @Test
    public void testBookDescription() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt);
        Prompt prompt = promptTemplate.create(Map.of("text_1", bookDescription));
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    // Create the steak in snoop dog style
    @Test
    public void CookSteakLikeSnoopDog() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt + "Give directions using the tone of Snoop Dog.");
        Prompt prompt = promptTemplate.create(Map.of("text_1", cookASteak));
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }

    // Create the steak in J K Rowlling style
    @Test
    public void CookSteakLikeJKRowling() {
        PromptTemplate promptTemplate = new PromptTemplate(directionsPrompt + "Give the directions using the tone, tools and imagination of JK Rowling in a Harry Potter book");
        Prompt prompt = promptTemplate.create(Map.of("text_1", cookASteak));
        ChatResponse response = chatModel.call(prompt);
        System.out.println("Response: \n" + response.getResult().getOutput().getText());
    }
}
