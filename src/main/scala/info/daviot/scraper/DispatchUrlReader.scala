package info.daviot.scraper

import scala.concurrent.Future
import grizzled.slf4j.Logging
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global

class DispatchUrlReader extends Reader[String, String] with Logging {
  def read(urlStr: String): Future[String] = {
    debug(s"reading from $urlStr")
    Thread.sleep(500)
    val svc = url(urlStr)
    for (data <- Http(svc OK as.String)) yield data
  }
}