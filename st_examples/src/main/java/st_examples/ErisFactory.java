package st_examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.threewaves.eris.engine.IBuilder;
import org.threewaves.eris.engine.ICommand;
import org.threewaves.eris.engine.IErisFactory;
import org.threewaves.eris.engine.IBuilder.Scope;

public class ErisFactory implements IErisFactory {

	@Override
	public List<IBuilder> createBuilders(Scope scope) {
		if (scope == Scope.TEST_CASE) {
			return Arrays.asList(new IBuilder[] { 
					new ServiceBuilder(), 
					new DatabaseBuilder(Scope.TEST_CASE, "b")
			});
		}
		if (scope == Scope.ENGINE) {
			return Arrays.asList(new IBuilder[] { 
					new DatabaseBuilder(Scope.ENGINE, "c")
			});
		}
		return Collections.emptyList();
	}

	@Override
	public List<ICommand> createExternalCommands() {
		return Collections.emptyList();
	}
}
