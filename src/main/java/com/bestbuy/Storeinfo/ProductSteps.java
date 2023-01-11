package com.bestbuy.Storeinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.model.ProductPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductSteps {
    @Step
    public ValidatableResponse getAllProducts() {
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then();
    }

    @Step("Creating product with name :{0}, type : {1}, price :{2}, shipping:{3},upc:{4},description:{5},manufacturer:{6},model:{7},url:{8},image{9}")
    public ValidatableResponse createANewProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(EndPoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Get product details of id : {0}")
    public HashMap<String, Object> getProductInfoById(int productID){
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParams("productID", productID)
                .get(EndPoints.GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;

    }

    @Step("Update product details of id: {0}")
    public ValidatableResponse updateProduct(int productID, String name) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .pathParam("productID", productID)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Delete product details by id: {0}")
    public ValidatableResponse deleteSingleProduct(int productID) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .delete(EndPoints.GET_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting Store with ID {0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(EndPoints.DELETE_SINGLE_STORES_BY_ID)
                .then();
    }

}
