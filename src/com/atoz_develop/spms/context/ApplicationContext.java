package com.atoz_develop.spms.context;

import com.atoz_develop.spms.annotation.Component;
import com.atoz_develop.spms.vo.Project;
import org.reflections.Reflections;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ApplicationContext {

    // 만든 객체 보관용
    Map<String, Object> objTable = new Hashtable<>();

    // 객체를 꺼낼 getter
    public Object getBean(String key) {
        return objTable.get(key);
    }

    // 외부에서 생성한 객체 등록용
    public void addBean(String name, Object obj) {
        objTable.put(name, obj);
    }

    /**
     * 프로퍼티에 따라 객체 준비
     * @param propertiesPath
     * @throws Exception
     */
    public void prepareObjectsByProperties(String propertiesPath) throws Exception {
        Properties props = new Properties();
        props.load(new FileReader(propertiesPath));

        Context ctx = new InitialContext();
        String key = null;
        String value = null;

        for (Object item: props.keySet()) {
            key = (String) item;
            value = props.getProperty(key);
            if (key.startsWith("jndi.")) {
                objTable.put(key, ctx.lookup(value));
            } else {
                objTable.put(key, Class.forName(value).getDeclaredConstructor().newInstance());
            }
        }
    }

    /**
     * 각 객체가 필요로 하는 의존 객체 할당
     * @throws Exception
     */
    public void injectDependency() throws Exception {
        for (String key: objTable.keySet()) {
            if (!key.startsWith("jndi.")) {
                callSetter(objTable.get(key));  // Setter 호출
            }
        }
    }

    /**
     * 파라미터로 주어진 객체에 대해 setter 메소드를 찾아 호출
     * @param object setter 메소드를 찾을 객체
     * @throws Exception
     */
    private void callSetter(Object object) throws Exception {
        Object dependency = null;

        for (Method m: object.getClass().getMethods()) {
            if (m.getName().startsWith("set")) {    // Setter()를 찾아서
                dependency = findObjectByType(m.getParameterTypes()[0]);

                if (dependency != null) {   // Setter()를 호출
                    m.invoke(object, dependency);
                }
            }
        }
    }

    /**
     * Map에서 type에 맞는 의존 객체를 찾아 리턴
     * @param type
     * @return 의존 객체
     */
    private Object findObjectByType(Class<?> type) {
        for (Object obj: objTable.values()) {
            if (type.isInstance(obj)) {
                return obj;
            }
        }

        return null;
    }

    /**
     * classpath를 뒤져서 Component 어노테이션이 지정된 클래스를 찾은뒤 해당 클래스의 객체를 준비한다.
     * 생성된 객체는 객체 테이블에 담는다.
     * 오픈소스 라이브러리 Reflections 사용: 자바 리플랙션 API보다 사용하기 쉽고 편함
     * @throws Exception
     */
    public void prepareObjectsByAnnotation(String basePackage) throws Exception {
        Reflections reflector = new Reflections(basePackage);

        Set<Class<?>> list = reflector.getTypesAnnotatedWith(Component.class);
        String key = null;
        for(Class<?> clazz: list) {
            key = clazz.getAnnotation(Component.class).value();
            objTable.put(key, clazz.getDeclaredConstructor().newInstance());
        }
    }
}
