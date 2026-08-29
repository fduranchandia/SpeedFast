/**
 * Representa un pedido de encomienda dentro del sistema SpeedFast
 */

public class PedidoEncomienda extends Pedido implements Despachable, Rastreable{

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Asigna automaticamente un repartidor para el pedido de encomienda
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para encomienda...");
        System.out.println("Repartidor asignado: Daniela Tapia");
    }

    /**
     * Despacha el pedido de encomienda
     */

    @Override
    public void despachar() {
        System.out.println("Pedido de encomienda despachado correctamente.");
    }

    /**
     * Consulta el historial asociadoo al pedido de encomienda
     */
    @Override
    public void verHistorial() {
        System.out.println("Consultando historial de pedido de encomienda #" + getIdPedido());
    }

    /**
     * Calcula el tiempo de entrega para un pedido de encomienda
     */
    @Override
    public int calcularTiempoEntrega() {
        return (int)(20 + (1.5 * getDistanciaKm()));
    }
}
