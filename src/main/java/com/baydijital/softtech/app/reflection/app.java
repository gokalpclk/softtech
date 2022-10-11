package com.baydijital.softtech.app.reflection;

/**
 * @author Gokalp on 8/9/22
 */
public class app {
    public static void main(String[] args) {
        Class<?> clazz;
        try {
            clazz = Class.forName("com.baydijital.softtech.app.reflection.CustomerDto");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("sasad");
        }

        System.out.println(clazz);
    }
}
