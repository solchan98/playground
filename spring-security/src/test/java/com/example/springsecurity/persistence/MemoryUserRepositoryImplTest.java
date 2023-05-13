package com.example.springsecurity.persistence;

import com.example.springsecurity.database.User;
import com.example.springsecurity.persistence.impl.MemoryUserRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("메모리 디비 유저 레포지토리 테스트")
public class MemoryUserRepositoryImplTest {
    private final UserRepository userRepository = new MemoryUserRepositoryImpl();

    @Test
    void 유효하지_않은_username으로_find_by_username_호출_시_null_optional객체를_반환한다() {
        // given
        String invalidUsername = "73d4b68d-11e6-4631-95d0-6104d598e045";
        
        // when
        Optional<User> optionalUser = userRepository.findByUsername(invalidUsername);

        // then
        assertThat(optionalUser).isEmpty();
    }

    @Test
    void 유효한_username으로_find_by_username_호출_시_user가_포함된_optional객체를_반환한다() {
        // given
        String username = "c5652f12-7640-4c95-8cc6-e4118fc348cb";
        String userId = "sol";
        String password = "psolsolw";
        User user = new User(username, userId, password, Boolean.FALSE);
        userRepository.create(user);

        // when
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());

        // then
        assertThat(optionalUser).isPresent();
        User userInDB = optionalUser.get();
        assertAll(
                () -> assertThat(userInDB.getUsername()).isEqualTo(user.getUsername()),
                () -> assertThat(userInDB.getPassword()).isEqualTo(user.getPassword()),
                () -> assertThat(userInDB.getEnabled()).isEqualTo(user.getEnabled())
        );
    }

    @Test
    void findAll_호출_시_모든_user를_collection으로_반환한다() {
        // given
        Collection<User> users = List.of(
                new User("123", "sol", "psolsolw", Boolean.FALSE),
                new User("321", "chan", "pchanw", Boolean.FALSE)
        );
        users.forEach(userRepository::create);

        // when
        Collection<User> usersInDB = userRepository.findAll();

        // then
        assertThat(usersInDB.size()).isEqualTo(2);
    }
}
