/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Tony
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
                }else {
                String[] datos = request.getParameterValues("xcod");
                objDatos.eliminarAreas(datos);
                request.getRequestDispatcher("/controladorPrincipal?accion=listadoAreas").forward(
                   request,response );
            }

            }else if ( accion.compareTo( "GRABAR_AREA") == 0 ) {
            if ( request.getParameter( "boton" ).compareTo( "GRABAR" ) == 0 ) {
             String operacion = request.getParameter("operacion");
             if ( operacion.equals("INSERT")) {
                String[] datos = new String[3];
                datos[0] = request.getParameter("xnom");
                datos[1] = request.getParameter("xabr");
                datos[2] = request.getParameter("xest");
                objDatos.grabarNuevaArea(datos);
            }else {
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
            }
            
            else if ( accion.compareTo( "modificarArea" ) == 0 ) {
            String xcod = request.getParameter( "xcod" );
            Vector registro = objDatos.buscarArea(xcod);

            request.setAttribute( "registro", registro );
            request.setAttribute( "operacion","UPDATE");
            request.setAttribute( "titulo","Modificar Area");
            request.getRequestDispatcher( "/mantenimientos/editarAreas.jsp" ).forward( 
             request,response );
            }
        
            else
              out.println("Accion: (" + accion + ") no reconocida...");
        }catch(Exception ex) {
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
