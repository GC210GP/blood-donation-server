package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.RecommendResponseDto;
import com.gachonsw.blooddonation.dto.TrainDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlaskRelayService {

    private final UserService userService;
    private final LikedService likedService;


    //1.get방식 요청
//    public String getRecommend(String userId) {
//
//        //URI를 빌드한다
//        URI uri = UriComponentsBuilder
//                .fromUriString("http://127.0.0.1:5001")
//                .path("/recommend")
//                //uuid name 변경
//                .queryParam("uuid", userId)
//                .encode(Charset.defaultCharset())
//                .build()
//                .toUri();
//
//        System.out.println(uri.toString());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        //getForEntity는 응답을 ResponseEntity로 받을 수 있도록 해준다 .
//        //파라미터 첫번째는 요청 URI 이며 , 2번째는 받을 타입
////        ResponseEntity<RecommendResponseDto> result = restTemplate.getForEntity(uri, RecommendResponseDto.class);
////
////        System.out.println(result.getStatusCode());
////        System.out.println(result.getBody());
////
////        return result.getBody();
//
//        String result = restTemplate.getForObject(uri, String.class);
//        return result;
//    }

    public RecommendResponseDto trainUserAndRecommend() {
        //http://localhost:9090/api/server/user/{userId}/name/{username}

        URI uri = UriComponentsBuilder
                .fromUriString("http://127.0.0.1:5001")
                .path("/recommend")
                .encode()
                .build()
                //pathVariable사용을 위한 메소드 순서대로 들어간다.
                .toUri();

        System.out.println(uri);

        //아래 순서로 변환
        //http body - object - object mapper -> json - > http body json

        Long userId = userService.findUserFromToken().getId();
        List<Long> likedList = likedService.findListByUser(userId).stream()
                .map(m -> m.getToUser().getId())
                .collect(Collectors.toList());

        TrainDataDto req = new TrainDataDto(userId, likedList);

        RestTemplate restTemplate = new RestTemplate();

        //파라미터 1 요청 주소 , 2 요청 바디 , 3 응답 바디
        //3번째 파라미터 수정
        ResponseEntity<RecommendResponseDto> response = restTemplate.postForEntity(uri, req, RecommendResponseDto.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }

    public ResponseEntity<?> updateModel() {
        //http://localhost:9090/api/server/user/{userId}/name/{username}

        URI uri = UriComponentsBuilder
                .fromUriString("http://127.0.0.1:5001")
                .path("/recommend/model")
                .encode()
                .build()
                //pathVariable사용을 위한 메소드 순서대로 들어간다.
                .toUri();

        System.out.println(uri);

        //아래 순서로 변환
        //http body - object - object mapper -> json - > http body json

        RestTemplate restTemplate = new RestTemplate();

        //파라미터 1 요청 주소 , 2 요청 바디 , 3 응답 바디
        ResponseEntity<String> response = restTemplate.postForEntity(uri, null, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response;
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
