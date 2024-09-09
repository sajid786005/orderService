package com.Abbas.Online.food.Ordering.Controller;

import com.Abbas.Online.food.Ordering.Config.JwtProvider;
import com.Abbas.Online.food.Ordering.Repository.CartRepository;
import com.Abbas.Online.food.Ordering.Repository.UserRepository;
import com.Abbas.Online.food.Ordering.Request.LoginRequest;
import com.Abbas.Online.food.Ordering.Response.AuthRespons;
import com.Abbas.Online.food.Ordering.Service.CustomerUserDetailService;
import com.Abbas.Online.food.Ordering.model.Cart;
import com.Abbas.Online.food.Ordering.model.USER_ROLE;
import com.Abbas.Online.food.Ordering.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {
@Autowired
    private UserRepository userRepository;
@Autowired
    private PasswordEncoder passwordEncoder;
@Autowired
    private JwtProvider jwtProvider;
@Autowired
    private CustomerUserDetailService customerUserDetailService;
@Autowired
    private CartRepository cartRepository;

@PostMapping("/signup")
public ResponseEntity<AuthRespons>createUserHandler(@RequestBody User user) throws Exception {
    User isEmailExist = userRepository.findByEmail(user.getEmail());
    if ((isEmailExist!=null)){
        throw new Exception("Email is already used with another account");
    }
    User createdUser=new User();
    createdUser.setEmail(user.getEmail());
    createdUser.setFullName(user.getFullName());
    createdUser.setRole(user.getRole());
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

    User savedUser = userRepository.save(createdUser);
    Cart cart = new Cart();
    cart.setCustomer(savedUser);
    cartRepository.save(cart);

    UsernamePasswordAuthenticationToken authentication= new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt= jwtProvider.getEmailFromJwtToken(String.valueOf(authentication));
    AuthRespons authResponse = new AuthRespons();

    Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
    String role= authorities.isEmpty()? null: authorities.iterator().next().getAuthority();
    authResponse.setMessage("login success");

    authResponse.setRole(savedUser.getRole());
    return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

}

@PostMapping("/signin")
public  ResponseEntity<AuthRespons> singnin(@RequestBody LoginRequest request){
    String username= request.getEmail();
    String password= request.getPassword();

    Authentication authentication= authenticate(username,password);
    Collection<? extends GrantedAuthority> authorities= authentication.getAuthorities();
    String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

    String jwt = jwtProvider.generateToken(authentication);

    AuthRespons authRespons= new AuthRespons();
    authRespons.setJwt(jwt);
    authRespons.setMessage("Register success");
    authRespons.setRole(USER_ROLE.valueOf(role));

    return  new ResponseEntity<>(authRespons,HttpStatus.CREATED);

}

    private Authentication authenticate(String username, String password) {
     UserDetails userDetails= customerUserDetailService.loadUserByUsername(username);
     if (userDetails==null){
         throw new BadCredentialsException("invialed password...");
     }
     if (! passwordEncoder.matches(password,userDetails.getPassword())){
         throw  new BadCredentialsException("inbialid password");
     }
     return  new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities() );
    }
    }


