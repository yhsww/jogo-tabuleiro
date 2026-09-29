package src.util;

import java.util.Scanner;

public class Utilitarios {

    private static Scanner sc = new Scanner(System.in);

    public static int inteiroValido(){

        int leitura = 0;

        try{

            leitura = Integer.parseInt(Utilitarios.sc.nextLine());

        }catch(IllegalArgumentException e){
            System.out.println("Valor inválido. Insira um valor numérico:");
            leitura = Integer.parseInt(Utilitarios.sc.nextLine());

        }

        return leitura;
    }

    public static double doubleValido(){

        double leitura = 0;

        try{

            leitura = Double.parseDouble(Utilitarios.sc.nextLine());

        }catch(IllegalArgumentException e){
            System.out.println("Valor inválido. Insira um valor numérico:");
            leitura = Double.parseDouble(Utilitarios.sc.nextLine());

        }

        return leitura;
    }

    public static String stringValida(){

        String leitura = null;

        try{

            leitura = Utilitarios.sc.nextLine();

        }catch(IllegalArgumentException e){
            System.out.println("Valor inválido. Insira texto: ");
            leitura = Utilitarios.sc.nextLine();

        }

        return leitura;
    }

    
}
