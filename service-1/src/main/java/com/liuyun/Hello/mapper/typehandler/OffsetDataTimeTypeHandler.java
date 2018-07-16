package com.liuyun.Hello.mapper.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@MappedJdbcTypes(value = JdbcType.VARCHAR)
@MappedTypes(value = OffsetDateTime.class)
public class OffsetDataTimeTypeHandler extends BaseTypeHandler<OffsetDateTime> {
    final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OffsetDateTime parameter, JdbcType jdbcType) throws SQLException {
            ps.setString(i, formatter.format(parameter));
    }

    @Override
    public OffsetDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return OffsetDateTime.parse(rs.getString(columnName), formatter);
    }

    @Override
    public OffsetDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return OffsetDateTime.parse(rs.getString(columnIndex), formatter);
    }

    @Override
    public OffsetDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return OffsetDateTime.parse(cs.getString(columnIndex), formatter);
    }
}
