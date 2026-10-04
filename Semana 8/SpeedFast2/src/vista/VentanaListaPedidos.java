package vista;

import dao.PedidoDAO;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private PedidoDAO pedidoDAO;

    public VentanaListaPedidos() {

        pedidoDAO = new PedidoDAO();

        setTitle("Lista de Pedidos");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Estado");

        tablaPedidos = new JTable(modeloTabla);

        tablaPedidos.setRowHeight(25);
        tablaPedidos.getTableHeader().setReorderingAllowed(false);

        add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        cargarPedidos();

        setVisible(true);
    }

    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : pedidoDAO.listar()) {

            modeloTabla.addRow(new Object[]{
                    pedido.getIdPedido(),
                    pedido.getDireccionEntrega(),
                    pedido.getClass().getSimpleName(),
                    pedido.getEstado()
            });
        }
    }
}