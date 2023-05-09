import Conn.DataBaseConnection;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Ejercicio4_8 {

    public static void main(String[] args) {

        try (Connection conexion = DataBaseConnection.getInstance().getConexion();
             Scanner sc = new Scanner(System.in); ) {
            //Pedimos los datos al usuario
            System.out.println("Introduce la cantidad menor que el precio de los productos que quieres ver: ");
            int cantidad;
            boolean correcto = false;
            do {
                try {
                    cantidad = sc.nextInt();
                    correcto = true;
                }catch (InputMismatchException e ) {
                    System.err.println("Introduce un número");
                    cantidad = -1;
                    sc.nextLine();
                }
            } while (!correcto);
            System.out.println("Introduce la primera letra del nombre de los productos que quieres ver: ");
            char letra;
            do {
                letra = sc.next().toUpperCase().charAt(0);
            } while (!Character.isLetter(letra));
            //Creamos el Statement para poder hacer las consultas y ejecutarlas.
            PreparedStatement sentencia = conexion.prepareStatement("UPDATE products SET productName = UPPER(productName) WHERE MSRP < ? AND upper(productName) LIKE ?");
            PreparedStatement sentencia2 = conexion.prepareStatement("SELECT * FROM products WHERE MSRP < ? AND UPPER(productName) LIKE ?");
            //creamos la sentencia con los datos del usuario y la ejecutamos con executeUpdate porque es un UPDATE y no un SELECT.
            sentencia.setInt(1, cantidad);
            sentencia.setString(2, letra + "%");
            //creamos la sentencia con los datos del usuario y la ejecutamos con executeQuery porque es un SELECT y no un UPDATE.
            sentencia2.setInt(1, cantidad);
            sentencia2.setString(2, letra + "%");
            //Ejecutamos la consulta
            sentencia.executeUpdate();
            ResultSet resultados = sentencia2.executeQuery();

            //Mostramos los resultados
            System.out.println("Los productos con un precio menor que " + cantidad + " y que empiezan por " + letra + " son: ");
            //Recorremos los resultados mientras haya y los mostramos.
            while (resultados.next()) {
                String codigo = resultados.getString("productCode");
                String nombre = resultados.getString("productName");
                double precio = resultados.getDouble("MSRP");
                System.out.println(codigo + " - " + nombre + " - " + precio);
            }

            resultados.close();
            sentencia.close();
            sentencia2.close();

        } catch (SQLException e) {
            System.err.println("Error al realizar la conexión con la base de datos " + e.getMessage());
        }
    }
}
