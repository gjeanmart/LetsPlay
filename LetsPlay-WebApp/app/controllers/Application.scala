package controllers

import javax.inject.Inject

import play.Logger
import play.api.mvc._
import play.api.i18n._


class Application @Inject()(val messagesApi: MessagesApi) extends Controller
  with I18nSupport
  with CookieLang {

  /**********************************
    * ACTION
    ***********************************/
  def index() = Action { implicit request =>
    Ok(views.html.index("Your new application is ready"))
  }
}
