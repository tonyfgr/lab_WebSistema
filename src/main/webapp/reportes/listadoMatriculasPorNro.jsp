<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@ page import="modelos.Conexion" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<%
 Conexion con = new Conexion();
 String accion = request.getParameter("accion");
 
if (accion.compareTo ("MOSTRAR_MATRICULAS") == 0) {
    File reportFile = new File(application.getRealPath( 
                                 "/reportes/listarMatriculasPorNro.jasper"));
    Map parameters = new HashMap();
    //-------------------------------------------
    String cod = request.getParameter("cod");
    parameters.put("p_nro", cod);

    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath (),
                      parameters, con.Conectar());
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream ouputStream = response.getOutputStream();
    ouputStream.write(bytes, 0, bytes.length);
    ouputStream.flush();
    ouputStream.close();
 }
%>