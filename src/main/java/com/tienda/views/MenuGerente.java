package com.tienda.views;

import com.tienda.controllers.ProductosController;
import com.tienda.models.Usuario;
import com.tienda.views.subMenus.MenuInformes;

import java.util.Scanner;

public class MenuGerente {
    public static void main(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("Menú principal");
            System.out.println("1) Ver Productos");
            System.out.println("2) Informe de ventas");
            System.out.println("0) Cerrar sesión");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    ProductosController.listProductsController();
                    ProductosController.viewProduct();
                    break;
                case "2":
                    MenuInformes.menu();
                case "0":
                    System.out.println("Sesión finalizada.");
                    System.out.println();
                    break;
            }
        }while (!option.equals("0"));
    }
}
