package net.dinkla.lbnn.spark

import java.text.SimpleDateFormat

import net.dinkla.lbnn.utils.TextDate
import CheckIn._

/**
 * Created by dinkla on 19/06/15.
 */
class CheckIn(val id: CustomerId,
              val date: TextDate,
              val locX: Coordinate,
              val locY : Coordinate,
              val locId: LocationId)
  extends java.io.Serializable
  with Ordered[CheckIn]
{

  override def compare(that: CheckIn): Int = {
    val a = id compare that.id
    if (a != 0) {
      return a
    } else {
      return date.compareTo(that.date)
    }
  }

  override def toString = s"$id, $date, ($locX, $locY), $locId"

}

object CheckIn {

  type CustomerId = Int
  type Coordinate = Double
  type LocationId = Int

  val format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

  def split(line: String): Array[String] = line.split("[ \t]+")

  def parse(parts: Array[String]) : CheckIn = {
    require(parts.size == 5)
    val id = parts(0).toInt
    val date = new TextDate(format.parse(parts(1)))
    val locX = parts(2).toDouble
    val locY = parts(3).toDouble
    val locId = parts(4).toInt
    new CheckIn(id, date, locX, locY, locId)
  }

}
