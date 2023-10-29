<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" 
              href="../webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
        <div class="card" style="width: 50rem; padding: 30px 0px 0px 30px;">
        <form method="post" action="listadoMatriculasPorNro.jsp">
        <input type="hidden" name="accion" value="MOSTRAR_MATRICULAS">
            <table class="table table-striped table-hover">
                <tr>
                    <td>Seleccione el N° de Doc. de matrícula</td>
                    <td>
                        <input name="cod" size=60>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <input type="submit" class="btn btn-primary" value="Mostrar Reporte Mat">
                    </td>
                </tr>
            </table>
        </form>
        </div>
    </body>
</html>
