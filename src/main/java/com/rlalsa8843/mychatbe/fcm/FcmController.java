package com.rlalsa8843.mychatbe.fcm;

import com.rlalsa8843.mychatbe.fcm.service.FirebaseCloudMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
public class FcmController {
    private final FirebaseCloudMessageService messageService;

    @Autowired
    public FcmController(FirebaseCloudMessageService firebaseCloudMessageService) {
        this.messageService = firebaseCloudMessageService;
    }

    @PostMapping("/send")
    @ResponseBody
    public String send(@RequestBody Map<Object, String> map) {
        String token = map.get("token");
//        String targetToken = "cnL9oDrsT9qqeV3kx3ZzeC:APA91bGYmIiU8RdMCvzwm5nqfW6kdjjLV6tatVYjOgMx0" +
//                "-olnPGObgn4Gw20Srxl1kSkzJd3bx8pG6Z2jk0NObVygF5IKfL64Bs2NUKoKzB8ujaMz1unpOnBHMsCKDNcCFqk7icNPkPI";
        String title = map.get("title");
        String body = map.get("body");

        try {
            messageService.sendMessageTo(token, title, body);
        } catch (IOException e) {
            e.printStackTrace();

            return "FAIL";
        }
        return "SUCCESS";
    }
}
