package com.BFF_paye_ton_kawa.product;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Unsafe {
    public static void main(String[] args) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"));
        Object obj = ois.readObject(); // This should trigger a vulnerability
    }
}
