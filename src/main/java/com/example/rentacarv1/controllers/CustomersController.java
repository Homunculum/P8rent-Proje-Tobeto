package com.example.rentacarv1.controllers;

import com.example.rentacarv1.core.utilities.results.DataResult;
import com.example.rentacarv1.core.utilities.results.Result;
import com.example.rentacarv1.services.abstracts.CustomerService;
import com.example.rentacarv1.services.dtos.requests.customer.AddCustomerRequest;
import com.example.rentacarv1.services.dtos.requests.customer.UpdateCustomerRequest;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerListResponse;
import com.example.rentacarv1.services.dtos.responses.customer.GetCustomerResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@AllArgsConstructor
public class CustomersController {
    private CustomerService customerService;

    @GetMapping("/getAll")
    public DataResult<List<GetCustomerListResponse>> getAll(){
        return this.customerService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<GetCustomerResponse> getById(@PathVariable int id){
        return this.customerService.getById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid AddCustomerRequest addCustomerRequest){
        return this.customerService.add(addCustomerRequest);
    }


    @PutMapping("/update")
    public Result update( @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
       return this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/{id}")
    public Result delete( @PathVariable int id){

        return  this.customerService.delete(id);
    }
}
