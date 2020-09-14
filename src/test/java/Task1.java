import APIUser.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.io.IOException;



public class Task1  {
/*
    private final String userPath = "https://jsonplaceholder.typicode.com/";


    @Test
    void test1() throws IOException {
        //Given the path and users information
        Faker faker = new Faker();
        User newUser = new User(faker.name().name(),faker.name().username(),
                faker.name().username()+"@"+"stuff.com",
                faker.address().fullAddress(), faker.phoneNumber().cellPhone(),
                faker.rickAndMorty().character(),faker.company().name());
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(newUser);
       // Sending Post Request
        APItool.setPath(userPath+"users");
        APItool.postRequest(jsonString);
        String code = APItool.ResponseCode();
        reporter.testcomplete(APItool.getResponseBody().string(), APItool.ResponseCode().equals("201") || APItool.ResponseCode().equals("200") );

    }

    @Test
    void test2() throws IOException {

        APItool.setPath(userPath+"users/1");
        APItool.setRequest();
        //give the body API
        reporter.testcomplete(APItool.getResponseBody().string(), APItool.ResponseCode().equals("201") || APItool.ResponseCode().equals("200") );


    }

    //stil need to fix...
    @Test
    void test3() throws IOException {

        APItool.setPath(userPath+"users");
        APItool.setRequest();
        reporter.testcomplete(APItool.getResponseBody().string(), APItool.ResponseCode().equals("201") || APItool.ResponseCode().equals("200") );
    }

*/
}
