package com.springboot.config.security;

import com.springboot.domain.SysUser;
import com.springboot.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功
 * Created by wanglu-jf on 17/8/17.
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException,ServletException {
        logger.info("[LoginSuccessHandler][onAuthenticationSuccess]invoke...start");
        //获得授权后可得到用户信息   可使用SUserService进行数据库操作
        SysUser userDetails = (SysUser)authentication.getPrincipal();
       /* Set<SysRole> roles = userDetails.getSysRoles();*/
        //输出登录提示信息
        logger.info("[LoginSuccessHandler][管理员{}登录成功,IP :{}]",userDetails.getName(), HttpUtil.getIpAddress(request));
        logger.info("[LoginSuccessHandler][onAuthenticationSuccess][result:{}]...end",userDetails);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
