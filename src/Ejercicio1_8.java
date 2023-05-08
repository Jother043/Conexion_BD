import java.sql.*;

public class Ejercicio1_8 {
    public static void main(String[] args) {

        //Conexión con la base de datos.
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        String usuario = "root";
        String password = "admin.123";
        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM products";
            sentencia.executeQuery(sql);
            if (sentencia.execute(sql)) {
                System.out.println("Se ha ejecutado la consulta");
            }
            ResultSet rs = sentencia.getResultSet();
            int contador = 1;
            while (rs.next()) {
                System.out.println("Producto " + (contador++) + ": " + rs.getString("productName") + "\n  Linea de producto: " + rs.getString("productLine")
                        + " \n  Escala producto: " + rs.getString("productScale") + " \n  Vendedor del producto: " + rs.getString("productVendor") + " \n  Descripción del producto: "
                        + rs.getString("productDescription") + " \n  Stock producto: " + rs.getInt("quantityInStock") + " \n  Precio de producto: " + rs.getDouble("buyPrice") + " "
                        + rs.getDouble("MSRP"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

