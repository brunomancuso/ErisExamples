package database;

import java.io.Closeable;
import java.io.IOException;

import org.threewaves.eris.util.Sql;

import st_examples.DatabaseBuilder;

public class MyDatabaseService implements Closeable {

	@Override
	public void close() throws IOException {

	}

	public void insert(long id, String text) {
		try (Sql sql = new Sql(DatabaseBuilder.connectDb(), true)) {
			boolean b = sql.update("insert into MY_TABLE values(" + id + ", '" + text + "')");
			System.out.println("Record inserted = " + b);
		}
	}
}
