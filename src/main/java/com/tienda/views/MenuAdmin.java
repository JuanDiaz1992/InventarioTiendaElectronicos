package com.tienda.views;

import com.tienda.controllers.ProductosController;
import com.tienda.controllers.UsersController;
import com.tienda.models.Producto;
import com.tienda.models.Usuario;
import com.tienda.utils.PressEnter;
import com.tienda.views.subMenus.MenuInformes;

import java.util.Scanner;

public class MenuAdmin {
    public static void main(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("Menú principal");
            System.out.println("1) Gestionar Productos");
            System.out.println("2) Reportes de ventas");
            System.out.println("3) Gestionar usuario");
            System.out.println("0) Cerrar sesión");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    String optionProduct;
                    do {
                        System.out.println();
                        System.out.println("***Gestión de Productos***");
                        System.out.println("1) Ver todos los productos");
                        System.out.println("2) Crear nuevo producto");
                        System.out.println("3) Eliminar un producto");
                        System.out.println("4) Modificar un producto");
                        System.out.println("0) Volver atrás");
                        System.out.print("Elija una opción: ");
                        optionProduct = scanner.nextLine();
                        switch (optionProduct){
                            case "1":
                                ProductosController.listProductsController();
                                ProductosController.viewProduct();
                                break;
                            case "2":
                                ProductosController.createProductController();
                                PressEnter.pressEnter();
                                break;
                            case "3":
                                ProductosController.deleteProductController();
                                PressEnter.pressEnter();
                                break;
                            case "4":
                                ProductosController.updateProductController();
                                PressEnter.pressEnter();
                                break;
                            default:
                                System.out.println("Elija una opción valida");
                        }

                    }while (!optionProduct.equals("0"));
                    break;
                case "2":
                    MenuInformes.menu();
                    break;
                case "3":
                    String optinUser;
                    do {
                        System.out.println();
                        System.out.println("***Gestión de usuarios***");
                        System.out.println("1) Ver todos los usuarios");
                        System.out.println("2) Crear nuevo usuario");
                        System.out.println("3) Eliminar un usuario");
                        System.out.println("4) Modificar un usuario");
                        System.out.println("0) Volver atrás");
                        System.out.print("Elija una opción: ");
                        optinUser = scanner.nextLine();
                        switch (optinUser){
                            case "1":
                                UsersController.listUserController();
                                PressEnter.pressEnter();
                                break;
                            case "2":
                                UsersController.createUserController();
                                PressEnter.pressEnter();
                                break;
                            case "3":
                                UsersController.deleteUser(usuario);
                                PressEnter.pressEnter();
                                break;
                            case "4":
                                UsersController.updateUserController();
                                PressEnter.pressEnter();
                                break;
                            default:
                                System.out.println("Elija una opción valida");
                        }

                    }while (!optinUser.equals("0"));
                    break;
                case "0":
                    System.out.println("Sesión finalizada.");
                    System.out.println();
                    break;
            }
        }while (!option.equals("0"));
    }
}
