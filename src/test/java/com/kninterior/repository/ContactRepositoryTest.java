package com.kninterior.repository;

import com.kninterior.domain.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    @Test
    @DisplayName("create & read 테스트")
    public void Contact_저장하기() {
        //given
        Contact contact = Contact.builder()
                .name("테스트")
                .phone("01051711677")
                .email("aaa@aaa.com")
                .postCode("10323")
                .address("경기도용인시수지구죽전로")
                .addressDetail("도담단지")
                .structureType("아파트")
                .roomCnt(3)
                .bathroomCnt(2)
                .sizeNum(30)
                .sizeType("평orm3")
                .budget("1000만원")
                .callTime("오후2시")
                .password("1234")
                .description("문의드립니다.")
                .accessRoute("sns")
                .build();
        //when
        contactRepository.save(contact);
        //then
        Assertions.assertEquals(contact, contactRepository.findById(1L).get());
    }

    @Test
    @DisplayName("create & delete 테스트")
    public void Contact_저장하고_삭제() {
        //given
        Contact contact1 = Contact.builder()
                .name("테스트1")
                .phone("01051711677")
                .email("aaa@aaa.com")
                .postCode("10323")
                .address("경기도용인시수지구죽전로")
                .addressDetail("도담단지")
                .structureType("아파트")
                .roomCnt(3)
                .bathroomCnt(2)
                .sizeNum(30)
                .sizeType("평orm3")
                .budget("1000만원")
                .callTime("오후2시")
                .password("1234")
                .description("문의드립니다.")
                .accessRoute("sns")
                .build();
        Contact contact2 = Contact.builder()
                .name("테스트2")
                .phone("01051711677")
                .email("aaa@aaa.com")
                .postCode("10323")
                .address("경기도용인시수지구죽전로")
                .addressDetail("도담단지")
                .structureType("아파트")
                .roomCnt(3)
                .bathroomCnt(4)
                .sizeNum(40)
                .sizeType("평orm3")
                .budget("1000만원")
                .callTime("오후2시")
                .password("1234")
                .description("문의드립니다.")
                .accessRoute("sns")
                .build();
        contactRepository.save(contact1);
        contactRepository.save(contact2);
        //when
        contactRepository.deleteAll();
        //then
        Assertions.assertEquals(contactRepository.findAll().size(), 0);
    }
}