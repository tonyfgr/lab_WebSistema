package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.Alumnos;
import modelos.Conexion;
/**
 *
 * @author Joss
 */
public class AlumnoDAOImpl implements IAlumnosDAO{
    @Override
    public boolean registrar(Alumnos alumno){
        Conexion co= new Conexion();
        String xcod = co.generarCodigo("alumnos","codigo");
        boolean registrar = false;
        Statement stm= null;
        Connection con= null;
        String sql="INSERT INTO alumnos values(" + xcod + "," +
                "'"+ alumno.getNombre()+"','"+alumno.getDireccion()+"',"+
                "'"+ alumno.getEmail()+"','"+alumno.getTelefono()+"',"+
                "'"+ alumno.getCelular()+"','"+alumno.getSexo()+"',"+
                "'"+ alumno.getFec_nac()+"','"+alumno.getEstado()+"')";
        try{
            con=co.Conectar();
            stm= con.createStatement();
            
            stm.execute(sql);
            registrar=true;
            stm.close();
            con.close();
        }catch(SQLException e){
                System.out.println("Error: Clase AlumnoDAPImpl,"
                            + "metodo registrar");
                e.printStackTrace();
        }
        return registrar;
    }
    @Override
    public List<Alumnos> obtener(){
        Connection co=null;
        Statement stm= null;
        ResultSet rs=null;
        String sql="SELECT * FROM alumnos ORDER BY codigo";
        List<Alumnos>listaAlumnos=new ArrayList<Alumnos>();
        
        try{
            Conexion con= new Conexion();
            co=con.Conectar();
            stm=co.createStatement();
            rs=stm.executeQuery(sql);
            while(rs.next()){
                    Alumnos alumno=new Alumnos();
                    alumno.setCodigo(rs.getInt(1));
                    alumno.setNombre(rs.getString(2));
                    alumno.setDireccion(rs.getString(3));
                    alumno.setEmail(rs.getString(4));
                    alumno.setTelefono(rs.getString(5));
                    alumno.setCelular(rs.getString(6));
                    alumno.setSexo(rs.getString(7));
                    alumno.setFec_nac(rs.getDate(8));
                    alumno.setEstado(rs.getString(9));
                    listaAlumnos.add(alumno);
            }
            stm.close();
            rs.close();
            co.close();
        }catch (SQLException e){
                System.out.println("Error:Clase AlumnoDAOImpl,"
                        + "metodo obtener");
        }
        return listaAlumnos;
    }

    @Override
    public boolean actualizar(Alumnos alumno){
        Conexion co = new Conexion();
        boolean actualizar = false;
        Statement stm = null;
        Connection con = null;
        String sql = "UPDATE alumnos SET "+
                "nombre='" + alumno.getNombre()+"',direccion='" + alumno.getDireccion()+"',"+
                "email='" + alumno.getEmail()+ "',telefono='" + alumno.getTelefono()+"'," +
                "celular='" + alumno.getCelular()+ "',sexo='" + alumno.getSexo()+"',"+
                "fec_nac='" + alumno.getFec_nac()+"',estado='" + alumno.getEstado()+"'"+
                "WHERE codigo=" + alumno.getCodigo();
        try{
            con = co.Conectar();
            stm = con.createStatement();
            
            stm.execute(sql);
            actualizar=true;
            stm.close();
            con.close();
        } catch(SQLException e){
            System.out.println("Error:Clase AlumnoDaoImpl,"
                + "método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }


    
    @Override
    public Alumnos buscar(int codigo){
        Connection co=null;
        Statement stm=null;
        ResultSet rs=null;
        String sql="SELECT * FROM alumnos WHERE codigo=" + codigo;
        Alumnos alumno= new Alumnos();
        try{
                Conexion con= new Conexion();
                co= con.Conectar();
                stm=co.createStatement();
                rs= stm.executeQuery(sql);
                while(rs.next()){
                    alumno.setCodigo(rs.getInt(1));
                    alumno.setNombre(rs.getString(2));
                    alumno.setDireccion(rs.getString(3));
                    alumno.setEmail(rs.getString(4));
                    alumno.setTelefono(rs.getString(5));
                    alumno.setCelular(rs.getString(6));
                    alumno.setSexo(rs.getString(7));
                    alumno.setFec_nac(rs.getDate(8));
                    alumno.setEstado(rs.getString(9));
                }
                stm.close();
                rs.close();
                co.close();
        }catch(SQLException e){
                System.out.println("Error:Clase AlumnoDAOImpl,"
                            + "metodo buscar");
                e.printStackTrace();
        }
        return alumno;
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
            String sql = "DELETE FROM alumnos WHERE codigo in ( ";
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
}