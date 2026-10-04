package vista;

import modelo.ControladorDeEnvios;

import javax.swing.*;
import java.awt.*;

import modelo.Pedido;
import modelo.Repartidor;
import modelo.ZonaDeCarga;

public class VentanaPrincipal extends JFrame {

    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnEntregar;
    private JButton btnRepartidores;
    private JButton btnEntregas;

    private ControladorDeEnvios controlador;
    private ZonaDeCarga zonaDeCarga;

    public VentanaPrincipal() {

        controlador = new ControladorDeEnvios();
        zonaDeCarga = new ZonaDeCarga();

        setTitle("SpeedFast - Gestion Entregas");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelBotones = new JPanel(new GridLayout(5, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        btnRegistrar = new JButton("Registrar Pedido");
        btnListar = new JButton("Listar Pedidos");
        btnEntregar = new JButton("Asignar Repartidor / Iniciar Entrega");
        btnRepartidores = new JButton("Gestionar Repartidores");
        btnEntregas = new JButton("Gestionar Entregas");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEntregar);
        panelBotones.add(btnRepartidores);
        panelBotones.add(btnEntregas);

        add(panelBotones);

        btnRegistrar.addActionListener(e -> {
            new VentanaRegistroPedido(controlador);
        });

        btnListar.addActionListener(e -> {
            new VentanaListaPedidos();
        });

        btnEntregar.addActionListener(e -> iniciarEntregas());


        btnRepartidores.addActionListener(e -> {
            new VentanaRepartidores();
        });

        btnEntregas.addActionListener(e -> {
            new VentanaEntregas();
        });

        setVisible(true);
    }

    private void iniciarEntregas() {

        if (controlador.getHistorial().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay pedidos registrados.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        for (Pedido pedido : controlador.getHistorial()) {
            if (pedido.getEstado() == modelo.EstadoPedido.PENDIENTE) {
                zonaDeCarga.agregarPedido(pedido);
            }
        }

        Repartidor repartidor1 = new Repartidor("Felipe", zonaDeCarga);
        Repartidor repartidor2 = new Repartidor("Luis", zonaDeCarga);
        Repartidor repartidor3 = new Repartidor("Camila", zonaDeCarga);

        Thread hilo1 = new Thread(repartidor1);
        Thread hilo2 = new Thread(repartidor2);
        Thread hilo3 = new Thread(repartidor3);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        JOptionPane.showMessageDialog(
                this,
                "Las entregas han comenzado.",
                "SpeedFast",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

}
