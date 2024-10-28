package com.payilagam.MVC.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller  // ithu thaan controller class nu mention pandrom spring boot la
public class indexController {

     // yaarachum /index nu kadaisila url kuduthaa index.jsp call aagidum and then intha index() call aagum
    //white label error varum , heloo print aagum console la , apo this RequestMapping can access the index() but not index.jsp why? bcoz intha index ketu vantha response aa itha kuduknum namma solanum so athukku @ResponseBody nu kuduthaa it will return the particular statement. summa return "index.jsp" kuduthaa index.jsp nu print browser la vanthrum so @ResponseBody remove pananum.
    //  summa index.jsp call aagathu so,athukku jasper dependency pom.xml file la podanum. so athukku spring boot tomcat starter server dependency pom file la add pani  , athukku aprm maven keela irukkum athula tomcat embed core version paathu atha mvn repository la maven jasper nu search panni antha embed core version thaa download pannanum, our tomcat embed core version is 10.1.28 so  jasper dependency  version 10.1.28 
    @RequestMapping("/index")
    // @ResponseBody
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();// ipd kudutha intha request process aagi response kudukra varaikkum intha session open aagirkum and then close aagirum.
       
      String s=  request.getParameter("name"); //
     int ages=Integer.parseInt(request.getParameter("age"));
        System.out.println("heloo");
         //now i'm going to get the parameter and intha session mudiyara varaikkum intha data va vechuru that means response kudukkravaraikum data irukkum athu aprm clear aagidum. athukku session.setAttribut("some name to use in jsp",s);
         session.setAttribute("username", s);
         session.setAttribute("age", ages);
         return "index";

         //It is not a Spring boot it is servlet 

    }
    
     // in spring boot httprequest and httpresponse ellame automatic we only need to specify the client-data in the method parameters and Httpsession creation in parameter itself.. 
    @RequestMapping("/sb")
    public String sprngBot(@RequestParam("name") String uName,int age,HttpSession session){//url la client-data ku enna variable use pandramo athu thaan parameter la vaanganum , antha variable thaa 
        //url la irukka client-data la fully small letters la thaa mostly request kuduppom  bcoz html fully small la thaa ezhuthuvom.  but java la userName nu naduvula capital varuthu so intha confusion avoid pana @RequestParam("client-data's variable) method oda parameter laa kuduthurvaanga .so ipo namma java la use panna camelcase use panicklaam. @RequestParam use panlenna url la use pandra client-data and parameter la vaangura varible should be same.
        session.setAttribute("username",uName);//data is called as model 
        session.setAttribute("userage", age);//data
          
        return "sb";//view page
    }

    @RequestMapping("/mav")
    public ModelAndView mav(@RequestParam("name") String  uName,HttpSession s){
     
        //as per spring mvc we have to send the response  by Mpde+view Object so we have ModelAndView class itself in spring boot
        ModelAndView mav = new ModelAndView();
        mav.addObject("username", uName);//model
        mav.setViewName("index");//view
        s.setAttribute("username", uName);

        return mav;
        //returning directly the view name is hard coding...
        
    }
     @RequestMapping("/whole")
    public ModelAndView uD(UserDetails ud){
        ModelAndView mv = new ModelAndView();
        mv.addObject("details", ud);
        mv.setViewName("whole");

        return mv;
    }
}

