package com.tienda.controllers;

import com.tienda.models.*;
import com.tienda.services.CartServices;
import com.tienda.services.DetalleVentaService;
import com.tienda.services.ProductsServices;
import com.tienda.services.VentasServices;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class VentaController {
    private static Scanner scanner = new Scanner(System.in);

    public static void makeSale(Usuario usuario){
        try {
            System.out.println("¿Estas seguro que deseas completar la venta?");
            System.out.print("Escriba 1 para aceptar o 0 para cancelar: ");
            int optionSale = Integer.parseInt(scanner.nextLine());
            if (optionSale == 1){
                List<Cart> carts = CartServices.getCart(usuario);
                if (!carts.isEmpty()){
                    List<DetalleVenta> detalleVentas = new ArrayList<>();
                    AtomicInteger total = new AtomicInteger();
                    String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
                    String factura = VentaController.generateInvoiceNumber();
                    carts.forEach(cart-> {
                        total.addAndGet(cart.getTotalProducto());
                    });
                    Venta venta = new Venta(total.get(),currentDate,usuario.getIdUsuario(),factura);
                    carts.forEach(cart-> {
                        Producto producto = ProductsServices.getProduct(cart.getIdProducto());
                        int cantidad = producto.getStock() - cart.getCantidadProducto();
                        producto.setStock(cantidad);
                        ProductsServices.updateProductService(producto);
                        DetalleVenta detalleVenta = new DetalleVenta(factura,producto.getIdProducto(),cantidad,producto.getPrecio(),cart.getTotalProducto());
                        detalleVentas.add(detalleVenta);
                    });
                    CartServices.delteCart(usuario);
                    VentasServices.createSale(venta);
                    DetalleVentaService.createSale(detalleVentas);
                    System.out.println("Venta registrada, numero de factura: "+ factura);
                }else {
                    System.out.println("El carrito se encuentra vacío");
                }
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
        }

    }


    public static String generateInvoiceNumber() {
        String PREFIX = "FACT-";
        int randomNumber = (int) (Math.random() * (999 - 100 + 1)) + 100;
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String invoiceNumber = PREFIX + currentDate + randomNumber;

        return invoiceNumber;
    }
}
