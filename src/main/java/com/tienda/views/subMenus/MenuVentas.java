package com.tienda.views.subMenus;

import com.tienda.controllers.CartController;
import com.tienda.controllers.ProductosController;
import com.tienda.controllers.VentaController;
import com.tienda.models.Usuario;
import com.tienda.services.CartServices;
import com.tienda.utils.PressEnter;

import java.util.Scanner;

public class MenuVentas {
    private static Scanner scanner = new Scanner(System.in);

    public static void makeSale(Usuario usuario){
        System.out.println();
        System.out.println("************* Gestión de ventas *************");
        ProductosController.listProductsController();
        String optionSale;
        do {
            CartController.cart(usuario);
            System.out.println("1) Agregar productos al carrito");
            System.out.println("2) Quitar item del carrito");
            System.out.println("3) Borrar todo el carrito");
            System.out.println("4) Completar venta");
            System.out.println("0) Ir al menú principal");
            System.out.print("Elija una opción: ");
            optionSale = scanner.nextLine();
            switch (optionSale){
                case "1":
                    CartController.addToCart(usuario);
                    PressEnter.pressEnter();
                    break;
                case "2":
                    CartController.deleteItemFromCart(usuario);
                    PressEnter.pressEnter();
                    break;
                case "3":
                    CartController.deleteCart(usuario);
                    PressEnter.pressEnter();
                    break;
                case "4":
                    VentaController.makeSale(usuario);
                    PressEnter.pressEnter();
                    break;
            }
        }while (!optionSale.equals("0"));

    }


}
