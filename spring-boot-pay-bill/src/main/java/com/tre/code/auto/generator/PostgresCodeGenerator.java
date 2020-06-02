package com.tre.code.auto.generator;


//import com.tre.generate.generatecode.Generator_Postgresql_Auto;
//import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PostgresCodeGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PostgresCodeGenerator.class);

    public static void main(String[] args) throws Exception {

        //生成数据库中所有表实体
//        try {
//            Generator_Postgresql_Auto.postgresql(
//                    "D://AutoGenerator//PostgreSql"
//                    ,"jdbc:postgresql://47.105.124.238:5432/pay"
//                    ,"public"
//                    ,"postgres"
//                    ,"B7LRy+Dl12QUDYBbK6h9kg=="
//            );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        //生成数据库中指定表实体
//        String[] byTables = {"zgzn_bill"};
//        try {
//            Generator_Postgresql_Auto.postgresql(
//                    "D://AutoGenerator//PostgreSql"
//                    , "jdbc:postgresql://47.105.124.238:5432/pay"
//                    , "public"
//                    , "postgres"
//                    , "B7LRy+Dl12QUDYBbK6h9kg=="
//                    , byTables
//                    , true);
//        } catch (EncryptionOperationNotPossibleException ex) {
//            logger.error(ex.getMessage());
//        }

    }
}
