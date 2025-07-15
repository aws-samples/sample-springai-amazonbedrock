#!/bin/bash

# ANSI color codes
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}Amazon Corretto 21 Installation Script${NC}"
echo "=========================================="

# Function to install Amazon Corretto 21 based on OS
install_corretto_21() {
    echo -e "${YELLOW}Attempting to install Amazon Corretto 21...${NC}"
    
    # Detect OS
    if [[ "$OSTYPE" == "linux-gnu"* ]]; then
        # Linux installation
        if command -v apt-get &> /dev/null; then
            # Debian/Ubuntu
            echo -e "${YELLOW}Detected Debian/Ubuntu system${NC}"
            echo -e "${YELLOW}Installing Amazon Corretto 21...${NC}"
            wget -O- https://apt.corretto.aws/corretto.key | sudo apt-key add -
            sudo add-apt-repository 'deb https://apt.corretto.aws stable main'
            sudo apt-get update
            sudo apt-get install -y java-21-amazon-corretto-jdk
        elif command -v yum &> /dev/null; then
            # Amazon Linux/RHEL/CentOS
            echo -e "${YELLOW}Detected Amazon Linux/RHEL/CentOS system${NC}"
            echo -e "${YELLOW}Installing Amazon Corretto 21...${NC}"
            sudo rpm --import https://yum.corretto.aws/corretto.key
            sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
            sudo yum install -y java-21-amazon-corretto-devel
        else
            echo -e "${RED}Unsupported Linux distribution. Please install Amazon Corretto 21 manually.${NC}"
            echo -e "${YELLOW}Visit: https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html${NC}"
            return 1
        fi
    elif [[ "$OSTYPE" == "darwin"* ]]; then
        # macOS installation
        echo -e "${YELLOW}Detected macOS system${NC}"
        if command -v brew &> /dev/null; then
            echo -e "${YELLOW}Installing Amazon Corretto 21 using Homebrew...${NC}"
            brew tap homebrew/cask-versions
            brew install --cask corretto21
        else
            echo -e "${YELLOW}Homebrew not found. Installing Homebrew first...${NC}"
            /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
            if command -v brew &> /dev/null; then
                echo -e "${YELLOW}Installing Amazon Corretto 21 using Homebrew...${NC}"
                brew tap homebrew/cask-versions
                brew install --cask corretto21
            else
                echo -e "${RED}Failed to install Homebrew. Please install Amazon Corretto 21 manually.${NC}"
                echo -e "${YELLOW}Visit: https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html${NC}"
                return 1
            fi
        fi
    else
        echo -e "${RED}Unsupported operating system. Please install Amazon Corretto 21 manually.${NC}"
        echo -e "${YELLOW}Visit: https://docs.aws.amazon.com/corretto/latest/corretto-21-ug/downloads-list.html${NC}"
        return 1
    fi
    
    # Verify installation
    if command -v java &> /dev/null; then
        java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
        if [ "$java_version" -ge 21 ]; then
            echo -e "${GREEN}Amazon Corretto $java_version installed successfully. ✓${NC}"
            return 0
        else
            echo -e "${RED}Java installation found but version is $java_version, not 21 or higher.${NC}"
            return 1
        fi
    else
        echo -e "${RED}Java installation failed.${NC}"
        return 1
    fi
}

# Check Java version
echo -e "${YELLOW}Checking Java version...${NC}"
if command -v java &> /dev/null; then
    java_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
    if [ "$java_version" -ge 21 ]; then
        echo -e "${GREEN}Java $java_version detected. No installation needed. ✓${NC}"
        exit 0
    else
        echo -e "${YELLOW}Java $java_version detected, but version 21 or higher is required.${NC}"
        read -p "Do you want to install Amazon Corretto 21? (y/n): " install_choice
        if [[ $install_choice == "y" || $install_choice == "Y" ]]; then
            install_corretto_21 || exit 1
        else
            echo -e "${RED}Java 21 or higher is required to run this application.${NC}"
            exit 1
        fi
    fi
else
    echo -e "${YELLOW}Java not found. Java 21 or higher is required.${NC}"
    read -p "Do you want to install Amazon Corretto 21? (y/n): " install_choice
    if [[ $install_choice == "y" || $install_choice == "Y" ]]; then
        install_corretto_21 || exit 1
    else
        echo -e "${RED}Java 21 or higher is required to run this application.${NC}"
        exit 1
    fi
fi

echo -e "${GREEN}Java 21 installation check complete.${NC}"
