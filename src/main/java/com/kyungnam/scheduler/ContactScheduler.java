package com.kyungnam.scheduler;

import com.kyungnam.domain.Contact;
import com.kyungnam.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ContactScheduler {

    ContactService contactService;

    @Scheduled(cron = "0 42 15 * * *")
    public void dayContactTotalMessage() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> request = new HashMap<>();

        LocalDate date = LocalDate.now();
        List<Contact> contactCount = contactService.getContactCount(date);
        System.out.println(contactCount);


        request.put("username", "전체문의건수알림");
        //String text = String.format("전일 전체 문의 수 :\n %s 입니다.", contactCount);
        //request.put("text", text);

        HttpEntity<Map<String,Object>> entity = new HttpEntity<Map<String,Object>>(request);

        // 사용할 슬랙의 Webhook URL 넣기
        String url = "https://hooks.slack.com/services/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";
        // 신종 URL : https://hooks.slack.com/services/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
        // 경남인테리어 URL : https://hooks.slack.com/services/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
        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }
}
