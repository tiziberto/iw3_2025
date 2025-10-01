package ar.edu.iua.iw3.integration.cli2.model;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ar.edu.iua.iw3.integration.cli2.model.business.IComponentCli2Business;
import ar.edu.iua.iw3.model.business.BusinessException;
import ar.edu.iua.iw3.model.business.ICategoryBusiness;
import ar.edu.iua.iw3.model.business.NotFoundException;
import ar.edu.iua.iw3.util.JsonUtiles;

public class ProductCli2JsonDeserializer extends StdDeserializer<ProductCli2> {
    private ICategoryBusiness categoryBusiness;
    private IComponentCli2Business componentBusiness;

    protected ProductCli2JsonDeserializer(Class<?> vc) {
        super(vc);
    }

    public ProductCli2JsonDeserializer(Class<?> vc, ICategoryBusiness categoryBusiness,
            IComponentCli2Business componentBusiness) {
        super(vc);
        this.categoryBusiness = categoryBusiness;
        this.componentBusiness = componentBusiness;
    }

    @Override
    public ProductCli2 deserialize(com.fasterxml.jackson.core.JsonParser jp,
            com.fasterxml.jackson.databind.DeserializationContext ctxt)
            throws java.io.IOException, com.fasterxml.jackson.core.JacksonException {
        ProductCli2 r = new ProductCli2();
        com.fasterxml.jackson.databind.JsonNode node = jp.getCodec().readTree(jp);

        String productDesc = JsonUtiles.getString(node,
                "product,description,product_description,product_name".split(","), null);
        double price = JsonUtiles.getDouble(node, "product_price,price_product,price".split(","), 0);
        boolean stock = JsonUtiles.getBoolean(node, "stock,in_stock".split(","), false);
        r.setProduct(productDesc);
        if (productDesc == null || productDesc.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo 'product description' es obligatorio y no puede estar vacío.");
        }
        r.setPrecio(price);
        r.setStock(stock);
        String categoryName = JsonUtiles.getString(node, "category,product_category,category_product".split(","), null);
        if (categoryName != null) {
            try {
                r.setCategory(categoryBusiness.load(categoryName));
            } catch (Exception e) {
            }
        }
        // ...continúa el resto del método si es necesario...
        return r;
    }
}