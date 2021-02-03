package ar.com.gl.shop.product.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;
import ar.com.gl.shop.product.services.StockService;

@Service
public class StockServiceImpl implements StockService {

  @Autowired private StockRepository repository;

  // CRUD methods
  @Override
  public void createStock(Stock stock) {
    this.repository.save(stock);
    System.out.println("Created stock");
  }

  @Override
  public Stock getStock(Long id) {
    return this.repository.getOne(id);
  }

  @Override
  public List<Stock> getAllStocks() {

    return this.repository.findAll();
  }

  @Override
  public void updateStock(Stock updatedStock) {
    repository.save(updatedStock);
    System.out.println("Updated stock");
  }

  @Override
  public void deleteStock(Stock stock) {
    this.repository.delete(stock);
  }
}
