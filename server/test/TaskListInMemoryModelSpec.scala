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
      TaskListInMemoryModel.validateUser("Mark", "mypass") mustBe false
    }

    "get correct default tasks" in {
      TaskListInMemoryModel.getTasks("tommylau") mustBe List("code", "rollerblade", "sleep")
    }
  }
}
