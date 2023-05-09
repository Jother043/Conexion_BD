import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio5_8 {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        String usuario = "root";
        String password = "admin.123";

        try (Connection conexion = DriverManager.getConnection(url, usuario, password)){
            System.out.println("Introduce el nombre del producto: ");
            String nombre = sc.nextLine();
            //Creamos el Statement para poder hacer las consultas y ejecutarlas.
            Statement sentencia = conexion.createStatement();
            //Creamos la consulta
            String sql = "SELECT productVendor FROM products WHERE productName = '" + nombre + "'";
            //Ejecutamos la consulta
            sentencia.executeQuery(sql);
            //Comprobamos si se ha ejecutado la consulta
            if (sentencia.execute(sql)) {
                System.out.println("Se ha ejecutado la consulta");
            }else {
                System.err.println("No se ha ejecutado la consulta");
            }
            //Mostramos los resultados
            System.out.println("El proveedor del producto " + nombre + " es: ");
            //Recorremos los resultados mientras haya y los mostramos.
            while (sentencia.getResultSet().next()) {
                System.out.println(sentencia.getResultSet().getString("productVendor"));
            }

        } catch (SQLException e) {
            System.err.println("Error al realizar la conexión con la base de datos " + e.getMessage());
        }
    }
}
