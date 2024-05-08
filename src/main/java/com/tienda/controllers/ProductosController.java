package com.tienda.controllers;


import com.tienda.models.Producto;
import com.tienda.services.ProductsServices;
import com.tienda.utils.CreateTable;
import com.tienda.utils.PressEnter;

import java.util.List;
import java.util.Scanner;


public class ProductosController {

    private static Scanner scanner = new Scanner(System.in);

    public static void createProductController(){
        try{
            System.out.println();
            System.out.println("***Crear Producto***");
            System.out.print("Ingrese el nombre del producto: ");
            String nombreProducto = scanner.nextLine();
            Producto existProduct = ProductsServices.getProduct(nombreProducto);
            if (existProduct != null){
                System.out.println("El producto ya existe en el sistema: "+existProduct+"  ");
                throw new InstantiationException();
            }
            System.out.print("Ingrese una breve descripción del producto (Peso, Marca, Tipo etc.): ");
            String descripcion = scanner.nextLine();
            System.out.print("Ingrese el precio del producto: $");
            int precio = Integer.parseInt(scanner.nextLine());
            precio = precio > 0 ? precio : 0;
            System.out.print("Ingrese la cantidad de este producto: ");
            int cantidad  = Integer.parseInt(scanner.nextLine());
            cantidad = cantidad>0? cantidad:0;
            Producto producto = new Producto(nombreProducto,descripcion,precio,cantidad);
            ProductsServices.createProductService(producto);

        }catch (Exception e){
            System.out.println("Hubo un error al crear el producto, intentelo de nuevo.");
        }

    }

    public static boolean listProductsController(){
        System.out.println();
        System.out.println("***Lista de Productos***");
        List<Producto> productos = ProductsServices.getAllProducts();
        if (!productos.isEmpty()){
            CreateTable.printHeader("Id","Nombre", "Descripción", "Precio", "Stokc");
            productos.forEach(producto -> {
                CreateTable.printValue(producto);
            });
            return true;
        }else {
            System.out.println("No hay productos en el inventario");
            return false;
        }

    }

    public static void viewProduct(){
        if (ProductosController.listProductsController()){
            System.out.println("1) Ver el detalle de un producto");
            System.out.println("0) Volver al menú de productos");
            System.out.print("Elija una opción: ");
            String optionDetalle = scanner.nextLine();
            if (optionDetalle.equals("1")){
                System.out.print("Ingrese el id del producto: ");
                int idProduc = Integer.parseInt(scanner.nextLine());
                Producto producto = ProductsServices.getProduct(idProduc);
                if (producto!= null){
                    String mensaje = String.format("Id: %s, Nombre: %s, Descripción: %s, Precio: %s, Stock: %s",
                            producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(),
                            producto.getPrecio(), producto.getStock());
                    System.out.println(mensaje);
                }else {
                    System.out.println("El producto no existe");
                }
                PressEnter.pressEnter();
            }else if(!optionDetalle.equals("0")){
                System.out.println("Opción invalida");
            }
        }

    }

    public static void updateProductController(){
        try {
            System.out.println();
            System.out.println("***Modificar Producto***");
            if(ProductosController.listProductsController()){
                System.out.print("Ingrese el id del producto: ");
                int idProduc = Integer.parseInt(scanner.nextLine());
                Producto producto = ProductsServices.getProduct(idProduc);
                if (producto!= null){
                    String mensaje = String.format("Id: %s, Nombre: %s, Descripción: %s, Precio: %s, Stock: %s",
                            producto.getIdProducto(), producto.getNombre(), producto.getDescripcion(),
                            producto.getPrecio(), producto.getStock());
                    System.out.println(mensaje);
                    System.out.println("1) Nombre de producto");
                    System.out.println("2) Descripción del producto");
                    System.out.println("3) Precio del producto");
                    System.out.println("4) Stock");
                    System.out.print("Elija el dato que desea modificar: ");
                    String opcion = scanner.nextLine();
                    switch (opcion){
                        case "1":
                            System.out.print("Ingrese el nuevo nombre para "+producto.getNombre()+": ");
                            String newNombre = scanner.nextLine();
                            newNombre = !newNombre.isEmpty() ?  newNombre : producto.getNombre() ;
                            producto.setNombre(newNombre);
                            ProductsServices.updateProductService(producto);
                            break;
                        case "2":
                            System.out.print("Ingrese la nueva descipción para "+producto.getNombre()+": ");
                            String newDescription = scanner.nextLine();
                            newDescription = !newDescription.isEmpty() ?  newDescription : producto.getDescripcion() ;
                            producto.setDescripcion(newDescription);
                            ProductsServices.updateProductService(producto);
                            break;
                        case "3":
                            System.out.print("Ingrese el nuevo precio para "+producto.getNombre()+": ");
                            int newPrice = Integer.parseInt(scanner.nextLine());
                            newPrice = newPrice>0 ?  newPrice : producto.getPrecio() ;
                            producto.setPrecio(newPrice);
                            ProductsServices.updateProductService(producto);
                            break;
                        case "4":
                            System.out.print("Ingrese la cantidad de stock para "+producto.getNombre()+": ");
                            int newStock = Integer.parseInt(scanner.nextLine());
                            newStock = newStock>0 ?  newStock : producto.getStock() ;
                            producto.setStock(newStock);
                            ProductsServices.updateProductService(producto);
                            break;
                        default:
                            System.out.println("Opción invalida, intentelo de nuevo");
                    }
                }else {
                    System.out.println("El producto no existe");
                }
            }
        }catch (Exception e){
            e.printStackTrace(System.out);
            System.out.println("Los datos ingresados no son validos, intentelo de nuevo");
        }

    }

    public static void deleteProductController(){
        System.out.println();
        System.out.println("***Eliminar Producto***");
        if (ProductosController.listProductsController()){
            System.out.print("Ingrese el id del producto a eliminar: ");
            int idProducto = Integer.parseInt(scanner.nextLine());
            Producto producto = ProductsServices.getProduct(idProducto);
            if (producto != null){
                System.out.println("Estás seguro que deseas eliminar al usuario "+producto.getNombre());
                System.out.println("1) Aceptar");
                System.out.println("2) Cancelar");
                System.out.print("Elija: ");
                String optionDelete = scanner.nextLine();
                if (optionDelete.equals("1")){
                    ProductsServices.delteteProduct(producto);
                }else {
                    System.out.println("Operación cancelada");
                }
            }
        }
    }
}
