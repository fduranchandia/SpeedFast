package dao;

import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO {

    // CREAR
    public boolean insertar(Pedido pedido) {

        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, obtenerTipo(pedido));
            ps.setString(3, pedido.getEstado().name());

            int filas = ps.executeUpdate();

            if (filas > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        pedido.setIdPedido(rs.getInt(1));
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar pedido: " + e.getMessage());
        }

        return false;
    }


    // LEER
    public List<Pedido> listar() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado FROM pedidos";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estado = rs.getString("estado");

                Pedido pedido = crearPedido(id, direccion, tipo);

                pedido.setEstado(EstadoPedido.valueOf(estado));

                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }


    // ACTUALIZAR
    public boolean actualizar(Pedido pedido) {

        String sql = "UPDATE pedidos SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, pedido.getDireccionEntrega());
            ps.setString(2, obtenerTipo(pedido));
            ps.setString(3, pedido.getEstado().name());
            ps.setInt(4, pedido.getIdPedido());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar pedido: " + e.getMessage());
        }

        return false;
    }


    // ELIMINAR
    public boolean eliminar(int id) {

        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
        }

        return false;
    }


    // Determina el tipo según la clase concreta del pedido
    private String obtenerTipo(Pedido pedido) {

        if (pedido instanceof PedidoComida) {
            return "COMIDA";
        }

        if (pedido instanceof PedidoEncomienda) {
            return "ENCOMIENDA";
        }

        if (pedido instanceof PedidoExpress) {
            return "EXPRESS";
        }

        throw new IllegalArgumentException("Tipo de pedido no reconocido");
    }


    // Crea el objeto correcto según el tipo guardado en MySQL
    private Pedido crearPedido(int id, String direccion, String tipo) {

        switch (tipo) {

            case "COMIDA":
                return new PedidoComida(id, direccion, 0);

            case "ENCOMIENDA":
                return new PedidoEncomienda(id, direccion, 0);

            case "EXPRESS":
                return new PedidoExpress(id, direccion, 0);

            default:
                throw new IllegalArgumentException(
                        "Tipo de pedido desconocido: " + tipo
                );
        }
    }

}
