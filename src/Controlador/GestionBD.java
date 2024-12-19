/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Mensualidad;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * 
 */
public class GestionBD {

    /**
     * Clientes
     *
     *
     *
     */
    public static int altaCliente(Cliente c) {
        try {
            String existe = "SELECT * FROM Clientes WHERE DNI = '" + c.getDni() + "'";

            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(existe);

            if (rs.next()) {
                return 0;
            } else {
                int numCli = 1;
                String listaClientes = "SELECT * FROM Clientes ORDER BY NumClientes DESC";
                ResultSet rs1 = st.executeQuery(listaClientes);
                while (rs1.next()) {
                    numCli = rs1.getInt("NumClientes") + 1;
                    break;

                }
                String insert = "INSERT INTO Clientes(DNI, Nombre, Apellidos,Telefono,Dirección, Correo, Activo, Ruta_foto, NumClientes, Observaciones) "
                        + "VALUES ('" + c.getDni() + "','" + c.getNombre() + "','" + c.getApellidos() + "','"
                        + c.getTelefono() + "','" + c.getDireccion() + "','" + c.getCorreo() + "',true,'" + c.getRuta_foto() + "','" + numCli + "','" + c.getObservaciones() + "')";
                st.executeUpdate(insert);
                Mensualidad m = new Mensualidad(c.getDni());
                if (crearMensualidad(m) != 0) {
                    return -2;
                }
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }

    public static int modCliente(Cliente c) throws SQLException {
        //igual que alta
        try {
            String existeCliente = "SELECT * FROM Clientes WHERE DNI = ?";
            PreparedStatement psExisteCliente = Conexion.getConexion().prepareStatement(existeCliente);
            psExisteCliente.setString(1, c.getDni());
            ResultSet rs = psExisteCliente.executeQuery();

            if (!rs.next()) {
                // Si el cliente no existe, retorna -1
                return -1;
            }
            String actualizarCliente = "UPDATE Clientes SET Nombre = ?, Apellidos = ?, Telefono = ?, Dirección = ?, Correo = ?, Activo = ?, Ruta_foto = ?, Observaciones = ? WHERE DNI = ?";
            PreparedStatement psActualizar = Conexion.getConexion().prepareStatement(actualizarCliente);

            // Establece los cambios
            psActualizar.setString(1, c.getNombre());
            psActualizar.setString(2, c.getApellidos());
            psActualizar.setString(3, c.getTelefono());
            psActualizar.setString(4, c.getDireccion());
            psActualizar.setString(5, c.getCorreo());
            psActualizar.setBoolean(6, c.isActivo());
            psActualizar.setString(7, c.getRuta_foto());
            psActualizar.setString(8, c.getObservaciones());
            psActualizar.setString(9, c.getDni());

            psActualizar.executeUpdate();

            //1 si funciona
            return 1;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        //0 si se produce error
        return 0;
    }

    public static int borrarCliente(String dni) throws SQLException {

        try {
            // Comprueba si cliente existe
            String existeCliente = "SELECT * FROM Clientes WHERE DNI = ?";
            PreparedStatement psExisteCliente = Conexion.getConexion().prepareStatement(existeCliente);
            psExisteCliente.setString(1, dni);
            ResultSet rs = psExisteCliente.executeQuery();
            if (!rs.next()) {
                // Si el cliente no existe, retornamos -1 indicando error
                return -1;
            }
            //borra mensualidades (si existen)
            String borrarMensualidades = "DELETE FROM Mensualidad WHERE Clientes_DNI = ?";
            PreparedStatement psBorrarMensualidades = Conexion.getConexion().prepareStatement(borrarMensualidades);
            psBorrarMensualidades.setString(1, dni);
            psBorrarMensualidades.executeUpdate();
            //borra cliente
            String borrarCliente = "DELETE FROM Clientes WHERE DNI = ?";
            PreparedStatement psBorrarCliente = Conexion.getConexion().prepareStatement(borrarCliente);
            psBorrarCliente.setString(1, dni);
            psBorrarCliente.executeUpdate();

            // el return es 1 si funciona
            return 1;

        } catch (SQLException ex) {
            System.out.println(ex);
            //Si hay error retorna  0
            return 0;
        }

    }

    /*Mensualidades
    *
    *
    *
     */
 /*Mensualidades
    *
    *
    *
     */
    public static int crearMensualidad(Mensualidad m) {
        //igual que alta cliente
        try {

            String existe = "SELECT * FROM Mensualidad WHERE Clientes_DNI='" + m.getDni() + "' AND Mes_Ano='" + m.getMes_ano() + "'";
            String insert = "INSERT INTO Mensualidad(Clientes_DNI,Mes_Ano,Pagado,Fecha,Precio)\n"
                    + "VALUES ('" + m.getDni() + "','" + m.getMes_ano() + "'," + m.getPagado()
                    + ",null,'" + m.getCuota() + "');";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(existe);
            if (rs.next()) {
                return -1;
            } else {
                st.executeUpdate(insert);
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex + " - seccion 1");
            return 1;
        }
    }

    public static int borrarMensualidad(Mensualidad m) {

        //¿solo cuando se desactiva un cliente?
        //comprueba que existe
        return 0;
    }

    public static int pagarMensualidad(String dni, String mes_ano, double cantidad) {
        try {
            String existe = "SELECT * FROM Mensualidad WHERE Clientes_DNI='" + dni + "' AND Mes_Ano='" + mes_ano + "'";
            String update = "UPDATE Mensualidad SET Pagado=?,Fecha=?,Precio=? WHERE Clientes_DNI=? AND Mes_Ano=?";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(existe);
            Date fecha = new Date();
            java.sql.Date fechaPago = new java.sql.Date(fecha.getTime());
            if (rs.next()) {
                PreparedStatement ps = Conexion.getConexion().prepareStatement(update);
                ps.setBoolean(1, true);
                ps.setDate(2, fechaPago);
                ps.setDouble(3, cantidad);
                ps.setString(4, dni);
                ps.setString(5, mes_ano);

                ps.executeUpdate();

                return 0;
            } else {
                String insert = "INSERT INTO Mensualidad(Clientes_DNI,Mes_Ano,Pagado,Fecha,Precio)\n"
                        + "VALUES (?,?,?,?,?);";
                PreparedStatement ps = Conexion.getConexion().prepareStatement(insert);
                ps.setBoolean(3, true);
                ps.setDate(4, fechaPago);
                ps.setDouble(5, cantidad);
                ps.setString(1, dni);
                ps.setString(2, mes_ano);

                ps.executeUpdate();
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return 1;
        }
    }

    /*Otros
    *
    *
    *
     */
    public static ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            //pilla todos los clientes de la base de datos
            //Ordenados de forma ascendente
            String consulta = "SELECT * FROM Clientes ORDER BY NumClientes ASC";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setDni(rs.getString("DNI"));
                c.setNombre(rs.getString("Nombre"));
                c.setApellidos(rs.getString("Apellidos"));
                c.setTelefono(rs.getString("Telefono"));
                c.setDireccion(rs.getString("Dirección"));
                c.setCorreo(rs.getString("Correo"));
                c.setActivo(rs.getBoolean("Activo"));
                c.setRuta_foto(rs.getString("Ruta_foto"));
                c.setNum_cliente(rs.getInt("NumClientes"));
                c.setObservaciones(rs.getString("Observaciones"));
                clientes.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return clientes;
    }

    public static ArrayList<Mensualidad> getMensualidades(String dni) {
        ArrayList<Mensualidad> mensualidades = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM Mensualidad WHERE Clientes_DNI='" + dni + "'";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            Mensualidad m = new Mensualidad();
            while (rs.next()) {
                m.setDni(dni);
                m.setMes_ano(rs.getString("Mes_Ano"));
                m.setPagado(rs.getBoolean("Pagado"));
                m.setFecha(rs.getDate("Fecha"));
                m.setCuota(rs.getDouble("Precio"));
                mensualidades.add(m);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mensualidades;
    }

    public static boolean isMoroso(String dni) {
        try {
            boolean moroso = false;

            String consulta = "SELECT * FROM Mensualidad WHERE Clientes_DNI = ? AND Pagado = FALSE";
            PreparedStatement psExisteCliente = Conexion.getConexion().prepareStatement(consulta);
            psExisteCliente.setString(1, dni);
            ResultSet rs = psExisteCliente.executeQuery();
            if (rs.next()) {
                moroso = true;
            }
            return moroso;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static boolean existeMensualidad(Mensualidad m) {
        try {
            String existe = "SELECT * FORM Mensualidad WHERE Mes_Ano='" + m.getMes_ano() + "'";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(existe);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {

        }
        return false;

    }

    public static Iterable<Cliente> getClientesActivos() {

        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
            //pilla todos los clientes de la base de datos
            String consulta = "SELECT * FROM Clientes WHERE Activo";
            Statement st = Conexion.getConexion().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setDni(rs.getString("DNI"));
                c.setNombre(rs.getString("Nombre"));
                c.setApellidos(rs.getString("Apellidos"));
                c.setTelefono(rs.getString("Telefono"));
                c.setDireccion(rs.getString("Dirección"));
                c.setCorreo(rs.getString("Correo"));
                c.setActivo(rs.getBoolean("Activo"));
                c.setRuta_foto(rs.getString("Ruta_foto"));
                c.setNum_cliente(rs.getInt("NumClientes"));
                c.setObservaciones(rs.getString("Observaciones"));

                clientes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return clientes;

    }
}
