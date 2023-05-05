package org.myboot.service.impl;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.myboot.config.AppUrl;
import org.myboot.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * Demo服务接口实现
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Resource private AppUrl appUrl;

    @Override
    public String getAppUrl() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception ignored) {
        }

        return appUrl.getMyUrl();
    }
}
