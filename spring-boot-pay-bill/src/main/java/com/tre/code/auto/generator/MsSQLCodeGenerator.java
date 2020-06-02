package com.tre.code.auto.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.tre.generate.generatecode.Generator_Auto;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsSQLCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MsSQLCodeGenerator.class);

    public static void main(String[] args) throws Exception {

        //SQLSERVER
        try {
            Generator_Auto.generator(
                    "D://AutoGenerator//sqlserver"
                    , DbType.SQL_SERVER
                    , "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                    , "jdbc:sqlserver://xx.xx.xx.xx:1433;DatabaseName=test"
                    , ""
                    , "");

        } catch (EncryptionOperationNotPossibleException ex) {
            logger.error(ex.getMessage());
        }
    }
}
