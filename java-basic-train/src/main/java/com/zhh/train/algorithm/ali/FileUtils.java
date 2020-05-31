package com.zhh.train.algorithm.ali;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * 请用java实现以下shell脚本的功能（找出包含login的，去重，排序）
 * cat application.log | grep "Login" | uniq -c | sort -nr
 * @date : 2020/5/13 7:36 下午
 */
public class FileUtils {
    public static void main(String[] args) throws IOException {
        String data = FileUtils.class.getResource("/application.log").getFile();
        RandomAccessFile file = new RandomAccessFile(data, "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);
        int read = fileChannel.read(buffer);
        StringBuilder sb = new StringBuilder();
        String pattern = "login";
        while (read != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                char x = (char) buffer.get();
                if (x == ' ' || x == '\n' || x == '\t') {
                    String word = sb.toString();
                    sb = new StringBuilder();
                    if (word.toLowerCase().indexOf(pattern) != -1) {
                        System.out.println(word);
                    }
                } else {
                    sb.append(x);
                }
            }
            buffer.clear();
            read = fileChannel.read(buffer);
        }
        fileChannel.close();
    }

    private static void answer1() throws IOException {
        String file1 = FileUtils.class.getResource("/application.log").getFile();
        File file = new File(file1);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        Set<String> result = new TreeSet<String>();
        String pattern = "login";
        while ((line = bufferedReader.readLine()) != null) {
            String[] split = line.split("\\s");
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if (s.toLowerCase().indexOf(pattern) >= 0) {
                    result.add(split[i]);
                }
            }
        }
        System.out.println(result);
    }
}
