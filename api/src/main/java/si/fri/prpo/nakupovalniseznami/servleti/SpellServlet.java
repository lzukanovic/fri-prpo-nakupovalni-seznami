package si.fri.prpo.nakupovalniseznami.servleti;

import si.fri.prpo.nakupovalniseznami.dtos.ArtikelDto;
import si.fri.prpo.nakupovalniseznami.dtos.UporabnikDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
import si.fri.prpo.nakupovalniseznami.zrna.ArtikliZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UporabnikiZrno;
import si.fri.prpo.nakupovalniseznami.zrna.UpravljanjeUporabnikovZrno;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;


@WebServlet("/servletSpell")
public class SpellServlet extends HttpServlet {

    @Inject
    private ArtikliZrno artikliZrno;

    private static final Logger log = Logger.getLogger(JPAservlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json; charset=UTF-8\"");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        String naziv = req.getParameter("naziv");
        Response data = artikliZrno.spellCheck(naziv);
        //System.out.println(String.format("Status: %d, JSON Payload: %s", json.getStatus(), json.readEntity(String.class)));
        //System.out.println(json.readEntity(String.class));
        //writer.print(json);
        //writer.flush();
        req.setAttribute("data", data);
        req.getRequestDispatcher("shopping.jsp").forward(req, resp);

    }

}