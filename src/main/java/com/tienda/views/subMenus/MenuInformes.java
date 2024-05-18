package com.tienda.views.subMenus;

import com.tienda.controllers.InformesController;
import com.tienda.utils.DateManipulator;

import java.util.Scanner;

public class MenuInformes {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println();
        System.out.println("************* Generador de reportes *************");
        String optionGerencia = "0";
        do {
            System.out.println("1) Ver reporte de ventas");
            System.out.println("0) Ir al menú principal");
            System.out.print("Elija una opción: ");
            optionGerencia = scanner.nextLine();
            switch (optionGerencia){
                case "1":
                    System.out.println();
                    System.out.println("************* Reportes de ventas *************");
                    System.out.println("1) Ver informe del mes actual");
                    System.out.println("2) Ver informe de dia especifico");
                    System.out.println("3) Ver informe de un mes especifico del año actual");
                    System.out.print("Elija una opción: ");
                    String optionSelect = scanner.nextLine();
                    switch (optionSelect){
                        case "1":
                            break;
                        case "2":
                            System.out.print("Ingrese la fecha en el siguiente formato YYYY/MM/DD: ");
                            String date = scanner.nextLine();
                            if (DateManipulator.isValidDateFormat(date)){
                                String dateFormated = DateManipulator.dateFormate(date);
                                if(Integer.parseInt(dateFormated)<Integer.parseInt(DateManipulator.actuallityDay())){
                                    InformesController.viewSales(dateFormated);
                                }else{
                                    System.out.println("La fecha debe ser anterior al mes actual, intentelo de nuevo.");
                                }

                            }else {
                                System.out.println("Fecha mal ingresada, intentelo de nuevo.");
                            }
                            break;
                        case "3":
                            break;
                        default:
                            System.out.println("Opción invalida intentelo de nuevo");
                            break;
                    }
                    break;

            }

        }while (optionGerencia != "0");
    }
}
