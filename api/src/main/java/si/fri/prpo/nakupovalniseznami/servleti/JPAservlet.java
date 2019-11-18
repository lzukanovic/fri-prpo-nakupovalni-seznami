package si.fri.prpo.nakupovalniseznami.servleti;

import si.fri.prpo.nakupovalniseznami.dtos.UporabnikDto;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;
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
import java.util.logging.Logger;

@WebServlet("/servlet")
public class JPAservlet extends HttpServlet {
    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @Inject
    private UpravljanjeUporabnikovZrno upravljanjeUporabnikovZrno;

    private static final Logger log = Logger.getLogger(JPAservlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        // izpis uporabnikov
        writer.append("<br/><br/>Uporabniki:<br/>");
        uporabnikiZrno.pridobiUporabnike().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));

        /*
        // dodam novega uporabnika
        Uporabnik uDto = new Uporabnik();
        uDto.setIme("Lan");
        uDto.setPriimek("Zukanovic");
        uDto.setEmail("LanZuk@gmail.com");
        uDto.setUporabniskoIme("lanz");
        uDto.setId(3);

        Uporabnik up = uporabnikiZrno.dodajUporabnika(uDto);
        writer.append("----<br/>"+up.toString()+"<br/><br/>");
        writer.append("----<br/>"+uporabnikiZrno.pridobiUporabnika(3).toString()+"<br/>"+uporabnikiZrno.pridobiUporabnike().size()+"<br/>");

        // izpis uporabnikov
        writer.append("<br/><br/>Uporabniki:<br/>");
        uporabnikiZrno.pridobiUporabnike().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));
        */
    }
}