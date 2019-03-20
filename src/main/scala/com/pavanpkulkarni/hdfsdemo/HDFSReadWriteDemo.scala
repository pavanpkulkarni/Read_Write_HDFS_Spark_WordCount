package com.pavanpkulkarni.hdfsdemo

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

object HDFSReadWriteDemo {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
                .builder()
                .master("local")
                .appName("hdfs-to-s3-test")
                .getOrCreate()


    import spark.implicits._

    val inputFile = "hdfs://localhost:9000/input_dir/data.txt"
    val data = spark.read.textFile(inputFile)

    val wordCount = data.flatMap(line => line.split(" ")).groupByKey(identity).count()

    wordCount.show()

    val outputFile = "hdfs://localhost:9000/output_dir/WordCounts"
    wordCount.coalesce(1).write.json(outputFile)

  }

}


/*

Useful COmmands

hadoop fs -mkdir -p /input_dir/
hadoop fs -put ~/Documents/workspace/Hadoop_To_S3_Spark_WordCount/src/main/resources/data.txt /input_dir/
hadoop fs -ls /
hadoop fs -rm -r -f /output_dir

spark-submit --master local[2]  --verbose --class com.pavanpkulkarni.hdfsdemo.HDFSReadWriteDemo build/libs/Read_Write_HDFS_Spark_WordCount-1.0-SNAPSHOT.jar


*/