package jblog.controller.api;


import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jblog.service.UserService;
import jblog.vo.UserVo;

@RestController("userApiController")
@RequestMapping("/user/api")
public class UserController {
   private final UserService userService;
   
   public UserController(UserService userService) {
      this.userService = userService;
   }
   
   // "{ exist: false or true }"
   @GetMapping("/checkId")
   public Object checkId(@RequestParam(value="id", required=true, defaultValue = "") String id) {
      UserVo userVo = userService.getUser(id);
      return Map.of("exist", userVo != null);
   }
}
