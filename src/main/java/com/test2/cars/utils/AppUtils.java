package com.test2.cars.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

public class AppUtils {
    public static String getUrl(String file) throws MalformedURLException {
        HttpServletRequest req = getCurrentRequest();
        return new URL(
                req.getScheme(),
                req.getServerName(),
                req.getServerPort(),
                file
        ).toString();
    }

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
