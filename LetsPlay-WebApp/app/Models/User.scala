package Models

/**
  * User
  * Model of user
  * Created by Grégoire JEANMART on 2015-12-30.
  */
case class User(
  id          : Option[Long],
  username    : String,
  email       : String,
  password    : String,
  firstName   : String,
  lastName    : String) {
  def key = email
  def fullName: String = firstName + " " + lastName
}

object User {


}