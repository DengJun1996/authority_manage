package com.dj.system;

import com.dj.common.ResEntity;
import com.dj.common.ResultEnum;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysUserService;
import com.dj.system.service.WebSocketServer;
import com.dj.system.vo.SysUserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wxl
 * @date 2020-03-16 下午4:28
 * @company www.dfdk.com.cn
 */
@Api(tags = "登录接口API")
@RestController
public class LoginController {

    private SysUserService sysUserService;

    public LoginController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @ApiOperation(value = "登录接口")
    @PostMapping("/toLogin")
    public ResEntity<?> login(SysUserEntity userEntity) {

        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userEntity.getUserName(), userEntity.getPassword());
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResEntity.ERROR(ResultEnum.UNKNOWN_ACCOUNT);
        } catch (IncorrectCredentialsException e) {
            return ResEntity.ERROR(ResultEnum.INCORRECT_CREDENTIALS);
        } catch (LockedAccountException e) {
            return ResEntity.ERROR(ResultEnum.LOCKED_ACCOUNT);
        } catch (AuthenticationException e) {
            return ResEntity.ERROR(ResultEnum.AUTHENTICATION);
        }
        return ResEntity.SUCCESS();
    }

    /**
     * token登录
     *
     * @param sysUserVo
     * @return
     */
    @ApiOperation(value = "token登录接口")
    @PostMapping(value = "/tokenLogin")
    public ResEntity<?> tokenLogin(@Validated SysUserVo sysUserVo) {
        return sysUserService.tokenLogin(sysUserVo);
    }


    /**
     * token登出
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "token登出接口")
    @PostMapping(value = "/tokenLogout")
    public ResEntity<?> tokenLogout(HttpServletRequest request) {
        String header = request.getHeader("dnToken");
        return sysUserService.tokenLogout(header);
    }
}
