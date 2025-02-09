#!/bin/bash
# 删除已存在的 java-build-source.tar.gz 文件（如果存在）
rm -f scripts/java-build-source.tar.gz

# 创建新的 java-build-source.tar.gz 文件
tar -czvf java-build-source.tar.gz scripts/