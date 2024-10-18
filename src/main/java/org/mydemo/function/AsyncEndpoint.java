package org.mydemo.function;

import org.mydemo.dto.UserCreateDTO;
import org.mydemo.dto.UserIdentityDTO;
import org.mydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class AsyncEndpoint {

    public UserService userService;

    @Autowired
    public AsyncEndpoint(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public Supplier<Long> countUsers(){
        return () -> (long) userService.getAll().size();
    }

    @Bean
    public Function<String, Long> countUsersByAge(){
        return i -> userService.getAll().stream().filter(u -> u.getAge() == Integer.parseInt(i)).count();
    }

    @Bean
    public Consumer<UserCreateDTO> addUser(){
        return u -> userService.register(u);
    }

    @Bean // Used with Flux !!!
    public Function<String, Flux<UserIdentityDTO>> getUsersByAge(){
        return i -> Flux.fromStream(userService.getByAgeFlux(Integer.parseInt(i)));
    }

    @Bean // Used with Mono !!!
    public Function<String, Mono<UserIdentityDTO>> getUserById(){
        return i -> Mono.just(userService.getByIdMono(Integer.parseInt(i)));
    }
}
