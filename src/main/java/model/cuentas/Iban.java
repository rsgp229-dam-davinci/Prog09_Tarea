package model.cuentas;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que representa un IBAN.
 *
 * Algunos métodos son estáticos para permitir utilizarlos sin tener que instanciar la clase.
 *
 * La expresión regular se ha adaptado a la normativa, que indica que las letras tienen que ser mayúsculas, por lo que
 * la regex no permite minúsculas; en caso de ser requisito, el grupo primero se sustituye por (ES|es).
 *
 * Al igual que en casi todas las clases del modelo, se han sobreescrito los métodos equals y hashCode para que la
 * comparación corresponda realmente con el dato relevante de la clase; en este caso el string que representa el IBAN.
 */
public class Iban {
    private final static String IBAN_REGEX ="^(ES)[0-9]{22}$";
    private final static Pattern IBAN_pattern = Pattern.compile(IBAN_REGEX);
    private String iban;
    public Iban (String iban) throws IllegalArgumentException{
        if (isValidIBAN(iban)){
            this.iban = iban;
        } else throw new IllegalArgumentException("El número del IBAN no es válido");
    }
    public static boolean isValidIBAN (String input){
        Matcher m = IBAN_pattern.matcher(input);
        return m.matches();
    }
    public String getIbanNumber() {
        return new String(iban);
    }

    @Override
    public String toString() {
        return "IBAN: " + iban + System.lineSeparator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iban iban1 = (Iban) o;
        return Objects.equals(iban, iban1.iban);
    }
    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }
}
