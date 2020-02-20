package com.atoz_develop.spms.servlets;

import com.atoz_develop.spms.bind.DataBinding;
import com.atoz_develop.spms.bind.ServletRequestDataBinder;
import com.atoz_develop.spms.context.ApplicationContext;
import com.atoz_develop.spms.controls.*;
import com.atoz_develop.spms.listeners.ContextLoaderListener;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Front Controller
 * 배치 URL을 "*.do"로 지정하여 클라이언트의 요청 중 서블릿 경로 이름이 .do로 끝나는 경우 이 서블릿이 처리하도록 함
 * <p>
 * 1. 클라이언트의 요청을 알맞은 PageController에게 전달
 * 2. PageController가 필요한 데이터 준비
 * 3. PageController의 결과 데이터를 JSP에서 사용할 수 있도록 서블릿 보관소에 저장
 * 4. Exception 처리
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    /*
    참고: Servlet 인터페이스의 service()가 아니므로 서블릿 컨테이너가 직접 호출하지 않음
    서블릿 컨테이너는 service(ServletRequest, ServletResponse)를 호출하고
    이 메소드가 service(HttpServletRequest, HttpSerlvetResponse)를 호출하는 구조
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        // 요청 서블릿 URL
        String servletPath = req.getServletPath();

        try {
            ApplicationContext ctx = ContextLoaderListener.getApplicationContext();

            // FrontController - PageController 데이터 전달을 위한 Map 객체
            Map<String, Object> model = new HashMap<>();
            model.put("session", req.getSession());

            // 요청 서블릿 URL에 따라 PageController 지정
            // 어플리케이션 시작 시 생성된 PageController 사용
            Controller pageController = (Controller) ctx.getBean(servletPath);
            if (pageController == null) {
                throw new Exception("요청한 서비스를 찾을 수 없습니다.");
            }

            // DataBinding 구현 여부 확인 후 데이터를 준비하는 prepareRequestData() 호출
            if(pageController instanceof DataBinding) {
                prepareRequestData(req, model, (DataBinding) pageController);
            }

            // PageController에 실행 요청
            String viewUrl = pageController.execute(model);

            // PageController 실행 완료 후 결과 데이터를 JSP가 사용할 수 있도록 ServletRequest에 보관
            for (String key : model.keySet()) {
                req.setAttribute(key, model.get(key));
            }

            // 화면 출력을 위해 ServletRequest에 보관된 뷰 URL로 실행 위임 -s
            if (viewUrl.startsWith("redirect:")) {   // redirect
                resp.sendRedirect(req.getServletContext().getContextPath() + viewUrl.substring(9));

                return;
            } else {    // include
                RequestDispatcher rd = req.getRequestDispatcher(viewUrl);
                rd.include(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
            rd.forward(req, resp);
        }
    }

    /**
     * 데이터 준비
     * @param request
     * @param model
     * @param dataBinding
     * @throws Exception
     */
    private void prepareRequestData(HttpServletRequest request, Map<String, Object> model, DataBinding dataBinding) throws Exception {
        // 필요한 데이터 확인
        Object[] dataBinders = dataBinding.getDataBinders();
//        System.out.println("필요한 데이터 : " + Arrays.toString(dataBinders));
        String dataName = null;
        Class<?> dataType = null;
        Object dataObj = null;

        for(int i=0; i<dataBinders.length; i+=2) {
            dataName = (String) dataBinders[i];
            dataType = (Class<?>) dataBinders[i+1];
            // dataName과 일치하는 요청 매개변수 값을 dataType 인스턴스에 저장하고 반환
            dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
            model.put(dataName, dataObj);
        }
    }
}
