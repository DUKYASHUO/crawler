package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import javax.inject.Inject
import daos.params.CtripDao.Create
import org.joda.time.DateTime
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.mvc._
import services.CtripService
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

class CtripController @Inject()(ctripService: CtripService, ws: WSClient) extends Controller {

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
    val request: WSRequest = ws.url("http://www.baidu.com")

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
}
