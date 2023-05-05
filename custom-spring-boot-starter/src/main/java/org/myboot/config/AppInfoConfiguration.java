package org.myboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 自动配置类：在META-INF中不做配置时，会抛出警告 <code>Application context not configured for this file</code> <br>
 * 自动配置类<br>
 * 这里可以加一些逻辑：比如 没有设置就弃用自动注入
 */
@AutoConfiguration
public class AppInfoConfiguration {
    @Value("${app.url.myUrl}")
    private String myUrl;

    public String getMyUrl() {
        return myUrl;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }

    @Bean
    public AppUrl generateAppUrl() {
        AppUrl appUrl = new AppUrl();
        appUrl.setMyUrl(myUrl);
        return appUrl;
    }
}
