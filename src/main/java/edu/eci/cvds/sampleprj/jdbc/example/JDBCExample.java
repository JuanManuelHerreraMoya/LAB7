/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdpruba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba20219";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=20152008;
            registrarNuevoProducto(con, suCodigoECI, "Buñuelo", 99999999);            
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
        //Asignar parámetros
        //usar 'execute'
        PreparedStatement psInsertar = null;
        if (null == psInsertar){
            psInsertar = con.prepareStatement(
                    "INSERT INTO ORD_PRODUCTOS VALUES(?,?,?)");
        }
        psInsertar.setInt(1,codigo);
        psInsertar.setString(2,nombre);
        psInsertar.setInt(3,precio);
        con.commit();
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido) throws SQLException{
        List<String> np=new LinkedList<>();
        //asignar parámetros
        //usar executeQuery
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla

        PreparedStatement ps= null;
        String consulta = " select nombre from ORD_PRODUCTOS p,ORD_DETALLE_PEDIDO d where p.codigo = d.producto_fk and d.pedido_fk = ?";
        ps = con.prepareStatement(consulta);
        ps.setInt(1, codigoPedido);

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("nombre");
            np.add(name);
        }
        con.commit();

        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido)throws SQLException{
        PreparedStatement vtp= null;
        String consultaValor = "SELECT pedido_fk,SUM(precio*cantidad) AS total FROM ORD_PRODUCTOS,ORD_DETALLE_PEDIDO " +
                "WHERE producto_fk=codigo AND pedido_fk = ? " +
                "GROUP BY pedido_fk";
        vtp = con.prepareStatement(consultaValor);
        vtp.setInt(1, codigoPedido);

        ResultSet rs = vtp.executeQuery();
        String aux = null;
        while (rs.next()) {
            aux = rs.getString("total");
        }
        if (aux == null){
            throw new NullPointerException("Consulta Vacia");
        }
        con.commit();
        //Crear prepared statement
        //asignar parámetros
        //usar executeQuery
        //Sacar resultado del ResultSet
        
        return Integer.parseInt(aux);
    }
    

    
    
    
}
