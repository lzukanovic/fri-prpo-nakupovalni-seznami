package si.fri.prpo.nakupovalniseznami.api.v1;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(info = @Info(title = "NakupovanjeAPI", version = "v1.0.0", contact = @Contact(), license = @License(name = "something")), servers = @Server(url = "http://localhost:8080"))
@ApplicationPath("v1")
public class NakupovalniSeznamiApplication extends Application {
}

// ce pustimo razred prazen se bodo upostevale neke privzete nastavitve
// - vsi razredi ki so znotraj tega paketa, se bodo povezali s tem razredom