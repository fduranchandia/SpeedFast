/**
 * Representa un pedido express dentro del sistema SpeedFast
 */
public class PedidoExpress extends Pedido implements Cancelable{

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);


    }

    /**
     * Asigna automáticamente un repartidor para el pedido express
     */
    @Override
    public void asignarRepartidor() {
        System.out.println("Asignando repartidor para pedido express...");
        System.out.println("Repartidor asignado: Carlos Soto");
    }

    /**
     * Cancela el pedido express
     */
    @Override
    public void cancelar(){
        System.out.println("Cancelando pedido express...");
        System.out.println("Pedido cancelado exitosamente");
    }

    /**
     * Calcula el tiempo de entrega según la distancia del pedido
     */
    @Override
    public int calcularTiempoEntrega() {
        if (getDistanciaKm()>5) {
            return 15;
        } else {
            return 10;
        }
    }
}
