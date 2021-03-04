package com.atguigu.guli.service.base.handler;

import com.atguigu.guli.service.base.result.R;
import com.atguigu.guli.service.base.result.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.commons.lang3.exception.ExceptionUtils;
/**
 * description:
 * Created by Kris on 2021/3/4
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R error(Exception e){
//        e.printStackTrace();
//        log.error(e.getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
//        log.error(ExceptionUtils.getStackTrace(e));
        return R.error();
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseBody
    public R error(BadSqlGrammarException e){
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseBody
    public R error(HttpMessageNotReadableException e){
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }
}
