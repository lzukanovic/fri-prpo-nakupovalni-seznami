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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@WebServlet("/servletArtikel")
public class ArtikelServlet extends HttpServlet {

    @Inject
    private ArtikliZrno artikliZrno;

    private static final Logger log = Logger.getLogger(JPAservlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        artikliZrno.pridobiArtikle().stream().forEach(u -> writer.append("<li>"+u.toString() + "</li>"));

    }

}