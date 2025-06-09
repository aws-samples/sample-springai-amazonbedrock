# Spring AI Amazon Bedrock Application

This application demonstrates the use of Spring AI with Amazon Bedrock. It provides multiple ways to interact with AI services:

1. RESTful API (Web)
2. Command Line Interface (CLI) using Spring Shell
3. Console Runner
4. Direct Service Invocation

## Project Structure

- `src/main/java/org/aws/springaiamazonbedrock/`
  - `cli/` - Command-line interface using Spring Shell
  - `console/` - Console runner for direct service invocation
  - `controller/` - REST controllers
  - `model/` - Data models
  - `service/` - Service classes for AI functionality
  - `config/` - Configuration classes
  - `exception/` - Exception handling

## Running the Application

### Web Mode (Default)

```bash
mvn spring-boot:run
```

This starts the application as a web service, exposing REST endpoints.

### CLI Mode (Spring Shell)

```bash
mvn spring-boot:run
```

This starts the application with Spring Shell enabled, providing an interactive command-line interface.

### Console Mode

```bash
./scripts/run-console.sh
```

This starts the application in console mode, providing a simple interactive console.

### Direct Service Invocation

```bash
./scripts/invoke-service.sh chat "Tell me about AWS Bedrock"
```

This directly invokes a service method without starting a full Spring Boot application.

## Available Commands

### Spring Shell Commands (CLI Mode)

- `chat <prompt>` - Send a chat message to the AI model
- `chat-template <city>` - Send a chat message with a city template
- `chat-format <city>` - Send a chat message with formatted request/response
- `app-info` - Display application information
- `help-commands` - Display help information about available commands

### Console Commands

- `chat <prompt>` - Send a chat message to the AI model
- `chat-template <city>` - Send a chat message with a city template
- `chat-format <city>` - Send a chat message with formatted request/response
- `help` - Display help information
- `exit`, `quit` - Exit the console

### Direct Service Invocation

```bash
./scripts/invoke-service.sh <command> [arguments]
```

Available commands:
- `chat <prompt>` - Send a chat message to the AI model
- `chat-template <city>` - Send a chat message with a city template
- `chat-format <city>` - Send a chat message with formatted request/response

## Examples

### Spring Shell (CLI Mode)

```
shell:> chat "What is Amazon Bedrock?"
[Response from AI model about Amazon Bedrock]

shell:> chat-template "New York"
[Response with temperature information for New York]
```

### Console Mode

```
console> chat Tell me about AWS Bedrock
Response: [Response from AI model about AWS Bedrock]

console> chat-template Paris
Response: [Response with temperature information for Paris]
```

### Direct Service Invocation

```bash
./scripts/invoke-service.sh chat "Tell me about AWS Bedrock"
Response: [Response from AI model about AWS Bedrock]

./scripts/invoke-service.sh chat-template "London"
Response: [Response with temperature information for London]
```
