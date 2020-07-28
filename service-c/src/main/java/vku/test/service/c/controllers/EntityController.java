package vku.test.service.c.controllers;


import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vku.test.service.c.dto.RequestTestEntityPresentation;
import vku.test.service.c.dto.ResponseTestEntityPresentation;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;

@RestController
@Validated //  need for Validating path variables and request parameters
@RequestMapping("entity")
@Api(value="test API", description="basic entity operations")
public class EntityController {
// Validating path variables and request parameters works a little differently then bean validation.
    //https://reflectoring.io/bean-validation-with-spring-boot/

    //In contrast to request body validation a failed validation will trigger a
    // ConstraintViolationException instead of a MethodArgumentNotValidException.

    @GetMapping ("/intTest/")
    public ResponseTestEntityPresentation getByValidatedInteger(@RequestParam("tst") @Min(10) int id) throws IOException {


        ResponseTestEntityPresentation result  = new ResponseTestEntityPresentation();
        result.setMessage("Stub message for int");
        result.setDetailedMessage("Stub detailed message for int");
        return result;
    }

    @GetMapping ("/{id}")
    public ResponseTestEntityPresentation getById(@PathVariable("id") String entityId) throws IOException {

        // add some mock
        if ("io".equals(entityId)) {
            throw  new IOException("stub io exception");
        }

        ResponseTestEntityPresentation result  = new ResponseTestEntityPresentation();
        result.setMessage("Stub message");
        result.setDetailedMessage("Stub detailed message");
        return result;
    }

    @PostMapping()
    public ResponseTestEntityPresentation add(@RequestBody  @Valid RequestTestEntityPresentation presentation) {
        ResponseTestEntityPresentation result  = new ResponseTestEntityPresentation();
        result.setMessage("Stub add message");
        result.setDetailedMessage("Stub add detailed message");
        return result;
    }

}
