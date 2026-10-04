package dao;

import modelo.Entrega;
import modelo.Pedido;
import modelo.PedidoComida;
import modelo.PedidoEncomienda;
import modelo.PedidoExpress;
import modelo.Repartidor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    // CREAR
    public boolean insertar(Entrega entrega) {

        String sql = "INSERT INTO entregas " +
                "(id_pedido, id_repartidor, fecha, hora) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, entrega.getPedido().getIdPedido());
            ps.setInt(2, entrega.getRepartidor().getId());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));

            int filas = ps.executeUpdate();

            if (filas > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        entrega.setId(rs.getInt(1));
                    }
                }

                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar entrega: "
                    + e.getMessage());
        }

        return false;
    }


    // LEER
    public List<Entrega> listar() {

        List<Entrega> lista = new ArrayList<>();

        String sql = """
                SELECT e.id,
                       e.fecha,
                       e.hora,
                       p.id AS pedido_id,
                       p.direccion,
                       p.tipo,
                       p.estado,
                       r.id AS repartidor_id,
                       r.nombre
                FROM entregas e
                INNER JOIN pedidos p
                    ON e.id_pedido = p.id
                INNER JOIN repartidores r
                    ON e.id_repartidor = r.id
                """;

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                int idEntrega = rs.getInt("id");

                int idPedido = rs.getInt("pedido_id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");

                Pedido pedido;

                switch (tipo) {
                    case "COMIDA":
                        pedido = new PedidoComida(
                                idPedido, direccion, 0);
                        break;

                    case "ENCOMIENDA":
                        pedido = new PedidoEncomienda(
                                idPedido, direccion, 0);
                        break;

                    case "EXPRESS":
                        pedido = new PedidoExpress(
                                idPedido, direccion, 0);
                        break;

                    default:
                        throw new IllegalArgumentException(
                                "Tipo de pedido desconocido: " + tipo);
                }

                int idRepartidor = rs.getInt("repartidor_id");
                String nombreRepartidor = rs.getString("nombre");

                Repartidor repartidor =
                        new Repartidor(
                                idRepartidor,
                                nombreRepartidor,
                                null);

                java.time.LocalDate fecha =
                        rs.getDate("fecha").toLocalDate();

                java.time.LocalTime hora =
                        rs.getTime("hora").toLocalTime();

                Entrega entrega = new Entrega(
                        idEntrega,
                        pedido,
                        repartidor,
                        fecha,
                        hora);

                lista.add(entrega);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar entregas: "
                    + e.getMessage());
        }

        return lista;
    }


    // ACTUALIZAR
    public boolean actualizar(Entrega entrega) {

        String sql = "UPDATE entregas SET " +
                "id_pedido = ?, " +
                "id_repartidor = ?, " +
                "fecha = ?, " +
                "hora = ? " +
                "WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, entrega.getPedido().getIdPedido());
            ps.setInt(2, entrega.getRepartidor().getId());
            ps.setDate(3, Date.valueOf(entrega.getFecha()));
            ps.setTime(4, Time.valueOf(entrega.getHora()));
            ps.setInt(5, entrega.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar entrega: "
                    + e.getMessage());
        }

        return false;
    }


    // ELIMINAR
    public boolean eliminar(int id) {

        String sql = "DELETE FROM entregas WHERE id = ?";

        try (Connection conexion = ConexionDB.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar entrega: "
                    + e.getMessage());
        }

        return false;
    }

}
