package com.atoz_develop.spms.listeners;

import com.atoz_develop.spms.context.ApplicationContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.InputStream;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 공용 자원 세팅
     *
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        applicationContext = new ApplicationContext();

        String resource = "com/atoz_develop/spms/dao/mybatis-config.xml";
        try {
            // Resources.getResourceAsStream(): ClASSPATH 경로에 있는 파일의 입력 스트림 얻음
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);

            ServletContext sc = sce.getServletContext();
            // 프로퍼티 파일의 이름과 경로 정보를 web.xml에서 가져옴
            String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
            applicationContext.prepareObjectsByProperties(propertiesPath);
            applicationContext.prepareObjectsByAnnotation("");
            applicationContext.injectDependency();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 공용 자원 해제
     *
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 톰캣 서버에 해제 설정이 되어있으므로 구현이 필요 없음
    }
}
