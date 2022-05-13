package com.gachonsw.blooddonation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostsInAssociationDto {

    private Long associationId;
    private Slice<PostResponseDto> postResponseDto;
}
