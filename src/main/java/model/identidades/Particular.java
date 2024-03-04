package model.identidades;

/**
 * Clase específica que representa un cliente particular. La comparación entre clientes se realiza mediante
 * el documento que le representa por ser un dato unívoco. La comparación se realiza gracias al método equals
 * sobreescrito en la clase padre Cliente.
 *
 * Implementa la interfaz Imprimible
 *
 * @author Rafael SGP
 */
public class Particular extends Cliente{

    public Particular(String dni) throws IllegalArgumentException{
        super.setDocumento(dni);
    }
    private String apellidos;
    public String getApellidos() {
        if (apellidos == null)
            return "";
        else
            return apellidos;
    }
    public void setApellidos(String apellidos) {
        if (apellidos == null)
            this.apellidos = "";
        else
            this.apellidos = apellidos;
    }
    @Override
    public String devolverInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(getNombre()).append(System.lineSeparator());
        sb.append("Apellidos: ").append(getApellidos()).append(System.lineSeparator());
        sb.append("NIF: ").append(getDocumento()).append(System.lineSeparator());
        return sb.toString();
    }
}