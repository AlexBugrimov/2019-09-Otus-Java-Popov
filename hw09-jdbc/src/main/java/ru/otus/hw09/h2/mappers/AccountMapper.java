package ru.otus.hw09.h2.mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.hw09.orm.ORMapper;

import javax.sql.DataSource;
import java.sql.SQLException;

public class AccountMapper extends ORMapper {
    private final static Logger logger = LoggerFactory.getLogger(UserMapper.class);

    public AccountMapper(DataSource dataSource) {
        super(dataSource, "create table if not exists account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)");
    }

    @Override
    public void createTable() throws SQLException {
        super.createTable();
        logger.info("Table for Account created");
    }
}
