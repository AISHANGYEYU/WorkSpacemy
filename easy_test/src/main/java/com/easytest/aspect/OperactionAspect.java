package com.easytest.aspect;


import com.easytest.annotation.GlobalInterceptor;
import com.easytest.annotation.VerifyParam;
import com.easytest.constants.Constants;
import com.easytest.entity.enums.ResponseCodeEnum;
import com.easytest.exception.BusinessException;
import com.easytest.utils.StringTools;
import com.easytest.utils.VerifyUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class OperactionAspect {//切面类
    private static final Logger logger = LoggerFactory.getLogger(OperactionAspect.class);
    private static final String[] TYPE_BASE = {"java.lang.String", "java.lang.Integer", "java.lang.Long"};

    @Pointcut("@annotation(com.easytest.annotation.GlobalInterceptor)")
    private void requestInterceptor() {
    }

    @Around("requestInterceptor()")//切点
    public Object interceptorDo(ProceedingJoinPoint point) {
        //下面时通知
        try {
            Object target = point.getTarget();//获取目标代理对象
            Object[] argument = point.getArgs();//获取目标方法运行的参数数组
            String methodName = point.getSignature().getName();//获取目标运行方法名
            Class<?>[] parameterTypes =
                    ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();//拿方法参数类型类：比如String.class,已便获取方法时候用
            Method method =
                    target.getClass().getMethod(methodName, parameterTypes);//获取代理对象target的classd对象，再通过方法名和参数类型选出对应的方法

//            Signature signature = point.getSignature();
//            MethodSignature methodSignature = (MethodSignature) signature;
//            Method method = methodSignature.getMethod();

            GlobalInterceptor interceptor =
                    method.getAnnotation(GlobalInterceptor.class);
            //使用了 method.getAnnotation(GlobalInterceptor.class)
            // 来获取目标方法上的 GlobalInterceptor 注解，
            // 这样你就可以在切面中根据这个注解执行相应的逻辑。
            /**
             * 校验登录
             */
            if (interceptor.checkLogin()) {
                checkLogin();
            }
            /**
             *校验参数
             */
            if (interceptor.checkParams()) {
                /**
                 * 遍历参数注解VerifyParam
                 */
                validateParams(method, argument);
            }

            Object pointResult = point.proceed();
            return pointResult;
        } catch (BusinessException e) {
            logger.error("全局拦截器异常" + e);
            System.out.println(789789);
            throw e;
        } catch (Exception e) {
            logger.error("全局拦截器异常" + e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        } catch (Throwable e) {
            logger.error("全局拦截器异常" + e);
            throw new BusinessException(ResponseCodeEnum.CODE_500);
        }
    }
    //检查登录
    private void checkLogin(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Constants.SESSION_KEY);
        if(null==obj){
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }


    //检查参数
    private void validateParams(Method m, Object[] araguments) {
        Parameter[] parameters = m.getParameters();
        System.out.println(araguments);
        for (int i = 0; i < parameters.length; i++) {
            //获取参数类型
            Parameter parameter = parameters[i];  //java.lang.Integer id
            //实际参数
            Object value = araguments[i];
            //获取annotation,筛选出有@VerifyParam标注的参数
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            if (ArrayUtils.contains(TYPE_BASE, parameter.getParameterizedType().getTypeName())) {
                // parameter.getParameterizedType().getTypeName()=java.lang.Integer
                checkValue(value, verifyParam);
            } else {
                checkObjValue(parameter,value);
            }
        }
    }

    private void checkObjValue(Parameter parameter, Object value) {
        try {
            String typeName = parameter.getType().getTypeName();
            Class aClass = Class.forName(typeName);
            Field[] fields = aClass.getDeclaredFields();//  获取字段 egs:private java.util.Date com.easytest.entity.po.UserInfo.userId
            for (Field field : fields) {
                VerifyParam fieldVerifyParam = field.getAnnotation(VerifyParam.class);
                if (fieldVerifyParam == null){
                    continue;
                }
                field.setAccessible(true);
                Object aaa = field.get(value);
                checkValue(aaa,fieldVerifyParam);
            }

        } catch (Exception e) {
            logger.error("校验参数失败"+ e);
            throw new BusinessException(e.getMessage());
        }
    }

    private void checkValue(Object value, VerifyParam verifyParam) {
        Boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
        Integer length = value == null ? 0 : value.toString().length();
        //校验空   如果传进来的注解的required()的是true并且isEmpty为空（true）
        if (isEmpty && verifyParam.required()) {
            System.out.println("校验空不通过");
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        /**
         * 校验长度，如果不为空，则校验长度
         */
        if (!isEmpty && (verifyParam.max() != -1
                && verifyParam.max() < length || verifyParam.min() != -1
                && verifyParam.min() > length)) {
            System.out.println("校验长度不通过");
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        /**
         * 校验正则，如果不为空，并且正则不为NO，NO就是空
         */
        if (!isEmpty && !StringTools.isEmpty(verifyParam.regex().getRegex())
                && !VerifyUtils.verfiy(verifyParam.regex(), String.valueOf(value))) {
            System.out.println(verifyParam.regex());
            System.out.println("校验正则不通过");
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
        System.out.println("校验通过");
    }
}


