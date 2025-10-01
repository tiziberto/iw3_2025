package ar.edu.iua.iw3.integration.cli2.model.persistence;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.iw3.integration.cli2.model.ProductCli2;
import ar.edu.iua.iw3.integration.cli2.model.ProductCli2SlimView;

@Repository
public interface ProductCli2Respository extends JpaRepository<ProductCli2, Long> {
	public List<ProductCli2> findByExpirationDateBeforeOrderByExpirationDateDesc(Date expirationDate);
	
	public List<ProductCli2SlimView> findByOrderByPrecioDesc();

	//TRABAJO PRACTICO 3:
	//Metodos para buscar por rango de precios:
	public List<ProductCli2> findByPrecioBetweenOrderByPrecio(double min, double max);
	public List<ProductCli2> findByPrecioGreaterThanEqualOrderByPrecio(double min);
	public List<ProductCli2> findByPrecioLessThanEqualOrderByPrecio(double max);
	public List<ProductCli2> findAllByOrderByPrecio();
}

