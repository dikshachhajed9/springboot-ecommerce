package com.ecommerce.sb_ecom.Service;

import com.ecommerce.sb_ecom.Model.User;
import com.ecommerce.sb_ecom.payload.AddressDTO;

import java.util.List;

public interface AddressServices {
    AddressDTO createAddress(AddressDTO addressDTO, User user);

    List<AddressDTO> getAddress();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getUserAddresses(User user);

    AddressDTO updateAddress(Long addressId, AddressDTO addressDTO);

    String deleteAddress(Long addressId);
}
