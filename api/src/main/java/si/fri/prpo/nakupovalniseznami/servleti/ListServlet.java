package si.fri.prpo.nakupovalniseznami.servleti;

import si.fri.prpo.nakupovalniseznami.dtos.ArtikelDto;
import si.fri.prpo.nakupovalniseznami.entitete.Artikel;
import si.fri.prpo.nakupovalniseznami.entitete.NakupovalniSeznam;
import si.fri.prpo.nakupovalniseznami.zrna.ArtikliZrno;

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

        if(ar.isEmpty())
            writer.append("Ni priporočenih artiklov..");
        else
            for (Map.Entry<Artikel, Integer> entry : ar.entrySet())
                writer.append("<li>"+entry.getKey()+" ("+entry.getValue()+")").append("</li>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naziv = request.getParameter("artikel");

        PrintWriter writer = response.getWriter();

        // Belezimo nov klik artikla
        ArtikelDto artikelDto = new ArtikelDto();
        artikelDto.setNaziv(naziv);
        artikliZrno.beleziPriporocenArtikel(artikelDto);

        // Dodamo artikel v PB za prikaz nakupovalnega seznama
        NakupovalniSeznam prvi = new NakupovalniSeznam();
        prvi.setId(1);

        Artikel art = new Artikel();
        art.setNaziv(naziv);
        art.setNakupovalniSeznam(prvi);
        artikliZrno.dodajArtikel(art);

        response.sendRedirect("shopping.jsp");
    }
}