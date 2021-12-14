package com.rlalsa8843.mychatbe.api.v1;

import com.rlalsa8843.mychatbe.fcm.service.FirebaseCloudMessageService;
import com.rlalsa8843.mychatbe.mongo.dto.Person;
import com.rlalsa8843.mychatbe.mongo.dto.User;
import com.rlalsa8843.mychatbe.mongo.service.MongoService;
import com.rlalsa8843.mychatbe.mongo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class ApiController {
    private final MongoService mongoService;
    private final UserService userService;
    private final FirebaseCloudMessageService messageService;

    public ApiController(MongoService mongoService, UserService userService, FirebaseCloudMessageService messageService) {
        this.mongoService = mongoService;
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String home() {
        return "Hello, This is mongo";
    }

    @GetMapping("/findOne/{name}")
    public Person findOne(@PathVariable String name) {
        return mongoService.findByName(name);
    }

    @PostMapping("/save")
    public String save(@RequestBody User user) {
        userService.save(user);

        return "저장하였습니다.";
    }

    @GetMapping("/findByID/{deviceID}")
    public User findByID(@PathVariable String deviceID) {
        return userService.findByDeviceID(deviceID);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public Map<String, Object> needSignup() {
        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("message", "Must Need SignUp");

        return map;
    }



    @PostMapping("/save/token")
    public String saveToken(@RequestBody String token) {
        System.out.println(token);

        return "Success";
    }

    @PostMapping("/send")
    public String send(@RequestBody List<Map<Object, Object>> message) {
        Map<Object, Object> _message = message.get(0);
        String body = _message.get("text").toString();
        String _id = ((Map<Object, String>) _message.get("user") ).get("_id");
        String token = userService.getToken(_id);
        String title = "title";

        System.out.println(_id);
        System.out.println(token);

        try {
            messageService.sendMessageTo(token, title, body);
        } catch (IOException e) {
            e.printStackTrace();

            return "fail";
        }

        return "Success";

    }
}
