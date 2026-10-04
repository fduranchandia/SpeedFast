package vista;

import dao.PedidoDAO;
import modelo.ControladorDeEnvios;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtDireccion;
    private JComboBox<String> comboTipo;
    private JButton btnGuardar;

    private ControladorDeEnvios controlador;
    private PedidoDAO pedidoDAO;

    public VentanaRegistroPedido(ControladorDeEnvios controlador) {

        this.controlador = controlador;
        this.pedidoDAO = new PedidoDAO();

        setTitle("Registrar Pedido");
        setSize(450, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelFormulario =
                new JPanel(new GridLayout(3, 2, 10, 10));

        panelFormulario.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        JLabel lblDireccion = new JLabel("Dirección:");
        JLabel lblTipo = new JLabel("Tipo:");

        txtDireccion = new JTextField();

        comboTipo = new JComboBox<>();
        comboTipo.addItem("Comida");
        comboTipo.addItem("Encomienda");
        comboTipo.addItem("Express");

        btnGuardar = new JButton("Guardar");

        panelFormulario.add(lblDireccion);
        panelFormulario.add(txtDireccion);

        panelFormulario.add(lblTipo);
        panelFormulario.add(comboTipo);

        panelFormulario.add(new JLabel());
        panelFormulario.add(btnGuardar);

        add(panelFormulario);

        btnGuardar.addActionListener(e -> guardarPedido());

        setVisible(true);
    }

    private void guardarPedido() {

        String direccion = txtDireccion.getText().trim();
        String tipo = (String) comboTipo.getSelectedItem();

        // Validación
        if (direccion.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar una dirección.",
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Pedido pedido;

        // El ID comienza en 0 porque MySQL generará el ID real
        switch (tipo) {

            case "Comida":
                pedido = new PedidoComida(0, direccion, 0.0);
                break;

            case "Encomienda":
                pedido = new PedidoEncomienda(0, direccion, 0.0);
                break;

            case "Express":
                pedido = new PedidoExpress(0, direccion, 0.0);
                break;

            default:
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un tipo de pedido.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
        }

        // Guardar en MySQL
        boolean guardado = pedidoDAO.insertar(pedido);

        if (guardado) {

            // Mantener también el pedido en el controlador
            controlador.registrarEntrega(pedido);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido #" + pedido.getIdPedido()
                            + " registrado correctamente."
            );

            txtDireccion.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el pedido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}