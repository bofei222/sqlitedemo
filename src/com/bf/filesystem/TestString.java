package com.bf.filesystem;

/**
 * @author bofei
 * @date 2018/9/10 14:19
 */
public class TestString {
    public static void main(String[] args) {
            String s = new String("abc");
            String s2 = new String("abc");


        String a = s.getClass().getName() + Integer.toHexString(s.hashCode());
        String b = s2.getClass().getName() + Integer.toHexString(s2.hashCode());

        System.out.println(a);
        System.out.println(b);

        System.out.println(s == s2);

        Object o = new Object();
        o.equals(o);
    }
}
