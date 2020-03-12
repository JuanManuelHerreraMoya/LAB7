package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Item;
import org.apache.ibatis.exceptions.PersistenceException;

public interface TipoItemDAO {
    void save(Item it) throws PersistenceException;

    Item load(int id) throws PersistenceException;
}

