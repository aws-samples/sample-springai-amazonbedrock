# Spring AI Amazon Bedrock Application

This application demonstrates the use of Spring AI with Amazon Bedrock. It provides multiple ways to interact with AI services:

1. RESTful API (Web)
2. Interactive Console Application
3. RAG (Retrieval-Augmented Generation) capabilities

## Project Structure

- `src/main/java/org/aws/springaiamazonbedrock/`
  - `controller/` - REST controllers for web API endpoints
  - `model/` - Data models and request/response objects
  - `service/` - Service classes for AI functionality
  - `config/` - Configuration classes
  - `exception/` - Exception handling

## Prerequisites

Before running the application, ensure you have the following installed:
- Java 21 or higher
- AWS CLI configured with appropriate credentials
- Access to Amazon Bedrock service with permissions for Claude and Cohere models
- AWS environment variables set up (AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY, AWS_DEFAULT_REGION)

Note: This project includes Maven Wrapper (`mvnw`), so you don't need to install Maven separately.

## AWS Configuration

The application requires AWS credentials to access Amazon Bedrock. You can set these up in one of two ways:

### Option 1: Environment Variables

Set the following environment variables:

```bash
export AWS_ACCESS_KEY_ID="your-access-key"
export AWS_SECRET_ACCESS_KEY="your-secret-key"
export AWS_DEFAULT_REGION="your-region" # e.g., us-east-1
export AWS_SESSION_TOKEN="your-session-token" # if using temporary credentials
```

### Option 2: AWS CLI Configuration

If you have AWS CLI installed and configured:

```bash
aws configure
```

## Running the Application

### Building the Application

Before running the application, you need to build it using Maven to download dependencies and compile the code:

```bash
# Using Maven Wrapper
./mvnw clean install
```

This command will:
1. Clean any previous builds
2. Download all required dependencies
3. Compile the application
4. Run tests
5. Package the application

### Running the Built Application

After building, you can run the application in interactive console mode:

```bash
./mvnw spring-boot:run
```

This will start the Spring Boot application with an interactive console where you can:
- Chat with the AI model
- Use chat with memory capabilities
- Check temperature for cities
- Use RAG functionality for movie information

### Using the Script (with AWS credentials)

For convenience, you can use the provided script:

```bash
./scripts/run-console.sh
```

Note: Make sure to update the AWS credentials in the script before running it.

## Using the Application

Once the application is running, you'll see a welcome message and a prompt to select an action:

```
Welcome to the Spring AI Amazon Bedrock Workshop!
====================================================
Selection your choice of action (chat/chatmemory/checkTemp/rag/quit):
```

### Available Commands

- `chat` - Simple chat with the AI model
  ```
  Selection your choice of action: chat
  Please enter your prompt:
  What is Amazon Bedrock?
  ```

- `chatmemory` - Chat with memory (conversation context)
  ```
  Selection your choice of action: chatmemory
  Your chat:
  Tell me about AWS services
  ```

- `checkTemp` - Get temperature information for a city
  ```
  Selection your choice of action: checkTemp
  Please enter your city for check the temperature:
  New York
  ```

- `rag` - Ask questions about movies using RAG capabilities
  ```
  Selection your choice of action: rag
  Ask you question on the movies:
  What are the top-rated movies?
  ```

- `quit` - Exit the application

### Web API Endpoints

The application also exposes REST endpoints:

- `POST /api/chat` - Send a chat message
  ```bash
  curl -X POST http://localhost:8080/api/chat \
    -H "Content-Type: application/json" \
    -d '{"prompt":"What is Amazon Bedrock?"}'
  ```

- `POST /api/temperature/{city}` - Get temperature for a city
  ```bash
  curl -X POST http://localhost:8080/api/temperature/Paris
  ```

- `POST /api/temperature/format` - Get formatted temperature response
  ```bash
  curl -X POST http://localhost:8080/api/temperature/format \
    -H "Content-Type: application/json" \
    -d '{"city":"London"}'
  ```

## Troubleshooting

### AWS Credentials Issues

If you encounter AWS credential errors:

1. Verify your AWS credentials are correctly set
2. Ensure you have access to Amazon Bedrock service
3. Check that the required models (Claude and Cohere) are enabled in your Bedrock console

### Application Startup Issues

If the application fails to start:

1. Ensure Java 21 is installed and set as the active JDK
2. Check that all required environment variables are set
3. Verify Maven is working correctly by running `./mvnw -v`

### Model Access Issues

If you get errors about model access:

1. Verify you have enabled the required models in Amazon Bedrock:
   - Anthropic Claude 3 Sonnet
   - Cohere Embed Multilingual v3
