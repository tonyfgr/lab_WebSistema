package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.cBaseDatos;
import modelos.*;
import dao.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thoni
 */
@WebServlet(name = "controladorPrincipal", urlPatterns = {"/controladorPrincipal"})
public class controladorPrincipal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
        cBaseDatos objDatos = new cBaseDatos();
        String accion = request.getParameter("accion");
        if (accion == null) {
            accion = "bienvenida";
        }

        if (accion.equals("bienvenida")) {
            request.getRequestDispatcher("/contenido.html").forward(request, response);
        } else if (accion.equals("listadoAreas")) {
            Vector arrAreas = (Vector) objDatos.getAreas();
            request.setAttribute("arrAreas", arrAreas);
            request.getRequestDispatcher("/mantenimientos/listadoAreas.jsp").forward(request, response);
        } else if (accion.equals("NuevoEliminarArea")) {
            if ("Nuevo Registro".equals(request.getParameter("boton"))) {
                Vector registro = new Vector();
                registro.add("");
                registro.add("");
                registro.add("");
                registro.add("");

                request.setAttribute("registro", registro);
                request.setAttribute("operacion", "INSERT");
                request.setAttribute("titulo", "Nueva Area");
                request.getRequestDispatcher("/mantenimientos/editarAreas.jsp").forward(request, response);
            } else {
                String[] datos = request.getParameterValues("xcod");
                objDatos.eliminarAreas(datos);
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                   request,response );
            }
        } else if (accion.compareTo( "GRABAR_AREA") == 0 ) {
            if ( request.getParameter( "boton" ).compareTo( "GRABAR" ) == 0 ) {
                String operacion = request.getParameter("operacion");
                if ( operacion.equals("INSERT")) {
                    String[] datos = new String[3];
                    datos[0] = request.getParameter("xnom");
                    datos[1] = request.getParameter("xabr");
                    datos[2] = request.getParameter("xest");
                    objDatos.grabarNuevaArea(datos);
                } else {
                     String[] datos = new String[4];
                     datos[0] = request.getParameter("xcod");
                     datos[1] = request.getParameter("xnom");
                     datos[2] = request.getParameter("xabr");
                     datos[3] = request.getParameter("xest");
                     objDatos.modificarArea(datos);
                 }
            }
            request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
            request,response );
        } else if ( accion.compareTo( "modificarArea" ) == 0 ) {
            String xcod = request.getParameter( "xcod" );
            Vector registro = objDatos.buscarArea(xcod);

            request.setAttribute( "registro", registro );
            request.setAttribute( "operacion","UPDATE");
            request.setAttribute( "titulo","Modificar Area");
            request.getRequestDispatcher( "/mantenimientos/editarAreas.jsp" ).forward( 
             request,response );
        } else if(accion.equals("listadoAlumnos")){
            List<Alumnos>arrAlumnos = new ArrayList<Alumnos>();
            IAlumnosDAO dao= new AlumnoDAOImpl();
            arrAlumnos = dao.obtener();
            request.setAttribute("arrAlumnos", arrAlumnos);
            request.getRequestDispatcher("/mantenimientos/listadoAlumnos.jsp").forward(request, response);
        } else if(accion.equals("NuevoEliminarAlumno")){
            if (request.getParameter("boton").compareTo("Nuevo Registro")==0){
                Alumnos alumno = new Alumnos();
                request.setAttribute("alumno", alumno);
                request.setAttribute("operacion", "INSERT");
                request.setAttribute("titulo","Nuevo Alumno");
                request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(request, response);
            }else{
                String[] codigos = request.getParameterValues("xcod");
                IAlumnosDAO dao = new AlumnoDAOImpl();
                dao.eliminar(codigos);
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(request, response);
                
            }
        } else if(accion.compareTo("GRABAR_ALUMNO")==0){
            if(request.getParameter("boton").compareTo("GRABAR")==0){
                String operacion = request.getParameter("operacion");
                String strDate = request.getParameter("xfec");
                Date xfec = Date.valueOf(strDate);
                Alumnos alumno = new Alumnos();
                alumno.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                alumno.setNombre(request.getParameter("xnom"));
                alumno.setDireccion(request.getParameter("xdir"));
                alumno.setEmail(request.getParameter("xema"));
                alumno.setTelefono(request.getParameter("xtel"));
                alumno.setCelular(request.getParameter("xcel"));
                alumno.setSexo(request.getParameter("xsex"));
                alumno.setFec_nac(xfec);
                alumno.setEstado(request.getParameter("xest"));
                if(operacion.equals("INSERT")){
                    IAlumnosDAO dao = new AlumnoDAOImpl();
                    dao.registrar(alumno);
                }else{
                    IAlumnosDAO dao= new AlumnoDAOImpl();
                    dao.actualizar(alumno);
                }
            }
            request.getRequestDispatcher("/controladorPrincipal?accion=listadoAlumnos").forward(request, response);
        }else if(accion.compareTo("modificarAlumno")== 0){
                String xcod = request.getParameter("xcod").trim();
                IAlumnosDAO dao = new AlumnoDAOImpl();
                Alumnos alumno = dao.buscar(Integer.parseInt(xcod));
                request.setAttribute("alumno", alumno);
                request.setAttribute("operacion", "UPDATE");
                request.setAttribute("titulo", "Modificar Alumno");
                request.getRequestDispatcher("/mantenimientos/editarAlumnos.jsp").forward(request, response);
        
        }
        
                    // CURSOS
            
            else if (accion.equals("listadoCursos")) {
                List<Cursos> arrCursos = new ArrayList<Cursos>();
                ICursosDAO dao = new CursosDAOImpl();
                arrCursos = dao.obtener();
                request.setAttribute("arrCursos", arrCursos);
                request.getRequestDispatcher("/mantenimientos/listadoCursos.jsp").forward(
                        request, response);
            }else if (accion.equals("NuevoEliminarCurso")) {
                if (request.getParameter("boton").compareTo("Nuevo Registro") == 0) {
                    Cursos curso = new Cursos();
                    request.setAttribute("curso", curso);
                    request.setAttribute("operacion", "INSERT");
                    request.setAttribute("titulo", "Nuevo Curso");
                    request.getRequestDispatcher("mantenimientos/editarCursos.jsp").forward(request, response);
                } else {
                    String[] codigos = request.getParameterValues("xcod");
                    ICursosDAO dao = new CursosDAOImpl();
                    dao.eliminar(codigos);
                    request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(
                            request, response);
                }

            }else if (accion.compareTo("GRABAR_CURSO") == 0) {
                if (request.getParameter("boton").compareTo("GRABAR") == 0) {
                    String operacion = request.getParameter("operacion");
                    String strDate = request.getParameter("xfec_ini");
                    Date xfec_ini = Date.valueOf(strDate);
                    String strDate2 = request.getParameter("xfec_fin");
                    Date xfec_fin = Date.valueOf(strDate2);
                    
                    
                    Cursos curso = new Cursos();
                    curso.setCodigo(Integer.parseInt(request.getParameter("xcod")));
                    curso.setNombre(request.getParameter("xnom"));
                    curso.setCosto(Double.parseDouble(request.getParameter("xcos")));
                    curso.setFec_ini(xfec_ini);
                    curso.setFec_fin(xfec_fin);
                    curso.setDuracion(Integer.parseInt(request.getParameter("xdur")));
                    curso.setSesiones(Integer.parseInt(request.getParameter("xses")));
                    curso.setCapacidad(Integer.parseInt(request.getParameter("xcap")));
                    curso.setInscritos(Integer.parseInt(request.getParameter("xins")));
                    curso.setEstado(request.getParameter("xest"));
                    if (operacion.equals("INSERT")) {
                        ICursosDAO dao = new CursosDAOImpl();
                        dao.registrar(curso);
                    } else {
                        ICursosDAO dao = new CursosDAOImpl();
                        dao.actualizar(curso);
                    }
                }
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoCursos").forward(request, response);
            }else if (accion.compareTo("modificarCurso") == 0) {
                String xcod = request.getParameter("xcod").trim(); 
                ICursosDAO dao = new CursosDAOImpl(); 
                Cursos curso = dao.buscar(Integer.parseInt(xcod) ); 
                request.setAttribute("curso", curso); 
                request.setAttribute("operacion", "UPDATE"); 
                request.setAttribute("titulo", "Modificar Curso"); 
                request.getRequestDispatcher("/mantenimientos/editarCursos.jsp").forward(request, response);
            }
        
        
        else {
            out.println("Accion: (" + accion + ") no reconocida...");
        }
    } catch(Exception ex) {
        System.out.println( ex.toString() );   
    } finally { 
        out.close();
    }
}




    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}