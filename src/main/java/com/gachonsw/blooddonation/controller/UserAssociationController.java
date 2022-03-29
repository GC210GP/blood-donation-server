package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.dto.UserAssociationDto;
import com.gachonsw.blooddonation.entity.UserAssociation;
import com.gachonsw.blooddonation.service.UserAssociationService;
import com.gachonsw.blooddonation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/associations")
public class UserAssociationController {

    private final UserAssociationService userAssociationService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserAssociationDto> createUserAssociation(@RequestBody UserAssociationDto userAssociationDto, UriComponentsBuilder b) {
        Long userAssociationId = userAssociationService.createUserAssociation(userAssociationDto);

        UriComponents uriComponents =
                b.path("/associations/{userAssociationId}").buildAndExpand(userAssociationId);

        UserAssociationDto result = UserAssociationDto.builder()
                .userId(userAssociationDto.getUserId())
                .userAssociationId(userAssociationId)
                .associationName(userAssociationDto.getAssociationName())
                .build();

        return ResponseEntity.created(uriComponents.toUri()).body(result);
    }


    @GetMapping
    public Result<List<UserAssociationDto>> getUserAssociationList(@RequestParam Long userId) {
        List<UserAssociation> byUser = userAssociationService.findListByUserId(userId);

        List<UserAssociationDto> res = byUser.stream()
                .map(m -> new UserAssociationDto(m.getUser().getId(),m.getId(), m.getAssociation().getId(), m.getAssociation().getName()))
                .collect(Collectors.toList());

        return new Result<>(res);
    }

    @DeleteMapping("/{userAssociationId}")
    public ResponseEntity<?> deleteUserAssociation(@PathVariable Long userAssociationId) {
        userAssociationService.deleteById(userAssociationId);
        return ResponseEntity.noContent().build();
    }

}
