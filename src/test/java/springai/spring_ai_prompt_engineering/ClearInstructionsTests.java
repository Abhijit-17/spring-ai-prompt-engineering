package springai.spring_ai_prompt_engineering;

import org.junit.jupiter.api.Test;
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

}
