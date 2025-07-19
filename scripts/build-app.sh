#!/bin/bash

# ANSI color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Spring AI Amazon Bedrock Application Build${NC}"
echo "=========================================="

# Check Java version
echo -e "${YELLOW}Checking Java version...${NC}"
if command -v java &> /dev/null; then
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
    if [ "$java_version" -ge 21 ]; then
        echo -e "${GREEN}Java $java_version detected. ✓${NC}"
    else
        echo -e "${YELLOW}Java $java_version detected, but version 21 or higher is required.${NC}"
        echo -e "${YELLOW}Please run the install-java21.sh script to install Amazon Corretto 21.${NC}"
        echo -e "${YELLOW}Command: ./scripts/install-java21.sh${NC}"
        exit 1
    fi
else
    echo -e "${YELLOW}Java not found. Java 21 or higher is required.${NC}"
    echo -e "${YELLOW}Please run the install-java21.sh script to install Amazon Corretto 21.${NC}"
    echo -e "${YELLOW}Command: ./scripts/install-java21.sh${NC}"
    exit 1
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

echo -e "${GREEN}Application build completed successfully.${NC}"
