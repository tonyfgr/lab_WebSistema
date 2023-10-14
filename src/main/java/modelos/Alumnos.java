package modelos;
import java.util.Date;
/**
 *
 * @author Joss
 */
public class Alumnos {
    int codigo;
    String nombre;
    String direccion;
    String email;
    String telefono;
    String celular;
    String sexo;
    Date fec_nac;
    String estado;
    
    public Alumnos(){
    
    }
    
    public Alumnos(int codigo, String nombre, String direccion, String email, String telefono, 
            String celular, String sexo, Date fec_nac, String estado){
        this.codigo= codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.celular = celular;
        this.sexo = sexo;
        this.fec_nac = fec_nac;
        this.estado = estado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFec_nac(Date fec_nac) {
        this.fec_nac = fec_nac;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFec_nac() {
        return fec_nac;
    }

    public String getEstado() {
        return estado;
    }
}




