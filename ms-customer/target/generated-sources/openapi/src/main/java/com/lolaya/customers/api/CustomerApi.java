/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.4.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.lolaya.customers.api;

import com.lolaya.customers.model.CustomerDto;
import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-19T16:27:19.852006500-05:00[America/Lima]")
@Validated
@Tag(name = "Customer", description = "the Customer API")
public interface CustomerApi {

    /**
     * POST /customer : Add a new customer to the Data base.
     * Add a new customer to Data base.
     *
     * @param customerDto Create a new customer in the Data base. (required)
     * @return Successful operation (status code 200)
     *         or Invalid input (status code 400)
     *         or Validation exception (status code 422)
     */
    @Operation(
        operationId = "addCustomer",
        summary = "Add a new customer to the Data base.",
        tags = { "Customer" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  Map.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "422", description = "Validation exception")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/customer",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/xml", "application/x-www-form-urlencoded" }
    )
    default Mono<ResponseEntity<Map<String, Object>>> _addCustomer(
        @Parameter(name = "CustomerDto", description = "Create a new customer in the Data base.", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<CustomerDto> customerDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return addCustomer(customerDto, exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<Map<String, Object>>> addCustomer(Mono<CustomerDto> customerDto,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(Mono.empty());

    }


    /**
     * PUT /customer : Update an existing customer.
     * Update an existing customer by Id.
     *
     * @param customerDto Update an existent customer in Data base. (required)
     * @return Successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Customer not found (status code 404)
     *         or Validation exception (status code 422)
     */
    @Operation(
        operationId = "updateCustomer",
        summary = "Update an existing customer.",
        tags = { "Customer" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation =  CustomerDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "422", description = "Validation exception")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/customer",
        produces = { "application/json", "application/xml" },
        consumes = { "application/json", "application/x-www-form-urlencoded" }
    )
    default Mono<ResponseEntity<CustomerDto>> _updateCustomer(
        @Parameter(name = "CustomerDto", description = "Update an existent customer in Data base.", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<CustomerDto> customerDto,
        @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return updateCustomer(customerDto, exchange);
    }

    // Override this method
    default  Mono<ResponseEntity<CustomerDto>> updateCustomer(Mono<CustomerDto> customerDto,  final ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"id\" : 10, \"userName\" : \"Luis Olaya Córdova\", \"email\" : \"lolayaco@nttdata.com\", \"status\" : \"enabled\" }";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                String exampleString = "<Customer> <id>10</id> <userName>Luis Olaya Córdova</userName> <email>lolayaco@nttdata.com</email> <status>enabled</status> </Customer>";
                result = ApiUtil.getExampleResponse(exchange, mediaType, exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }

}