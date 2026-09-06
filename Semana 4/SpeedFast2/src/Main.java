//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Repartidor camila = new Repartidor("Camila");
        Repartidor luis = new Repartidor("Luis");
        Repartidor felipe = new Repartidor("Felipe");

        //Creacion de pedidos
        PedidoComida pedido1 = new PedidoComida(1,"Av. Italia 456",4);
        PedidoEncomienda pedido2 = new PedidoEncomienda(2, "Av. Independencia 123", 6);
        PedidoExpress pedido3 = new PedidoExpress(3, "Av. Apoquindo 1500", 7);

        PedidoComida pedido4 = new PedidoComida(4,"Av. Providencia 100",3);
        PedidoEncomienda pedido5 = new PedidoEncomienda(5, "Av. Las Condes 500", 5);
        PedidoExpress pedido6 = new PedidoExpress(6, "Av. Macul 800", 8);

        camila.agregarPedido(pedido1);
        camila.agregarPedido(pedido2);

        luis.agregarPedido(pedido3);
        luis.agregarPedido(pedido4);

        felipe.agregarPedido(pedido5);
        felipe.agregarPedido(pedido6);

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
        }

        System.out.println("Todos los repartidores han terminado sus entregas.");

    }
}