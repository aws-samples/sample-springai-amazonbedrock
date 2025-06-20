package org.aws.springaiamazonbedrock;

import org.aws.springaiamazonbedrock.service.RagService;
import org.aws.springaiamazonbedrock.service.SimpleChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class SpringAiAmazonBedrockApplication implements CommandLineRunner {

    @Autowired
    private SimpleChatService simpleChatService;

    @Autowired
    private RagService ragService;

    public static void main(String[] args) {
        SpringApplication.run(SpringAiAmazonBedrockApplication.class, args);
    }

    public void run(String... args) {
        System.out.println("Welcome to the Spring AI Amazon Bedrock Workshop!");
        System.out.println("====================================================");

        while(true) {
            System.out.println("Selection your choice of action (chat/checkTemp/rag/quit):");
            Scanner scanner = new Scanner(System.in);
            String action = scanner.nextLine();
            System.out.println("""
                Thank you for selection. Initiating {action} service for you""");
            if (action.equals("chat")) {
                System.out.println("Please enter your prompt:");
                String prompt = scanner.nextLine();
                System.out.println("Response: " + simpleChatService.chat(prompt));
            } else if (action.equals("checkTemp")) {
                System.out.println("Please enter your city for check the temperature:");
                String city = scanner.nextLine();
                System.out.println("Response: " + simpleChatService.chatWithTemplate(city));
            } else if (action.equals("rag")) {
                System.out.println("Ask you question on the movies:");
                String prompt = scanner.nextLine();
                System.out.println("Response: " + ragService.getResponse(prompt));
            } else if (action.equals("quit")) {
                System.out.println("Thank you for using the Spring AI Amazon Bedrock Workshop. Goodbye!");
                break;
            } else {
                System.out.println("Invalid action. Please try again.");
            }
        }
    }

}
