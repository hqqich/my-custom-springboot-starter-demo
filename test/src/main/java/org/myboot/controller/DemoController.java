package org.myboot.controller;

import com.tsinglink.common.utils.db.LevelDBUtil;
import java.util.Optional;
import javax.annotation.Resource;
import org.myboot.service.DemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Resource
    private DemoService demoService;

    @PostMapping("/test")
    public String test() {

        Optional<String> key = LevelDBUtil.readObject("app.url.myUrl", String.class);

        if (key.isPresent()) {
            return key.get();
        } else {
            String appUrl = demoService.getAppUrl();
            LevelDBUtil.writeObject("app.url.myUrl", appUrl);
            return appUrl;
        }
    }
}
