package com.tienda;

import com.tienda.models.Usuario;
import com.tienda.services.UserServices;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){

        //Usuario usuarioNew = new Usuario("Admin","123456789",1);
        //UserServices.createUserService(usuarioNew);
        //Descomente las anteriores lineas solo para crear el usuario Admin en la base de datos

        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("***Sistema gestión inventarios****");
            System.out.println("1) Loguearse");
            System.out.println("0) Salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    System.out.println("Logueo");
                    System.out.print("Ingrese su usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String password = scanner.nextLine();
                    Usuario usuario = UserServices.loginService(nombreUsuario,password);
                    if (usuario!= null){
                        System.out.println("Bienvenido "+ usuario.getNombre());
                    }else {
                        System.out.println("El usuario o la contraseña ingresados no son validos");
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
