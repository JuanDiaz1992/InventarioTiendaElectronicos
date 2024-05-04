package com.tienda.views;

import com.tienda.models.Usuario;

import java.util.Scanner;

public class MenuAdmin {
    public static void main(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("Menú principal");
            System.out.println("1) Gestionar Productos");
            System.out.println("2) Reportes de ventas e inventarios");
            System.out.println("3) Gestionar usuario");
            System.out.println("0) Cerrar sesión");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "0":
                    System.out.println("Sesión finalizada.");
                    System.out.println("");
                    break;
            }
        }while (!option.equals("0"));
    }
}
