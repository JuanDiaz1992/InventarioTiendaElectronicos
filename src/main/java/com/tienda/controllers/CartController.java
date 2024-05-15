package com.tienda.controllers;

import com.tienda.models.Cart;
import com.tienda.models.Producto;
import com.tienda.models.Usuario;
import com.tienda.services.CartServices;
import com.tienda.services.ProductsServices;
import com.tienda.utils.CreateTable;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class CartController {
    private static Scanner scanner = new Scanner(System.in);


    public static void addToCart(Usuario usuario){
        try {
            Producto producto = null;
            boolean existProductInCart = false;
            int totalProducto;
            do {
                System.out.print("Ingrese el id del producto: ");
                int id = Integer.parseInt(scanner.nextLine());
                producto = ProductsServices.getProduct(id);

                if (producto == null){
                    System.out.println("Producto no existe, intentelo de nuevo.");
                }else {
                    Cart cart = CartServices.getCart(producto);
                    if (cart != null){
                        System.out.println("El elemento ya se encuentra en el carrito");
                        boolean isValid = true;
                        int cantidad=0;
                        do {
                            System.out.print("Ingrese la nueva cantidad total para este producto en el carrito: ");
                            cantidad = Integer.parseInt(scanner.nextLine());
                            if (cantidad<= 0){
                                System.out.println("Ingrese un valor invalido, intentelo de nuevo.");
                            } else if (cantidad > producto.getStock()) {
                                System.out.println("El valor ingresado excede la cantidad en stock, ingrese un valor valido.");
                            }else {
                                totalProducto = cantidad*producto.getPrecio();
                                cart.setCantidadProducto(cantidad);
                                cart.setTotalProducto(totalProducto);
                                CartServices.update(cart);
                                isValid = false;
                                existProductInCart = true;
                                System.out.println("Valor actualizado correctamente.");

                            }
                        }while (isValid);

                    }
                }
            }
            while (producto == null);
            if (!existProductInCart){
                System.out.println("Producto: "+ producto.getNombre() + ", Cantidad: "+producto.getStock()+", Precio: "+ producto.getPrecio());
                boolean isValid = true;
                int cantidad=0;
                do {
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = Integer.parseInt(scanner.nextLine());
                    if (cantidad<= 0){
                        System.out.println("Ingrese un valor invalido, intentelo de nuevo.");
                    } else if (cantidad > producto.getStock()) {
                        System.out.println("El valor ingresado excede la cantidad en stock, ingrese un valor valido.");
                    }else {
                        isValid = false;
                    }
                }while (isValid);
                totalProducto = cantidad*producto.getPrecio();
                Cart cart = new Cart(producto.getIdProducto(),producto.getNombre(), cantidad, producto.getPrecio(), usuario.getIdUsuario(),totalProducto);
                CartServices.insertToCart(cart);
                System.out.println("Se agregó correctamente.");
            }

        }catch (Exception e){
            e.printStackTrace(System.out);
            System.out.println("Ingrese un valor valido, intentelo de nuevo.");
        }
    }

    public static void deleteItemFromCart(Usuario usuario){
        try{
            List<Cart> carts = CartServices.getCart(usuario);
            if (!carts.isEmpty()){
                Producto producto = null;
                do {
                    System.out.print("Ingrese el id del producto que desea eliminar: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    producto = ProductsServices.getProduct(id);
                    if (producto == null){
                        System.out.println("Producto no existe en el inventario");
                    }
                    else {
                        Cart cart = CartServices.getCart(producto);
                        if (cart != null){
                            CartServices.delete(cart);
                            System.out.println("Se elimino el producto del carrito");
                        }else {
                            System.out.println("El producto "+producto.getNombre() +" no está en el carrito");
                        }

                    }
                }
                while (producto == null);
            }else {
                System.out.println("No hay elementos que eliminar en el carrito");
            }

        }catch (Exception e){
            System.out.println("Opción no valida");
        }
    }

    public static void deleteCart(Usuario usuario){
        try {
            System.out.println("¿Estás seguro que deseas eliminar todo el carrito de compras?");
            System.out.print("Escriba 1 para aceptar o 0 para cancelar: ");
            int optionDelet = Integer.parseInt(scanner.nextLine());
            if (optionDelet == 1){
                CartServices.delteCart(usuario);
                System.out.println("Carrito eliminado.");
            }
        }catch (Exception e){
            System.out.println("Opción invalida");
        }

    }

    public static int cart(Usuario usuario){
        List<Cart> carts = CartServices.getCart(usuario);
        AtomicInteger total = new AtomicInteger();
        if (!carts.isEmpty()){
            System.out.println("///////////////////////////////");
            System.out.println("Productos en el carro de compras:");
            CreateTable.printHeader("Id producto", "producto", "Cantidad", "Precio", "Total P","Id vendedor");

            carts.forEach(cart-> {
                CreateTable.printValue(cart);
                total.addAndGet(cart.getTotalProducto());
            });
            System.out.println("Total compra: "+total);
        }else{
            System.out.println("///////////////////////////////");
            System.out.println("No hay productos en el carrito");
            System.out.println("///////////////////////////////");
        }
        return total.get();
    }
}
