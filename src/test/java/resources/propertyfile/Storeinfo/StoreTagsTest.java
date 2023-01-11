package resources.propertyfile.Storeinfo;


import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(SerenityRunner.class)
public class StoreTagsTest extends TestBase {

    @WithTag("Storefeature:POSITIVE")
    @Title("Provide HTTP method is used to access resource")
    @Test
    public void validMethod() {
        SerenityRest.rest()
                .given()
                .when()
                .post()
                .then();
    }

    @WithTags({
            @WithTag("Storefeature:SMOKE"),
            @WithTag("Storefeature:POSITIVE")
    })
    @Title("This test will verify if a status code of 200 is returned for GET request")
    @Test
    public void verifyIfTheStatusCodeIs200() {
        SerenityRest.rest()
                .given()
                .when()
                .get()
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTags({
            @WithTag("Storefeature:SMOKE"),
            @WithTag("Storefeature:NEGATIVE")
    })
    @Title("This test will provide an error code of 404 when user tries to access an invalid resource")
    @Test
    public void inCorrectResource() {
        SerenityRest.rest()
                .given()
                .when()
                .get("/Store123")
                .then()
                .statusCode(404)
                .log().all();
    }


}
