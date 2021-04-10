package com.example.resourceservice.feignService;

import com.example.resourceservice.dtos.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Path;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @GetMapping("/user/id={id}")
    public UserDetailsDTO getUserById(@PathVariable Long id);

    @GetMapping("/user/name={name}")
    public UserDetailsDTO getUserByName(@PathVariable String name);

    @PostMapping("/user/register/id={id}/name={name}/password={password}")
    public HttpStatus registerUser(@PathVariable Long id, @PathVariable String name, @PathVariable String password);
}

/*
* GET & POST API Mappings
The rest of all the GET and POST mapping should be as same as API end points available in user-service controller. (refer user-service controller).
* Then the feign client resolve the end point in smart way.

* */
/*
* @GetMapping("/user/id={id}")
    public UserEntityDTO getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/user/name={name}")
    public UserEntityDTO getUserByName(@PathVariable String name) {
        return userService.getByName(name);
    }

    @PostMapping("/user/register/id={id}/name={name}/password={password}")
    public HttpStatus registerUser(@PathVariable Long id, @PathVariable String name, @PathVariable String password) {
        userService.register(new UserEntityDTO(id, name, password));
        return HttpStatus.OK;
    }

    @PutMapping("/user/modify/new_password={password}")
    public HttpStatus modifyUserPassword(@PathVariable String password) {
        // TO BE IMPLEMENTEDDD LOOOL
        return HttpStatus.OK;
    }
* */