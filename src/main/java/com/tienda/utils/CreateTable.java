package com.tienda.utils;

import java.util.List;

public class CreateTable {
    public static void printHeader(String ...nameTabs){
        String format;
        StringBuilder formatSb = new StringBuilder();
        StringBuilder columnsSb = new StringBuilder();

        // Construir el formato y la cadena de columnas
        for (int i = 0; i < nameTabs.length; i++) {
            formatSb.append("|%15s");
            columnsSb.append('"').append(nameTabs[i]).append('"');
            if (i < nameTabs.length - 1) {
                columnsSb.append(", ");
            }
        }

        formatSb.append("|%n");
        format = formatSb.toString();
        String columns = columnsSb.toString();
        System.out.println();
        System.out.println();
        System.out.printf(format, (Object[]) columns.split(", "));
        for (int i = 0; i < nameTabs.length; i++) {
            System.out.print("+---------------");
        }
        System.out.println("+");
    }

    public static void printValue(Object object){
        String[] atributes = object.toString().split(", ");
        String format;
        StringBuilder formatSb = new StringBuilder();;
        // Construir el formato y la cadena de columnas
        for (int i = 0; i < atributes.length; i++) {
            formatSb.append("|%15s");
        }
        formatSb.append("|%n");
        format = formatSb.toString();
        String data = object.toString();
        System.out.printf(format, (Object[]) data.split(", "));
    }
}
