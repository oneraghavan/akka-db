package com.akkadb

import akka.actor.{ActorSystem, Props}
import com.akkademy.AkkaDb

object Main extends App {
  val system = ActorSystem("akkadb")
  system.actorOf(Props[AkkaDb], name = "akkademy-db")
}
