import java.sql.*;

public class Ejercicio1_8 {
    public static void main(String[] args) {

        //Conexión con la base de datos.
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        String usuario = "root";
        String password = "admin.123";
        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            //Creamos el Statement para poder hacer las consultas y ejecutarlas.
            Statement sentencia = conexion.createStatement();
            //Creamos la consulta
            String sql = "SELECT * FROM products";
            //Ejecutamos la consulta
            sentencia.executeQuery(sql);
            //Comprobamos si se ha ejecutado la consulta
            if (sentencia.execute(sql)) {
                System.out.println("Se ha ejecutado la consulta");
            }else{
                System.err.println("No se ha ejecutado la consulta");
            }
            //Mostramos los resultados de la consulta de la tabla products de la base de datos classicmodels.
            ResultSet rs = sentencia.getResultSet();
            //Recorremos los resultados mientras haya y los mostramos.
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

