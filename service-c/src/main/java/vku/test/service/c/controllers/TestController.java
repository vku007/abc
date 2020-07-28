package vku.test.service.c.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vku.test.service.c.dto.ErrorEntity;

import java.io.IOException;

@RestController
@Api(value="test API", description="basic test operations")
public class TestController {

    @GetMapping("/tst")
    public String getTestMessage() {
        return "Hi there!" + System.currentTimeMillis();
    }
    @GetMapping("/tst/err")
    @ApiOperation(value = "Emulates runtime exception", response = ErrorEntity.class)
    public String getTestErrorMessage() {
        throw new RuntimeException("Boom! its Runtime exception");
    }

    @GetMapping("/tst/errTerrible")
    @ApiOperation(value = "Emulates io exception exception", response = ErrorEntity.class)
    @ApiResponse(responseCode ="500", description = "some error desc")
    public String getTestErrorMessage2() throws IOException {
        throw new IOException("Boom! its IOException exception");
    }
}
