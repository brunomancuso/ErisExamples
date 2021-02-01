package third_party_authorization;

import org.threewaves.eris.engine.footprint.Footprint;
import org.threewaves.eris.simulator.ISimulator;
import org.threewaves.eris.simulator.SimulatorFactory;

public class Simulator implements IClient {
	public static final String MIPS = "mips";

	private static final String THIRD_PARTY_AUTHORIZATION = "THIRD_PARTY_AUTHORIZATION";

	private Footprint log = new Footprint("third_party_authorization");

	private static ISimulator simulator = SimulatorFactory.create(THIRD_PARTY_AUTHORIZATION);

	public static void put(String key, String response) {
		simulator.put(THIRD_PARTY_AUTHORIZATION, key, response);
	}

	public static String pop(String key) {
		return simulator.pop(THIRD_PARTY_AUTHORIZATION, key);
	}

	@Override
	public String authorize(String xml) {
		log.write("authorize:\r\n", xml);
		return pop("authorize");
	}

}
