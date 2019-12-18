package si.fri.prpo.nakupovalniseznami.servleti;

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
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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

        uporabnikiZrno.pridobiUporabnike().stream().forEach(u -> writer.append(u.toString() + "<br/><br/>"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String username = request.getParameter("username");

        PrintWriter writer = response.getWriter();

        boolean preveri = upravljanjeUporabnikovZrno.emailCheck(email);

        if(preveri) {

            UporabnikDto uporabnikDto = new UporabnikDto();
            uporabnikDto.setId(Integer.parseInt(id));
            uporabnikDto.setIme(firstName);
            uporabnikDto.setPriimek(lastName);
            uporabnikDto.setEmail(email);
            uporabnikDto.setUporabniskoIme(username);

            Uporabnik up = upravljanjeUporabnikovZrno.dodajUporabnika(uporabnikDto);

            response.sendRedirect("input.jsp");
        } else {
            writer.write("<h1>Niste vnesli pravilnega emaila!</h1><h3>Pojdite nazaj in poskusite znova.</h3>");
            writer.append("</br>")
                    .append("<button onclick=\"location.href='input.jsp'\" type=\"button\">Nazaj</button>");
        }
    }
}