package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import models.TaskListInMemoryModel
import play.api.libs.json._

@Singleton
class WebSocketChat @Inject() (cc: ControllerComponents) extends AbstractController(cc) {
  def index = Action { implicit request =>
    Ok(views.html.chatPage())
  }
}
