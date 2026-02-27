package com.ecommerce.sb_ecom.controller;


import com.ecommerce.sb_ecom.Model.User;
import com.ecommerce.sb_ecom.Service.AddressServices;
import com.ecommerce.sb_ecom.payload.AddressDTO;
import com.ecommerce.sb_ecom.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api")
@RestController
public class AddressController {

    @Autowired
    AuthUtil authUtil;
    @Autowired
    AddressServices addressServices;

    @PostMapping("/addresses")
    public ResponseEntity<AddressDTO> createAdress(@Valid @RequestBody AddressDTO addressDTO){

        User user=authUtil.loggedInUser();
        AddressDTO savedaddressDTO= addressServices.createAddress(addressDTO,user);
        return new ResponseEntity<>(savedaddressDTO, HttpStatus.CREATED);

    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAddress(){
        List<AddressDTO> addressList=addressServices.getAddress();
         return new ResponseEntity<>(addressList,HttpStatus.OK);

    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long addressId){
        AddressDTO addressDTO=addressServices.getAddressById(addressId);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);
    }
    @GetMapping("/users/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddresses(){
        User user=authUtil.loggedInUser();
        List<AddressDTO> addressList=addressServices.getUserAddresses(user);
        return new ResponseEntity<>(addressList,HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddresses(@PathVariable Long addressId,
                                                      @RequestBody AddressDTO addressDTO){
        AddressDTO updatdAddress=addressServices.updateAddress(addressId,addressDTO);
        return new ResponseEntity<>(addressDTO,HttpStatus.OK);

    }
    @DeleteMapping("/addresses/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable Long addressId){
        String status=addressServices.deleteAddress(addressId);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }


}
