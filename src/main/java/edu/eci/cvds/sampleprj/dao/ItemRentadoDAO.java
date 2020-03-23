package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ItemRentadoDAO {

   public void save(ItemRentado itemRentado) throws PersistenceException;

   public List<ItemRentado> load(int id) throws PersistenceException;

}
