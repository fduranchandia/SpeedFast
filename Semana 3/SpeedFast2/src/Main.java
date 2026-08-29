//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        //Creacion de pedidos
        PedidoComida comida = new PedidoComida(1,"Av. Italia 456",4);
        PedidoEncomienda encomienda = new PedidoEncomienda(2, "Av. Independencia 123", 6);
        PedidoExpress express = new PedidoExpress(3, "Av. Apoquindo 1500", 7);

        System.out.println("========================================");
        System.out.println("             PEDIDO COMIDA");
        System.out.println("========================================");

        comida.mostrarResumen();
        comida.reservarPedido();
        System.out.println();
        System.out.println("Tiempo estimado: " + comida.calcularTiempoEntrega() + " minutos");
        comida.asignarRepartidor();
        comida.despachar();
        System.out.println();

        System.out.println("========================================");
        System.out.println("             PEDIDO ENCOMIENDA");
        System.out.println("========================================");

        encomienda.mostrarResumen();
        encomienda.reservarPedido();
        System.out.println();
        System.out.println("Tiempo estimado: " + encomienda.calcularTiempoEntrega() + " minutos");
        encomienda.asignarRepartidor();
        encomienda.despachar();
        System.out.println();

        System.out.println("========================================");
        System.out.println("             PEDIDO EXPRESS");
        System.out.println("========================================");

        express.mostrarResumen();
        express.reservarPedido();
        System.out.println();
        System.out.println("Tiempo estimado: " + express.calcularTiempoEntrega() + " minutos");
        express.asignarRepartidor();
        express.cancelar();
        System.out.println();

        //Registro de los pedidos entregados
        controlador.registrarEntrega(comida);
        controlador.registrarEntrega(encomienda);

        System.out.println("========================================");
        System.out.println("          HISTORIAL DE ENTREGAS");
        System.out.println("========================================");

        controlador.verHistorial();

    }
}