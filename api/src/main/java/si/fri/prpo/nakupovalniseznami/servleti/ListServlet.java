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
import java.util.Map;
import java.util.logging.Logger;


@WebServlet("/servletList")
public class ListServlet extends HttpServlet {

    @Inject
    private ArtikliZrno artikliZrno;

    private static final Logger log = Logger.getLogger(JPAservlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        Map<Artikel, Integer> ar = artikliZrno.pridobiPriporoceneArtikle();
        for (Map.Entry<Artikel, Integer> entry : ar.entrySet())
            writer.append(entry.getKey()+" ("+entry.getValue()+")").append("<br/>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naziv = request.getParameter("artikel");

        ArtikelDto artikelDto = new ArtikelDto();
        artikelDto.setNaziv(naziv);
        artikliZrno.beleziPriporocenArtikel(artikelDto);

        Artikel art = new Artikel();
        art.setNaziv(naziv);
        artikliZrno.dodajArtikel(art);

        response.sendRedirect("shopping.jsp");
    }
}