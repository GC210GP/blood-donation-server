package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.AssociationResponseDto;
import com.gachonsw.blooddonation.dto.PostAndAssociationDto;
import com.gachonsw.blooddonation.dto.PostResponseDto;
import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostViewService {

    private final PostService postService;
    private final UserService userService;


    public List<PostAndAssociationDto> findListWithAssociation() {

        List<Post> listByUser = postService.findListByUser();
        List<PostAndAssociationDto> res = new ArrayList<>();

        for (Post post : listByUser) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            AssociationResponseDto associationResponseDto = new AssociationResponseDto(post.getAssociation());

            res.add(new PostAndAssociationDto(associationResponseDto ,postResponseDto));
        }

        return res;
    }

    public PostAndAssociationDto findWithAssociation(){

    }
}
