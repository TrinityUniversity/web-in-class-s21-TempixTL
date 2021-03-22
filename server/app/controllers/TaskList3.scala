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
            Ok(Json.toJson(false))
          }
        case e @ JsError(_) => Redirect(routes.TaskList3.load())
      }
    }.getOrElse(Redirect(routes.TaskList3.load()))
  }

  def createUser = Action { implicit request =>
    request.body.asJson.map { body =>
      Json.fromJson[UserData](body) match {
        case JsSuccess(ud, path) => 
          val username = ud.username
          val password = ud.password
          if (TaskListInMemoryModel.createUser(username, password)) {
            Ok(Json.toJson(true))
              .withSession(
                "username" -> username,
                "csrfToken" -> play.filters.csrf.CSRF.getToken.get.value)
          } else {
            Ok(Json.toJson(false))
          }
        case e @ JsError(_) => Redirect(routes.TaskList3.load())
      }
    }.getOrElse(Redirect(routes.TaskList3.load()))
  }

  def taskList = Action { implicit request =>
    val usernameOpt = request.session.get("username")
    usernameOpt.map { username =>
      Ok(Json.toJson(TaskListInMemoryModel.getTasks(username)))
    }.getOrElse(Ok(Json.toJson(Seq.empty[String])))
  }

  def addTask = Action { implicit request =>
    val usernameOption = request.session.get("username")

    usernameOption.map { username =>
      request.body.asJson.map { body =>
        Json.fromJson[String](body) match {
          case JsSuccess(task, path) => 
            TaskListInMemoryModel.addTask(username, task)
            Ok(Json.toJson(true))
          case e @ JsError(_) => Redirect(routes.TaskList3.load())
        }
      }.getOrElse(Ok(Json.toJson(false)))
    }.getOrElse(Ok(Json.toJson(false)))
  }

  def deleteTask = Action { implicit request =>
    val usernameOption = request.session.get("username")

    usernameOption.map { username =>
      request.body.asJson.map { body =>
        Json.fromJson[Int](body) match {
          case JsSuccess(index, path) => 
            TaskListInMemoryModel.removeTask(username, index)
            Ok(Json.toJson(true))
          case e @ JsError(_) => Redirect(routes.TaskList3.load())
        }
      }.getOrElse(Ok(Json.toJson(false)))
    }.getOrElse(Ok(Json.toJson(false)))
  }

  def logout = Action { implicit request =>
    Ok(Json.toJson(true)).withNewSession
  }
}