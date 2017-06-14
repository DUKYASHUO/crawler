package daos.params

import org.joda.time.DateTime

object CtripDao {

  case class Create(
      dCtiy: String,
      aCtiy: String,
      dCtiyName: String,
      aCtiyName: String,
      dDate: DateTime,
      aDate: DateTime
  )
}
