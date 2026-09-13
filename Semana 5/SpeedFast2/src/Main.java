//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();

        //Creacion de pedidos
        PedidoComida pedido1 = new PedidoComida(1,"Av. Italia 456",4);
        PedidoEncomienda pedido2 = new PedidoEncomienda(2, "Av. Independencia 123", 6);
        PedidoExpress pedido3 = new PedidoExpress(3, "Av. Apoquindo 1500", 7);
        
        PedidoComida pedido4 = new PedidoComida(4,"Av. Providencia 100",3);
        PedidoEncomienda pedido5 = new PedidoEncomienda(5, "Av. Las Condes 500", 5);
        PedidoExpress pedido6 = new PedidoExpress(6, "Av. Macul 800", 8);

        zonaDeCarga.agregarPedido(pedido1);
        zonaDeCarga.agregarPedido(pedido2);
        zonaDeCarga.agregarPedido(pedido3);
        zonaDeCarga.agregarPedido(pedido4);
        zonaDeCarga.agregarPedido(pedido5);
        zonaDeCarga.agregarPedido(pedido6);

        Repartidor camila = new Repartidor("Camila", zonaDeCarga);
        Repartidor luis = new Repartidor("Luis", zonaDeCarga);
        Repartidor felipe = new Repartidor("Felipe", zonaDeCarga);

        Thread hiloCamila = new Thread(camila);
        Thread hiloLuis = new Thread(luis);
        Thread hiloFelipe = new Thread(felipe);

        hiloCamila.start();
        hiloLuis.start();
        hiloFelipe.start();

        try {
            hiloCamila.join();
            hiloLuis.join();
            hiloFelipe.join();
        } catch (InterruptedException e){
            System.out.println("La ejecución de los hilos fue interrumpida.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Todos los pedidos han sido entregados correctamente");

    }
}