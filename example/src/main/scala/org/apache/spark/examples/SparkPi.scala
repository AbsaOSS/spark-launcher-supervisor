package org.apache.spark.examples

import org.apache.spark.sql.SparkSession

import scala.math.random

object SparkPi extends App {
  val spark = SparkSession
    .builder
    .appName("Spark Pi")
    .getOrCreate()
  val slices = 10
  val n = math.min(100000L * slices, Int.MaxValue).toInt // avoid overflow
  val count = spark.sparkContext.parallelize(1 until n, slices).map { i =>
    val x = random * 2 - 1
    val y = random * 2 - 1
    if (x * x + y * y <= 1) 1 else 0
  }.reduce(_ + _)
  println(s"Pi is roughly ${4.0 * count / (n - 1)}")
  spark.stop()
}
