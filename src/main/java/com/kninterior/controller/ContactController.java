package com.kninterior.controller;


import com.kninterior.domain.Contact;
import com.kninterior.dto.ContactDTO;
import com.kninterior.service.ContactService;
import com.kninterior.util.ContactUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor // 아래 생성자 주입 방식 해당 어노테이션으로 대체 가능.
public class ContactController {

    private final ContactService contactService;
    //public ContactController(ContactService contactService) {
    //    this.contactService = contactService;
    //} 생성자 주입 방식

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/contact/list")
    public String contactList(@PageableDefault Pageable pageable, Model model) {
        Page<Contact> contactList = contactService.getList(pageable);
        for( Contact contact : contactList) {
            contact.setName(ContactUtil.getEncodeName(contact.getName()));
        }
        model.addAttribute("contactList", contactList);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                contactList.getTotalElements(), contactList.getTotalPages(), contactList.getSize(),
                contactList.getNumber(), contactList.getNumberOfElements());
        return "contact_list";
    }

    @PostMapping("/contact/list/form")
    public String contactForm(@RequestParam("user-id") Long id, @RequestParam("password") String password, Model model, RedirectAttributes ra) throws Exception {
        boolean checkedVal = contactService.comparePassword(id, password);
        if(checkedVal) {
            ContactDTO contactDTO = contactService.findById(id);
            model.addAttribute("contact", contactDTO);
            return "contact_confirm";
        } else {
            return "redirect:/contact/list";
        }
    }

    @PostMapping("send")
    public String send(ContactDTO contactDTO) throws Exception {
        contactService.saveContact(contactDTO);
        contactService.sendMessage(contactDTO);

        return "redirect:/contact/list";
    }

    @GetMapping("/getSearchList")
    public String contactListByName(@PageableDefault Pageable pageable, @RequestParam("type") String type, @RequestParam("keyword") String name, Model model) {
        Page<Contact> contactListByKeyword = contactService.getListByKeyword(name, type, pageable);
        for( Contact contact : contactListByKeyword) {
            contact.setName(ContactUtil.getEncodeName(contact.getName()));
        }
        model.addAttribute("contactList", contactListByKeyword);
        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
                contactListByKeyword.getTotalElements(), contactListByKeyword.getTotalPages(), contactListByKeyword.getSize(),
                contactListByKeyword.getNumber(), contactListByKeyword.getNumberOfElements());
        return "contact_list";
    }
}
