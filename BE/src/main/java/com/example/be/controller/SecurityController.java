package com.example.be.controller;

import com.example.be.dto.PasswordDto;
import com.example.be.jwt.JwtUtility;
import com.example.be.model.Account;
import com.example.be.payload.request.LoginRequest;
import com.example.be.payload.response.JwtResponse;
import com.example.be.service.IAccountService;
import com.example.be.service.Impl.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/public")
@CrossOrigin("*")
public class SecurityController {

    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IAccountService accountService;

    /**
     * Created by: TienP
     * Date created: 29/03/2023
     * Function:  authenticate user
     * @param: loginRequest
     * @return: HttpStatus.No_Content if result is error or HttpStatus.OK is result is not error
     */

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(
                        jwt,
                        userDetails.getUsername(),
                        roles)
        );
    }

    /**
     * Created by: TienP
     * Date created: 31/03/2023
     * Function:  change password
     * @param: PasswordDto, BindingResult
     * @return: HttpStatus.bad_request if result is error or HttpStatus.OK is result is not error
     */
    @PatchMapping("/change-password")
    public ResponseEntity<List<FieldError>> changePassword(@RequestBody @Valid PasswordDto passwordDto,
                                                           BindingResult bindingResult) {
        new PasswordDto().validate(passwordDto, bindingResult);
        Account account = accountService.findAccountByEmail(passwordDto.getUsername());
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String password = account.getPassword();
        String oldPassWord = passwordDto.getOldPassword();
        Boolean checkOldPassword = accountService.checkOldPassword(oldPassWord, password);
        if (!checkOldPassword) {
            bindingResult.rejectValue("oldPassword", "passwordError2", "Mật khẩu cũ sai");
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }
        String hashPassword = BCrypt.hashpw(passwordDto.getPasswordConfirm(), BCrypt.gensalt(12));
        account.setPassword(hashPassword);
        accountService.updateAccount(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
