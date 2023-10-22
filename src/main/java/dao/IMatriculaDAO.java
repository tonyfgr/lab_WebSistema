/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelos.*;


public interface IMatriculaDAO {
    public List<Alumnos> buscarAlumnos(Alumnos alumno);
    public List<Cursos> buscarCursos();
    public List<Matriculas> obtener(Matriculas matricula);
    public boolean grabarMatricula(String[] datosMatricula, String[] codigoCursos, String[] montos);
    public List<String> getCodeMat();
}