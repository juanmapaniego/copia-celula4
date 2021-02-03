package ar.com.gl.shop.api.feign.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.gl.shop.api.feign.dto.CategoryDTO;
import ar.com.gl.shop.api.feign.dto.CategoryDescriptionDTO;
import ar.com.gl.shop.api.feign.dto.ProductDTO;
import ar.com.gl.shop.api.feign.dto.ProductDescriptionDTO;

@FeignClient(value = "shop-product")
public interface ShopProductClient {

    // CRUD Product
    @GetMapping(path = "/shopproduct/v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO findById(@PathVariable(name = "productId") Long productId);

    @GetMapping(path = "/shopproduct/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<ProductDTO> findAll(@RequestParam(name = "name", required = false) String name,
	    @RequestParam(name = "category", required = false) Long categoryId);

    @PostMapping(path = "/shopproduct/v1/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO create(@RequestBody ProductDTO productDTO);

    @DeleteMapping(path = "/shopproduct/v1/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable(name = "productId") Long productId);

    @PutMapping(path = "/shopproduct/v1/products/{product}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO update(@PathVariable("product") Long productId, @RequestBody ProductDTO updateProductDTO);

    @PatchMapping(path = "/shopproduct/v1/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO updateDescription(@PathVariable Long productId,
	    @RequestBody ProductDescriptionDTO productDescription);

    // CRUD Catalog
    @GetMapping(path = "/shopproduct/v1/catalogs", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDTO> getAll(@RequestParam(name = "name", required = false) String name);

    @GetMapping(value = "/shopproduct/v1/catalogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO getById(@PathVariable(name = "id") Long id);

    @PostMapping(path = "/shopproduct/v1/catalogs", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO);

    @PatchMapping(value = "/shopproduct/v1/catalogs/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDTO patchCategory(@RequestBody CategoryDescriptionDTO categoryDescription, @PathVariable Long id);

    @DeleteMapping(value = "/shopproduct/v1/catalogs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCategory(@PathVariable Long id);

}
