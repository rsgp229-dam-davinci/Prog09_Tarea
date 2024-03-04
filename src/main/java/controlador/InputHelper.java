package controlador;

import java.util.Scanner;

/**
 * Esta clase implementa funciones para controlar la entrada por texto
 * @author Rafael SGP
 */
public class InputHelper {

    /**
     * Solicita una entrada de texto controlando que no sea null
     * @param message Mensaje a mostrar
     * @return El texto recibido
     */
    public static String getString(String message){
        System.out.println(message);
        String output = null;
        while (output == null){
            Scanner sc = new Scanner(System.in);
            output = sc.nextLine();
        }
        return output;
    }

    /**
     * Controla la entrada de números enteros, entre 0 y un límite
     * @param message El mensaje a mostrar para la entrada
     * @param maxInclusive El número máximo inclusive aceptado
     * @return El número introducido
     */
    public static int getInteger(String message, int maxInclusive){
        int output = -1;
        while (output < 0 || output > maxInclusive) {
            try {
                System.out.println(message);
                Scanner sc = new Scanner(System.in);
                output = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada no válida, introduzca un entero entre 0 y " + maxInclusive);
            }
        }
        return output;
    }
}
