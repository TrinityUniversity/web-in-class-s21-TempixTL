package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import models.TaskListInMemoryModel
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import models.UserData
import play.api.libs.json._

@Singleton
class TaskList3 @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  implicit val userDataReads = Json.reads[UserData]

  def load = Action { implicit request =>
    Ok(views.html.version3Main())
  }
    
  def data = Action {
    Ok(Json.toJson(Seq("a", "b", "c")))
  }
    
  def validate = Action { implicit request =>
    request.body.asJson.map { body =>
      Json.fromJson[UserData](body) match {
        case JsSuccess(ud, path) => 
          val username = ud.username
          val password = ud.password
          if (TaskListInMemoryModel.validateUser(username, password)) {
            Ok(Json.toJson(true))
              .withSession(
                "username" -> username,
                "csrfToken" -> play.filters.csrf.CSRF.getToken.get.value)
          } else {
            Ok(views.html.login2())
          }
        case e @ JsError(_) => Redirect(routes.TaskList3.load())
      }
    }.getOrElse(Redirect(routes.TaskList3.load()))
  }
}