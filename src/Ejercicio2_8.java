import java.sql.*;

public class Ejercicio2_8 {
    //Creamos la consulta como constante.
    private static final String SQL = "SELECT * FROM products where buyPrice < 400";
public static void main(String[] args) {
        //Conexión con la base de datos.
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        String usuario = "root";
        String password = "admin.123";
        //Conexión a la base de datos en un try-with-resources para que se cierre automáticamente.
        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            //Creamos el Statement para poder hacer las consultas y ejecutarlas.
            Statement sentencia = conexion.createStatement();
            //Creamos la consulta
            String sql = SQL;
            //Ejecutamos la consulta
            sentencia.executeQuery(sql);
            if (sentencia.execute(sql)) {
                System.out.println("Se ha ejecutado la consulta");
            }else {
                System.err.println("No se ha ejecutado la consulta");
            }
            ResultSet rs = sentencia.getResultSet();
            int contador = 1;
            while (rs.next()) {
                System.out.println("Producto " + (contador++) + ": " + rs.getString("productName") + ". Precio producto: " + rs.getDouble("buyPrice"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
