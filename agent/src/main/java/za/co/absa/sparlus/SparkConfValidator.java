package za.co.absa.sparlus;

import org.apache.spark.SparkConf;
import scala.Tuple2;

import java.util.HashMap;
import java.util.Map;

import static za.co.absa.sparlus.Logger.info;
import static za.co.absa.sparlus.Logger.warn;

public class SparkConfValidator {

    private static final Map<String, String> EXPECTED_CONF_PROPS = new HashMap<String, String>() {{
        this.put("spark.master", "yarn");
        this.put("spark.submit.deployMode", "cluster");
    }};

    public static void validate(Object sparkConfObj) {
        final SparkConf sparkConf = (SparkConf) sparkConfObj;

        // print current Spark conf
        final StringBuilder confDebugStrBuilder = new StringBuilder();
        for (Tuple2<String, String> tuple : sparkConf.getAll()) {
            confDebugStrBuilder.append("\n\t").append(tuple._1).append("=").append(tuple._2);
        }
        info("Spark Conf:" + confDebugStrBuilder);

        // compare given Spark conf with expected values
        boolean success = true;
        for (Map.Entry<String, String> entry : EXPECTED_CONF_PROPS.entrySet()) {
            final String actual = sparkConf.get(entry.getKey());
            final String expected = entry.getValue();

            if (!expected.equals(actual)) {
                warn("Spark conf property '" + entry.getKey() + "' must be '" + expected + "', but was '" + actual + "'");
                success = false;
            }
        }

        if (success) {
            info("No errors found in Spark config");
        }
    }
}
