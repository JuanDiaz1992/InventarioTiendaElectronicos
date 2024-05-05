package com.tienda.utils;

import java.util.Scanner;

public class PressEnter {
    public static void pressEnter(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Presiona Enter para continúar");
        scanner.nextLine();
    }
}
