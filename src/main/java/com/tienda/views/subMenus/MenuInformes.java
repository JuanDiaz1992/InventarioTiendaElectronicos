package com.tienda.views.subMenus;

import com.tienda.controllers.InformesController;
import com.tienda.utils.DateManipulator;
import com.tienda.utils.PressEnter;

import java.util.Scanner;

public class MenuInformes {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println();
        System.out.println("************* Generador de reportes *************");
        String optionGerencia;
        do {
            System.out.println("1) Ver reporte de ventas");
            System.out.println("2) Ver lista de mejores vendedores del mes");
            System.out.println("0) Ir al menú principal");
            System.out.print("Elija una opción: ");
            optionGerencia = scanner.nextLine();
            switch (optionGerencia){
                case "1":
                    System.out.println();
                    System.out.println("************* Reportes de ventas *************");
                    System.out.println("1) Ver informe del mes actual");
                    System.out.println("2) Ver informe de dia especifico");
                    System.out.println("3) Ver informe de un mes especifico");
                    System.out.print("Elija una opción: ");
                    String optionSelect = scanner.nextLine();
                    switch (optionSelect){
                        case "1":
                            try {
                                String thisDate = DateManipulator.actuallityDay();
                                String monthAndYear = thisDate.substring(0,thisDate.length()-2);
                                InformesController.viewInform(monthAndYear+"%");
                                InformesController.viewDatailSale();
                            }catch (Exception e){
                                System.out.println("Datos mal ingresados, intentelo de nuevo");
                            }
                            PressEnter.pressEnter();
                            break;
                        case "2":
                            try {
                                System.out.print("Ingrese la fecha en el siguiente formato YYYY/MM/DD: ");
                                String date = scanner.nextLine();
                                if (DateManipulator.isValidDateFormat(date)){
                                    String dateFormated = DateManipulator.dateFormate(date);
                                    if(Integer.parseInt(dateFormated)<Integer.parseInt(DateManipulator.actuallityDay())){
                                        InformesController.viewInform(dateFormated);
                                    }else{
                                        System.out
                                                .println("La fecha debe ser anterior al mes actual, intentelo de nuevo.");
                                    }

                                }else {
                                    System.out.println("Fecha mal ingresada, intentelo de nuevo.");
                                }
                                InformesController.viewDatailSale();
                            }catch (Exception e){
                                System.out.println("Datos mal ingresados, intentelo de nuevo");
                            }
                            PressEnter.pressEnter();
                            break;
                        case "3":
                            try {
                                System.out.println("Informe de un mes especifico");
                                System.out.print("Ingrese el año ejemplo(2024): ");
                                String year = scanner.nextLine();
                                System.out.print("Ingrese el número del mes, ejemplo para abril ingresa 04: ");
                                String mounth = scanner.nextLine();
                                InformesController.viewInform(year+mounth+"%");
                                InformesController.viewDatailSale();
                            }catch (Exception e){
                                System.out.println("Datos mal ingresados, intentelo de nuevo");
                            }
                            PressEnter.pressEnter();
                            break;
                        default:
                            System.out.println("Opción invalida intentelo de nuevo");
                            break;
                    }
                    break;

                case "2":
                    System.out.println("Vendedores del mes: ");
                    InformesController.viewRaitingSales();
                    PressEnter.pressEnter();
                    break;

            }
        }while (!optionGerencia.equals("0"));
    }
}
