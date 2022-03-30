package com.gachonsw.blooddonation.controller;

import com.gachonsw.blooddonation.dto.*;
import com.gachonsw.blooddonation.entity.Association;
import com.gachonsw.blooddonation.service.AssociationService;
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
public class AssociationController {

    private final AssociationService associationService;

    @GetMapping
    public Result<AssociationResponseDto> getAssociation(@RequestParam Long associationId) {
        Association association = associationService.findById(associationId);

        return new Result<>(new AssociationResponseDto(association));
    }

    @PostMapping
    public ResponseEntity<AssociationResponseDto> createAssociation(@RequestBody CreateAssociationDto createAssociationDto, UriComponentsBuilder b) {
        Long assoId = associationService.createAssociation(createAssociationDto);

        UriComponents uriComponents =
                b.path("/associations/{associationId}").buildAndExpand(assoId);

        AssociationResponseDto result = new AssociationResponseDto(associationService.findById(assoId));
        return ResponseEntity.created(uriComponents.toUri()).body(result);
    }

    @GetMapping("/search-name")
    public Result<List<AssociationResponseDto>> searchAssociationNameLike(@RequestParam String query){
        List<AssociationResponseDto> result = associationService.searchAssociationNameLike(query).stream()
                .map(AssociationResponseDto::new)
                .collect(Collectors.toList());

        return new Result<>(result);
    }
}
