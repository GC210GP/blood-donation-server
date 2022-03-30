package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.CreateUserAssociationDto;
import com.gachonsw.blooddonation.dto.Result;
import com.gachonsw.blooddonation.dto.UserAssociationDto;
import com.gachonsw.blooddonation.service.UserAssociationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-associations")
public class UserAssociationController {

    private final UserAssociationService userAssociationService;

    @PostMapping
    public ResponseEntity<?> createUserAssociation(@RequestBody CreateUserAssociationDto createUserAssociationDto) {
        userAssociationService.createUserAssociation(createUserAssociationDto);
        return ResponseEntity.noContent().build();
    }


    //    @GetMapping
//    public Result<List<AssociationResponseDto>> getAssociationsByUser() {
//        List<AssociationResponseDto> res = userAssociationService.findAssoListByUser().stream()
//                .map(AssociationResponseDto::new)
//                .collect(Collectors.toList());
//
//        return new Result<>(res);
//    }
    @GetMapping
    public Result<List<UserAssociationDto>> getUserAssociations() {
        List<UserAssociationDto> res = userAssociationService.findListWithAssociationByUser().stream()
                .map(UserAssociationDto::new)
                .collect(Collectors.toList());

        return new Result<>(res);
    }

    @DeleteMapping("/{userAssociationId}")
    public ResponseEntity<?> deleteUserAssociation(@PathVariable Long userAssociationId) {
        userAssociationService.deleteById(userAssociationId);
        return ResponseEntity.noContent().build();
    }

}
