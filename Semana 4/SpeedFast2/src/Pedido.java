/**
 * Clase abstracta que representa un pedido general de SpeedFast
 * Contiene los atributos y comportamientos comunes a todos los pedidos
 *
 */

public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    /**
     * Muestra la info basica del pedido
     */
    public void mostrarResumen() {
        System.out.println("Pedido #" + idPedido);
        System.out.println("Direccion: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    /**
     * Reserva el pedido
     */
    public void reservarPedido() {
        System.out.println("Pedido #" + idPedido + " reservado correctamente");
    }

    /**
     * Sobrecarga de asignarRepartidor
     * Permite asignar manualmente un repartidor mediante su nombre
     */
    public void asignarRepartidor(String nombre){
        System.out.println("Repartidor asignado manualmente: " + nombre);
    }

    /**
     * Cada subclase define su propia forma de asignar
     * automaticamente un repartidor
     */
    public abstract void asignarRepartidor();

    /**
     * Cada sublclase define su propia forma de calcular
     * el tiempo de entrega
     */
    public abstract int calcularTiempoEntrega();

}
