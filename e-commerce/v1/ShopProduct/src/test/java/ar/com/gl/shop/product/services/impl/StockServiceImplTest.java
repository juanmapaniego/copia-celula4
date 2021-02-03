package ar.com.gl.shop.product.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

	private static final Long ST_ID = 1L;

	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	@Mock
	private StockRepository stockRepository;

	@Test
	@DisplayName("Create stock")
	void testCreateStock() {
		stockServiceImpl.createStock(Stock.builder().build());

		Mockito.verify(stockRepository).save(Mockito.any());
	}

	@Test
	@DisplayName("Create stock should throw IAException")
	void testCreateStockShouldThrowException() {
		Mockito.when(stockRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> stockServiceImpl.createStock(Stock.builder().build()));
	}

	@Test
	@DisplayName("Get one stock")
	public void getCategoryByIdTest() {
		Mockito.when(stockRepository.getOne(Mockito.anyLong())).thenReturn(Stock.builder().id(ST_ID).build());

		assertEquals(ST_ID, stockServiceImpl.getStock(ST_ID).getId());
	}

	@Test
	@DisplayName("Get one stock shoud return null")
	public void getCategoryByIdHouldReturnNullTest() {
		Mockito.when(stockRepository.getOne(Mockito.anyLong())).thenReturn(null);

		assertNull(stockServiceImpl.getStock(ST_ID));
	}

	@Test
	@DisplayName("Get all stocks")
	public void getAllCategoriesTest() {
		Mockito.when(stockRepository.findAll()).thenReturn(new ArrayList<>());

		assertTrue(stockServiceImpl.getAllStocks().isEmpty());

	}

	@Test
	@DisplayName("Update stock")
	public void updateCategoryTest() {

		stockServiceImpl.updateStock(Stock.builder().build());

		Mockito.verify(stockRepository).save(Mockito.any());

	}

	@Test
	@DisplayName("Update stock throws Illegal pointer exception")
	void updateCategoryException() {

		Mockito.when(stockRepository.save(Mockito.any())).thenThrow(IllegalArgumentException.class);

		assertThrows(IllegalArgumentException.class, () -> stockServiceImpl.updateStock(Stock.builder().build()));

	}

	@Test
	@DisplayName("Delete stock")
	public void deleteCategory() {

		stockServiceImpl.deleteStock(Stock.builder().build());

		Mockito.verify(stockRepository).delete(Mockito.any());
	}

}
