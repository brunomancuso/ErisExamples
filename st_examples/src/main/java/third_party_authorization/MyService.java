package third_party_authorization;

import java.io.Closeable;
import java.io.IOException;
import java.util.Date;

import org.threewaves.eris.engine.footprint.Footprint;
import org.threewaves.eris.util.Format;

public class MyService implements Closeable {
	public static class Response {
		public final boolean approved;

		public Response(boolean approved) {
			this.approved = approved;
		}

	}

	private Footprint log = new Footprint("my_service");
	private IClient client = new Simulator();

	public Response someBusinessRules(long amount) {
		System.out.println("Entering someBusinessRules");
		String xml = "<root>\r\n" + "<date>" + Format.toString(new Date(), "yyMMdd HHmmss") + "</date>\r\n" + "<amount>"
				+ amount + "</amount>\r\n" + "</root>\r\n";
		String response = client.authorize(xml);
		Response rta;
		if (response != null && response.equals("MESSAGE_APPROVED")) {
			rta = new Response(true);
		} else {
			rta = new Response(false);
		}
		log.write("someBusinessRules", rta);
		System.out.println("Leaving someBusinessRules");
		return rta;
	}

	@Override
	public void close() throws IOException {

	}
}
