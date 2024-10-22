// package viettridao.mockproject.configurations.annotations;
// import org.springframework.beans.MutablePropertyValues;
// import org.springframework.core.MethodParameter;
// import org.springframework.stereotype.Component;
// import org.springframework.web.bind.WebDataBinder;
// import org.springframework.web.bind.annotation.InitBinder;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.support.WebDataBinderFactory;
// import org.springframework.web.context.request.NativeWebRequest;
// import
// org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
// import org.springframework.web.method.support.HandlerMethodArgumentResolver;
// import org.springframework.web.method.support.ModelAndViewContainer;

// import java.lang.reflect.Field;
// import java.util.HashMap;
// import java.util.Map;

// @Component
// public class CustomModelAttributeMethodProcessor extends
// ModelAttributeMethodProcessor {

// public CustomModelAttributeMethodProcessor() {
// super(true);
// }

// @Override
// public boolean supportsParameter(MethodParameter parameter) {
// return parameter.hasParameterAnnotation(ModelAttribute.class);
// }

// @Override
// protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest
// request) {
// Object target = binder.getTarget();
// if (target != null) {
// Class<?> targetClass = target.getClass();
// Map<String, String> aliasMapping = new HashMap<>();

// // Tạo ánh xạ alias từ custom annotation
// for (Field field : targetClass.getDeclaredFields()) {
// if (field.isAnnotationPresent(RequestParamAlias.class)) {
// RequestParamAlias alias = field.getAnnotation(RequestParamAlias.class);
// aliasMapping.put(alias.value(), field.getName());
// }
// }

// // Tạo một map các giá trị request parameter với alias
// Map<String, String[]> parameterMap = new
// HashMap<>(request.getParameterMap());
// for (Map.Entry<String, String> entry : aliasMapping.entrySet()) {
// if (parameterMap.containsKey(entry.getKey())) {
// parameterMap.put(entry.getValue(), parameterMap.remove(entry.getKey()));
// }
// }

// // Sử dụng WebDataBinder để bind các giá trị từ request vào đối tượng target
// MutablePropertyValues mpvs = new MutablePropertyValues(parameterMap);
// binder.bind(mpvs);
// }
// }
// }
