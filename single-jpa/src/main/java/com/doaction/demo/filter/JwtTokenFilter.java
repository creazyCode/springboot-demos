package com.doaction.demo.filter;

import com.alibaba.fastjson.JSON;
import com.doaction.demo.api.BizCode;
import com.doaction.demo.api.base.resp.RespData;
import com.doaction.demo.common.constants.JwtKeys;
import com.doaction.demo.common.enums.CharEnum;
import com.doaction.demo.common.pojo.JwtPayload;
import com.doaction.demo.common.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebFilter(filterName = "tokenFilter",urlPatterns =
        {"/api/v1/chain-manager-service/operation/*",
                "/api/v1/chain-manager-service/user/*",
               "/api/v1/chain-manager-service/common/*"})
public class JwtTokenFilter implements Filter {

    public static final String AUTH = "Authorization";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 设置输出编码格式，如有需要更改text/json返回类型请自行在相应方法中进行修改
        response.setHeader("Content-type", "text/json;charset=UTF-8");
        response.setCharacterEncoding(CharEnum.UTF_8.getCode());

        String token = request.getHeader(AUTH);
        try{
            JwtPayload jwtPayload = JwtTokenUtil.verify(token, JwtTokenUtil.JWT_SECRET);
            request.setAttribute(JwtKeys.CID, jwtPayload.getCid());
            request.setAttribute(JwtKeys.RID, jwtPayload.getRid());
            request.setAttribute(JwtKeys.CNAME, jwtPayload.getCname());
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }catch (Exception e){
            //todo 完善日志
            log.info("login failed ！");
            e.printStackTrace();
            response.getWriter().print(JSON.toJSONString(RespData.fail(BizCode.UNAUTHORIZED)));
        }
        response.getWriter().print(JSON.toJSONString(RespData.fail(BizCode.UNAUTHORIZED)));
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
