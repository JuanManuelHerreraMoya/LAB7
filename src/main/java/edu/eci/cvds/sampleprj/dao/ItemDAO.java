package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import org.apache.ibatis.exceptions.PersistenceException;
import java.util.List;


	public interface ItemDAO {
	    public void save(Item it) throws PersistenceException;

	    public Item load(int id) throws PersistenceException, ExcepcionServiciosAlquiler;

	    public int valorMultaRetrasoxDia(int id ) throws PersistenceException;

	    public List<Item> consultarItemsDisponibles() throws PersistenceException;

	    public long consultarCostoAlquiler(int iditem, int numdias) throws PersistenceException;

	    public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException;
}