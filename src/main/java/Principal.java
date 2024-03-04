import controlador.InputHelper;
import controlador.Program;
import model.identidades.Banco;

public class Principal {
    public static void main(String[] args) {
        Program p = new Program();
        p.iniciar(new Banco());
    }
}
