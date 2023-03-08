package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    //ResponseBody 를 붙이게 되면 body 에 해단 return 값이 바로 들어간다.
    //-> RestController 를 사용한 것과 동일한 결과를 얻을 수 있다.
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            //parameter 와 argument 의 명명이 같다면 RequestParam 에서 지정하는 부분 생략가능
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
            //parameter 와 argument 의 명명이 같다면 RequestParam 생략가능(단, 단순타입일 경우.(String, int, Integer...))
            String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            //required = false 를 사용할 경우 해당 값이 들어오지 않으면 null 이 들어가게 된다.
            //그러나 이 경우엔 int type 엔 null 이 들어갈 수 없기 때문에 500 internal error 이 발생하게 된다.
            //이러한 경우 int -> Integer 로 변경해야 한다.
            @RequestParam(required = false) int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue 항목을 설정하게 되면 required 항목은 의미가 없어진다.
            //해당 설정이 true/false 둘 중 어느 것이어도 항상 defaultValue 값으로 진행되기 때문.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    //@ModelAttribute 는 생략이 가능하다.
    //@RequestParam 또한 생략이 가능하기 때문에 혼동이 올 수 있다.
    //단순 타입은 requestParam 으로, 나머지 중 argument resolver 로 지정해둔 타입 이외엔 modelAttribute 로 설정된다.
    public String modelAttributeV2(HelloData helloData) {

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);
        return "ok";
    }
}
