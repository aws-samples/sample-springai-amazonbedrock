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
- Java 21 or higher (Amazon Corretto 21 recommended)
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

### Installing Java 21 (if needed)

For convenience, you can use the provided script to install Amazon Corretto 21:

```bash
./scripts/install-corretto.sh
```

This script will:
1. Check if Java 21 is already installed
2. If not, offer to install Amazon Corretto 21 based on your operating system
3. Verify the installation

### Running with the Convenience Script

The easiest way to run the application is with the provided script:

```bash
./scripts/run-console.sh
```

This script will:
1. Check Java version and AWS credentials
2. Test AWS connectivity
3. Check if port 8080 is available
4. Build the application
5. Start the Spring Boot application

### Manual Build and Run

Alternatively, you can build and run the application manually:

```bash
# Using Maven Wrapper
./mvnw clean install
./mvnw spring-boot:run
```

## Using the Application

Once the application is running, you'll see a welcome message and a prompt to select an action:

```
Welcome to the Spring AI Amazon Bedrock Workshop!
====================================================
Selection your choice of action (chat/tempcheck/rag/quit):
```

### Available Commands

- `chat` - Simple chat with the AI model
  ```
  Selection your choice of action: chat
  Please enter your prompt:
  What is Amazon Bedrock?
  ```

- `tempcheck` - Get temperature information for a city
  ```
  Selection your choice of action: tempcheck
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

## Configuration Details

The application uses the following Spring AI configurations:

```properties
# AWS Credentials
spring.ai.bedrock.aws.region=${AWS_DEFAULT_REGION}
spring.ai.bedrock.aws.access-key=${AWS_ACCESS_KEY_ID}
spring.ai.bedrock.aws.secret-key=${AWS_SECRET_ACCESS_KEY}
spring.ai.bedrock.aws.session-token=${AWS_SESSION_TOKEN}

# Model Configuration
spring.ai.bedrock.converse.chat.options.model=anthropic.claude-3-sonnet-20240229-v1:0

# RAG Configurations
app.ai.vectorstore.path=/tmp/springai-vectorstore.json
app.ai.vectorstore.documents=classpath:Top_Movies.csv
spring.ai.model.embedding=bedrock-cohere
spring.ai.bedrock.cohere.embedding.model=cohere.embed-multilingual-v3
spring.ai.bedrock.cohere.embedding.options.input-type=SEARCH_DOCUMENT
```

## Troubleshooting

### AWS Credentials Issues

If you encounter AWS credential errors:

1. Verify your AWS credentials are correctly set
2. Ensure you have access to Amazon Bedrock service
3. Check that the required models are enabled in your Bedrock console

### Application Startup Issues

If the application fails to start:

1. Ensure Java 21 is installed and set as the active JDK
2. Check that all required environment variables are set
3. Verify Maven is working correctly by running `./mvnw -v`
4. Check if port 8080 is already in use by another application

### Model Access Issues

If you get errors about model access:

1. Verify you have enabled the required models in Amazon Bedrock:
   - Anthropic Claude 3 Sonnet (anthropic.claude-3-sonnet-20240229-v1:0)
   - Cohere Embed Multilingual v3 (cohere.embed-multilingual-v3)
