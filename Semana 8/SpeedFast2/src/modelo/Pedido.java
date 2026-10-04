package modelo;

/**
 * Clase abstracta que representa un pedido general de SpeedFast
 * Contiene los atributos y comportamientos comunes a todos los pedidos
 *
 */

public abstract class Pedido {

    private int idPedido;
    private String direccionEntrega;
    private double distanciaKm;
    private EstadoPedido estado;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = EstadoPedido.PENDIENTE;
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

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setEstado(String nuevoEstado) {
        this.estado = EstadoPedido.valueOf(nuevoEstado);
    }

    @Override
    public String toString() {
        return "modelo.Pedido #" + idPedido +
                " | Direccion: " + direccionEntrega +
                " | Estado: " + estado;
    }

    public void mostrarResumen() {
        System.out.println("modelo.Pedido #" + idPedido);
        System.out.println("Direccion: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    public void reservarPedido() {
        System.out.println("modelo.Pedido #" + idPedido + " reservado correctamente");
    }


    public void asignarRepartidor(String nombre){
        System.out.println("modelo.Repartidor asignado manualmente: " + nombre);
    }

    public abstract void asignarRepartidor();

    public abstract int calcularTiempoEntrega();

}
