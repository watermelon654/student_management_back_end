package com.student_management.demo.filter;

import com.alibaba.fastjson2.JSON;
import com.student_management.demo.service.auth.UserGrantedAuthority;
import com.student_management.demo.utils.token.JwtTokenUtil;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.student_management.demo.common.error.ErrorCodeConstants.*;
import static com.student_management.demo.utils.exception.ServiceExceptionUtil.exception;

/**
 * JWT过滤器：从请求头的Authorization中获取JWT中存入的用户信息
 * 并添加到Spring Security的上下文中
 * 以致于Spring Security后续的组件（包括过滤器等）能从上下文中获取此用户的信息
 * 从而验证是否已经登录、是否具有权限等
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JwtAuthenticationFilter.doFilterInternal()"+request.getRequestURL());
        // 清除Spring Security上下文中的数据
        // 避免此前曾经存入过用户信息，后续即使没有携带JWT，在Spring Security仍保存有上下文数据（包括用户信息）
        System.out.println("清除Spring Security上下文中的数据");
        SecurityContextHolder.clearContext();
        // 客户端提交请求时，必须在请求头的Authentication中添加JWT数据，这是当前服务器程序的规定，客户端必须遵守
        // 尝试获取JWT数据
        String token = request.getHeader("Authorization");
        if(token != null && token.contains("Bearer")){
            token = token.substring(7);
        }
        System.out.println("从请求头中获取到的JWT=" + token);
        // 判断是否不存在jwt数据
        if (!StringUtils.hasText(token)) {
            // 不存在jwt数据，则放行，后续还有其它过滤器及相关组件进行其它的处理，例如未登录则要求登录等
            // 此处不宜直接阻止运行，因为“登录”、“注册”等请求本应该没有jwt数据
            System.out.println("请求头中无JWT数据，当前过滤器将放行");
            filterChain.doFilter(request, response); // 继续执行过滤器链中后续的过滤器
            return; // 必须
        }

        // 注意：此时执行时，如果请求头中携带了Authentication，日志中将输出，且不会有任何响应，因为当前过滤器尚未放行
        // 以下代码有可能抛出异常的
        // TODO 密钥和各个Key应该统一定义
        String username = null;
        String permissionsString = null;
        try {
            System.out.println("请求头中包含JWT，准备解析此数据……");
            username = jwtTokenUtil.getUsernameFromToken(token);
            permissionsString = jwtTokenUtil.getPermissionsFromToken(token);
            System.out.println("username=" + username);
            System.out.println("permissionsString=" + permissionsString);
        } catch (ExpiredJwtException e) {
            request.setAttribute("filterError", TOKEN_EXPIRED);
            request.getRequestDispatcher("/error/jwt").forward(request, response);
            return;
            //throw exception(TOKEN_EXPIRED);
        } catch (MalformedJwtException e) {
            request.setAttribute("filterError", ERROR_TOKEN_DATA);
            request.getRequestDispatcher("/error/jwt").forward(request, response);
            return;
            //throw exception(ERROR_TOKEN_DATA);
        } catch (SignatureException e) {
            request.setAttribute("filterError", ERROR_TOKEN_SIGNATURE);
            request.getRequestDispatcher("/error/jwt").forward(request, response);
            return;
            //throw exception(ERROR_TOKEN_SIGNATURE);
        } catch (Throwable e) {
            request.setAttribute("filterError", ERROR_TOKEN);
            request.getRequestDispatcher("/error/jwt").forward(request, response);
            return;
            //throw exception(ERROR_TOKEN);
        }

        // 将此前从JWT中读取到的permissionsString（JSON字符串）转换成Collection<? extends GrantedAuthority>
        //List<UserGrantedAuthority> permissions = UserGrantedAuthority.convert(permissionsString);
        String[] permissionstrs= permissionsString
                        .replace("authority=","")
                        .replace("{","")
                        .replace("}","")
                        .replace("[","")
                        .replace("]","")
                .split(", ");

        List<SimpleGrantedAuthority> permissions = new ArrayList<>();
        for (String s:permissionstrs) {
            permissions.add(new SimpleGrantedAuthority(s));
        }

        System.out.println("从JWT中获取到的权限转换成Spring Security要求的类型：" + permissions);
        // 将解析得到的用户信息传递给Spring Security
        // 获取Spring Security的上下文，并将Authentication放到上下文中
        // 在Authentication中封装：用户名、null（密码）、权限列表
        // 因为接下来并不会处理认证，所以Authentication中不需要密码
        // 后续，Spring Security发现上下文中有Authentication时，就会视为已登录，甚至可以获取相关信息
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(username, null, permissions);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("将解析得到的用户信息传递给Spring Security");
        // 放行
        System.out.println("JwtAuthenticationFilter 放行");
        filterChain.doFilter(request, response);
    }

}
