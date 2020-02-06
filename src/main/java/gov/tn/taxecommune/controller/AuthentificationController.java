//package gov.tn.taxecommune.controller;
//
//import javax.websocket.server.PathParam;
//
////import javax.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.data.rest.webmvc.RepositoryRestController;
//import org.springframework.http.HttpMethod;
////import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
////import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.CrossOrigin;
////import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
////import org.springframework.web.bind.annotation.RequestMethod;
////import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//import gov.tn.taxecommune.message.request.LoginForm;
//import gov.tn.taxecommune.message.response.JwtResponse;
//import gov.tn.taxecommune.repository.RoleRepository;
//import gov.tn.taxecommune.repository.UserRepository;
//import gov.tn.taxecommune.security.jwt.JwtProvider;
//
//@CrossOrigin("*")
////@RestController("authentification") // shorthand for @Controller and @ResponseBody rolled together
////@GetMapping(value="/item", produces=MediaType.APPLICATION_JSON_VALUE)
////@RepositoryRestController
////@EnableAutoConfiguration
////@RequestMapping(value="/authentification/", produces=MediaType.APPLICATION_JSON_VALUE)
//@Controller
//@RequestMapping("/taxecommune/rest/v1/services/authentification")
//public class AuthentificationController {
//
//	@Autowired	
//	AuthenticationManager authenticationManager;
//
//	@Autowired
//	UserRepository userRepository;
//
//	@Autowired
//	RoleRepository roleRepository;
//
//	@Autowired
//	PasswordEncoder encoder;
//		
//	@Autowired
//	JwtProvider jwtProvider;
//	
////	@Autowired
////	public AuthentificationController(UserRepository userRepository) {
////		this.userRepository = userRepository;
////	}
////	@RequestMapping(method = RequestMethod.GET, value="/authentification/login")
////	@GetMapping("login")
////	@RequestMapping(value="/login", method= RequestMethod.GET)
//	@PostMapping(value="/login")
//	public  ResponseEntity<?> loginUser(@PathParam(value="username") String username,@PathParam(value="password") String password) {
//	//public ResponseEntity<?> loginUser(@Valid @RequestBody LoginForm loginRequest) {
//		
//		UserDetails userDetails = null;
//		String jwt = null;
//		try {
//			Authentication authentication = authenticationManager.authenticate(
////					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//					new UsernamePasswordAuthenticationToken(username, password));
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//			jwt = jwtProvider.generateJwtToken(authentication);
//			userDetails = (UserDetails) authentication.getPrincipal();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));		
//	}
//
//	@GetMapping("signin")	
//	public String signinUser(Model model) {
//		return "redirect:login";
//	}
//
//}