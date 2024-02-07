package org.example.storeadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController { // 템플릿으로 이동하기 위한 Controller

    @RequestMapping(path = {"", "/main"}) //기본 주소, 메인
    public ModelAndView main(){

        return new ModelAndView("main");
        //view 의 name -> main.html 매핑: .html 붙여줌

    }

    @RequestMapping("/order")
    public ModelAndView order(){ //order.html 매핑
            return new ModelAndView("order/order");
            //order 하위에 order

    }
}

