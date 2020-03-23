package edu.eci.cvds.sampleprj.dao.mybatis;

import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

public class MyBATISTipoItemDAO implements TipoItemDAO {
	@Inject
    private TipoItemMapper TipoitemMapper;
    @Override
    public void save(Item it) throws PersistenceException{
        try{
        	TipoitemMapper.addTipoItem(it);
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al registrar el item "+it.toString(),e);
        }

    }

    @Override
    public Item load(int id) throws ExcepcionServiciosAlquiler {
        Item item = null;
        try{
            item = TipoitemMapper.getTipoItem(id);
            if (item == null) {
                throw new ExcepcionServiciosAlquiler("Error al consultar");
            }
        }
        catch(org.apache.ibatis.exceptions.PersistenceException e){
            throw new PersistenceException("Error al consultar el item "+id,e);
        }
        return item;

    }

	@Override
	public TipoItem consultarTipoItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoItem> consultarTiposItem() {
		// TODO Auto-generated method stub
		return null;
	}
}
