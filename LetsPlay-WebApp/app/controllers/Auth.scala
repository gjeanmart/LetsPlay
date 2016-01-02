package controllers

import Models.User
import play.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.i18n._
import play.api.mvc.{Action, Controller}

import play.api.i18n.Messages.Implicits._
import javax.inject.Inject

/**
  *
  * Auth
  * Controller for authentication
  * Created by Grégoire JEANMART on 2015-12-30.
  */
class Auth @Inject()(val messagesApi: MessagesApi) extends Controller
  with I18nSupport
  with CookieLang{


  /**********************************
    * FORM
    ***********************************/
  val signUpForm = Form(
    mapping(
      "id"              -> ignored(None: Option[Long]),
      "username"        -> nonEmptyText,
      "email"           -> email.verifying(maxLength(250)),
      "password"        -> nonEmptyText.verifying(minLength(6)),
      "firstName"       -> nonEmptyText,
      "lastName"        -> nonEmptyText
    )(User.apply)(User.unapply)
  )

  /**********************************
    * ACTION
    ***********************************/
  def signin_show = Action { implicit request =>
    Logger.debug("ACTION = signin_show()")

    Ok(views.html.auth_signin(null))
  }

  def signup_show = Action { implicit request =>
    Logger.debug("ACTION = signup_show()")

    Ok(views.html.auth_signup(signUpForm))
  }

  def signup_register = Action { implicit request =>
    Logger.debug("ACTION = signup_register()")

    signUpForm.bindFromRequest.fold(
      formKO => {
        // binding failure, you retrieve the form containing errors:
        BadRequest(views.html.auth_signup(formKO))
      },
      formOK => {
        val r = scala.util.Random
        val newUser = User(
          Option(r.nextLong()),
          formOK.username,
          formOK.email,
          formOK.password,
          formOK.firstName,
          formOK.lastName)
        //val id = models.User.create(newUser)

        Logger.debug(s"New user = $newUser")

        Redirect(routes.Auth.signin_show())
      }
    )
  }

}
