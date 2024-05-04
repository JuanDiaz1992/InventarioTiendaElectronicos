package com.tienda;

import com.tienda.menu.MenuAdmin;
import com.tienda.menu.MenuGerente;
import com.tienda.menu.MenuVendedor;
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
            System.out.println();
            System.out.println("****Sistema gestión inventarios****");
            System.out.println("1) Iniciar sesión");
            System.out.println("0) Salir");
            System.out.print("Elija una opción: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    System.out.print("Ingrese su usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    System.out.print("Ingrese su contraseña: ");
                    String password = scanner.nextLine();
                    System.out.println("Validando credenciales...");
                    Usuario usuario = UserServices.loginService(nombreUsuario,password);
                    if (usuario!= null){
                        System.out.println("");
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
