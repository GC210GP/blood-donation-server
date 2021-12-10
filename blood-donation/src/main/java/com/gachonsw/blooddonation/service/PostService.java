package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.PostDto;
import com.gachonsw.blooddonation.entity.Post;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.repository.PostRepository;
import com.gachonsw.blooddonation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Transactional
    public Long createPost(PostDto postDto){
        User user = userService.findById(postDto.getUserId());
        Post post = postDto.toEntityExceptUser(postDto);
        post.changeUser(user);

        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void updatePost(Long postId, PostDto postDto){
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));
        post.update(postDto);
    }

    @Transactional
    public void deletePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 포스트입니다."));

        postRepository.deleteById(id);
    }

    public Post findPost(Long id){
        return postRepository.findById(id).orElseThrow(()->  new EntityNotFoundException("존재하지 않는 포스트입니다."));
    }

    @Transactional
    public void deleteAll(List<Post> posts){
        postRepository.deleteAll(posts);
    }


}
