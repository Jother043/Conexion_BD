import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ejercicio3_8 {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //URL de conexión
        String url = "jdbc:mysql://localhost:3309/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
        //Usuario
        String usuario = "root";
        //Contraseña
        String password = "admin.123";
        //Conexión a la base de datos en un try-with-resources para que se cierre automáticamente.
        try (Connection conexion = DriverManager.getConnection(url, usuario, password)) {
            //Pedimos los datos al usuario
            System.out.println("Introduce la cantidad menor que el precio de los productos que quieres ver: ");
            int cantidad = sc.nextInt();
            System.out.println("Introduce la primera letra del nombre de los productos que quieres ver: ");
            String letra = sc.next();
            //Creamos el Statement para poder hacer las consultas y ejecutarlas.
            Statement sentencia = conexion.createStatement();
            //Creamos la consulta
            String sql = "SELECT * FROM products WHERE buyPrice < " + cantidad + " AND productName LIKE '" + letra + "%'";
            //Ejecutamos la consulta
            sentencia.executeQuery(sql);
            //Comprobamos si se ha ejecutado la consulta
            if (sentencia.execute(sql)) {
                System.out.println("Se ha ejecutado la consulta");
            }else {
                System.err.println("No se ha ejecutado la consulta");
            }
            //Mostramos los resultados
            System.out.println("Los productos con un precio menor que " + cantidad + " y que empiezan por " + letra + " son: ");
            //Recorremos los resultados mientras haya y los mostramos.
            while (sentencia.getResultSet().next()) {
                System.out.println(sentencia.getResultSet().getString("productName"));
            }
        } catch (SQLException e) {
            System.err.println("Error al realizar la conexión con la base de datos " + e.getMessage());
        }

    }
}
