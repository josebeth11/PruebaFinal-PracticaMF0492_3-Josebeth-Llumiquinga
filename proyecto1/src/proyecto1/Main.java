/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1;
import java.util.List;
/**
 *
 * @author joseb
 */




import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Creamos una instancia de VehiculoDAO
        VehiculoDAO vehiculoDAO = new VehiculoDAO();

        // Crear un nuevo vehículo
        Vehiculo vehiculoNuevo = new Vehiculo(0, "Toyota", "Corolla", 2021, 25000);
        vehiculoDAO.createVehiculo(vehiculoNuevo);
        System.out.println("Vehículo creado: " + vehiculoNuevo);

        // Leer todos los vehículos
        List<Vehiculo> vehiculos = vehiculoDAO.readVehiculos();
        System.out.println("Todos los vehículos:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }

        // Leer un vehículo por su ID
        int idBusqueda = 1; // Suponemos que el ID 1 existe en la base de datos
        Vehiculo vehiculoEncontrado = vehiculoDAO.readVehiculoById(idBusqueda);
        if (vehiculoEncontrado != null) {
            System.out.println("Vehículo encontrado con ID " + idBusqueda + ": " + vehiculoEncontrado);
        } else {
            System.out.println("No se encontró el vehículo con ID " + idBusqueda);
        }

        // Actualizar un vehículo
        if (vehiculoEncontrado != null) {
            vehiculoEncontrado.setPrecio(22000); // Actualizamos el precio del vehículo encontrado
            vehiculoDAO.updateVehiculo(idBusqueda, vehiculoEncontrado);
            System.out.println("Vehículo actualizado: " + vehiculoEncontrado);
        }

        // Eliminar un vehículo
        int idEliminar = 2; // Suponemos que el ID 2 existe en la base de datos
        vehiculoDAO.deleteVehiculo(idEliminar);
        System.out.println("Vehículo con ID " + idEliminar + " eliminado.");

        // Leer todos los vehículos nuevamente para verificar la eliminación
        vehiculos = vehiculoDAO.readVehiculos();
        System.out.println("Todos los vehículos después de la eliminación:");
        for (Vehiculo vehiculo : vehiculos) {
            System.out.println(vehiculo);
        }
    }
}
