package Aplicacion;

import Controlador.ControladorMenu;
import Vista.VentanaMenu;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaMenu ventana = new VentanaMenu();
                new ControladorMenu(ventana);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            }
        });
    }
}
