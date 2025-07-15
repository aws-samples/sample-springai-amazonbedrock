#!/bin/bash

# ANSI color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Spring AI Amazon Bedrock Application Setup${NC}"
echo "=========================================="

# Check Java version
echo -e "${YELLOW}Checking Java version...${NC}"
if command -v java &> /dev/null; then
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
    if [ "$java_version" -ge 21 ]; then
        echo -e "${GREEN}Java $java_version detected. ✓${NC}"
    else
        echo -e "${YELLOW}Java $java_version detected, but version 21 or higher is required.${NC}"
        echo -e "${YELLOW}Please run the install-corretto.sh script to install Amazon Corretto 21.${NC}"
        echo -e "${YELLOW}Command: ./scripts/install-corretto.sh${NC}"
        exit 1
    fi
else
    echo -e "${YELLOW}Java not found. Java 21 or higher is required.${NC}"
    echo -e "${YELLOW}Please run the install-corretto.sh script to install Amazon Corretto 21.${NC}"
    echo -e "${YELLOW}Command: ./scripts/install-corretto.sh${NC}"
    exit 1
fi

# Check AWS credentials
echo -e "${YELLOW}Checking AWS credentials...${NC}"
if [ -z "$AWS_ACCESS_KEY_ID" ] || [ -z "$AWS_SECRET_ACCESS_KEY" ] || [ -z "$AWS_DEFAULT_REGION" ]; then
    echo -e "${RED}Error: AWS credentials not found in environment variables.${NC}"
    echo -e "${YELLOW}Please set the following environment variables:${NC}"
    echo "export AWS_ACCESS_KEY_ID=your-access-key"
    echo "export AWS_SECRET_ACCESS_KEY=your-secret-key"
    echo "export AWS_DEFAULT_REGION=your-region"
    echo -e "${YELLOW}Optional: export AWS_SESSION_TOKEN=your-session-token (if using temporary credentials)${NC}"
    exit 1
else
    echo -e "${GREEN}AWS credentials found in environment variables. ✓${NC}"
fi

# Test AWS connectivity
echo -e "${YELLOW}Testing AWS connectivity...${NC}"
if aws sts get-caller-identity &> /dev/null; then
    echo -e "${GREEN}AWS connectivity successful. ✓${NC}"
else
    echo -e "${RED}Error: Could not connect to AWS. Please check your credentials and network connection.${NC}"
    exit 1
fi

# Check if port 8080 is in use
echo -e "${YELLOW}Checking if port 8080 is available...${NC}"
if command -v lsof &> /dev/null; then
    port_check=$(lsof -i:8080 -t)
    if [ -n "$port_check" ]; then
        echo -e "${YELLOW}Port 8080 is in use by process ID(s): $port_check${NC}"
        read -p "Do you want to kill these processes? (y/n): " kill_choice
        if [[ $kill_choice == "y" || $kill_choice == "Y" ]]; then
            echo "Killing processes on port 8080..."
            kill -9 $port_check
            echo -e "${GREEN}Processes killed. ✓${NC}"
        else
            echo -e "${YELLOW}Continuing with port 8080 in use. The application may fail to start.${NC}"
        fi
    else
        echo -e "${GREEN}Port 8080 is available. ✓${NC}"
    fi
else
    echo -e "${YELLOW}Warning: 'lsof' command not found. Cannot check if port 8080 is in use.${NC}"
fi

# Navigate to project root directory
cd "$(dirname "$0")/.." || exit

# Build the application
echo -e "${YELLOW}Building the application...${NC}"
./mvnw clean install
if [ $? -ne 0 ]; then
    echo -e "${RED}Build failed. Please check the error messages above.${NC}"
    exit 1
else
    echo -e "${GREEN}Build successful. ✓${NC}"
fi

# Run the Spring AI Amazon Bedrock application
echo -e "${YELLOW}Starting Spring AI Amazon Bedrock Application...${NC}"
./mvnw spring-boot:run
