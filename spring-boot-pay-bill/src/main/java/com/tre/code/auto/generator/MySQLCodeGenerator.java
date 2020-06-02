package com.tre.code.auto.generator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.tre.generate.generatecode.Generator_Auto;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySQLCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(MySQLCodeGenerator.class);

    public static void main(String[] args) throws Exception {

        //MySQL
        try {
            Generator_Auto.generator(
                    "D://AutoGenerator//mysql"
                    , DbType.MYSQL
                    , "com.mysql.jdbc.Driver",
                    "jdbc:mysql://xx.xx.xx.xx:3306/jdevtemplate?characterEncoding=utf8"
                    , "root"
                    , "");
        } catch (EncryptionOperationNotPossibleException ex) {
            logger.error(ex.getMessage());
        }
    }

}
