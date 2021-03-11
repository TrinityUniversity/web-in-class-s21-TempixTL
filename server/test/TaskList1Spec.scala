import org.scalatestplus.play.guice.GuiceOneServerPerSuite
import org.scalatestplus.play.OneBrowserPerSuite
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.HtmlUnitFactory

class TaskList1Spec extends PlaySpec with GuiceOneServerPerSuite with OneBrowserPerSuite with HtmlUnitFactory {
  "Task list 1" must {
    "login and access functions" in {
      go to s"http://localhost:$port/login1"

      click on "username-login"
      textField("username-login").value = "tommylau"
      click on "password-login"
      textField("password-login").value = "mypass"
      submit()
    }
  }
}
