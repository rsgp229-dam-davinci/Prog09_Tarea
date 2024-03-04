package model.identidades;

/**
 * Clase que representa una empresa.
 *
 * @author RafaelSGP
 */
public class Empresa extends Cliente{
    public Empresa(String cif) throws IllegalArgumentException{
        super.setDocumento(cif);
    }
    @Override
    public String devolverInfoString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(this.getNombre()).append(System.lineSeparator());
        sb.append("CIF: ").append(this.getDocumento()).append(System.lineSeparator());
        return sb.toString();
    }
}
