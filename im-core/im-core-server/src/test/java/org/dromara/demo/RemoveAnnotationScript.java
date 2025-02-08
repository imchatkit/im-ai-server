package org.dromara.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveAnnotationScript {
    public static void main(String[] args) {
        String directoryPath = "D:\\code\\github\\im-ai-cloud\\im-ai-server\\im-core\\im-core-server\\src\\main\\java\\com\\imai\\core\\api\\controller";

        try {
            Files.walk(Paths.get(directoryPath))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().endsWith(".java"))
                .forEach(RemoveAnnotationScript::processFile);

            System.out.println("注解删除完成！");
        } catch (IOException e) {
            System.err.println("处理文件时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processFile(Path filePath) {
        try {
            // 读取所有行
            List<String> lines = Files.readAllLines(filePath);

            // 过滤掉包含@SaCheckPermission的行
            List<String> newLines = lines.stream()
                .filter(line -> !line.trim().startsWith("@SaCheckPermission"))
                .collect(Collectors.toList());

            // 只有当有行被删除时才写回文件
            if (lines.size() != newLines.size()) {
                Files.write(filePath, newLines);
                System.out.println("已处理文件: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("处理文件 " + filePath + " 时发生错误: " + e.getMessage());
        }
    }
}
