package types

import org.joda.time.DateTime

case class Ctrip(
  id: Int,
  dCtiy: String,
  aCtiy: String,
  dCtiyName: String,
  aCtiyName: String,
  dDate: DateTime,
  aDate: DateTime,
  createDate: DateTime
)
