package info.daviot.hearthstats.model

import com.mysql.jdbc.Driver
import scala.slick.driver.MySQLDriver
import com.mysql.jdbc.Driver
import scala.slick.driver.MySQLDriver

object DbCodeGen extends App {
  scala.slick.codegen.SourceCodeGenerator.main(
    Array(classOf[MySQLDriver].getName,
      classOf[Driver].getName,
      "jdbc:mysql://hearthprod.cf7zalaj5nzl.us-west-2.rds.amazonaws.com:3306/hearthstats_production",
      "src/main/scala",
      "info.daviot.hearthstats.model",
      "michel",
      args(0)))
}