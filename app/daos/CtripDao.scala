package daos

import daos.params.CtripDao.Create
import org.joda.time.DateTime
import types._
import scalikejdbc._

object Ctrip extends SQLSyntaxSupport[Ctrip]

class CtripDao extends SQLSyntaxSupport[Ctrip] {

  val col = Ctrip.column

  def create(params: Create)(implicit dbSession: DBSession): Long = applyUpdateAndReturnGeneratedKey {
    QueryDSL.insertInto(Ctrip).namedValues(
      col.aCtiy -> params.aCtiy,
      col.dCtiy -> params.dCtiy,
      col.aCtiyName -> params.aCtiyName,
      col.dCtiyName -> params.dCtiyName,
      col.dDate -> params.dDate,
      col.aDate -> params.aDate,
      col.createDate -> DateTime.now
    )
  }
}
