package st_examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hsqldb.cmdline.SqlTool;
import org.hsqldb.cmdline.SqlTool.SqlToolException;
import org.threewaves.eris.engine.IBuilder;
import org.threewaves.eris.engine.footprint.Footprint;
import org.threewaves.eris.util.Sql;

public class DatabaseBuilder implements IBuilder {

	private static final String db = "my_db";
	private final Map<String, String> tableOrderBy = Stream.of(new String[][] { { "MY_TABLE", "ID" }, })
			.collect(Collectors.toMap(data -> data[0], data -> data[1]));
	private final Scope scope;
	private Footprint log;
	private boolean debug = false;
	private String name;

	public DatabaseBuilder(Scope scope, String name) {
		this.scope = scope;
		this.name = name;
		this.log = new Footprint("database");
		if (scope == Scope.ENGINE) {
			createDb(Paths.get("db"));
		}
		if (scope == Scope.TEST_CASE) {
			deleteTables();
		}
	}

	public void debug() {
		debug = true;
	}

	@Override
	public void destroy() {
		if (scope == Scope.TEST_CASE) {
			for (String table : tableOrderBy.keySet()) {
				log.write(tablesToFootprint(table));
			}
		}
	}

	private void createDb(Path path) {
		try {
			// check if db has been created
			if (tables().size() == 0) {
				List<String> args = new ArrayList<>();
				args.add("--inlineRc=url=jdbc:hsqldb:mem:" + db
						+ ",transiso=TRANSACTION_READ_COMMITTED,user=sa,password=");
				try (Stream<Path> s = Files.list(path)) {
					s.forEach(f -> {
						args.add(f.toString());
					});
				}
				SqlTool.objectMain(args.toArray(new String[0]));
				tables().stream().forEach(a -> {
					if (debug) {
						System.out.println("Check table: " + a);
					}
				});
			}
		} catch (RuntimeException | IOException | SqlToolException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection connectDb() {
		return Sql.connect("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:" + db, "sa", "", 1);
	}

	private void deleteTables() {
		try (Sql sql = new Sql(connectDb(), false)) {
			for (String t : tableOrderBy.keySet()) {
				sql.update("DELETE FROM " + t);
			}
		}
	}

	public List<String> tables() throws SQLException {
		try (Sql sql = new Sql(connectDb(), false)) {
			List<Object[]> result = sql.select(1,
					"SELECT TABLE_NAME FROM INFORMATION_SCHEMA.SYSTEM_TABLES WHERE TABLE_TYPE = 'TABLE'");
			List<String> ls = new ArrayList<>();
			for (int i = 0; i < result.size(); i++) {
				ls.add(result.get(i)[0].toString());
			}
			return ls;
		}
	}

	public String tablesToFootprint(String tableName) {
		try (Sql sql = new Sql(connectDb(), false)) {
			List<Object[]> result = sql.select(2,
					"SELECT TABLE_NAME, COLUMN_NAME FROM INFORMATION_SCHEMA.SYSTEM_COLUMNS WHERE TABLE_NAME LIKE '"
							+ tableName + "'");
			List<String> ls = new ArrayList<>();
			String select = "*";
			for (int i = 0; i < result.size(); i++) {
				if (i == 0) {
					select = "";
				}
				ls.add(result.get(i)[1].toString());
				select += result.get(i)[1].toString();
				if (i < result.size() - 1) {
					select += ",";
				}
			}
			result = sql.select(ls.size(),
					"SELECT " + select + " FROM " + tableName + " ORDER BY " + tableOrderBy.get(tableName));
			String buff = "";
			for (int i = 0; i < result.size(); i++) {
				buff += tableName + ":" + "\r\n";
				for (int j = 0; j < ls.size(); j++) {
					buff += "\t" + ls.get(j) + "=" + result.get(i)[j] + "\r\n";
				}
			}
			if (buff.isEmpty()) {
				return null;
			}
			return buff;
		}
	}

	@Override
	public Scope scope() {
		return scope;
	}

	@Override
	public String name() {
		return name;
	}

}
