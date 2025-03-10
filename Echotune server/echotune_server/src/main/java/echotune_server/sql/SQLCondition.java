package echotune_server.sql;

public class SQLCondition {
	String cond = "";
	public SQLCondition(String column1, SQLOperator operator, String column2) throws SQLConditionException {
		switch (operator) {
		case LESS_THAN:
			this.cond += "`" + column1 + "` < " + "'" + column2 + "'";
			break;
		case GREATER_THAN:
			this.cond += "`" + column1 + "` > " + "'" + column2 + "'";
			break;
		case EQUALS:
			this.cond += "" + column1 + " = " + "'" + column2 + "'";
			break;
		default:
			throw new SQLConditionException("Operator " + operator + " not found!");
		}
	}
	
	public String build() {
		return this.cond;
	}
}