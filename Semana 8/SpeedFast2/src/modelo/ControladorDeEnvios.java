package modelo;

import java.util.ArrayList;

/**
 * Controla el historial de entregas de SpeedFast
 */

public class ControladorDeEnvios implements Rastreable {

    private ArrayList<Pedido> historial;

    public ControladorDeEnvios(){
        historial = new ArrayList<>();
    }

    /**
     * Registra un pedido en el historial de entregas
     */
    public void registrarEntrega(Pedido pedido){
        historial.add(pedido);
    }

    public ArrayList<Pedido> getHistorial() {
        return historial;
    }

    /**
     * Muestra el historial de entregas registradas
     */
    @Override
    public void verHistorial() {
        for (Pedido pedido: historial) {
            pedido.mostrarResumen();
            System.out.println("----------------");
        }
    }


}
