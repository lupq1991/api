package com.cn.phoenix.api.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Enumeration;


/**
 * 定义一个切面
 */
@Aspect
@Component
public class LogRecordAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogRecordAspect.class);
    private final String packagePath = "execution(*  *..*.*.controller..*.*(..))";
    private static final String UTF_8 = "utf-8";

    /**
     * 定义切点Pointcut
     */
    @Pointcut(packagePath)
    public void excudeService() {
    }

    /**
     * 执行切点 之前
     *
     * @param pjp
     */
    @Before("excudeService()")
    public void exBefore(JoinPoint pjp) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
    }


    /**
     * 通知（环绕）
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        long endTime = System.currentTimeMillis();
        String method = request.getMethod();
        String url = request.getRequestURI();
        String queryString = request.getQueryString();
        Enumeration<String> headerNames = request.getHeaderNames();
        logger.info("——————————————————start→header——————————————————");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            logger.info("{}→ {}", headerName, request.getHeader(headerName));
        }
        logger.info("——————————————————end→header——————————————————————");
        Object[] args = pjp.getArgs();
        String params;
        // result的值就是被拦截方法的返回值
        Object result = pjp.proceed();
        try {
            long startTime = (long) request.getAttribute("startTime");
            //获取请求参数集合并进行遍历拼接
            if (args.length > 0) {
                Object object = args[0];
                params = JSON.toJSONString(object, SerializerFeature.WriteMapNullValue);
                params = URLDecoder.decode(params, UTF_8);
            } else {
                params = queryString;
            }
            logger.info("——————————————————start————————————————————————————");
            logger.info("IP:{}", request.getRemoteAddr());
            logger.info("requestMethod:{}", method);
            logger.info("url:{}", url);
            logger.info("params:{}", params);
            logger.info("responseBody:{}", JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
            logger.info("elapsed:{} ms", (endTime - startTime));
            logger.info("——————————————————end——————————————————————————");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("log error !!", e);
        }
        return result;
    }


    //    执行切点之后
    @After("excudeService()")
    public void exAfter(JoinPoint joinPoint) {
    }

}


