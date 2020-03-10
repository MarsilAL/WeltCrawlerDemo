package weltcrawlerdemo.infrastructure.web;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PingPongHandler extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String echo = "";

        if(request.getParameter("echo") != null){
            echo = request.getParameter("echo");
        }
        if(!echo.isEmpty()){

            if(echo.equals("ping")){

                response.getWriter().println("pong");

            } else if(echo.equals("pong")){

                response.getWriter().println("ping");

            }  else {


                response.setStatus(400);
            }
        } else {

            response.getWriter().println("please enter a parameter ");

        }
        baseRequest.setHandled(true);

    }}
