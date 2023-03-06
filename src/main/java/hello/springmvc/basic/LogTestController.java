package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    //Logger 는 slf4j interface 를 사용한다.
    //@Slf4j 를 사용함으로써 해당 line 은 필요없다.
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        //log 작성 시 {}를 사용하며 다른 parameter 로 출력할 value 를 넘기는 이유
        //+value 를 사용할 경우 해당 log 가 level 차이에 의해 나타나지 않음에도 +뒤에 로직이 있다면 해당 로직이 시행된다.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        //RestController 의 경우 String return 값은 view 의 이름이 아닌 message body 에 해당 String 이 바로 들어간다.
        //Controller 의 경우 String return 값은 view 의 이름이다.
        return "ok";
    }
}
