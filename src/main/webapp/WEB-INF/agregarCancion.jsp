<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Agregar Canción</title>
    </head>
    <body>
        <h1>Agregar Canción</h1>
        <form:form action="/canciones/procesa/agregar" method="POST" modelAttribute="cancion">
            <div>
                <form:label path="titulo">Título:</form:label>
                <form:input path="titulo" />
                <form:errors path="titulo"/>
            </div>
            <div>
                <form:label path="artista">Artista:</form:label>
                <select id="artista" name="artistaId">
                    <c:forEach var="artista" items="${artistas}">
                        <option value="${artista.id}">${artista.nombre} ${artista.apellido}</option>
                    </c:forEach>
                </select>
                <form:errors path="artista"/>
            </div>
            
            <div>
                <form:label path="album">Álbum:</form:label>
                <form:input path="album"/>
                <form:errors path="album"/>
            </div>
            <div>
                <form:label path="genero">Género:</form:label>
                <form:input path="genero"/>
                <form:errors path="genero"/>
            </div>
            <div>
                <form:label path="idioma">Idioma:</form:label>
                <form:input path="idioma"/>
                <form:errors path="idioma"/>
            </div>
            <div>
                <input type="submit" value="Agregar Canción"/>
            </div>
        </form:form>
        <a href="/canciones">Volver a lista de canciones</a>
    </body>
</html>
