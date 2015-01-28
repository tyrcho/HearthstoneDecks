package info.daviot.scraper

import scala.concurrent.Future
import grizzled.slf4j.Logging
import dispatch._
import scala.concurrent.ExecutionContext.Implicits.global

object DispatchUrlReader extends Logging {
  def read(urlStr: String): Future[String] = {
    debug(s"reading from $urlStr")
    val svc = url(urlStr)
    for (data <- Http(svc OK as.String)) yield data
  }
}