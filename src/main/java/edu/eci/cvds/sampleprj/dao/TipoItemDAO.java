package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

public interface TipoItemDAO {
    void save(Item it) throws PersistenceException;

    Item load(int id) throws PersistenceException, ExcepcionServiciosAlquiler;

	TipoItem consultarTipoItem(int id);

	List<TipoItem> consultarTiposItem();
}

