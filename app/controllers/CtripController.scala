package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.Inject

import daos.params.CtripDao.Create
import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import org.joda.time.DateTime
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.mvc._
import services.CtripService

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.Element

class CtripController @Inject()(ctripService: CtripService, ws: WSClient) extends Controller {

  val url = "http://www.ctrip.com/#ctm_ref=nb_cn_top"

  def create() = Action {
    val id = ctripService.create(
      Create(
        dCtiy = "dCtiy",
        aCtiy = "aCtiy",
        dCtiyName = "dCtiyName",
        aCtiyName = "aCtiyName",
        dDate = DateTime.now(),
        aDate = DateTime.now()
      )
    )
    Ok("Got it : " + id)
  }

  def ping() = Action {
    val request: WSRequest = ws.url(url)

    val complexRequest: WSRequest =
      request.withHeaders("Accept" -> "application/json")

    val futureResponse: Future[WSResponse] = complexRequest.get()

    futureResponse.onSuccess {
      case result => {
        println(result.body)
      }
    }

    val result = Await.result(futureResponse, Duration(100, "millis")).allHeaders
    Ok("Got it ." + result)
  }

  def jsoupBrowser() = Action {
    val jsoupBrowser = JsoupBrowser()
    val doc = jsoupBrowser.parseFile("data/test.html")
    val doc2 = jsoupBrowser.get("http://example.com")

    val header = doc >> text("#header")
    val items = doc >> elementList("#menu span")
    val views = doc >> attr("content")("meta[name=viewport]")
    val aText = items.map(_ >> allText("a"))

    val date = doc >> extractor("#date", text, asLocalDate("yyyy-MM-dd"))

    val doubles = doc >> extractor("#mytable td", texts, seq(asDouble))

    val h1s = doc >> extractor("h1", element, asIs[Element])

    val h3 = doc >> "h3"

    val allTexts = doc >> allText

    val active = doc >> elementList(".active")

    val ps = doc >> elementList("a")

    val activeText = active >> text(".active")

//    val as = doc >> elementList("#menu") >?> attr("href")("a")
    val as = doc >> elementList("a") >?> attr("href")

    Ok("Got it " + as)
  }
}
