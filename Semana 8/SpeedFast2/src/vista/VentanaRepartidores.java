package vista;

import dao.RepartidorDAO;
import modelo.Repartidor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaRepartidores extends JFrame {

    private JTextField txtNombre;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTabla;

    private RepartidorDAO repartidorDAO;

    public VentanaRepartidores() {

        repartidorDAO = new RepartidorDAO();

        setTitle("Gestión de Repartidores");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel superior
        JPanel panelFormulario = new JPanel(new FlowLayout());

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField(20);
        btnRegistrar = new JButton("Registrar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");

        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(btnRegistrar);
        panelFormulario.add(btnEditar);
        panelFormulario.add(btnEliminar);

        // Tabla
        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");

        tablaRepartidores = new JTable(modeloTabla);

        tablaRepartidores.setRowHeight(25);
        tablaRepartidores.getTableHeader()
                .setReorderingAllowed(false);

        add(panelFormulario, BorderLayout.NORTH);
        add(new JScrollPane(tablaRepartidores), BorderLayout.CENTER);

        // Botón registrar
        btnRegistrar.addActionListener(e -> registrarRepartidor());
        btnEditar.addActionListener(e -> editarRepartidor());
        btnEliminar.addActionListener(e -> eliminarRepartidor());

        cargarRepartidores();

        setVisible(true);
    }

    private void cargarRepartidores() {

        modeloTabla.setRowCount(0);

        for (Repartidor repartidor : repartidorDAO.listar()) {

            modeloTabla.addRow(new Object[]{
                    repartidor.getId(),
                    repartidor.getNombre()
            });
        }
    }

    private void registrarRepartidor() {

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del repartidor.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Repartidor repartidor =
                new Repartidor(nombre, null);

        boolean registrado = repartidorDAO.insertar(repartidor);

        if (registrado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor #" + repartidor.getId()
                            + " registrado correctamente."
            );

            txtNombre.setText("");

            cargarRepartidores();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible registrar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void editarRepartidor() {

        int filaSeleccionada = tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor de la tabla.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String nuevoNombre = txtNombre.getText().trim();

        if (nuevoNombre.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nuevo nombre.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        Repartidor repartidor =
                new Repartidor(id, nuevoNombre, null);

        boolean actualizado = repartidorDAO.actualizar(repartidor);

        if (actualizado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor actualizado correctamente."
            );

            txtNombre.setText("");

            cargarRepartidores();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible actualizar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void eliminarRepartidor() {

        int filaSeleccionada = tablaRepartidores.getSelectedRow();

        if (filaSeleccionada == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un repartidor de la tabla.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de eliminar al repartidor " + nombre + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = repartidorDAO.eliminar(id);

        if (eliminado) {

            JOptionPane.showMessageDialog(
                    this,
                    "Repartidor eliminado correctamente."
            );

            txtNombre.setText("");

            cargarRepartidores();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible eliminar el repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
