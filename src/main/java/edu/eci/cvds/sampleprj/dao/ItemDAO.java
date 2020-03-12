package edu.eci.cvds.sampleprj.dao;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ItemDAO {

     void save(Item it) throws PersistenceException;

     Item load(int id) throws PersistenceException, ExcepcionServiciosAlquiler;

}