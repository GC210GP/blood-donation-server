package com.gachonsw.blooddonation.service;

import com.gachonsw.blooddonation.dto.UserUpdateDto;
import com.gachonsw.blooddonation.entity.Authority;
import com.gachonsw.blooddonation.entity.User;
import com.gachonsw.blooddonation.entity.UserAuthority;
import com.gachonsw.blooddonation.repository.AuthorityRepository;
import com.gachonsw.blooddonation.repository.UserAuthorityRepository;
import com.gachonsw.blooddonation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    private final PasswordEncoder passwordEncoder;

//    public UserDto getUserWithAuthorities(String email) {
//        return UserDto.from(userRepository.findWithAuthoritesByEmail(email).orElse(null));
//    }
//
//    public UserDto getMyUserWithAuthorities() {
//        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findWithAuthoritesByEmail).orElse(null));
//    }

    @Transactional
    public Long createUser(User user) {
        user.changePw(passwordEncoder.encode(user.getPassword()));

        validateDuplicateUser(user.getEmail());
        userRepository.save(user);

//        Authority authority = new Authority("ROLE_USER");
//        authorityRepository.save(authority);

        Authority role = authorityRepository.findById("ROLE_USER").get();

        UserAuthority userAuthority = UserAuthority.builder()
                .authority(role)
                .user(user)
                .build();
        userAuthorityRepository.save(userAuthority);

        return user.getId();
    }

    public void validateDuplicateUser(String email) {
        if (email == null)
            return;
        Optional<User> findUser = userRepository.findByEmail(email);
        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 사용중인 이메일입니다.");
        }
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
    }

    @Transactional
    public void updateUser(Long id, UserUpdateDto userUpdateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        if (!userUpdateDto.getEmail().equals(user.getEmail()))
            validateDuplicateUser(userUpdateDto.getEmail());

        if (userUpdateDto.getPassword() != null)
            user.changePw(passwordEncoder.encode(userUpdateDto.getPassword()));

        user.update(userUpdateDto);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));

        userRepository.delete(user);
    }

    @Transactional
    public void changeDormant(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 유저입니다."));
        user.changeDormant();
    }

}
