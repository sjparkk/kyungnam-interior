package com.kninterior.service;

import com.kninterior.domain.Contact;
import com.kninterior.dto.ContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ContactService {

    void sendMessage(ContactDTO contactDTO);

    Long register(ContactDTO dto);

    ContactDTO get(Long bno);

    Page<Contact> getList(Pageable pageable);

    Page<Contact> getListByKeyword(String keyword, String type, Pageable pageable);

    ContactDTO findById(Long id);

    void saveContact(ContactDTO contactDTO) throws Exception;

    boolean comparePassword(Long id, String password) throws Exception;

    List<Contact> getContactCount(LocalDate date);

    /**
     * 자바8 이전에는 인터페이스는 정적 상수와 추상메소드만을 가질 수 있었지만 함수형 인터페이스 등이 도입되면서
     * 메소드에 바디까지 가진 default 메소드와 정적 메소드를 넣을 수 있다. 이 메소드들은 오버라이딩이 필요없이
     * implements 해주면 그냥 사용이 가능하다.
     */
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
