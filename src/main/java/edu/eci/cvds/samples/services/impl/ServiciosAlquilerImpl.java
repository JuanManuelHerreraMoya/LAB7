package edu.eci.cvds.samples.services.impl;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.ItemRentadoDAO;
//import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
@Singleton
public class ServiciosAlquilerImpl implements ServiciosAlquiler {
	@Inject
	   private ItemDAO itemDAO;
	   @Inject
	   private ClienteDAO clienteDAO;
	   @Inject
	   private ItemRentadoDAO itemRentadoDAO;
	   @Inject
	   private TipoItemDAO tipoItemDAO;


	   @Override
	   public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler {
	       try{
	           return itemDAO.valorMultaRetrasoxDia(itemId);
	       }catch (PersistenceException ex){
	           throw new ExcepcionServiciosAlquiler("Error al consultar el valor de multa "+ itemId , ex);
	       }
	       
	   }

    @Override
    public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler {
        try {
            return clienteDAO.consultarCliente(docu);
        }
        catch (PersistenceException ex){
         throw new ExcepcionServiciosAlquiler("Error al consultar el cliente: "+docu,ex);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.consultarItemsCliente((int) idcliente);
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar el cliente");
        }
    }

    @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try{
            return clienteDAO.consultarClientes();
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar los clientes",ex);
        }
    }

    @Override
    public Item consultarItem(int id) throws ExcepcionServiciosAlquiler {
        try {
            return itemDAO.load(id);
        } catch (PersistenceException ex) {
            throw new ExcepcionServiciosAlquiler("Error al consultar el item "+ id , ex);
        }
    }

    @Override
    public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler {
        try{
            return itemDAO.consultarItemsDisponibles();
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al consultar los items disponibles ", ex);
        }
    }

    @Override
    public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler {
        try{
            return tipoItemDAO.consultarTipoItem(id);
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error el tipo del item "+id, ex);
        }
    }

    @Override
    public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler {
        try{
            return tipoItemDAO.consultarTiposItem();
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error el tipo del item ", ex);
        }
    }

    @Override
    public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler {
        try{
            clienteDAO.save(c);
        }
        catch (PersistenceException ex){
            throw new ExcepcionServiciosAlquiler("Error al insertar cliente "+c, ex);
        }
   }

    @Override
    public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler {
        try{
             return itemDAO.consultarCostoAlquiler(iditem, numdias);
         }
         catch (PersistenceException ex){
             throw new ExcepcionServiciosAlquiler("Error al insertar cliente "+iditem, ex);
         }
    }

    @Override
    public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler {
         try{
             itemDAO.actualizarTarifaItem(id,tarifa);
         }
         catch (PersistenceException ex){
             throw new ExcepcionServiciosAlquiler("Error al cambiar la tarifa del item "+id, ex);
         }
    }
    @Override
    public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
        try{
             itemDAO.save(i);
         }
         catch (PersistenceException ex){
             throw new ExcepcionServiciosAlquiler("Error al registrar el item "+i, ex);
         }
    }

    @Override
    public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
        try{
            if (estado) clienteDAO.actualizarCliente (docu,1);
            else clienteDAO.actualizarCliente (docu,0);
         }
         catch (PersistenceException ex){
             throw new ExcepcionServiciosAlquiler("Error al cambiar la tarifa del item "+docu, ex);
         }
    }
}