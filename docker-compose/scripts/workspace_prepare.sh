#!/bin/bash

# 定义安装路径
INSTALL_PATH="/usr/local/src/software"

# 检查并安装 Git
check_git() {
    if ! command -v git &> /dev/null; then
        echo "Git 未安装，正在安装..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            if command -v brew &> /dev/null; then
                brew install git
            else
                echo "Homebrew 未安装，请先安装 Homebrew。"
                exit 1
            fi
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            if command -v apt &> /dev/null; then
                sudo apt update
                sudo apt install -y git
            elif command -v yum &> /dev/null; then
                sudo yum install -y git
            else
                echo "无法找到合适的包管理器，请手动安装 Git。"
                exit 1
            fi
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "Git 已安装，版本: $(git --version)"
    fi
}

# 检查并安装 JDK 到固定路径
check_jdk() {
    local jdk_version=$1
    JDK_PATH="$INSTALL_PATH/jdk_$jdk_version"
    if [[ ! -d "$JDK_PATH" ]]; then
        echo "JDK $jdk_version 未安装，正在安装到 $JDK_PATH..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            echo "macOS 不支持直接下载并安装 JDK 到固定路径，请手动安装。"
            exit 1
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            sudo mkdir -p "$INSTALL_PATH"
            cd "$INSTALL_PATH" || exit 1

            case "$jdk_version" in
                8)
                    wget -O "jdk_$jdk_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/jdk/jdk-8u361-linux-x64.tar.gz"
                    ;;
                11)
                    wget -O "jdk_$jdk_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/jdk/jdk-11.0.26_linux-x64_bin.tar.gz"
                    ;;
                17)
                    wget -O "jdk_$jdk_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/jdk/jdk-17.0.14_linux-x64_bin.tar.gz"
                    ;;
                21)
                    wget -O "jdk_$jdk_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/jdk/jdk-21_linux-x64_bin.tar.gz"
                    ;;
                *)
                    echo "不支持的 JDK 版本: $jdk_version"
                    exit 1
                    ;;
            esac

            sudo tar -xzf "jdk_$jdk_version.tar.gz"
            # 获取解压后的目录名称
            # shellcheck disable=SC2155
            local extracted_dir=$(tar -tzf "jdk_$jdk_version.tar.gz" | head -1 | cut -f1 -d"/")
            sudo mv "$extracted_dir" "$JDK_PATH"
            echo "JDK $jdk_version 已安装到 $JDK_PATH"
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "JDK $jdk_version 已安装在 $JDK_PATH"
    fi
}

# 检查并安装 Maven 到固定路径
check_maven() {
    local maven_version=$1
    MAVEN_PATH="$INSTALL_PATH/apache-maven_$maven_version"
    if [[ ! -d "$MAVEN_PATH" ]]; then
        echo "Maven $maven_version 未安装，正在安装到 $MAVEN_PATH..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            echo "macOS 不支持直接下载并安装 Maven 到固定路径，请手动安装。"
            exit 1
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            sudo mkdir -p "$INSTALL_PATH"
            cd "$INSTALL_PATH" || exit 1

            case "$maven_version" in
                3.6.3)
                    wget -O "apache-maven-$maven_version-bin.tar.gz" "https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz"
                    ;;
                3.8.8)
                    wget -O "apache-maven-$maven_version-bin.tar.gz" "https://archive.apache.org/dist/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.tar.gz"
                    ;;
                3.9.9)
                    wget -O "apache-maven-$maven_version-bin.tar.gz" "https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz"
                    ;;
                *)
                    echo "不支持的 Maven 版本: $maven_version"
                    exit 1
                    ;;
            esac

            sudo tar -xzf "apache-maven-$maven_version-bin.tar.gz"
            sudo mv "apache-maven-$maven_version" "$MAVEN_PATH"
            echo "Maven $maven_version 已安装到 $MAVEN_PATH"
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "Maven $maven_version 已安装在 $MAVEN_PATH"
    fi
}

# 检查并安装 Go 到固定路径
check_go() {
    local go_version=$1
    GO_PATH="$INSTALL_PATH/go_$go_version"
    if [[ ! -d "$GO_PATH" ]]; then
        echo "Go $go_version 未安装，正在安装到 $GO_PATH..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            echo "macOS 不支持直接下载并安装 Go 到固定路径，请手动安装。"
            exit 1
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            sudo mkdir -p "$INSTALL_PATH"
            cd "$INSTALL_PATH" || exit 1

            case "$go_version" in
                1.17.13)
                    wget -O "go_$go_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/go/go1.17.13.linux-amd64.tar.gz"
                    ;;
                1.18.10)
                    wget -O "go_$go_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/go/go1.18.10.linux-amd64.tar.gz"
                    ;;
                1.19.5)
                    wget -O "go_$go_version.tar.gz" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/go/go1.19.5.linux-amd64.tar.gz"
                    ;;
                *)
                    echo "不支持的 Go 版本: $go_version"
                    exit 1
                    ;;
            esac

            sudo tar -xzf "go_$go_version.tar.gz"
            # 获取解压后的目录名称
            # shellcheck disable=SC2155
            local extracted_dir=$(tar -tzf "go_$go_version.tar.gz" | head -1 | cut -f1 -d"/")
            sudo mv "$extracted_dir" "$GO_PATH"
            echo "Go $go_version 已安装到 $GO_PATH"
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "Go $go_version 已安装在 $GO_PATH"
    fi
}

# 检查并安装 Node.js 到固定路径
check_node() {
    local node_version=$1
    NODE_PATH="$INSTALL_PATH/node_$node_version"
    if [[ ! -d "$NODE_PATH" ]]; then
        echo "Node.js $node_version 未安装，正在安装到 $NODE_PATH..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            echo "macOS 不支持直接下载并安装 Node.js 到固定路径，请手动安装。"
            exit 1
        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            # Linux
            sudo mkdir -p "$INSTALL_PATH"
            cd "$INSTALL_PATH" || exit 1

            wget -O "node_$node_version.tar.gz" "https://nodejs.org/dist/$node_version/node-$node_version-linux-x64.tar.gz"
            sudo tar -xzf "node_$node_version.tar.gz"
            # 获取解压后的目录名称
            # shellcheck disable=SC2155
            local extracted_dir=$(tar -tzf "node_$node_version.tar.gz" | head -1 | cut -f1 -d"/")
            sudo mv "$extracted_dir" "$NODE_PATH"
            echo "Node.js $node_version 已安装到 $NODE_PATH"
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "Node.js $node_version 已安装在 $NODE_PATH"
    fi
}

# 检查 Docker 是否已安装并就绪
check_docker() {
    if ! command -v docker &> /dev/null; then
        echo "Docker 未安装，请手动安装 Docker。"
        exit 1
    else
        echo "Docker 已安装，版本: $(docker --version)"
        # 检查 Docker 是否正在运行
        if ! sudo docker info &> /dev/null; then
            echo "Docker 未运行，请启动 Docker 服务。"
            exit 1
        else
            echo "Docker 正在运行。"
        fi
    fi
}

# 检查并安装 SonarScanner 到固定路径
check_sonar_scanner() {
    local sonar_version="5.0.1.3006"
    SONAR_SCANNER_PATH="$INSTALL_PATH/sonar-scanner_$sonar_version"
    if [[ ! -d "$SONAR_SCANNER_PATH" ]]; then
        echo "SonarScanner $sonar_version 未安装，正在安装到 $SONAR_SCANNER_PATH..."
        if [[ "$OSTYPE" == "darwin"* ]]; then
            # macOS
            # macOS
            echo "macOS 不支持直接下载并安装 Node.js 到固定路径，请手动安装。"
            exit 1

        elif [[ "$OSTYPE" == "linux-gnu"* ]]; then
            sudo mkdir -p "$INSTALL_PATH"
            cd "$INSTALL_PATH" || exit 1

            wget -O "sonar-scanner-cli-$sonar_version-macosx.zip" "https://rany-ops.oss-cn-hangzhou.aliyuncs.com/sonar/sonar-scanner-cli-5.0.1.3006-macosx.zip"
            sudo unzip "sonar-scanner-cli-$sonar_version-macosx.zip"
            # 获取解压后的目录名称
            # shellcheck disable=SC2155
            local extracted_dir=$(unzip -l "sonar-scanner-cli-$sonar_version-macosx.zip" | grep sonar-scanner | head -1 | awk '{print $4}' | cut -d'/' -f1)
            sudo mv "$extracted_dir" "$SONAR_SCANNER_PATH"
            echo "SonarScanner $sonar_version 已安装到 $SONAR_SCANNER_PATH"
        else
            echo "不支持的操作系统。"
            exit 1
        fi
    else
        echo "SonarScanner $sonar_version 已安装在 $SONAR_SCANNER_PATH"
    fi
}

# 主函数
work_space_prepare() {
    echo "【setenv】开始运行..."
    local jdk_version=$1
    local maven_version=$2
    local node_version=$3
    local go_version=$4

    check_docker
    check_git
    check_sonar_scanner

    # 逐个判空，空则跳过执行
    if [[ -z "$jdk_version" ]]; then
        echo "JDK 版本为空，跳过 JDK 安装。"
    else
        # jdk 仅支持8，11，17，21
        if [[ "$jdk_version" != "8" && "$jdk_version" != "11" && "$jdk_version" != "17" && "$jdk_version" != "21" ]]; then
            echo "不支持的 JDK 版本: $jdk_version"
            exit 1
        fi
        echo "JDK 版本为 $jdk_version"
        check_jdk "$jdk_version"
    fi

    if [[ -z "$maven_version" ]]; then
        echo "Maven 版本为空，跳过 Maven 安装。"
    else
        # maven 仅支持3.6.3，3.8.8，3.9.9
        if [[ "$maven_version" != "3.6.3" && "$maven_version" != "3.8.8" && "$maven_version" != "3.9.9" ]]; then
            echo "不支持的 Maven 版本: $maven_version"
            exit 1
        fi
        echo "Maven 版本为 $maven_version"
        check_maven "$maven_version"
    fi

    if [[ -z "$node_version" ]]; then
        # node 版本支持范围可以根据需要调整
        echo "Node.js 版本为 $node_version"
        check_node "$node_version"
    fi

    if [[ -z "$go_version" ]]; then
        # go 仅支持1.17.13，1.18.10，1.19.5
        if [[ "$go_version" != "1.17.13" && "$go_version" != "1.18.10" && "$go_version" != "1.19.5" ]]; then
            echo "不支持的 Go 版本: $go_version"
            exit 1
        fi
        echo "Go 版本为 $go_version"
        check_go "$go_version"
    fi

    echo "【setenv】运行结束..."
}

# 主函数
work_space_prepare "$1" "$2" "$3" "$4"
