package com.kninterior.restcontroller;

import com.kninterior.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ContactController {

    private final ContactService contactService;


}
