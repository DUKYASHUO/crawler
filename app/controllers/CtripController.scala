package controllers

import javax.inject.Inject
import daos.params.CtripDao.Create
import org.joda.time.DateTime
import play.api.mvc._
import services.CtripService

class CtripController @Inject()(ctripService: CtripService) extends Controller {

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
}
