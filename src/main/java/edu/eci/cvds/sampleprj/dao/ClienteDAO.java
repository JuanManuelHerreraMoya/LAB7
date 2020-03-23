package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import org.apache.ibatis.exceptions.PersistenceException;

import java.util.List;

public interface ClienteDAO {
    void save(Cliente cli) throws PersistenceException;

    Cliente load(int id) throws ExcepcionServiciosAlquiler;

    void agregarItemRentado(int docu, int item, java.sql.Date fechaini, java.sql.Date fechafin);

    List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler;

    List<ItemRentado> consultarItemsCliente(int id) throws ExcepcionServiciosAlquiler;

	Cliente consultarCliente(long docu);

	void actualizarCliente(long docu, int i);
}
