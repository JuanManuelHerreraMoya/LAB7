package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ClienteDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import org.apache.ibatis.exceptions.PersistenceException;

import java.sql.Date;
import java.util.List;

public class MyBATISClienteDAO implements ClienteDAO {

    @Inject
    private ClienteMapper clienteMapper;

    @Override
    public void save(Cliente cli) throws PersistenceException {
        try{
            clienteMapper.insertarCliente(cli);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar: "+ cli.toString(),e);
        }
    }

    @Override
    public Cliente load(int id) throws ExcepcionServiciosAlquiler {
        try {
            return clienteMapper.consultarCliente(id);
        } catch (PersistenceException e) {
            throw new PersistenceException("Error al consultar: " + id, e);
        }

    }

    @Override
    public void agregarItemRentado(int docu, int item, Date fechaini, Date fechafin) {
        try {
            clienteMapper.agregarItemRentadoACliente(docu,item, fechaini,fechafin);
        }
        catch (PersistenceException e){
            throw new UnsupportedOperationException("Error al consultar de id: ",e);
        }
    }

   @Override
    public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler {
        try {
            return clienteMapper.consultarClientes();
        }
        catch (PersistenceException e){
            throw new UnsupportedOperationException("Error al consultar de id: ",e);
        }
    }

    @Override
    public List<ItemRentado> consultarItemsCliente(int id) throws ExcepcionServiciosAlquiler {
        try{
            return clienteMapper.consultarItemsCliente(id);
        }
        catch (PersistenceException e){
            throw new UnsupportedOperationException("Error al consultar de id: ",e);
        }
    }
}
