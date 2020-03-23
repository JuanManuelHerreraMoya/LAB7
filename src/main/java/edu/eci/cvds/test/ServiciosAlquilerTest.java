package edu.eci.cvds.test;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.Inject;

import edu.eci.cvds.*;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import samples.*;




public class ServiciosAlquilerTest {

	@Inject
	private SqlSession sqlSession;

	ServiciosAlquiler serviciosAlquiler;

	public ServiciosAlquilerTest() {
		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
	}

	@Before
	public void setUp() {
	}

	
	@Test
    public void DeberiaRegistrarItem()  {
        boolean correct=false;
        try {
            serviciosAlquiler.registrarItem(new Item());
            correct=true;
        } catch (ExcepcionServiciosAlquiler excepcionServiciosAlquiler) {
            excepcionServiciosAlquiler.printStackTrace();
        }
        Assert.assertTrue(correct);

    }
	@Test
	public void noItemFound() {
		boolean r = false;
		try {
			Item item = serviciosAlquiler.consultarItem(8);
		} catch(ExcepcionServiciosAlquiler e) {
			r = true;
		} catch(IndexOutOfBoundsException e) {
			r = true;
		}
		// Validate no Client was found;
		Assert.assertTrue(r);
	}
	@Test
	public void emptyDB() {
		for(int i = 0; i < 100; i += 10) {
			boolean r = false;
			try {
				Cliente cliente = serviciosAlquiler.consultarCliente(2020);
			} catch(ExcepcionServiciosAlquiler e) {

				r = true;
			} catch(IndexOutOfBoundsException e) {
				r = true;
			}
			Assert.assertTrue(r);
		}
	}
	@Test
	public void insertarAlquilerAlCliente() {
		boolean esValido = false;
		try {
			Item item = serviciosAlquiler.consultarItem(10102);
			serviciosAlquiler.registrarAlquilerCliente(Date.valueOf("2020-23-03") , 200, item, 1);
			esValido = true;
		} catch (ExcepcionServiciosAlquiler e) {
			esValido = false;
		}
		Assert.assertTrue(esValido);
	} 
}