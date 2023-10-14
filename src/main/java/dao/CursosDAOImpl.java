
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Conexion;
import modelos.Cursos;


public class CursosDAOImpl implements ICursosDAO{

    @Override
    public boolean registrar(Cursos curso) {
        Conexion co = new Conexion();
        String xcod = co.generarCodigo("cursos", "codigo");
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "INSERT INTO cursos values (" + xcod + ",'"
                + curso.getNombre() + "'," + curso.getCosto() + ",'" + curso.getFec_ini() + "','"
                + curso.getFec_fin() + "'," + curso.getDuracion() + ","
                + curso.getSesiones() + "," + curso.getCapacidad() + ","
                + curso.getInscritos() + ",'" + curso.getEstado() + "')";
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDao Impl," + "método registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public List<Cursos> obtener() {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos ORDER BY codigo";
        List<Cursos> listaCursos = new ArrayList<Cursos>();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cursos curso = new Cursos();
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getDouble(3));
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidad(rs.getInt(8));
                curso.setInscritos(rs.getInt(9));
                curso.setEstado(rs.getString(10));
                listaCursos.add(curso);
            }
        } catch (SQLException e) {
            System.out.println("Error:Clase CursoDaoImpl, método obtener");
        }
        return listaCursos;
    }

    @Override
    public boolean actualizar(Cursos curso) {
        Conexion co = new Conexion();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE cursos SET "
                + "nombre='" + curso.getNombre() + "', costo = " + curso.getCosto() + ", fec_ini='" + curso.getFec_ini() + "',"
                + "fec_fin='" + curso.getFec_fin() + "', duracion =" + curso.getDuracion() + ","
                + "sesiones=" + curso.getSesiones() + ", capacidad=" + curso.getCapacidad() + ","
                + "inscritos=" + curso.getInscritos() + ", estado='" + curso.getEstado() + "'"
                + " WHERE codigo=" + curso.getCodigo();
        try {
            con = co.Conectar();
            stm = con.createStatement();
            stm.execute(sql);
            actualizar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDao Impl," + "método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminar(String[] codigos) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        boolean eliminar = false;
        try {
            
            if (codigos.length <= 0) {
                return eliminar;
            }
            String sql = "DELETE FROM cursos WHERE codigo in ( ";
            eliminar = true;
            for (int xc = 0; xc < codigos.length; xc++) {
                if (eliminar) {
                    sql += "?";
                } else {
                    sql += ",?";
                }
                eliminar = false;
            }
            sql += ")";
            Conexion con = new Conexion();
            co = con.Conectar();
            PreparedStatement ps = co.prepareStatement(sql);
            for (int xc = 0; xc < codigos.length; xc++) {
                ps.setString(xc + 1, codigos[xc]);
            }
            ps.executeUpdate();
            co.close();
        } catch (SQLException e) {

        }
        return eliminar;
    }

    @Override
    public Cursos buscar(int codigo) {
        Connection co = null;
        Statement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cursos WHERE codigo=" + codigo;
        Cursos curso = new Cursos();
        try {
            Conexion con = new Conexion();
            co = con.Conectar();
            stm = co.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                curso.setCodigo(rs.getInt(1));
                curso.setNombre(rs.getString(2));
                curso.setCosto(rs.getDouble(3));
                curso.setFec_ini(rs.getDate(4));
                curso.setFec_fin(rs.getDate(5));
                curso.setDuracion(rs.getInt(6));
                curso.setSesiones(rs.getInt(7));
                curso.setCapacidad(rs.getInt(8));
                curso.setInscritos(rs.getInt(9));
                curso.setEstado(rs.getString(10));
            }
            stm.close();
            rs.close();
            co.close();
        } catch (SQLException e) {
            System.out.println("Error: Clase CursoDao Impl," + "método buscar");
            e.printStackTrace();
        }
        return curso;
    }
    
}
