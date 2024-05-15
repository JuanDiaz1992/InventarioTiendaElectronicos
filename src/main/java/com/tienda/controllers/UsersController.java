package com.tienda.controllers;

import com.tienda.models.Usuario;
import com.tienda.utils.EncryptPassword;
import com.tienda.services.UserServices;
import com.tienda.utils.CreateTable;

import java.util.List;
import java.util.Scanner;

public class UsersController {
    private static  Scanner scanner = new Scanner(System.in);

    public static Usuario login(){
        System.out.print("Ingrese su usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        System.out.println("Validando credenciales...");
        Usuario usuario = UserServices.loginService(nombreUsuario,password);
        return usuario;
    }

    public static void createUserController(){
        try{
            System.out.println();
            System.out.println("***Crear usuario***");
            System.out.print("Ingrese el nombre de usuario: ");
            String nombreUsuario = scanner.nextLine();
            Usuario existUser = UserServices.getUser(nombreUsuario);
            if (existUser != null){
                System.out.println("El nombre de usuario ya existe");
                throw new NullPointerException();
            }
            String password = "";
            String confirmPassword = "0";
            do{
                System.out.print("Ingrese la nueva  contraseña: ");
                password = scanner.nextLine();
                System.out.print("Confirme la contraseña: ");
                confirmPassword = scanner.nextLine();
                if (!password.equals(confirmPassword)){
                    System.out.println("Contraseña no coincide, intentelo de nuevo.");
                }
            }while (!password.equals(confirmPassword));
            System.out.println("1 para Administrados");
            System.out.println("2 para Vendedor");
            System.out.println("3 para Gerente");
            System.out.print("Elija el tipo de usuario: ");
            int tipoUsuario = Integer.parseInt(scanner.nextLine());
            Usuario usuario = new Usuario(nombreUsuario,password,tipoUsuario);
            UserServices.createUserService(usuario);
        }catch (Exception e){
            System.out.println("Hubo un error al crear el usuario, intentelo de nuevo.");
        }

    }

    public static void updateUserController(){
        System.out.println();
        System.out.println("***Modificar usuario***");
        UsersController.listUserController();
        System.out.print("Ingrese el id del usuario: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        Usuario usuario = UserServices.getUser(idUsuario);
        if (usuario == null){
            System.out.println("El nombre de usuario no existe");
        }else {
            CreateTable.printHeader("Id","Usuario", "Tipo usuario");
            CreateTable.printValue(usuario);
            System.out.println("1) Nombre de usuario");
            System.out.println("2) Contraseña");
            System.out.println("3) Tipo de usuario") ;
            System.out.print("Elija el dato que desea modificar: ");
            String opcion = scanner.nextLine();
            switch (opcion){
                case "1":
                    System.out.print("Ingrese el nuevo nombre de usuario: ");
                    String nombreUsuario = scanner.nextLine();
                    usuario.setNombre(nombreUsuario);
                    UserServices.updateUserService(usuario);
                    break;
                case "2":
                    String password = "";
                    String confirmPassword = "0";
                    do{
                        System.out.print("Ingrese la nueva  contraseña: ");
                        password = scanner.nextLine();
                        System.out.print("Confirme la contraseña: ");
                        confirmPassword = scanner.nextLine();
                        if (!password.equals(confirmPassword)){
                            System.out.println("Contraseña no coincide, intentelo de nuevo.");
                        }
                    }while (!password.equals(confirmPassword));
                    password = EncryptPassword.hashPassword(confirmPassword);
                    usuario.setPassword(password);
                    UserServices.updateUserService(usuario);
                    break;
                case "3":
                    System.out.println("1 para Administrados");
                    System.out.println("2 para Vendedor");
                    System.out.println("3 para Gerente");
                    System.out.print("Elija el tipo de usuario: ");
                    int tipoUsuario = Integer.parseInt(scanner.nextLine());
                    usuario.setRol(tipoUsuario);
                    UserServices.updateUserService(usuario);
                    break;
                default:
                    System.out.println("Opción invalida, intentelo de nuevo");
                    break;
            }

        }
    }

    public static void listUserController(){
        CreateTable.printHeader("Id","Usuario", "Tipo usuario");
        List<Usuario> usuarios = UserServices.getAllUsers();
        usuarios.forEach(usuario -> {
            CreateTable.printValue(usuario);
        });



    }

    public static void deleteUser(Usuario usuario){
        UsersController.listUserController();
        System.out.println();
        System.out.println("****Advertencia********");
        System.out.println("No se puede eliminar el usuario con el que está haciendo esta operación");
        System.out.println();
        System.out.print("Ingrese el id del usuario a eliminar: ");
        int idUsuario = Integer.parseInt(scanner.nextLine());
        Usuario usuarioObtenido = UserServices.getUser(idUsuario);
        if (usuario == null){
            System.out.println("El nombre de usuario no existe");
        }else if (usuarioObtenido.equals(usuario)){
            System.out.println("/////////////////////////////////////////////////////////////////////////////");
            System.out.println("No puedes eliminar el usuario con el que estás realizando esta operación.");
            System.out.println("Solo otro administrador puede eliminar este usuario.");
            System.out.println("/////////////////////////////////////////////////////////////////////////////");
        }else {
            System.out.println("Estás seguro que deseas eliminar al usuario "+usuarioObtenido.getNombre());
            System.out.println("1) Aceptar");
            System.out.println("2) Cancelar");
            System.out.print("Elija: ");
            String optionDelete = scanner.nextLine();
            if (optionDelete.equals("1")){
                UserServices.delteteUser(usuarioObtenido);
            }else {
                System.out.println("Operación cancelada");
            }
        }
    }
}
