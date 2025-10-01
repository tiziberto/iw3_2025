package ar.edu.iua.iw3.integration.cli2.model.business;


import java.util.Date;
import java.util.List;

import ar.edu.iua.iw3.integration.cli2.model.ProductCli2;
import ar.edu.iua.iw3.integration.cli2.model.ProductCli2SlimView;
import ar.edu.iua.iw3.model.business.BusinessException;

public interface IProductCli2Business {
	public List<ProductCli2> listExpired(Date date) throws BusinessException;
	
	public List<ProductCli2SlimView> listSlim() throws BusinessException;

	public ProductCli2 addExternal(String json) throws BusinessException, FoundException;

	public List<ProductCli2> listByPriceRange(double min, double max) throws BusinessException;
}
