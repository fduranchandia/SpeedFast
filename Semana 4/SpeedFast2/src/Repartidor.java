import java.util.ArrayList;

public class Repartidor implements Runnable {

    private String nombre;
    private ArrayList<Pedido> pedidosAsignados;

    public Repartidor(String nombre) {
        this.nombre = nombre;
        this.pedidosAsignados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Pedido> getPedidosAsignados() {
        return pedidosAsignados;
    }

    public void agregarPedido(Pedido pedido) {
        pedidosAsignados.add(pedido);
    }

    @Override
    public void run() {
        for (Pedido pedido : pedidosAsignados) {
            System.out.println("[Repartidor: " + nombre + "] Entregando Pedido #" + pedido.getIdPedido()+"...");
            try {
                int tiempo = (int) (Math.random() * 2000) + 1000;
                Thread.sleep(tiempo);
            } catch(InterruptedException e) {
                System.out.println("La entrega fue interrumpida.");
            }
            System.out.println("[Repartidor: " + nombre + "] Pedido #" + pedido.getIdPedido() + " entregado.");
        }

    }

}
