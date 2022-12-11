package ru.nshi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import ru.nshi.task2.JsonConverter;
import ru.nshi.task2.Responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SortingServlet extends HttpServlet {
    public static final String JSON_VALUE = "application/json";
    private ObjectMapper mapper;

    @Override
    public void destroy() {
        System.out.println("Destroy method called and threads size: ");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init method called");
        mapper = new ObjectMapper();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(JSON_VALUE);
        if (!req.getContentType().contains(JSON_VALUE)) {
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "Expected " + JSON_VALUE));
            return;
        }
        try {
            Responce responce = mapper.readValue(req.getInputStream(), Responce.class);
            if (responce == null) {
                resp.setStatus(400);
                mapper.writeValue(resp.getWriter(), Map.of("error", "responce is null"));
                return;
            }

            if (responce.getValues() == null) {
                resp.setStatus(400);
                mapper.writeValue(resp.getWriter(), Map.of("error", "values are null"));
                return;
            }

            if (responce.getAlgorithm() == null) {
                responce.setAlgorithm("bubble");
            }

            if (!responce.getAlgorithm().equalsIgnoreCase("bubble") && !responce.getAlgorithm().equalsIgnoreCase("choice")) {
                resp.setStatus(404);
                mapper.writeValue(resp.getWriter(), Map.of("error", "this algorithm not found"));
                return;
            }

            mapper.writeValue(resp.getOutputStream(), JsonConverter.jsonSort(responce));
            resp.setStatus(200);
        }catch (Exception e){
            System.out.println(e.getMessage());
            resp.setStatus(400);
            mapper.writeValue(resp.getWriter(), Map.of("error", "invalid json structure"));
        }

    }
}