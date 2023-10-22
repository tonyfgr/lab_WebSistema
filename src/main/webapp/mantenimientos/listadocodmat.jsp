 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" 
              href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    </head>
    <body>
    <div class="card" style="padding: 30px 0px 0px 30px;">
        <h4 class="display-8">Listado por Codigo de matricula</h4>
        <form method=POST action="/WebSistema/controladorPrincipal">
           
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">Número de documento</th>
                        <th scope="col">Curso</th>
                        <th scope="col">Monto</th>
                        <th scope="col">Estado de matrícula</th>
                    </tr>
                </thead>
                <tbody>

                
                <c:forEach items="${arrMatriculas}" var="matricula">
                <c:if test="${param.xcod == matricula.nro_doc}">
                <tr>
                <td><c:out value='${matricula.nro_doc}'/></td>
                <td><c:out value='${matricula.nombre_curso}'/></td>
                <td><c:out value='${matricula.monto}'/></td>
                <td><c:out value='${matricula.estado}'/></td>
                </tr>
                </c:if>
                </c:forEach>

                
                </tbody>
            </table>
        </form>
    </div>
    </body>
</html>
