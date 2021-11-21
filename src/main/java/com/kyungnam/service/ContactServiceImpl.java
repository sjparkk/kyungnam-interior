package com.kyungnam.service;

import com.kyungnam.domain.Contact;
import com.kyungnam.dto.ContactDTO;
import com.kyungnam.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service // 인터페이스는 컴포넌트 스캔 대상에서 제외되기 때문에 인터페이스가 아닌 구현체에 @Service 어노테이션을 붙여야한다.
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    private static final int SALT_SIZE = 16;

    @Override
    public void sendMessage(ContactDTO contactDTO) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String,Object> request = new HashMap<>();
        request.put("username", "문의알림");
        String text = String.format("견적문의가 들어왔습니다.\n`고객명`: %s\n`연락처`: %s\n" +
                "`이메일`: %s\n`공사현장주소`: %s %s\n`건물구분`: %s\n`방 & 화장실`: %d, %d\n" +
                "`예산`: %s\n`통화가능시간`: %s\n`문의사항`: %s\n`접근경로`: %s\n",
                contactDTO.getName(), contactDTO.getPhone(),
                contactDTO.getEmail(), contactDTO.getAddress(), contactDTO.getAddressDetail(),
                contactDTO.getStructureType(), contactDTO.getRoomCnt(), contactDTO.getBathroomCnt(),
                contactDTO.getBudget(), contactDTO.getCallTime(),
                contactDTO.getDescription(), contactDTO.getAccessRoute());
        request.put("text", text);

        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);

        String url = "https://hooks.slack.com/services/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"; // 사용할 슬랙의 Webhook URL 넣기
        // 신종 URL : https://hooks.slack.com/services/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
        // 경남인테리어 URL : https://hooks.slack.com/services/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
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    @Override
    public Long register(ContactDTO dto) {
        Contact contact = dtoToEntity(dto);
        contactRepository.save(contact);
        return contact.getId();
    }

    @Override
    public ContactDTO get(Long id) {
        Object result = contactRepository.findAll();

        Object[] arr = (Object[]) result;
        return entityToDTO((Contact) arr[0]);
    }

    @Override
    public void modify(ContactDTO boardDTO) {

    }

    @Override
    public Page<Contact> getList(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        Sort sort = Sort.by(Sort.Order.desc("id"));
        pageable = PageRequest.of(page, 10, sort);

        return contactRepository.findAll(pageable);
    }

    @Override
    public Page<Contact> getListByKeyword(String keyword, String type, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page는 index 처럼 0부터 시작
        Sort sort = Sort.by(Sort.Order.desc("id"));
        pageable = PageRequest.of(page, 10, sort);


        if(type.equals("address")) {
            return contactRepository.getContactByAddress(keyword, pageable);
        } else if(type.equals("name")) {
            return contactRepository.getContactByName(keyword, pageable);
        } else {
            return contactRepository.findAll(pageable);
        }

    }

    @Override
    public ContactDTO findById(Long id) {
        Object result = contactRepository.findById(id).get();
        ContactDTO contactDTO = entityToDTO((Contact) result);

        return contactDTO;
    }

    @Override
    public void saveContact(ContactDTO contactDTO) throws Exception {
        Contact contact = dtoToEntity(contactDTO);

        String password = contact.getPassword();
        String saltVal = getSALT();
        String hashPassword = Hashing(password,saltVal);

        contact.saveHashPassword(hashPassword); // hash 값으로 변경 후 저장
        contact.createSalt(saltVal); // salt 값 개별 저장
        contactRepository.save(contact);
    }

    @Override
    public boolean comparePassword(Long id, String password) throws Exception {
        String existSalt = contactRepository.findById(id).get().getSalt(); // 해당 유저의 salt 값 가져옴

        return Hashing(password,existSalt).equals(contactRepository.findById(id).get().getPassword());
    }

    @Override
    public List<Contact> getContactCount(LocalDate date) {
        return contactRepository.getContactCountByDay(date);
    }

    // 비밀번호 해싱
    private String Hashing(String password, String Salt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");	// SHA-256 해시함수를 사용

        String temp = password + Salt;	// 패스워드와 Salt 를 합쳐 새로운 문자열 생성
        md.update(temp.getBytes());		// temp 의 문자열을 해싱하여 md 에 저장해둔다
        byte[] transPwd = md.digest();  // md 객체의 다이제스트를 얻어 password 를 갱신한다

        return Byte_to_String(transPwd);
    }

    // SALT 값 생성
    private String getSALT() throws Exception {
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[SALT_SIZE];
        rnd.nextBytes(temp);

        return Byte_to_String(temp);

    }

    // 바이트 값을 16진수로 변경해준다
    private String Byte_to_String(byte[] temp) {
        StringBuilder sb = new StringBuilder();
        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }
        return sb.toString();
    }



}
