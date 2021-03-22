package actors

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef

class ChatActor(out: ActorRef) extends Actor {
  def receive: Receive = {
    case s: String =>
      println("Got message " + s)
    case m => println("Unhandled message in ChatActor: " + m)
  }

  out ! "Connected"
}

object ChatActor {
  def props(out: ActorRef) = Props(new ChatActor(out))
}
