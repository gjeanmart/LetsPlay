package controllers

import play.api.mvc.{Action, Controller}
import play.api.i18n.{I18nSupport, Lang}
import play.api.data._
import play.api.data.Forms._
import play.api.Logger
import play.api.Play.current

trait CookieLang extends Controller with I18nSupport {

  val localeForm = Form("locale" -> nonEmptyText)

  val changeLocale = Action { implicit request =>
    val referrer = request.headers.get(REFERER).getOrElse(HOME_URL)

    localeForm.bindFromRequest.fold(
      errors => {
        Logger.debug("The locale can not be change to : " + errors.get)
        BadRequest(referrer)
      },
      locale => {
        Logger.debug("Change user lang to : " + locale)
        Redirect(referrer).withLang(Lang(locale))
      })

  }

  protected val HOME_URL = "/"
}