package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Stock;

public interface StockService {

	//CRUD methods
	public void createStock(Stock stock);
	public Stock getStock(Long id);
	public List<Stock> getAllStocks();
	public void updateStock(Stock updatedStock);
	public void deleteStock(Stock stock);
	
}
