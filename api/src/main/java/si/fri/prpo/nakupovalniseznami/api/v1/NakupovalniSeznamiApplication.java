package si.fri.prpo.nakupovalniseznami.api.v1;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("v1")
public class NakupovalniSeznamiApplication extends Application {
}

// ce pustimo razred prazen se bodo upostevale neke privzete nastavitve
// - vsi razredi ki so znotraj tega paketa, se bodo povezali s tem razredom