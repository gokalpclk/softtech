package com.baydijital.softtech.app;

/**
 * @author Gokalp on 9/6/22
 */
public class sample {
    public static void main(String[] args) {
        var line = new StringBuilder("Java");
        var anotherLine = line.append("Java");
        System.out.println(line == anotherLine);
        System.out.println(" ");
        System.out.println(anotherLine.length());
    }
}
