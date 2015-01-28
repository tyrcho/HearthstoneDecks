package info.daviot.soup

import info.daviot.scraper.Scraper
import org.jsoup.Jsoup
import info.daviot.scraper.DispatchUrlReader
import scala.concurrent.Future
import org.jsoup.nodes.Document
import scala.concurrent.ExecutionContext.Implicits.global

trait DispatchJsoupScraper extends Scraper {
  def read(id: Id): Future[String] = 
    DispatchUrlReader.read(id)
  
  def parseDocument(doc: String): Future[Document] =
    Future(Jsoup.parse(doc))

}