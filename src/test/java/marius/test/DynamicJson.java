package marius.test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import marius.files.ReUsableMethods;
import marius.files.payLoad;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.*;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle){

        RestAssured.baseURI = "http://216.10.245.166";

        String resp = given().log().all()
                .header("Content-Type", "application/json")
                .body(payLoad.Addbook(isbn, aisle))
                .when()
                .post("/Library/Addbook.php")
                .then()
                .assertThat()
                .log().all()
                .statusCode(200)
                .extract().response().asString();

        JsonPath js = ReUsableMethods.rawToJson(resp);
        String id = js.get("ID");
        System.out.println(id);

        //deleteBook

    }


    @DataProvider(name = "BooksData")
    public Object[][] getData(){

        //array = collection of elements
        //multidimensional array = collection of array

       return new Object[][] {{"dwsdw", "8238"}, {"edwbgduw", "1257162"}, {"bhugug", "7239739"}};

    }




}
