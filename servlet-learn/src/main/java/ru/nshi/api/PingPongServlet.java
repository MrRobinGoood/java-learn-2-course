package ru.nshi.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

//@WebServlet(urlPatterns = "/haha",description = "descripa",displayName = "dispName")
public class PingPongServlet extends HttpServlet {
    public static final String JSON_VALUE = "application/json";
    private AtomicInteger counter;
    private Set<String> threadSets;

    @Override
    public void destroy() {
        System.out.println("Destroy method called and threads size: ");
        System.out.println(threadSets.size());
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init method called");
        counter = new AtomicInteger(0);
        threadSets = new ConcurrentSkipListSet<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("req.getServletPath() = " + req.getServletPath());
        System.out.println("req.getPathInfo() = " + req.getPathInfo());
        System.out.println("req.getParameterValues(\"key\") = " +
                Arrays.toString(req.getParameterValues("key")));

        System.out.println("req.getParameterMap() = " + req.getParameterMap());

        String threadName = Thread.currentThread().getName();
        System.out.println("Request id: " + counter.incrementAndGet()
                + " Thread name: " + threadName);
        resp.getWriter().println("<b>pong</b>");
        resp.setContentType("text/plain");

        threadSets.add(threadName);
    }

}