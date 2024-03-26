package com.jcx.military.project.nas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NasCheck {

    /**
     #!/bin/bash

     timeout 0.000002s dd of=$1 if=/dev/zero bs=2M count=2

     if [ $? -eq 124 ]; then
     echo "false"
     else
     echo "true"
     fi

     */
    public static boolean checkNas(String nasRoot) {

        // 要执行的 Linux 命令
        String command = "sh /home/jcx/mydata/java/nas.sh " + nasRoot ;

        try {
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            System.out.println("输出结果:\n" + output.toString());
            System.out.println("退出状态码: " + exitCode);

            return Boolean.parseBoolean(output.toString());

        } catch (IOException | InterruptedException  e) {

            // 发送邮件
            return  true;
        }

    }

    public static void main(String[] args) {
        checkNas("/home/jcx/mydata/");
    }
}
