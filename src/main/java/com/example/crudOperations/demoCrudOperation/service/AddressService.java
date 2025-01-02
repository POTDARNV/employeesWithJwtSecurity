package com.example.crudOperations.demoCrudOperation.service;

import com.example.crudOperations.demoCrudOperation.model.Address;
import com.example.crudOperations.demoCrudOperation.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;
    public Address addAddress(Address address)
    {
        return addressRepo.save(address);
    }
    public List<Address> getAddresse()
    {
        return addressRepo.findAll();
    }

    public void deleteAddress(int id)
    {
        addressRepo.deleteById(id);
    }
}
