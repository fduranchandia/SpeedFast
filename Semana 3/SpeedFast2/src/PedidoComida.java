/**
 * Representa un pedido de comida dentro del sistema SpeedFast
 */
public class PedidoComida extends Pedido implements Despachable, Cancelable {

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    /**
     * Calcula el tiempo de entrega para un pedido de comida
     */
    @Override
    public int calcularTiempoEntrega() {

        return 15 + (int)(2*getDistanciaKm());

    }

    /**
     * Despacha el pedido de comida
     */
    @Override
    public void despachar() {

        System.out.println("Pedido de comida despachado correctamente");
    }

    /**
     * Cancela el pedido de comida
     */
    @Override
    public void cancelar() {
        System.out.println("Cancelando pedido de comida...");
        System.out.println("Pedido de comida cancelado.");
    }

    /**
     * Asigna automáticamente un repartidor para el pedido de comida.
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para pedido de comida...");
        System.out.println("Repartidor asignado: Felipe Duran");
    }

}
