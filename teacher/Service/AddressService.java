package com.example.teacher.Service;

import com.example.teacher.Api.ApiException;
import com.example.teacher.Dto.AddressDto;
import com.example.teacher.Model.Address;
import com.example.teacher.Model.Teacher;
import com.example.teacher.Repository.AddressRepository;
import com.example.teacher.Repository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AddressService {
  private final AddressRepository addressRepository;
  private final TeacherRepository teacherRepository;

public List<Address>getAll(){
   return addressRepository.findAll();
    }


    public void addAdress(AddressDto addressDto){
        Teacher teacher=teacherRepository.findTeacherByTeacherId(addressDto.getTeacher_Id());
        if (teacher==null){
            throw new ApiException("teacher not found");
        }

        Address address=new Address(null,addressDto.getArea(),addressDto.getStreet(),addressDto.getBuildingNumber(),teacher);

    }

    public void updateAdress(AddressDto addressDto){
    Address address=addressRepository.findAddressByAddressId(addressDto.getTeacher_Id());
    if (address==null){
        throw new ApiException("address not found");
    }
    address.setArea(addressDto.getArea());
    address.setStreet(addressDto.getStreet());
    address.setBuildingNumber(addressDto.getBuildingNumber());
    addressRepository.save(address);

    }


    // i dont creat delete here because any way it won't delete because it has relation with father we should delete father first

}
