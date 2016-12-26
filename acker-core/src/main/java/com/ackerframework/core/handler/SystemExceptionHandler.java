package com.ackerframework.core.handler;

import com.ackerframework.base.entity.Result;
import com.ackerframework.utils.Constant;
import com.ackerframework.utils.GlobalUtils;
import com.ackerframework.utils.JsonMapper;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SystemExceptionHandler implements HandlerExceptionResolver {

    public static final String ERROR_500 = "/error/exception";
    protected Logger logger = LoggerFactory.getLogger(Constant.LOGGER_EXLOG);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        ModelAndView MV = new ModelAndView();
        if (GlobalUtils.isAjax(request)) {
            try {
                response.setStatus(HttpStatus.OK.value()); //设置状态码
                response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
                response.setCharacterEncoding("UTF-8"); //避免乱码
                response.setHeader("Cache-Control", "no-cache, must-revalidate");
                Result result = new Result();
                result.setStatus(false);
                result.setMessage(ex.getMessage());
                String json = JsonMapper.getInstance().toJson(result);
                response.getWriter().write(json);
                MDC.put("exName", ex.getClass().getName());
                logger.error(ex.getMessage().replace("'","‘").replace("--","-"));
            } catch (IOException e) {
                MDC.put("exName", e.getClass().getName());
                logger.error(e.getMessage().replace("'","‘").replace("--","-"));
            }
        } else {
            MV.setViewName(ERROR_500);
            MV.addObject("exceptionName", ex.getClass().getName());
            MV.addObject("message", ex.getMessage());
            MDC.put("exName", ex.getClass().getName());
            logger.error(ex.getMessage().replace("'","‘").replace("--","-"));
        }
        return MV;
    }
}
