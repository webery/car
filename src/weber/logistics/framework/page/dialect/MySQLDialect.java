package weber.logistics.framework.page.dialect;

import org.apache.ibatis.mapping.MappedStatement;

import weber.logistics.framework.page.domain.PageBounds;

/**
 * @author badqiu
 * @author miemiedev
 */
public class MySQLDialect extends Dialect {

	public MySQLDialect(MappedStatement mappedStatement,
			Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}

	protected String getLimitString(String sql, String offsetName, int offset,
			String limitName, int limit) {
		StringBuffer buffer = new StringBuffer(sql.length() + 20).append(sql);
		if (offset > 0) {
			buffer.append(" limit ?, ?");
			setPageParameter(offsetName, offset, Integer.class);
			setPageParameter(limitName, limit, Integer.class);
		} else {
			buffer.append(" limit ?");
			setPageParameter(limitName, limit, Integer.class);
		}
		return buffer.toString();
	}

}
