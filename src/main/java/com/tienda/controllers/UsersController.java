package com.tienda.controllers;

import com.tienda.models.Usuario;
import com.tienda.services.UserServices;

import java.util.Scanner;

public class UsersController {

    public static Usuario login(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();
        System.out.println("Validando credenciales...");
        Usuario usuario = UserServices.loginService(nombreUsuario,password);
        return usuario;
    }
}
