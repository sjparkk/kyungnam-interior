package com.kyungnam.service;

import com.kyungnam.domain.Contact;
import com.kyungnam.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ContactService {

    void sendMessage(ContactDTO contactDTO);

    Long register(ContactDTO dto);

    ContactDTO get(Long bno);

    void modify(ContactDTO boardDTO);

    Page<Contact> getList(Pageable pageable);

    Page<Contact> getListByKeyword(String keyword, String type, Pageable pageable);

    ContactDTO findById(Long id);

    void saveContact(ContactDTO contactDTO) throws Exception;

    boolean comparePassword(Long id, String password) throws Exception;

    List<Contact> getContactCount(LocalDate date);

    default Contact dtoToEntity(ContactDTO dto) {

        return Contact.builder()
                .id(dto.getId())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .postCode(dto.getPostCode())
                .address(dto.getAddress())
                .addressDetail(dto.getAddressDetail())
                .structureType(dto.getStructureType())
                .roomCnt(dto.getRoomCnt())
                .bathroomCnt(dto.getBathroomCnt())
                .sizeNum(dto.getSizeNum())
                .sizeType(dto.getSizeType())
                .budget(dto.getBudget())
                .callTime(dto.getCallTime())
                .password(dto.getPassword())
                .description(dto.getDescription())
                .accessRoute(dto.getAccessRoute())
                .build();
    }

    default ContactDTO entityToDTO(Contact contact) {

        return ContactDTO.builder()
                .id(contact.getId())
                .name(contact.getName())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .postCode(contact.getPostCode())
                .address(contact.getAddress())
                .addressDetail(contact.getAddressDetail())
                .structureType(contact.getStructureType())
                .roomCnt(contact.getRoomCnt())
                .bathroomCnt(contact.getBathroomCnt())
                .sizeNum(contact.getSizeNum())
                .sizeType(contact.getSizeType())
                .budget(contact.getBudget())
                .callTime(contact.getCallTime())
                .password(contact.getPassword())
                .description(contact.getDescription())
                .accessRoute(contact.getAccessRoute())
                .build();
    }

}
