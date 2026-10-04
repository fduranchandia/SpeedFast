package modelo;

public class Repartidor implements Runnable {

    private String nombre;
    private int id;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public Repartidor(int id, String nombre, ZonaDeCarga zonaDeCarga) {
        this.id = id;
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();
            if (pedido == null) {
                break;
            }

            System.out.println("[modelo.Repartidor - " + nombre + "] Retirando pedido #" + pedido.getIdPedido() + "...");

            pedido.setEstado(EstadoPedido.EN_REPARTO);
            System.out.println("[modelo.Repartidor - " + nombre + "] Estado: EN_REPARTO");

            System.out.println("[modelo.Repartidor - " + nombre + "] Entregando pedido #" + pedido.getIdPedido() + "...");
            try {
                int tiempo = (int) (Math.random() * 3000) + 1000;
                Thread.sleep(tiempo);
            } catch (InterruptedException e) {
                System.out.println("[modelo.Repartidor - " + nombre + "] La entrega fue interrumpida.");
                Thread.currentThread().interrupt();
                break;
            }
            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("[modelo.Repartidor - " + nombre + "] modelo.Pedido #" + pedido.getIdPedido() + " entregado.");


        }

    }

}
