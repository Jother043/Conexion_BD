import java.sql.*;
import java.util.Scanner;
public class Ejercicio4_8 {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        //URL de conexión
        String url = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC&useSSL=true&characterEncoding=UTF-8";
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
            //Creamos el update para cambiar los nombres de los productos a mayúsculas.
            String sql = "UPDATE products SET productName = UPPER(productName) WHERE buyPrice < " + cantidad + " AND productName LIKE '" + letra + "%'";
            //Creamos una consulta para mostrar los productos que cumplan la condición.
            String sql2 = "SELECT * FROM products WHERE buyPrice < " + cantidad + " AND productName LIKE '" + letra + "%'";
            //Ejecutamos la consulta
            sentencia.executeUpdate(sql);
            ResultSet resultados = sentencia.executeQuery(sql2);

            //Mostramos los resultados
            System.out.println("Los productos con un precio menor que " + cantidad + " y que empiezan por " + letra + " son: ");
            //Recorremos los resultados mientras haya y los mostramos.
            while (resultados.next()) {
                String codigo = resultados.getString("productCode");
                String nombre = resultados.getString("productName");
                double precio = resultados.getDouble("buyPrice");
                System.out.println(codigo + " - " + nombre + " - " + precio);
            }

            resultados.close();
            sentencia.close();

        } catch (SQLException e) {
            System.err.println("Error al realizar la conexión con la base de datos " + e.getMessage());
        }
    }
}
