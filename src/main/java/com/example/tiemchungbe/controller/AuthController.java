package com.example.tiemchungbe.controller;

import com.example.tiemchungbe.model.dto.SignUpRequest;
import com.example.tiemchungbe.model.entity.Account;
import com.example.tiemchungbe.payload.response.MessageResponse;
import com.example.tiemchungbe.service.AccountService;
import com.example.tiemchungbe.service.IAccountService;
import com.example.tiemchungbe.service.IPatientService;
import com.example.tiemchungbe.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPatientService patientService;

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signUp(@RequestBody SignUpRequest signUpRequest) throws MessagingException, UnsupportedEncodingException {
        if (accountService.existsById(signUpRequest.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("registered code"));
        }
        if (accountService.existByUserName(signUpRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body(new MessageResponse("email existed"));
        }
        Account account = new Account(signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));
        accountService.addNew(account.getUserName(), account.getEncryptPw(), signUpRequest.getEmail());

        Integer idAccountAfterCreatd = accountService.findIdUserByUserName(account.getUserName());
        roleService.setDefaultRole(idAccountAfterCreatd, 1);
        patientService.addPatient(
                signUpRequest.getName(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getGender(),
                signUpRequest.getGuardian(),
                signUpRequest.getPhone(),
                signUpRequest.getAddress(),
                signUpRequest.getEmail(), idAccountAfterCreatd, false);
        return ResponseEntity.ok(new MessageResponse("Register success!"));
    }
}
