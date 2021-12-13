package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.RecommendResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlaskRelayService {

    //1.get방식 요청
    public String getRecommend(String userId) {

        //URI를 빌드한다
        URI uri = UriComponentsBuilder
                .fromUriString("http://127.0.0.1:5001")
                .path("/api/recommend")
                //uuid name 변경
                .queryParam("uuid", userId)
                .encode(Charset.defaultCharset())
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
//        ResponseEntity<RecommendResponseDto> result = restTemplate.getForEntity(uri, RecommendResponseDto.class);
//
//        System.out.println(result.getStatusCode());
//        System.out.println(result.getBody());
//
//        return result.getBody();

        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

//    public UserResponse post(){
//        // http://localhost:9090/api/server/user/{userId}/name/{username}
//
//        URI uri =UriComponentsBuilder
//                .fromUriString("http://127.0.0.1:5001")
//                .path("/api/recommend")
//                .encode()
//                .build()
//                //pathVariable사용을 위한 메소드 순서대로 들어간다.
//                .expand("100","ugo")
//                .toUri();
//        System.out.println(uri);
//
//        //아래 순서로 변환
//        //http body - object - object mapper -> json - > http body json
//
//        UserRequest req = new UserRequest();
//        req.setName("ugo");
//        req.setAge(20);
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        //post 의 경우 PostForEntity를 사용한다. 파라미터 1 요청 주소 , 2 요청 바디 , 3 응답 바디
//        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri,req,UserResponse.class);
//
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getHeaders());
//        System.out.println(response.getBody());
//
//        return response.getBody();
//    }
}
