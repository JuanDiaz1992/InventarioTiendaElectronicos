package com.tienda.controllers;

import com.tienda.models.DetalleVenta;
import com.tienda.models.SalesRating;
import com.tienda.models.Venta;
import com.tienda.services.DetalleVentaService;
import com.tienda.services.InformesServices;
import com.tienda.utils.CreateTable;
import com.tienda.utils.DateManipulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class InformesController {
    private static Scanner scanner = new Scanner(System.in);
    private static InformesServices informesServices = new InformesServices();
    private static DetalleVentaService detalleVentaService = new DetalleVentaService();


    public static void viewInform(String date){
        List<Venta> ventas = informesServices.viewSales(date);
        AtomicInteger totalVentas = new AtomicInteger();
        AtomicInteger counter = new AtomicInteger();
        if (!ventas.isEmpty()) {
            CreateTable.printHeader("Id", "Valor Venta", "Fecha", "ID Usuario", "Numero Factura");
            ventas.forEach(venta -> {
                counter.addAndGet(1);
                CreateTable.printValue(venta);
                totalVentas.addAndGet(venta.getTotalVenta());
            });
            System.out.println("///////////////");
            System.out.println("Cantidad de ventas: "+counter);
            System.out.println("Total ventas: $"+totalVentas);
            System.out.println("///////////////");
            System.out.println();
        }else {
            System.out.println("No hay ventas registradas.");
        }

    }

    public  static void viewDatailSale(){
        try {
            System.out.println("Desea ver el detalle de una venta?");
            System.out.print("1 para ver o 0 para salir: ");
            String optionDetail = scanner.nextLine();

            if (optionDetail.equals("1")){
                System.out.print("Ingrese el id o el número de factura: ");
                String data = scanner.nextLine();
                List<DetalleVenta> detalleVentas = new ArrayList<>();
                detalleVentas = data.startsWith("FACT") ?  detalleVentaService.detalleVentasForFactura(data) : detalleVentaService.detalleVentasForId(data);
                AtomicInteger totalVentas = new AtomicInteger();
                if (!detalleVentas.isEmpty()){
                    CreateTable.printHeader("Id detalle v", "Id producto", "Cant producto", "Precio", "Importe", "Factura");
                    detalleVentas.forEach(detalleVenta -> {
                        CreateTable.printValue(detalleVenta);
                        totalVentas.addAndGet(detalleVenta.getImporte());
                    });
                    System.out.println("///////////////");
                    System.out.println("Total venta: "+totalVentas);
                    System.out.println("///////////////");
                }
            }
        }catch(Exception e){
            e.printStackTrace(System.out);
        }

    }

    public static void viewRaitingSales(){
        System.out.println("Rating de ventas del mes por empleado:");
        String thisDate = DateManipulator.actuallityDay();
        String monthAndYear = thisDate.substring(0,thisDate.length()-2);
        List<SalesRating> salesRatingList = informesServices.viewRating(monthAndYear+"%");
        if (!salesRatingList.isEmpty()){
            CreateTable.printHeader( "Usuario", "Id", "Total ventas", "Total ingresos");
            salesRatingList.forEach(salesRating -> {
                CreateTable.printValue(salesRating);
            });
        }else {
            System.out.println("No se han registrado ventas aún");
        }

    }




}
