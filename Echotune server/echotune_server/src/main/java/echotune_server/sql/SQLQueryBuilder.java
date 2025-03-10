package echotune_server.sql;

import java.util.Map;

public class SQLQueryBuilder {
	private String query = "";

	public SQLQueryBuilder() {
	}

	public SQLQueryBuilder select(String param) {
		this.query += "SELECT" + param + " ";
		return this;
	}

	public SQLQueryBuilder selectAll() {
		this.query += "SELECT * ";
		return this;
	}

	public SQLQueryBuilder from(String table) {
		this.query += "FROM " + table + " ";
		return this;
	}

	public SQLQueryBuilder where(SQLCondition condition) {
		this.query += "WHERE " + condition.build() + " ";
		return this;
	}

	public SQLQueryBuilder insertInto(String table) {
		this.query += "INSERT INTO " + table + " ";
		return this;
	}

	public <T> SQLQueryBuilder values(Map<String, String> keysAndValues) {
		String keys = "(";
		String values = "(";
		for(int i = 0; i < keysAndValues.size(); i++) {
			String key =  (String) keysAndValues.keySet().toArray()[i];
			if(i != keysAndValues.size() - 1) {
				keys += key + ", ";
				values += "'" + keysAndValues.get(key) + "', ";
			} else {
				keys += keysAndValues.keySet().toArray()[i];
				values += "'" + keysAndValues.get(key) + "'";
			}
		}
		keys += ")";
		values += ")";
		
		this.query += keys + " VALUES " + values;
		return this;
	}

	public String build() {
		return this.query;
	}
}