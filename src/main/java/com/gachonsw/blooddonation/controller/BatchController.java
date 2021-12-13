package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.entity.Sns;
import com.gachonsw.blooddonation.entity.SnsType;
import com.gachonsw.blooddonation.repository.SnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batch")
public class BatchController {

    private final SnsRepository snsRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<?> createPost() {
        Sns build = Sns.builder()
                .type(SnsType.FACEBOOK)
                .build();

        Sns build2 = Sns.builder()
                .type(SnsType.KAKAO)
                .build();
        Sns build3 = Sns.builder()
                .type(SnsType.INSTAGRAM)
                .build();
        Sns build4 = Sns.builder()
                .type(SnsType.TWITTER)
                .build();

        snsRepository.save(build);
        snsRepository.save(build2);
        snsRepository.save(build3);
        snsRepository.save(build4);

        return ResponseEntity.noContent().build();
    }
}
