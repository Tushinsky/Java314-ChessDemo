package com.example.chess_demo_spring_boot.service;

import com.example.chess_demo_spring_boot.domain.ChessMan;
import com.example.chess_demo_spring_boot.domain.Role;
import com.example.chess_demo_spring_boot.domain.State;
import com.example.chess_demo_spring_boot.dto.RegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final ChessManService chessManService;


//    private final PasswordEncoder encoder;

    public boolean RegisterUser(RegistrationDto registrationDto) {
        // проверяем на уникальность ника и адреса эл. почты
        ChessMan existNicUser = chessManService.getByNic(registrationDto.getNicName());
        ChessMan existEmailUser = chessManService.getByEmail(registrationDto.getEmail());
        if(existNicUser == null && existEmailUser == null) {
            ChessMan user = ChessMan.builder()
                    .name(registrationDto.getName())
                    .email(registrationDto.getEmail())
                    .city(registrationDto.getCity())
                    .country(registrationDto.getCountry())
                    .nic(registrationDto.getNicName())
                    .password(registrationDto.getPassword())
//                .password(encoder.encode(registrationDto.getPassword()))
                    .role(String.valueOf(Role.USER))
                    .state(String.valueOf(State.ACTIVE))
                    .build();
            chessManService.save(user);
            return true;
        }
        return false;
    }

}
