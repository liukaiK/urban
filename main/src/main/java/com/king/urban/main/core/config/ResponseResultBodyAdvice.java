package com.king.urban.main.core.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.text.StrFormatter;
import com.king.urban.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * JSON增强 把json用Result封装起来
 *
 * @author liukai
 */
@RestControllerAdvice
public class ResponseResultBodyAdvice {

    private static final Logger log = LoggerFactory.getLogger(ResponseResultBodyAdvice.class);


    /**
     * Exception异常处理
     */
//    @ExceptionHandler(Exception.class)
//    public Result exception(Exception exception) {
//        log.error("exception", exception);
//        return Result.fail(500, ExceptionUtils.getStackTrace(exception));
//    }
//
//
//    /**
//     * 运行时异常
//     */
//    @ExceptionHandler(RuntimeException.class)
//    public Object runtimeException(RuntimeException exception, HttpServletRequest request, HttpServletResponse response) {
//        log.error("系统发生异常", exception);
//        if (isAjaxRequest(request)) {
//            return Result.fail(500, ExceptionUtils.getStackTrace(exception));
//        }
//        return new ModelAndView(SystemConstant.PAGE + "/error/500");
//    }
//
    @ExceptionHandler({NotLoginException.class})
    public Result notLoginException(NotLoginException e) {
        log.warn(e.getMessage(), e);
        return Result.fail("500", StrFormatter.format("授权已过期 {}", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result illegalArgumentException(IllegalArgumentException e) {
        return Result.fail("500", e.getMessage());
    }


//    @ExceptionHandler(BusinessException.class)
//    public Object businessException(HttpServletRequest request, BusinessException e) {
//        log.error(e.getMessage(), e);
//        if (HttpUtil.isAjaxRequest(request)) {
//            return Result.fail(500, e.getMessage());
//        } else {
//            ModelAndView modelAndView = new ModelAndView();
//            modelAndView.addObject("errorMessage", e.getMessage());
//            modelAndView.setViewName("error/business");
//            return modelAndView;
//        }
//    }


    /**
     * 自定义验证异常
     */
//    @ExceptionHandler(BindException.class)
//    public Result validatedBindException(BindException e) {
//        log.warn("参数校验异常", e);
//        String message = e.getAllErrors().get(0).getDefaultMessage();
//        return Result.fail(500, message);
//    }

    /**
     * 数据校验异常
     */
//    @ExceptionHandler(DataValidException.class)
//    public Result dataValidException(DataValidException e) {
//        return Result.fail(400, e.getMessage());
//    }

//    /**
//     * 自定义验证异常
//     */
//    @ExceptionHandler(BindException.class)
//    public Result validatedBindException(BindException e) {
//        String message = e.getAllErrors().get(0).getDefaultMessage();
//        return Result.fail(400, message);
//    }
//
//
}
