package com.dj.system;

import com.dj.common.ResEntity;
import com.dj.common.ResultEnum;
import com.dj.system.model.SysUserEntity;
import com.dj.system.service.SysUserService;
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

/**
 * @author wxl
 * @date 2020-03-16 下午4:28
 * @company www.dfdk.com.cn
 */
@Api(tags = "登录接口API")
@RestController
public class LoginController {

    private SysUserService sysUserService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

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

}
