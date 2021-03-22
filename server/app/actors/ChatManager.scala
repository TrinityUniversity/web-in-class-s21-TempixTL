package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class ChatManager extends Actor {
  import ChatManager._

  private var chatters = List.empty[ActorRef]

  def receive: Receive = {
    case NewChatter(chatter) => chatters ::= chatter
    case Message(msg) =>
    case m => println("Unhandled message in ChatManager: " + m)
  }
}

object ChatManager {
  def props() = Props(new ChatManager())

  case class NewChatter(chatter: ActorRef)
  case class Message(msg: String)
}
