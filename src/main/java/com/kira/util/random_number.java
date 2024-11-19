/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kira.util;

import java.util.Random;

/**
 *
 * @author admin
 */
public class random_number {
    public static String getRandomNumber() {
        Random rd = new Random();
        String s1 = rd.nextInt(10) + "";
        String s2 = rd.nextInt(10) + "";
        String s3 = rd.nextInt(10) + "";
        String s4 = rd.nextInt(10) + "";
        String s5 = rd.nextInt(10) + "";
        String s6 = rd.nextInt(10) + "";
        String s7 = rd.nextInt(10) + "";
         String s = s1+s2+s3+s4+s5+s6+s7;
         return s;
    }
}
