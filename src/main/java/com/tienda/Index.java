package com.tienda;

import com.tienda.controllers.UsersController;
import com.tienda.models.Producto;
import com.tienda.models.Usuario;
import com.tienda.services.ProductsServices;
import com.tienda.services.UserServices;
import com.tienda.views.MenuAdmin;
import com.tienda.views.MenuGerente;
import com.tienda.views.MenuVendedor;

import java.util.Scanner;


public class Index {
    public static void main(String[] args){

        //Usuario usuarioNew = new Usuario("admin","123456789",1);
        //UserServices.createUserService(usuarioNew);
        //Descomente las anteriores lineas solo para crear el usuario Admin en la base de datos



        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println();
            System.out.println("****Sistema gestión inventarios****");
            System.out.println("1) Iniciar sesión");
            System.out.println("0) Salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    Usuario usuario = UsersController.login();
                    if (usuario!= null){
                        System.out.println();
                        System.out.println("***********Bienvenido "+ usuario.getNombre()+" ***********");
                        switch (usuario.getRol()){
                            case 1:
                                MenuAdmin.main(usuario);
                                break;
                            case 2:
                                MenuVendedor.main(usuario);
                                break;
                            case 3:
                                MenuGerente.main(usuario);
                                break;
                            default:
                                System.out.println("Ah ocurrido un error, valide con el administrador del sistema");
                                break;
                        }
                    }else {
                        System.out.println("////////////////////////////////////////////////////");
                        System.out.println("El usuario o la contraseña ingresados no son validos");
                        System.out.println("////////////////////////////////////////////////////");
                    }
                    break;
                case "2":
                    break;
                case "0":
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
                    break;
            }

        }while (!option.equals("0"));
    }

}
