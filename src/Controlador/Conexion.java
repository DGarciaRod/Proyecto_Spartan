package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion{
    private static Connection Con;
    
    public static boolean conectar(String url, String puerto, String bd, String usuario, String clave) {
        try{
            usuario="root";
            clave="2877";
            Class.forName("com.mysql.jdbc.Driver");
            String urlCon="jdbc:mysql://"+url+":"+puerto+"/"+bd+"?user="+usuario+"&password="+clave;
            Con=DriverManager.getConnection(urlCon);
//            Con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practicaud2", "root", "root");
            Con.setAutoCommit(true);
            //Con setAutocommitFalse  permite hacer varias operaciones en una transaccion
            //En el caso de que sea true se inicia una transacción automaticamente bien cuando se hace una operacion bien cuando
            // se recorren todos los registros en el caso de que se devuelvan en un resulset.
            //En general están en modo de automatico
          
            return true;
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, "No se ha podido conetar");
            
            return false;
        }
    }
    
    public static Connection getConexion() {
        return Con;
    }
}
