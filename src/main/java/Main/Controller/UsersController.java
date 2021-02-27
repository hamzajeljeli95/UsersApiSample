package Main.Controller;

import Main.Models.UserAuth;
import Main.Models.UsersEntity;
import Main.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "Users")
public class UsersController {

    @Autowired
    UsersService service;

    @RequestMapping(value = "/Signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> Signup(@RequestBody UsersEntity body) {
        return new ResponseEntity<>(service.Signup(body), HttpStatus.OK);
    }

    @RequestMapping(value = "/Signin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> Signin(@RequestBody UserAuth body) {
        return new ResponseEntity<>(service.Signin(body), HttpStatus.OK);
    }

    @RequestMapping(value = "/List", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsersEntity>> GetAll() {
        return new ResponseEntity<>(service.GetAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/Get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsersEntity> Get(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.Get(id), HttpStatus.OK);
    }
}
