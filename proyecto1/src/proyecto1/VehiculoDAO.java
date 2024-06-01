/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author joseb
 */
   import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {
    private final String url = "jdbc:mysql://localhost:3306/proyectos";
    private final String user = "root"; // Reemplaza con tu usuario
    private final String password = "labeth2001"; // Reemplaza con tu contraseña

    public VehiculoDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos (marca, modelo, año, precio) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, vehiculo.getMarca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setInt(3, vehiculo.getAño());
            stmt.setDouble(4, vehiculo.getPrecio());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                vehiculo.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehiculo> readVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getInt("id"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("año"),
                        rs.getDouble("precio")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculos;
    }

    public Vehiculo readVehiculoById(int id) {
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM vehiculos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    vehiculo = new Vehiculo(
                            rs.getInt("id"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("año"),
                            rs.getDouble("precio")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehiculo;
    }

    public void updateVehiculo(int id, Vehiculo updatedVehiculo) {
        String sql = "UPDATE vehiculos SET marca = ?, modelo = ?, año = ?, precio = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, updatedVehiculo.getMarca());
            stmt.setString(2, updatedVehiculo.getModelo());
            stmt.setInt(3, updatedVehiculo.getAño());
            stmt.setDouble(4, updatedVehiculo.getPrecio());
            stmt.setInt(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteVehiculo(int id) {
        String sql = "DELETE FROM vehiculos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
