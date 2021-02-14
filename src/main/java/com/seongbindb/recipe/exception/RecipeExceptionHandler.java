package com.seongbindb.recipe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * Exception 관련 처리를 하기 위해 SimpleMappingExceptionResolver 를 상속받아 구현한 ErrorException 클래스
 * 에러 발생 시 로그정보를 출력하기 위함
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:38:14
 * @Version : 1.0
 */
public class RecipeExceptionHandler extends SimpleMappingExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(RecipeExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("#########CATCH ERROR: " + ex);
        return super.resolveException(request, response, handler, ex);
    }

}

