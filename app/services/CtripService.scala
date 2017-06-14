package services

import daos.CtripDao
import daos.params.CtripDao.Create
import scalikejdbc.AutoSession

class CtripService {

  val ctripDao = new CtripDao
  implicit val session = AutoSession

  def create(ctripCreate : Create): Long = {
    val id = ctripDao.create(ctripCreate)
    id
  }
}
