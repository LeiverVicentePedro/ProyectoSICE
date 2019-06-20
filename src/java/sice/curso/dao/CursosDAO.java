/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sice.curso.dao;

import java.util.ArrayList;
import java.util.List;
import sice.conexion.Conexion;
import sice.curso.modelo.Cursos;

/**
 *
 * @author vplei
 */
public class CursosDAO extends Conexion{

    public CursosDAO() {
        super();
    }
    
    
    
    
    
    public Cursos consultaCurso(Cursos curso, int numConsulta) throws Exception{
        Cursos cursoCon = new Cursos();
        try{
            conectar();
            String consulta = "CALL CURSOSCON(?,?,?,?,?)";//Para hacer la llamada aL STORE
           
           callableStatement = getConexion().prepareCall(consulta);
           
           callableStatement.setInt("Par_CursoID",Integer.parseInt(curso.getCursoID()));
           callableStatement.setString("Par_Nombre","");
           callableStatement.setString("Par_Clave", "");
           callableStatement.setString("Par_Estatus", "");
           callableStatement.setInt("Par_NumConsulta", numConsulta);
           
           resultSet = callableStatement.executeQuery();

          while(resultSet.next()){
            cursoCon.setCursoID(resultSet.getString("CursoID"));
            cursoCon.setNombre(resultSet.getString("Nombre"));
            cursoCon.setClave(resultSet.getString("Clave"));
            cursoCon.setEstatus(resultSet.getString("Estatus"));
          }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            cerrar();
        }
        return cursoCon;
    }
    
     public List<Cursos> listaPrincipal(Cursos curso,int numLista) throws Exception{
        List<Cursos> lista = new ArrayList<Cursos>();
        try{
            conectar();
            String listaSQL = "CALL CURSOSLIS(?,?,?,?);";
            callableStatement = getConexion().prepareCall(listaSQL);
            callableStatement.setString("Par_CursoID",curso.getCursoID());
            callableStatement.setString("Par_Nombre",curso.getNombre());
            callableStatement.setString("Par_Clave", curso.getClave());
            callableStatement.setInt("Par_NumLista", numLista);
            resultSet = callableStatement.executeQuery();
            while(resultSet.next()){
                Cursos cursoLis = new Cursos();
                cursoLis.setCursoID(resultSet.getString("CursoID"));
                cursoLis.setNombre(resultSet.getString("Nombre"));
                lista.add(cursoLis);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            cerrar();
        }
        return lista;
    }
    
    
    
}
