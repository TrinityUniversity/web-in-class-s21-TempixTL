import org.scalatestplus.play._

import models._

class TaskListInMemoryModelSpec extends PlaySpec {
  "TaskListInMemoryModel" must {
    "do valid login for default user" in {
      TaskListInMemoryModel.validateUser("tommylau", "mypass") mustBe true
    }

    "reject login with wrong password" in {
      TaskListInMemoryModel.validateUser("tommylau", "wrongpass") mustBe false
    }

    "reject login with wrong username" in {
      TaskListInMemoryModel.validateUser("wronguser", "mypass") mustBe false
    }

    "reject login with wrong username and password" in {
      TaskListInMemoryModel.validateUser("wronguser", "wrongpass") mustBe false
    }

    "get correct default tasks" in {
      TaskListInMemoryModel.getTasks("tommylau") mustBe List("code", "rollerblade", "sleep")
    }

    "create new user with no tasks" in {
      TaskListInMemoryModel.createUser("mark", "password") mustBe true
      TaskListInMemoryModel.getTasks("mark") mustBe Nil
    }

    "create new user with existing name" in {
      TaskListInMemoryModel.createUser("tommylau", "diffpass") mustBe false
    }

    "add new task for default user" in {
      TaskListInMemoryModel.addTask("tommylau", "testing")
      TaskListInMemoryModel.getTasks("tommylau") must contain ("testing")
    }

    "add new task for new user" in {
      TaskListInMemoryModel.addTask("mark", "testing1")
      TaskListInMemoryModel.getTasks("mark") must contain ("testing1")
    }

    "remove task from default user" in {
      TaskListInMemoryModel.removeTask("tommylau", TaskListInMemoryModel.getTasks("tommylau").indexOf("code"))
      TaskListInMemoryModel.getTasks("tommylau") must not contain ("code")
    }
  }
}
