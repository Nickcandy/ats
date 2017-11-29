package com.hpe.itsma.bo.ats.api;

import com.hpe.itsma.bo.common.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile({"dev","prod", "kube"})
@Controller
public class HomeController extends BaseController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    void home(HttpServletResponse response) throws IOException {
        logger.info("home request: redirect to swagger-ui2");
        response.sendRedirect("api-spec/index.html");
    }
}
