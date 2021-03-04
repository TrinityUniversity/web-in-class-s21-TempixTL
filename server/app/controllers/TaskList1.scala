package controllers

import javax.inject._
import play.api.mvc._
import play.api.i18n._
import models.TaskListInMemoryModel

@Singleton
class TaskList1 @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def login = Action {
    Ok(views.html.login1())
  }

  def validateLoginGet(username: String, password: String) = Action {
    Ok(s"$username logged in with $password.")
  }

  def validateLoginPost = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args => 
      val username = args("username").head
      val password = args("password").head

      if (TaskListInMemoryModel.validateUser(username, password)) {
        Redirect(routes.TaskList1.taskList())
      } else {
        Redirect(routes.TaskList1.login())
      }
    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def createUser = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    postVals.map { args => 
      val username = args("username").head
      val password = args("password").head

      if (TaskListInMemoryModel.createUser(username, password)) {
        Redirect(routes.TaskList1.taskList())
      } else {
        Redirect(routes.TaskList1.login())
      }
    }.getOrElse(Redirect(routes.TaskList1.login()))
  }

  def taskList = Action {
    val username = "tommylau"
    val tasks = TaskListInMemoryModel.getTasks(username)
    Ok(views.html.taskList1(tasks))
  }
}
