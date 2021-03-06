import org.scalatestplus.play._
import controllers.Application
import play.api.test.Helpers
import play.api.test.Helpers._
import play.api.test.FakeRequest

class ControllerSpec extends PlaySpec {
  "Application#index" must {
    val controller = new Application(Helpers.stubControllerComponents())
    "give back expected page" in {
      val result = controller.index.apply(FakeRequest())
      val bodyText = contentAsString(result)

      bodyText must include ("Play and Scala.js")
      bodyText must include ("Play shouts out:")
      bodyText must include ("Scala.js shouts out:")
    }
  }
}
