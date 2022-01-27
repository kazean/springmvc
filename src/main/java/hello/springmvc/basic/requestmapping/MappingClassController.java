package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
@Slf4j
public class MappingClassController {

    /**
     * GET /mapping/users
     */
    @GetMapping
    public String users(){
        return "get users";
    }

    /**
     * POST /mapping/users
     */
    @PostMapping
    public String addUser(){
        return "post users";
    }

    /**
     * GET /mapping/users/{userId}
     */
    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "get userId ="+userId;
    }

    /**
     * PATH /mapping/users/{userId}
     */
    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update userId=" + userId;
    }

    /**
     * DELETE /mapping/users/{userId}
     */
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "Delete userId=" + userId;
    }

}
