package vista;

import dao.EntregaDAO;
import dao.PedidoDAO;
import dao.RepartidorDAO;
import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaEntregas extends JFrame {

    private JComboBox<Pedido> comboPedido;
    private JComboBox<Repartidor> comboRepartidor;
    private JButton btnRegistrar;

    private JTable tablaEntregas;
    private DefaultTableModel modeloTabla;

    private EntregaDAO entregaDAO;
    private PedidoDAO pedidoDAO;
    private RepartidorDAO repartidorDAO;

    public VentanaEntregas() {

        entregaDAO = new EntregaDAO();
        pedidoDAO = new PedidoDAO();
        repartidorDAO = new RepartidorDAO();

        setTitle("Gestión de Entregas");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario = new JPanel(new FlowLayout());

        JLabel lblPedido = new JLabel("Pedido:");
        JLabel lblRepartidor = new JLabel("Repartidor:");

        comboPedido = new JComboBox<>();
        comboRepartidor = new JComboBox<>();

        btnRegistrar = new JButton("Registrar Entrega");

        panelFormulario.add(lblPedido);
        panelFormulario.add(comboPedido);

        panelFormulario.add(lblRepartidor);
        panelFormulario.add(comboRepartidor);

        panelFormulario.add(btnRegistrar);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Pedido");
        modeloTabla.addColumn("Repartidor");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");

        tablaEntregas = new JTable(modeloTabla);

        tablaEntregas.setRowHeight(25);
        tablaEntregas.getTableHeader()
                .setReorderingAllowed(false);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarEntrega());

        cargarPedidos();
        cargarRepartidores();
        cargarEntregas();

        setVisible(true);
    }

    private void cargarPedidos() {

        comboPedido.removeAllItems();

        for (Pedido pedido : pedidoDAO.listar()) {
            comboPedido.addItem(pedido);
        }
    }

    private void cargarRepartidores() {

        comboRepartidor.removeAllItems();

        for (Repartidor repartidor : repartidorDAO.listar()) {
            comboRepartidor.addItem(repartidor);
        }
    }

    private void cargarEntregas() {

        modeloTabla.setRowCount(0);

        for (Entrega entrega : entregaDAO.listar()) {

            modeloTabla.addRow(new Object[]{
                    entrega.getId(),
                    entrega.getPedido().getIdPedido(),
                    entrega.getRepartidor().getNombre(),
                    entrega.getFecha(),
                    entrega.getHora()
            });
        }
    }
    private void registrarEntrega() {

        Pedido pedidoSeleccionado =
                (Pedido) comboPedido.getSelectedItem();

        Repartidor repartidorSeleccionado =
                (Repartidor) comboRepartidor.getSelectedItem();

        if (pedidoSeleccionado == null || repartidorSeleccionado == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un pedido y un repartidor.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Entrega entrega = new Entrega(
                0,
                pedidoSeleccionado,
                repartidorSeleccionado,
                java.time.LocalDate.now(),
                java.time.LocalTime.now()
        );

        boolean registrada = entregaDAO.insertar(entrega);

        if (registrada) {

            JOptionPane.showMessageDialog(
                    this,
                    "Entrega #" + entrega.getId()
                            + " registrada correctamente."
            );

            cargarEntregas();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar la entrega.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

}