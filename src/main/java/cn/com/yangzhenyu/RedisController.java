package cn.com.yangzhenyu;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.HostInfo;
import cn.hutool.system.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RedisController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("set")
    public String setSession(@RequestParam(required = false) String key, String value) {
        HostInfo hostInfo = SystemUtil.getHostInfo();
        if (StrUtil.isBlank(key)) {
            key = "key";
        }
        httpSession.setAttribute(key, value);
        return port + ":" + "ok";
    }

    @RequestMapping("get")
    public String getSession(String key) {
        HostInfo hostInfo = SystemUtil.getHostInfo();
        if (StrUtil.isBlank(key)) {
            key = "key";
        }
        return port + ":" + httpSession.getAttribute(key);
    }
}
