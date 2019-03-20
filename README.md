# Read and Write from HDFS

## Scope:
This project demo's the read and write functionality from HDFS location. 

## To Run

1. Clone this repo
2. Setup Hadoop as shown [here](https://drive.google.com/file/d/1iYdzX_K9EOyogfg2Jf2JdxoKXtsipRi6/view?usp=sharing)
3. Put the test file in HDFS
    
        hadoop fs -mkdir -p /input_dir/
        hadoop fs -put ~/Documents/workspace/Read_Write_HDFS_Spark_WordCount/src/main/resources/data.txt /input_dir/
 
 4. Build the project - `gradle clean build`
 5. Execute the job as
 
        spark-submit --master local[2]  
        --verbose 
        --class com.pavanpkulkarni.hdfsdemo.HDFSReadWriteDemo
        build/libs/Read_Write_HDFS_Spark_WordCount-1.0-SNAPSHOT.jar

6. Check the [HDFS location](http://localhost:9870/explorer.html) for output file


***P.S:*** Make sure you have the right HDFS port number in job. The HDFS location is usually the `fs.default.name` in core-site.xml file. 